package com.example.shirotest.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.shirotest.model.User;

@Repository
public interface UserMapper {
	public User findByUsername( @Param("username") String username);
}
