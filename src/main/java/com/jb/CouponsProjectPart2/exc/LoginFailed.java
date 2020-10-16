package com.jb.CouponsProjectPart2.exc;

public class LoginFailed extends Exception {

	public LoginFailed() {
		super("The Email or Password provided doesnt exist");
	}
}
