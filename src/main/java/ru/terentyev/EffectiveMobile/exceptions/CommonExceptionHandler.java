package ru.terentyev.EffectiveMobile.exceptions;

import java.rmi.AccessException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(value = TaskNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleNotFoundException(TaskNotFoundException tnfe, Model model) {
		model.addAttribute("error", tnfe.getMessage());
		System.out.println(tnfe.getMessage() + "\n TaskNotFoundException");
		return "tasks";
	}
	
	@ExceptionHandler(value = AccessException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public String handleAccessException(AccessException ae, Model model) {
		model.addAttribute("error", ae.getMessage());
		System.out.println(ae.getMessage() + "\n AccessException");
		return "tasks";
	}
	
	/*
	@ExceptionHandler(value = TaskIdentifierMismatchException.class)
	@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
	public String handleIdTaskMismatchException(TaskIdentifierMismatchException tme, Model model) {
		model.addAttribute("error", tme.getMessage());
		System.out.println(tme.getMessage());
		return "tasks";
	}
	*/
	
	@ExceptionHandler(value = PersonNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handlePersonException(PersonNotFoundException pnfe, Model model) {
		model.addAttribute("error", pnfe.getMessage());
		System.out.println(pnfe.getMessage() + "\n PersonNotFoundException");
		return "people";
	}
	
	@ExceptionHandler(value = MismatchIdentifierException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public String handleMismatchException(MismatchIdentifierException mie, Model model) {
		model.addAttribute("error", mie.getMessage());
		System.out.println(mie.getMessage() + "\n MismatchIdentifierException");
		return "task";
	}
}
