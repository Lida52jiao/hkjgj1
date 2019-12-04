package com.yj.bj.serviceImpl;

import com.yj.bj.entity.KSMerUsedArea;
import com.yj.bj.mapper.MerchantsUsedAreaMapper;
import com.yj.bj.service.MerchantsUsedAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantsUsedAreaServiceImpl extends BaseServiceImpl<KSMerUsedArea> implements
		MerchantsUsedAreaService {
	
	@Autowired
	private MerchantsUsedAreaMapper merchantsUsedAreaMapper;

}
