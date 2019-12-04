package com.yj.bj.serviceImpl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.bj.entity.CardInformation;
import com.yj.bj.entity.CountT1Entity;
import com.yj.bj.entity.MerChants;
import com.yj.bj.entity.Transactional;
import com.yj.bj.mapper.TransactionalMapper;
import com.yj.bj.service.CardInformationService;
import com.yj.bj.service.CountT1Service;
import com.yj.bj.service.MerChantsService;
import com.yj.bj.service.TransactionalService;
import com.yj.bj.util.DateUtil;
import com.yj.bj.util.MyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Service
public class TransactionalServiceImpl extends BaseServiceImpl<Transactional> implements TransactionalService {
    @Autowired
    private TransactionalMapper transactionalMapper;
    @Autowired
    private MerChantsService merChantsService;
    @Autowired
    private CardInformationService cardInformationService;
    @Autowired
    private CountT1Service countT1Service;

    @Override
    public PageInfo<Transactional> queryList(String merchantId, String orderMerchantId, String orderMerchantName, String agentId, String orderNo, String level, Long startTime, Long finshTime) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Integer pageNum = MyStringUtil.valueOf(request.getParameter("pageNum"), 1);
        Integer pageSize = MyStringUtil.valueOf(request.getParameter("pageSize"), 10);
        PageHelper.startPage(pageNum,pageSize);
        String orderField = request.getParameter("sort");
        String orderDirection = request.getParameter("order");
        if (MyStringUtil.isNotEmpty(orderField)) {
            PageHelper.orderBy(orderField);
            if (MyStringUtil.isNotEmpty(orderDirection)) {
                PageHelper.orderBy(orderField + " " + orderDirection);
            }
        }
        return new PageInfo<Transactional>(transactionalMapper.getList(merchantId,orderMerchantId,orderMerchantName,agentId,orderNo,level,startTime,finshTime));
    }

    @Override
    public void t1() {
        String datetime = DateUtil.longToString(new Date().getTime()-24*60*60*1000L,"yyyy-MM-dd");
        String startTime = datetime + " 00:00:00";
        String endTime = datetime + " 23:59:59";
        List<CountT1Entity> list = transactionalMapper.countT1(startTime,endTime);
        for(CountT1Entity t1 : list){
            MerChants merChants = new MerChants();
            merChants.setMerChantId(t1.getMerchantId());
            merChants = merChantsService.findByObject(merChants);
            t1.setMerName(merChants.getMerName());
            CardInformation s = new CardInformation();
            s.setMerChantId(t1.getMerchantId());
            s.setCardType("SC");
            List<CardInformation> cardInformation = cardInformationService.queryObjectForList(s);
            if(cardInformation.size() > 0){
                t1.setCardNumber(cardInformation.get(0).getCardNumber());
            }
            t1.setTransactionTime(datetime);
            countT1Service.save(t1);
        }
    }
}
