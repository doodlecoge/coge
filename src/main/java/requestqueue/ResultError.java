package requestqueue;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-3
 * Time: 上午7:10
 * 拒绝访问的原因
 */
public enum  ResultError {
    None,//无错误
    Full,//当前访问人数已满，需要排队
    IPAccessfrequently,//当前IP的请求操作太过频繁
}
