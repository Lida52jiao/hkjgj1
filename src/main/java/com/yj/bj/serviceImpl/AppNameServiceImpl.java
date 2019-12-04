package com.yj.bj.serviceImpl;

import com.yj.bj.entity.AppName;
import com.yj.bj.mapper.AppNameMapper;
import com.yj.bj.service.AppNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppNameServiceImpl extends BaseServiceImpl<AppName> implements AppNameService {
	
	@Autowired
	private AppNameMapper appNameMapper;
}
