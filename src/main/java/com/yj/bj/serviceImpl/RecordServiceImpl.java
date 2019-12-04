package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Record;
import com.yj.bj.mapper.RecordMapper;
import com.yj.bj.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl extends BaseServiceImpl<Record> implements RecordService {
	
	@Autowired
	private RecordMapper recordMapper;
}
