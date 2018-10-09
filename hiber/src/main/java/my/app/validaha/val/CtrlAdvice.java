package my.app.validaha.val;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.io.IOException;


@RestControllerAdvice
@Component
//@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CtrlAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<Long> go(Exception ex){
        System.err.println("inside exhandler for ConstrException"+ex.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(MyException.class)
    public void handleDefaultException(
                                       MyException exception) throws IOException {
        System.err.println("aaaaa");

    }

}
