package com.yj.bj.mapper;


import com.yj.bj.entity.Introduce;
import com.yj.bj.util.MyMapper;

import java.util.List;
import java.util.Map;


public interface IntroduceMapper extends MyMapper<Introduce> {

	List<Introduce> get(Map<String, Object> m);

	int selectCode(Map<String, Object> m);
}
