package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Distribution;
import com.yj.bj.mapper.DistributionMapper;
import com.yj.bj.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DistributionServiceImpl extends BaseServiceImpl<Distribution> implements DistributionService {

	@Autowired
	private DistributionMapper distributionMapper;
}
