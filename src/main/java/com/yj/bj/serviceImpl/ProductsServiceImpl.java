package com.yj.bj.serviceImpl;


import com.yj.bj.entity.Products;
import com.yj.bj.mapper.ProductsMapper;
import com.yj.bj.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductsServiceImpl extends BaseServiceImpl<Products> implements ProductsService {
	
	@Autowired
	private ProductsMapper productsMapper;
}
