package com.ge.exercise1;

import java.util.List;

public class ExtendedGroup extends Group {

	private List<ExtendedUser> users;

	public ExtendedGroup(String id, String name) {
		super(id, name);
	}

	public List<ExtendedUser> getUsers() {
		return users;
	}

	public void setUsers(List<ExtendedUser> users) {
		this.users = users;
	}	
	
}
