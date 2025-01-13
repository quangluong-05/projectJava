package controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import Model.ErrorReponseDTO;
import customexceptions.FieldRequiredException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Object> handleArithmecticException(ArithmeticException ex, WebRequest request) {
	    System.out.println("ArithmeticException handled");
	    ErrorReponseDTO error = new ErrorReponseDTO();
	    error.setError(ex.getMessage());
	    List<String> detailsList = new ArrayList<>();
	    detailsList.add("lỗi bỏi chia cho không");
	    error.setDetail(detailsList);
	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(FieldRequiredException.class)
	public ResponseEntity<Object> handleArithmecticException(FieldRequiredException ex, WebRequest request) {
	    System.out.println("ArithmeticException handled");
	    ErrorReponseDTO error = new ErrorReponseDTO();
	    error.setError(ex.getMessage());
	    List<String> detailsList = new ArrayList<>();
	    detailsList.add("lỗi do name or age or addresss isEmpty or not null ");
	    detailsList.add("lỗi do name or age or addresss isEmpty or not null ");
	    error.setDetail(detailsList);
	    return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
	}

}
