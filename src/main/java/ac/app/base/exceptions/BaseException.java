package ac.app.base.exceptions;

import ac.app.base.constant.Code;
import ac.app.base.response.BasicResult;

/**
 * 所有自定义的Exception，需要集成BaseException
 * 需要继承 RuntimeException 否则将无法回滚事务
 */
public class BaseException extends RuntimeException {
    private int codeInt;
    private String msg;
    private BasicResult baseResult;

    public BaseException(Code code) {
        super(code.getMsg());
        this.baseResult = new BasicResult<>(code);
    }

    public BaseException(Code code, String msg) {
        super(msg);
        this.baseResult = new BasicResult<>(code, msg);
    }

    public int getCodeInt() {
        return codeInt;
    }

    public void setCodeInt(int codeInt) {
        this.codeInt = codeInt;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BasicResult getBaseResult() {
        return baseResult;
    }

    public void setBaseResult(BasicResult baseResult) {
        this.baseResult = baseResult;
    }
}
