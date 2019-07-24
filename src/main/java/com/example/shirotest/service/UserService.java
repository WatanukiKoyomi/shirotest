package com.example.shirotest.service;

import com.example.shirotest.model.User;

public interface UserService {
	User findByUsername(String username);
}
