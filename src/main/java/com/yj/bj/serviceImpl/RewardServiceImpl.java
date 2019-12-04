package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Reward;
import com.yj.bj.mapper.RewardMapper;
import com.yj.bj.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RewardServiceImpl extends BaseServiceImpl<Reward> implements RewardService {
	
	@Autowired
	private RewardMapper rewardMapper;

	@Override
	public Reward find(Reward reward) {
		return rewardMapper.get(reward);
	}
}
