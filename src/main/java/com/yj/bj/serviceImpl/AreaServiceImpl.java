package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Area;
import com.yj.bj.mapper.AreaMapper;
import com.yj.bj.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService {
	
	@Autowired
	private AreaMapper areaMapper;
}
