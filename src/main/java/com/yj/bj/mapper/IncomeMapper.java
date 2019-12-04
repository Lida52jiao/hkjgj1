package com.yj.bj.mapper;

import com.yj.bj.entity.Income;
import com.yj.bj.util.MyMapper;

import java.util.List;


public interface IncomeMapper extends MyMapper<Income> {

	List<Number> get();
}
