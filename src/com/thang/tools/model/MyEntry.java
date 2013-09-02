package com.thang.tools.model;


import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.RosterPacket.ItemStatus;
import org.jivesoftware.smack.packet.RosterPacket.ItemType;
public class MyEntry {

	
	private String name;
	private ItemStatus status;
	private ItemType type;
	private String user;
	
	private RosterEntry entry=null;
	
	public MyEntry(RosterEntry entry){
		this.entry=entry;
		setName(entry.getName());
		setStatus(entry.getStatus());
		setType(entry.getType());
		setUser(entry.getUser());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public RosterEntry getEntry() {
		return entry;
	}

	public void setEntry(RosterEntry entry) {
		this.entry = entry;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
