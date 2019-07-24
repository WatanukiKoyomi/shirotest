package com.example.shirotest.model;

import java.util.HashSet;
import java.util.Set;

public class Role {

	private Integer rid;
	
	private String rname;
	
	private Set<Permission> permission = new HashSet<>();
	
	private Set<User> users = new HashSet<>();

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Set<Permission> getPermission() {
		return permission;
	}

	public void setPermission(Set<Permission> permission) {
		this.permission = permission;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	
	
	
}
