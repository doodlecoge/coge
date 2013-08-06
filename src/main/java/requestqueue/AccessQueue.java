package requestqueue;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-6-29
 * Time: 下午11:38
 * 用户访问控制队列
 */
public class AccessQueue {
    //成员变量
    private List<AccessNode> g_Nodes;   /*< 访问者队列，队列中的元素按时间从旧到新排列,第一个元素为最旧的元素*/
    int g_AccessMax;                    /*< 最大访问数数量，超过这个数量需要排队*/
    int g_OperSpanMaxSec;              /*< 操作时间间隔的最大值，单位为秒*/
    long g_OperSpanMinMilisec;        /*< 操作时间间隔的最小值，单位为毫秒*/

    //ip疑似列表
    IPSuspectedMap g_ipSuspectedMap;
    //ip疑似列表

    //---放号限制
    TimeLimitList g_TimeLimitList;
    //---放号限制

    //方法

    //构造函数
    public AccessQueue() {
        g_AccessMax = 50;                    //最大的访问数量为50个
        g_OperSpanMaxSec = 20 * 60;         //最大操作间隔为10分钟
        g_OperSpanMinMilisec = 1000;        //最小操作间隔为1秒钟
        g_Nodes = new ArrayList<AccessNode>();

        //ip疑似列表
        g_ipSuspectedMap = new IPSuspectedMap(60,6);//IP访问限制为:一个IP地址在60秒内最多请求6次
        //ip疑似列表

        //---放号限制
        g_TimeLimitList = new TimeLimitList(60, 10);//放号限制为60秒内最多释放10个号码
        //---放号限制
    }

    /**
     * 等待号源释放的时间限制
     */
    public void WaitNumberRelease() throws InterruptedException {
        while (false == g_TimeLimitList.AllowRelease()) {
            //如果放号时间限制未满足，则线程继续等待1秒
            Thread.sleep(1000);
        }
    }

    /**
     * 请求访问函数。当用户访问预约功能时.
     * 调用此函数来判断是否能访问，不能访问时包含不能访问的原因，number为排队的序号，从0开始计算。
     * @param session 访问者session对象
     * @return RequestResult 返回能否访问，失败原因及排队序号
     */
    public RequestResult Request(HttpSession session) {
        boolean allow = true;
        int number = 0;
        ResultError error = ResultError.None;

        String ClientIP = (String) session.getAttribute("ClientIP");

        //ip疑似列表
        if (false == g_ipSuspectedMap.IPIsAllow(ClientIP))
        {
            return new RequestResult(false, ResultError.IPAccessfrequently, 0);
        }
        //ip疑似列表

        //处理队列中的超时元素
        ISR();

        int index = SearchAccessNodes(session.getId());
        if (-1 == index) {
            //队列中无此session id
            //1 添加session id,和session ip
            AccessNode newNode = new AccessNode(session.getId(), ClientIP);
            g_Nodes.add(newNode);
            //2
            index = g_Nodes.size() - 1;
        }
        else
        {
            //更新对此节点的访问时间
            g_Nodes.get(index).setStepTime(new Date());
        }

        if (index >= g_AccessMax) {
            //进入排队队列
            allow = false;
            number = index - g_AccessMax;
            error = ResultError.IPAccessfrequently;
        }
        else
        {
            //允许立即访问
            allow = true;
            number = 0;
            error = ResultError.None;
        }

        return new RequestResult(allow, error, number);
    }

    //结束预约功能。当预定结束或者session终止之后调用此函数，来更新队列
    public void EndVisit(HttpSession session) {
        int index = SearchAccessNodes(session.getId());
        if (-1 != index) {
            g_Nodes.remove(index);
        }
    }

