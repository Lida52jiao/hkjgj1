package com.yj.bj.mapper;


import com.yj.bj.entity.Reward;
import com.yj.bj.util.MyMapper;

public interface RewardMapper extends MyMapper<Reward> {

	Reward get(Reward reward);
}
