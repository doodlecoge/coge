package requestqueue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-3
 * Time: 下午4:15
 * 放号时间限制队列
 */
public class TimeLimitList
{
    /**
     * 时间记录列表,记录按照时间顺从旧到新排列。
     */
    List<TimeLimitNode> g_list;

    /**
     * 放号限制为：在g_LimitTimeSec秒之内，最多只能释放g_LimitNum个号源
     */
    long g_LimitTimeSec;
    int g_LimitNum;

    public long getG_LimitTimeSec() {
        return g_LimitTimeSec;
    }

    public void setG_LimitTimeSec(long g_LimitTimeSec) {
        this.g_LimitTimeSec = g_LimitTimeSec;
    }

    public int getG_LimitNum() {
        return g_LimitNum;
    }

    public void setG_LimitNum(int g_LimitNum) {
        this.g_LimitNum = g_LimitNum;
    }

    /**
     * 构造函数
     * 放号时间限制规则为，在limitTime时间内，最多放出limitNum个号源
     */
    public TimeLimitList(long limitTime, int limitNum)
    {
        g_list = new ArrayList<TimeLimitNode>();
        g_LimitTimeSec = limitTime;
        g_LimitNum = limitNum;
    }

    /**
     * 放号请求
     * AllowRelease里面维护了一个放号时间队列，根据队列来判断是否达到放号时间、个数的限制要求。
     */
    public boolean AllowRelease()
    {
        //将队列中超过g_LimitNum的旧的元素删除。
        while(g_list.size() > g_LimitNum)
        {
            g_list.remove(0);
        }

        TimeLimitNode node;
        if(g_list.size()<g_LimitNum)
        {
            //总的放号数少于g_LimitNum
            node = new TimeLimitNode(new Date());
            g_list.add(node);
            return true;
        }
        else
        {
            //总的放号数等于g_LimitNum个
            //计算现在离最远的一次放号时间间隔，单位为秒。
            long oldTimeSec = g_list.get(0).TimeGap(new Date());
            if(oldTimeSec > g_LimitTimeSec)
            {
                node = new TimeLimitNode(new Date());
                g_list.add(node);
                g_list.remove(0);
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
 * 放号时间限制队列元素
 */
class TimeLimitNode
{
    /**
     * 号源释放时间
     */
    private Date ReleaseTime;

    public Date getReleaseTime() {
        return ReleaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        ReleaseTime = releaseTime;
    }

    /**
     * 计算时间间隔，从ReleaseTime到newTime，单位为秒。
     * @param newTime 比较的时间
     * @return 从ReleaseTime到newTime的时间间隔，单位为秒。
     */
    public long TimeGap(Date newTime)
    {
        long gap;
        gap = (newTime.getTime() - ReleaseTime.getTime())/1000;
        return gap;
    }

    /**
     * 构造函数
     * @param releaseTime 号源释放时间
     */
    public TimeLimitNode(Date releaseTime) {
        ReleaseTime = releaseTime;
    }
}