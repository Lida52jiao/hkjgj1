package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Subsidy;
import com.yj.bj.mapper.SubsidyMapper;
import com.yj.bj.service.SubsidyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubsidyServiceImpl extends BaseServiceImpl<Subsidy> implements SubsidyService {
	
	@Autowired
	private SubsidyMapper subsidyMapper;

}
