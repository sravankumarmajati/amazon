package ac.app.base.exceptions;

import ac.app.base.constant.Code;

public class WrongResponseException extends BaseException {
    public WrongResponseException(Code code, String msg) {
        super(code, msg);
    }
}
