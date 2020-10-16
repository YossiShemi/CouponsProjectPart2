package com.jb.CouponsProjectPart2.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.jb.CouponsProjectPart2.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart2.exc.InvalidAction;
import com.jb.CouponsProjectPart2.exc.ItemAlreadyExist;
import com.jb.CouponsProjectPart2.exc.LoginFailed;

@ControllerAdvice
@RestController
public class ExcControllerAdvice {

	@ExceptionHandler(IDDoesntExistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ErrorDetails IDDoesntExistException(IDDoesntExistException ex, WebRequest request) {
		ErrorDetails err = new ErrorDetails();
		err.setCode(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Sorry, the id doesnt exist.");
		err.setValue(request.getParameter("id"));
		return err;
	}
	
	@ExceptionHandler(LoginFailed.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ErrorDetails LoginFailed(LoginFailed ex, WebRequest request) {
		ErrorDetails err = new ErrorDetails();
		err.setCode(HttpStatus.BAD_REQUEST.value());
		err.setMessage(ex.getMessage());
		err.setValue(request.getParameter("id"));
		return err;
	}

	@ExceptionHandler(InvalidAction.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ErrorDetails InvalidAction(InvalidAction ex, WebRequest request) {
		ErrorDetails err = new ErrorDetails();
		err.setCode(HttpStatus.BAD_REQUEST.value());
		err.setMessage(ex.getMessage());
		err.setValue(request.getParameter("id"));
		return err;
	}

	@ExceptionHandler(ItemAlreadyExist.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ErrorDetails ItemAlreadyExist(ItemAlreadyExist ex, WebRequest request) {
		ErrorDetails err = new ErrorDetails();
		err.setCode(HttpStatus.BAD_REQUEST.value());
		err.setMessage(ex.getMessage());
		err.setValue(request.getParameter("id"));
		return err;
	}

}
