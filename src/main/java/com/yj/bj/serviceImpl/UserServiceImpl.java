package com.yj.bj.serviceImpl;


import com.yj.bj.entity.UserEntity;
import com.yj.bj.mapper.UserMapper;
import com.yj.bj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity> implements
		UserService {

	@Autowired
	private UserMapper userMapper;
}