    /**
     * 预约操作过程中的访问请求，用户在请求访问一个新的页面之前，应使用OperateRequest来完成以下两个功能：
     * 1 判断页面之间的切换操作是否超时、过短
     * 2 判断每个页面的访问是否按顺序来进行。Step代表顺访问序号，首页面从0开始
     * 页面操作顺序规则：
     * 1 每次调用时，Step必须为上次的Step加一，否则则认为没有按顺序访问这些页面。
     * 2 在调用过程中，从任意页面跳转到0号页面也是允许的，代表开始重新访问。
     * 3 在调用过程中，从任意页面跳转到本页面也是允许的，代表刷新访问。
     * 4 除以上三种情况，其他页面切换顺序都认为是非法的。
     *
     * @param session 请求访问的session
     * @param Step     访问页面编号，申请访问首页面从0开始。
     * @return 能否进行访问操作
     */
    public boolean OperateRequest(HttpSession session, int Step) {
        //处理队列中的超时元素
        ISR();

        //在访问队列中搜索指定的访问者
        int index = SearchAccessNodes(session.getId());
        if (-1 == index)
        {
            //未搜索到指定访问者
            //当定时函数ISR判断某个元素超时后，会将其从队列中删除
            //返回超时
            return false;
        }
        else
        {
            //访问访问顺序贩判断判断
            if (0 == Step)
            {
                //申请访问首页面,总是允许
                //保存当前访问位置
                g_Nodes.get(index).setStep(Step);
            } else if (Step == g_Nodes.get(index).getStep())
            {
                //刷新页面，允许
            }
            else if(Step != (g_Nodes.get(index).getStep() + 1)) {
                //页面访问顺序异常
                //将其从队列中删除
                g_Nodes.remove(index);
                return false;
            } else {
                //不是访问首页面，并且顺序符合要求
                //保存当前操作序号
                g_Nodes.get(index).setStep(Step);
            }

            Date lastDate = g_Nodes.get(index).getStepTime();

            //访问时间是否过短
            if (OperateTimeShort(lastDate)) {
                //访问时间过短
                //1 删除访问队列元素
                g_Nodes.remove(index);
                //2 返回拒绝访问
                return false;
            }

            if (OperateTimeOut(lastDate)) {
                //访问超时
                //1 删除访问队列元素
                g_Nodes.remove(index);
                //2返回超时
                return false;
            } else {
                //访问未超时
                //1更新最近访问时间
                g_Nodes.get(index).setStepTime(new Date());
                //2返回未超时
                return true;
            }
        }
    }

    //处理队列中的超时元素
    private void ISR() {
        int index;
        for (index = 0; index < g_Nodes.size(); ) {
            if (OperateTimeOut(g_Nodes.get(index).getStepTime())) {
                g_Nodes.remove(index);
            } else {
                index++;
            }
        }
    }

    //搜索VisitorSessionID在队列中的下标，如果无此VisitorSessionID则返回-1；
    private int SearchAccessNodes(String VisitorSessionID) {
        int indexMax = g_Nodes.size();
        for (int i = 0; i < indexMax; i++) {
            if (g_Nodes.get(i).getSessionID() != null && g_Nodes.get(i).getSessionID().equals(VisitorSessionID)) {
                return i;
            }
        }
        return -1;
    }

    //判断是否超时
    private boolean OperateTimeOut(Date lastDate) {
        try {
            Date now = new Date();
            long spanMilSec = now.getTime() - lastDate.getTime();
            if ((spanMilSec / 1000) > g_OperSpanMaxSec) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return true;
        }
    }

    //判断访问时间是否过短
    private boolean OperateTimeShort(Date lastDate) {
        Date now = new Date();
        long spanMilisec = now.getTime() - lastDate.getTime();
        if (spanMilisec < g_OperSpanMinMilisec) {
            return true;
        } else {
            return false;
        }
    }
}

//访问控制队列元素节点
class AccessNode {
    private String SessionID;       /*< 访问者session id*/
    private String ClientIP;        /*< 访问者IP地址*/

    private Date RequestTime;       /*< 请求时间*/
    //private int RequestCount;       /*<请求次数*/

    //private Date AccessTime;        /*< 被接收时间*/
    private Date StepTime;          /*< 最近一次的操作时间*/
    private int Step;               /*< 当前访问页面的编号*/

    int getStep() {
        return Step;
    }

    void setStep(int step) {
        Step = step;
    }

    /*
    int getRequestCount() {
        return RequestCount;
    }

    void setRequestCount(int requestCount) {
        RequestCount = requestCount;
    }
    */

    Date getRequestTime() {
        return RequestTime;
    }

    void setRequestTime(Date requestTime) {
        RequestTime = requestTime;
    }

    /*
    Date getAccessTime() {
        return AccessTime;
    }

    void setAccessTime(Date accessTime) {
        AccessTime = accessTime;
    }
    */

    void setSessionID(String sessionID) {
        SessionID = sessionID;
    }

    public String getSessionID() {
        return this.SessionID;
    }

    public Date getStepTime() {
        return this.StepTime;
    }

    public void setStepTime(Date stepTime) {
        this.StepTime = stepTime;
    }

    String getClientIP() {
        return ClientIP;
    }

    void setClientIP(String clientIP) {
        ClientIP = clientIP;
    }

    //构造函数
    public AccessNode(String sessionID, String clientIP) {
        this.SessionID = sessionID;
        this.ClientIP = clientIP;

        this.RequestTime = new Date();
        //this.RequestCount = 1;
        //this.AccessTime = null;
        this.StepTime = new Date();
        this.Step = 0;
    }
}
