package vn.tungnt.interview.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.tungnt.interview.service.dto.error.BusinessErrorResponse;
import vn.tungnt.interview.service.exception.BusinessException;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<BusinessErrorResponse> handleBusinessError(BusinessException ex) {
        BusinessErrorResponse error = new BusinessErrorResponse(ex.getMessage(), "E001");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
