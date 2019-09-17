package com.ge.exercise1;

import java.util.List;

public class Result {
	
	private String id;
	
    private String name;
    
    private List<ExtendedUser> userList;
    
    private List<ExtendedGroup> groupList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExtendedUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ExtendedUser> userList) {
		this.userList = userList;
	}

	public List<ExtendedGroup> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<ExtendedGroup> groupList) {
		this.groupList = groupList;
	}

}
