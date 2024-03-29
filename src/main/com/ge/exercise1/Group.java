package com.ge.exercise1;

public abstract class Group {
    private String id;
    private String name;

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public int size;

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + "]";
	}
    
    

}
