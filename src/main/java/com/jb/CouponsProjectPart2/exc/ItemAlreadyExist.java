package com.jb.CouponsProjectPart2.exc;

public class ItemAlreadyExist extends Exception {

	public ItemAlreadyExist(String message) {
		super("The item already exist. "+message);
	}
}
