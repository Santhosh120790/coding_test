package com.ge.exercise1;

public class ExtendedUser extends User {

	public ExtendedUser(String id, String name) {
		super(id, name);
	}

	@Override
	public String toString() {
		return "Users:{id = " + getId() + ", name =" + getName() + "}";
	}
	
}
