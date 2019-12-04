package com.yj.bj.mapper;


import com.yj.bj.entity.CardInformation;
import com.yj.bj.util.MyMapper;

public interface CardInformationMapper extends MyMapper<CardInformation> {
	
	int get(String merChantId);

	int getcard(String merChantId);
}
