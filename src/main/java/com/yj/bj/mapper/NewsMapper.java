package com.yj.bj.mapper;


import com.yj.bj.entity.News;
import com.yj.bj.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface NewsMapper extends MyMapper<News> {

	List<News> get(Map<String, Object> m);

	int selectCode(Map<String, Object> m);
}
