package com.yj.bj.service;


import com.yj.bj.entity.MerChants;

public interface OpenAccountService {
	
	void openAccount(MerChants mer, String agentId, String oldAgentId);

}
