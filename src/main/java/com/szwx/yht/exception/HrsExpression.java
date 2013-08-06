package com.szwx.yht.exception;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-10
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class HrsExpression extends RuntimeException {
    private Object data;

    public HrsExpression() {
        super();
    }

    public HrsExpression(String msg) {
        super(msg);
    }

    public HrsExpression(Throwable throwable) {
        super(throwable);
    }

    public HrsExpression(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public HrsExpression(String msg, Throwable throwable, Object data) {
        super(msg, throwable);
        this.data = data;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
