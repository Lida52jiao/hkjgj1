package com.yj.bj.service;


import com.yj.bj.entity.Banner;

import java.util.List;

public interface BannerService extends BaseService<Banner> {

	List<Banner> queryForList(Banner banner);
}
