package com.yj.bj.service;


import com.yj.bj.entity.CardInformation;

public interface CardInformationService extends BaseService<CardInformation> {

	int find(String token, CardInformation cardInformation);

	int gains(String merChantId);

	int selectcard(String merChantId);
}
