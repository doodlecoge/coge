package requestqueue;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-3
 * Time: 上午7:38
 * IP疑似队列
 */
public class IPSuspectedMap {
    private HashMap<String, IPSuspectedNode> IPHashMap;
    private long IPRequestTimeSec;
    private int IPRequestNumMax;//在IPRequestTimeSec时间内，访问的最大次数
    private long IPClearTimeSec;//元素无操作清理时间。

    /**
     * IP可疑列表构造函数，访问限制为：一个ip在RequestTimeSec秒内最多访问IPRequestNum次.
     * @param RequestTimeSec 限制时间
     * @param IPRequestNum 限制访问次数
     */
    public IPSuspectedMap(int RequestTimeSec, int IPRequestNum) {
        this.IPHashMap = new HashMap();

        this.IPRequestTimeSec = RequestTimeSec;
        this.IPRequestNumMax = IPRequestNum;

        this.IPClearTimeSec = 5*60;//将5分钟没有操作的ip从ip疑似队列中清除
    }

    public boolean IPIsAllow(String IP) {
        //删除IP疑似列表中超时元素
        ClearTimeIP();
        //处理IP疑似列表
        IPSuspectedNode node = IPHashMap.get(IP);
        if (node == null)
        {
            node = new IPSuspectedNode(IP);
            IPHashMap.put(IP, node);
            return true;
        }
        else
        {
            if (node.IPRequestGap(new Date()) >= IPRequestTimeSec)
            {
                node.setRequestTime(new Date());
                node.setRequestNum(0);
                return true;
            }
            else
            {
                node.setRequestNum(node.getRequestNum()+1);
                if(node.getRequestNum() <= IPRequestNumMax)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }

    /**
     * 删除IPHashMap中超时的元素
     */
    public void ClearTimeIP() {
        Iterator iter = IPHashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String)entry.getKey();
            IPSuspectedNode val = (IPSuspectedNode)entry.getValue();
            if(val!=null && val.IPRequestGap(new Date())>IPClearTimeSec)
            {
                iter.remove();
            }
        }
    }
}

class IPSuspectedNode
{
    private String IP;
    private Date RequestTime;//请求基准时间
    private int RequestNum;//请求次数

    int getRequestNum() {
        return RequestNum;
    }

    void setRequestNum(int requestNum) {
        RequestNum = requestNum;
    }

    public IPSuspectedNode(String ip)
    {
        this.IP = ip;
        this.RequestTime = new Date();
        //实现在指定时间内访问的次数限制
        this.RequestNum = 0;
    }

    /**
     * 计算newTime 与 RequestTime的间隔时间.
     *
     * @param newTime 新的访问时间.
     * @return 间隔时间，单位为秒.
     */
    public long IPRequestGap(Date newTime) {
        return (newTime.getTime() - RequestTime.getTime()) / 1000;
    }

    public Date getRequestTime() {
        return RequestTime;
    }

    public void setRequestTime(Date requestTime) {
        RequestTime = requestTime;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}
