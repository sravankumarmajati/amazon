package ac.app.base.exceptions;

import ac.app.base.constant.Code;
import ac.app.base.response.BasicResult;

import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.DecoderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 所有自定的，需要抛出的Exception， 这里需要添加一个handler
 * 全局统一异常处理
 */
@RestControllerAdvice
public class GlobalExceptionInterceptor {
    private static final Logger logger = Logger.getLogger(GlobalExceptionInterceptor.class);

    @ExceptionHandler(DecoderException.class)
    @ResponseBody
    public ResponseEntity<BasicResult> handleBaseException(DecoderException e) {
        return new ResponseEntity<>(new BasicResult<>(Code.INVALID_PARAM_ERROR, "data hash is illegal"), HttpStatus.OK);
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity<BasicResult> handleBaseException(BaseException be) {
        return new ResponseEntity<>(be.getBaseResult(), HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<BasicResult> handleJSONException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(new BasicResult<>(Code.INVALID_PARAM_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 404
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<BasicResult> noHandlerFoundException(NoHandlerFoundException e) {
        return new ResponseEntity<>(new BasicResult<>(Code.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    // 405
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<BasicResult> noHandlerFoundException(HttpRequestMethodNotSupportedException e) {

        return new ResponseEntity<>(new BasicResult<>(Code.METHOD_NOT_SUPPORT), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<BasicResult> handleArgumentNotValidException(Exception be) {
        logger.error(be);
        be.printStackTrace();
        return new ResponseEntity<>(new BasicResult<>(Code.INVALID_PARAM_ERROR), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<BasicResult> handleDefaultException(Exception be) {
        logger.error(be);
        be.printStackTrace();
        return new ResponseEntity<>(new BasicResult<>(Code.UNKNOWN_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
