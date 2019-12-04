package com.yj.bj.mapper;


import com.yj.bj.entity.Banner;
import com.yj.bj.util.MyMapper;

import java.util.List;

public interface BannerMapper extends MyMapper<Banner> {

	List<Banner> query(Banner banner);
}
