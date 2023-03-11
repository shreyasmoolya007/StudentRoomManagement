package com.xadmin.studentroommanagement.model;


public class SRoom {
	protected int id;
	protected String name;
	protected String usn;
	protected String room;
	
	public SRoom() {
	}
	
	public SRoom(String name, String usn, String room) {
		super();
		this.name = name;
		this.usn = usn;
		this.room = room;
	}

	public SRoom(int id, String name, String usn, String room) {
		super();
		this.id = id;
		this.name = name;
		this.usn = usn;
		this.room = room;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsn() {
		return usn;
	}
	public void setUsn(String usn) {
		this.usn = usn;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
}