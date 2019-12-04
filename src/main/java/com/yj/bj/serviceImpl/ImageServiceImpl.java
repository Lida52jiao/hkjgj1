package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Image;
import com.yj.bj.mapper.ImageMapper;
import com.yj.bj.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImageServiceImpl extends BaseServiceImpl<Image> implements ImageService {
	
	@Autowired
	private ImageMapper imageMapper;
}
