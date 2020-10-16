package com.jb.CouponsProjectPart2.exc;

public class InvalidAction extends Exception {

	public InvalidAction(String s) {
		super("Invalid action-  "+s);
	}
}
