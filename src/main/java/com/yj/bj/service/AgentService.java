package com.yj.bj.service;


import com.yj.bj.entity.Agent;

import java.util.List;

public interface AgentService extends BaseService<Agent> {
    List<Agent> getUpAgentListByMerId(String merChantId);
}
