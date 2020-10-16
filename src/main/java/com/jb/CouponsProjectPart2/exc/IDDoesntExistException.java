package com.jb.CouponsProjectPart2.exc;

public class IDDoesntExistException extends Exception {

	public IDDoesntExistException() {
		super("The ID provided doesn't exist");
	}
}
