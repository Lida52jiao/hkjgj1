package com.yj.bj.mapper;


import com.yj.bj.entity.MerChant;
import com.yj.bj.entity.MerChants;
import com.yj.bj.util.MyMapper;

public interface MerChantsMapper extends MyMapper<MerChants> {
	
	int gain(MerChants v);

	int selectCode(String merChantId);

	int get(String merChantId);

	MerChant receive(MerChant t);
}
