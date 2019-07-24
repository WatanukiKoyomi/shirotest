package com.example.shirotest.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.shirotest.mapper.UserMapper;
import com.example.shirotest.model.User;
import com.example.shirotest.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.findByUsername(username);
	}
	
	
}
