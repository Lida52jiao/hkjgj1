package com.yj.bj.serviceImpl;

import com.yj.bj.entity.News;
import com.yj.bj.mapper.NewsMapper;
import com.yj.bj.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {
	
	@Autowired
	private NewsMapper newsMapper;

	@Override
	public Map<String, Object> receive(Integer page, Integer rows, String merChantId, String appId) {
		if(page==null){
			page=1;
		}
		if(rows==null){
			rows=10;
		}
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("sindex", (page-1)*rows);
		m.put("eindex", rows);
		m.put("merChantId", merChantId);
		m.put("appId", appId);
		List<News> n=newsMapper.get(m);
		int total=newsMapper.selectCode(m);
		
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("rows", n);
		result.put("total", total);
		result.put("totalPages", total%rows==0?total/rows:(total/rows+1));
		result.put("currentPage", page);
		result.put("pageSize", rows);
		
		return result;
	}
}
