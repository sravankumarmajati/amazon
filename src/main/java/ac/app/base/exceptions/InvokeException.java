package ac.app.base.exceptions;

import ac.app.base.constant.Code;

public class InvokeException extends BaseException {
    public InvokeException(Code code, String msg) {
        super(code, msg);
    }
}
