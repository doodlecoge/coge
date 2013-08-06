package requestqueue;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-6-30
 * Time: 上午11:03
 * 请求访问的返回结果
 */
public class RequestResult {
    private boolean Allow;  /*< 是否允许立即访问预约网页,如果不允许，则通过Number给出排队当前排队序号*/
    private int Number;     /*< 排队序号,从0开始排序*/
    private ResultError Error;/*< 错误类型*/

    public ResultError getError() {
        return Error;
    }

    public boolean isAllow() {
        return Allow;
    }

    public int getNumber() {
        return Number;
    }

    //构造函数
    public RequestResult(boolean allow, ResultError error, int number)
    {
        this.Allow = allow;
        this.Number = number;
        this.Error = error;
    }
}
