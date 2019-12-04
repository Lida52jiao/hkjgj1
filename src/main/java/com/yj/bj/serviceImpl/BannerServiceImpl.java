package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Banner;
import com.yj.bj.mapper.BannerMapper;
import com.yj.bj.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BannerServiceImpl extends BaseServiceImpl<Banner> implements BannerService {
	
	@Autowired
	private BannerMapper bannerMapper;

	@Override
	public List<Banner> queryForList(Banner banner) {
		List<Banner> bannerList=bannerMapper.query(banner);
		return bannerList;
	}
}
