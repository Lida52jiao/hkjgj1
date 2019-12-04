package com.yj.bj.serviceImpl;

import com.yj.bj.entity.ChannelRate;
import com.yj.bj.mapper.ChannelRateMapper;
import com.yj.bj.service.ChannelRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChannelRateServiceImpl extends BaseServiceImpl<ChannelRate> implements ChannelRateService {
	
	@Autowired
	private ChannelRateMapper channelRateMapper;
}
