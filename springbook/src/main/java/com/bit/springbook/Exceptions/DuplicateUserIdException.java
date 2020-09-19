package com.bit.springbook.Exceptions;

public class DuplicateUserIdException extends RuntimeException {
	public DuplicateUserIdException(Throwable cause) {
		super(cause);
	}
}
