package com.yj.bj.service;

import com.yj.bj.entity.Income;

import java.util.List;


public interface IncomeService extends BaseService<Income> {

	List<Number> gain();
}
