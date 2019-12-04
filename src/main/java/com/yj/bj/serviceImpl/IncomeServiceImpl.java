package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Income;
import com.yj.bj.mapper.IncomeMapper;
import com.yj.bj.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IncomeServiceImpl extends BaseServiceImpl<Income> implements IncomeService {
	
	@Autowired
	private IncomeMapper incomeMapper;

	@Override
	public List<Number> gain() {
		List<Number> n = incomeMapper.get();
		return n;
	}
}
