package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Transaction;
import com.yj.bj.mapper.TransactionMapper;
import com.yj.bj.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionServiceImpl extends BaseServiceImpl<Transaction>
		implements TransactionService {

	@Autowired
	private TransactionMapper transactionMapper;
}
