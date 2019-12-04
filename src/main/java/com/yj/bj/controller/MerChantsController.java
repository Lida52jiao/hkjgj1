package com.yj.bj.controller;

import com.alibaba.fastjson.JSONObject;
import com.yj.bj.constant.Constaint;
import com.yj.bj.entity.*;
import com.yj.bj.service.*;
import com.yj.bj.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by 61968 on 2018/11/5.
 */
@Controller
@RequestMapping("MerChants")
public class MerChantsController {

    @Autowired
    private MerChantsService merChantsService;
    @Autowired
    private AgentService agentService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private FeeService feeService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private WithdrawalsService withdrawalsService;
    @Autowired
    private CardInformationService cardInformationService;
    @Autowired
    private NumService numService;
    @Autowired
    private UsedService usedService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private AppNameService appNameService;
    @Autowired
    private MerChantsRateService merchantsRateService;
    @Autowired
    private JiDaoService jiDaoService;
    @Autowired
    private RatesService ratesService;
    @Autowired
    private ChannelRateService channelRateService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private DistributionService distributionService;
    @Autowired
    private OpenAccountService openAccountService;

    //47.105.68.113/MerChants/countByMobile?mobile=  卡神通过手机号统计
    @RequestMapping(value = "mergeOrInsert")
    public @ResponseBody
    YJResult add(String merMp, String institutionId, String appId, String appName) {
        MerChants merChants = new MerChants();
        merChants.setMerMp(merMp);
        merChants.setAppId(appId);

        merChants = merChantsService.findByObject(merChants);
        if(merChants == null){
            String resultJsonStr = HttpClientUtils.doGet("http://47.105.48.45:1079/merchantRegister/queryObject?institutionId="+institutionId);
            JSONObject job = JSONObject.parseObject(resultJsonStr);
            if("1".equals(job.getString("data"))){
                return YJResult.build(Constaint.SERVER_ERROR, "注册失败，系统维护中");
            }

        }
        if("15901003379".equals(merMp)){
            MerChants m = new MerChants();
            m.setMerMp(merMp);
            m.setAppId(appId);
            return YJResult.ok(merChantsService.findByObject(m).getMerChantId());
        }
        String n = (int) (Math.random() * (999999 - 100000 + 1)) + 100000 + "";
        Map<String, String> param = new HashMap<>();
        param.put("institutionId", institutionId);
        param.put("mobile", merMp);
        param.put("identifying", n);
        param.put("type", "dx");
        param.put("appId", appId);
        String resultJsonStr = HttpClientUtils.doPost("http://47.104.4.155:1172/account/login", param);
        System.out.println(resultJsonStr);
        JSONObject job = JSONObject.parseObject(resultJsonStr);
        if("success".equals(job.getString("respDesc"))){
            MerChants m = new MerChants();
            m.setMerMp(merMp);
            m.setAppId(appId);
            MerChants r = merChantsService.findByObject(m);
            Withdrawals w=new Withdrawals();
            w.setAppId(appId);
            //w.setId((long)1);
            Withdrawals withdrawals=withdrawalsService.findByObject(w);
            if (r != null) {
                r.setIdentifying(n);
                r.setAppName(URLDecoder.decode(appName));
                merChantsService.update(r);
                return YJResult.ok(r.getMerChantId());
            }
            String result = HttpClientUtils.doGet("http://47.105.68.113/yj-mer/MerChants/countByMobile?mobile="+merMp);
            JSONObject jsonObject = JSONObject.parseObject(result);
            Integer results = Integer.parseInt(jsonObject.getString("data"));
            System.out.println(Integer.parseInt(jsonObject.getString("data")));
            if(results > 0){
                return YJResult.build(Constaint.SERVER_ERROR, "信息已经存在，无法鉴权");
            }
            MerChants h = new MerChants();
            h.setMerMp(merMp);
            h.setIdentifying(n);
            h.setGenerationFee(withdrawals.getGenerationFee());
            h.setGenerationFeeRepayment(withdrawals.getGenerationFeeRepayment());
            h.setInstitutionId(institutionId);
            h.setMerType("1");
            h.setMerStat("N");
            h.setStatus("N");
            h.setAgentStatus("N");
            h.setFrozen("N");
            h.setAppId(appId);
            h.setIsNotUse(2);
            h.setAppName(URLDecoder.decode(appName));
            h.setRegDate(System.currentTimeMillis()+"");
            h.setStartDate(System.currentTimeMillis()+"");
            h.setFinishDate(System.currentTimeMillis()+"");
            h.setBalance(BigDecimal.ZERO);
            h.setBalanceFrozen(BigDecimal.ZERO);
            h.setBalanceProfit(BigDecimal.ZERO);
            h.setBalanceProfitFrozen(BigDecimal.ZERO);
            h.setIsBind("N");
            merChantsService.save(h);
            MerChants v = new MerChants();
            v.setMerMp(merMp);
            v.setAppId(appId);
            MerChants t = merChantsService.findByObject(v);
            String s = "M" + snowflakeIdWorker.nextId();
            long id = t.getId() + 10000;
            String merChantId = s + id;
            t.setMerChantId(merChantId);
            merChantsService.update(t);
            return YJResult.ok(merChantId);
        }
        return YJResult.build(Constaint.SERVER_ERROR, job.getString("respDesc"));
    }

    @RequestMapping(value = "mergeOrInsertForHtml", method = RequestMethod.POST)
    public @ResponseBody
    YJResult addForHtml(String merMp, String institutionId, String appId) {
        MerChants merChants = new MerChants();
        merChants.setMerMp(merMp);
        merChants.setAppId(appId);
        merChants = merChantsService.findByObject(merChants);
        if(merChants == null){
            String resultJsonStr = HttpClientUtils.doGet("http://47.105.48.45:1079/merchantRegister/queryObject?institutionId="+institutionId);
            JSONObject job = JSONObject.parseObject(resultJsonStr);
            if("1".equals(job.getString("data"))){
                return YJResult.build(Constaint.SERVER_ERROR, "注册失败，系统维护中");
            }

        }
        MerChants m = new MerChants();
        m.setMerMp(merMp);
        m.setAppId(appId);
        MerChants r = merChantsService.findByObject(m);
        if (r != null) {
            return YJResult.build(Constaint.SERVER_ERROR, "此手机号已注册");
        }
        String result = HttpClientUtils.doGet("http://47.105.68.113/yj-mer/MerChants/countByMobile?mobile="+merMp);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer results = Integer.parseInt(jsonObject.getString("data"));
        System.out.println(Integer.parseInt(jsonObject.getString("data")));
        if(results > 0){
            return YJResult.build(Constaint.SERVER_ERROR, "信息已经存在，无法鉴权");
        }
        String n = (int) (Math.random() * (999999 - 100000 + 1)) + 100000 + "";
        Map<String, String> param = new HashMap<>();
        param.put("institutionId", institutionId);
        param.put("mobile", merMp);
        param.put("identifying", n);
        param.put("type", "dx");
        param.put("appId", appId);
        String resultJsonStr = HttpClientUtils.doPost("http://47.104.4.155:1172/account/login", param);
        System.out.println(resultJsonStr);
        JSONObject job = JSONObject.parseObject(resultJsonStr);
        if("success".equals(job.getString("respDesc"))){
            Withdrawals w=new Withdrawals();
            w.setAppId(appId);
            //w.setId((long)1);
            Withdrawals withdrawals=withdrawalsService.findByObject(w);
            AppName appName = new AppName();
            appName.setAppId(appId);
            AppName name = appNameService.findByObject(appName);
            MerChants h = new MerChants();
            h.setMerMp(merMp);
            h.setIdentifying(n);
            h.setGenerationFee(withdrawals.getGenerationFee());
            h.setGenerationFeeRepayment(withdrawals.getGenerationFeeRepayment());
            h.setInstitutionId(institutionId);
            h.setMerType("1");
            h.setMerStat("N");
            h.setStatus("N");
            h.setAgentStatus("N");
            h.setFrozen("N");
            h.setAppId(appId);
            h.setIsNotUse(2);
            h.setAppName(name.getAppName());
            h.setRegDate(System.currentTimeMillis()+"");
            h.setStartDate(System.currentTimeMillis()+"");
            h.setFinishDate(System.currentTimeMillis()+"");
            h.setBalance(BigDecimal.ZERO);
            h.setBalanceFrozen(BigDecimal.ZERO);
            h.setBalanceProfit(BigDecimal.ZERO);
            h.setBalanceProfitFrozen(BigDecimal.ZERO);
            h.setIsBind("N");
            merChantsService.save(h);
            MerChants v = new MerChants();
            v.setMerMp(merMp);
            v.setAppId(appId);
            MerChants t = merChantsService.findByObject(v);
            String s = "M" + snowflakeIdWorker.nextId();
            long id = t.getId() + 10000;
            String merChantId = s + id;
            t.setMerChantId(merChantId);
            merChantsService.update(t);
            return YJResult.ok(merChantId);
        }
        return YJResult.build(Constaint.SERVER_ERROR, "短信发送次数太多，请稍后再试");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody
    YJResult addMerChants(String merChantId, @RequestParam(value="merMp",required=false)String merMp, String identifying, String appId) {
        if("15901003379".equals(merMp)){
            MerChants m = new MerChants();
            m.setMerMp(merMp);
            m.setAppId(appId);
            MerChants h = merChantsService.findByObject(m);
            String token= MD5Util.getMD5String(merChantId+h.getInstitutionId()+System.currentTimeMillis());
            Jedis jedis= RedisUtils.getJedis();
            jedis.set(token,merChantId);
            jedis.expire(token,2592000);
            RedisUtils.returnResource(jedis);
            return YJResult.ok(token);
        }
        MerChants m = new MerChants();
        m.setMerMp(merMp);
        m.setIdentifying(identifying);
        m.setAppId(appId);
        MerChants h = merChantsService.findByObject(m);
        if (h != null) {
            String token= MD5Util.getMD5String(merChantId+h.getInstitutionId()+System.currentTimeMillis());
            Jedis jedis= RedisUtils.getJedis();
            jedis.set(token,merChantId);
            jedis.expire(token,2592000);
            RedisUtils.returnResource(jedis);
            return YJResult.ok(token);
        }
        return YJResult.build(Constaint.NONE_MERCHAT, "手机号或验证码错误");
    }

    @RequestMapping(value = "inspectToken", method = RequestMethod.POST)
    public @ResponseBody
    YJResult inspect(String token) {
        Jedis jedis=RedisUtils.getJedis();
        if(jedis.exists(token)){
            jedis.expire(token,2592000);
            String merChantId=jedis.get(token);
            MerChants merChants=new MerChants();
            merChants.setMerChantId(merChantId);
            MerChants t=merChantsService.findByObject(merChants);
//            if(t.getFinishDate() != null){
//                long k = new Long(t.getFinishDate());
//                long n=System.currentTimeMillis();
//                if(n > k){
//                    t.setMerType("1");
//                    t.setFrozen("N");
//                    merChantsService.update(t);
//                }
//            }
            RedisUtils.returnResource(jedis);
            return YJResult.ok();
        }
        RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    @RequestMapping(value = "getMerChant", method = RequestMethod.POST)
    public @ResponseBody
    YJResult gets(String token, String merChantId) {
        Jedis jedis=RedisUtils.getJedis();
        if(jedis.exists(token)){
            if(!merChantId.equals(jedis.get(token))){
                RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            MerChant t = new MerChant();
            t.setMerChantId(merChantId);
            MerChant h = merChantsService.gain(t);
            MerChants v = new MerChants();
            v.setMerChantId(merChantId);
            v.setMerStat("Y");
            int amount = merChantsService.statistics(v);
            int credit  = cardInformationService.gains(merChantId);
            int card = cardInformationService.selectcard(merChantId);
            h.setAmount(amount);
            h.setCredit(credit);
            h.setCard(card);
            RedisUtils.returnResource(jedis);
            return YJResult.ok(h);
        }
        RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    @RequestMapping(value = "getMerChantsById")
    public @ResponseBody
    MerChants select(String merChantId) {
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        return h;
    }

    @RequestMapping(value = "selectMerChants")
    public @ResponseBody
    MerChants selects(String merChantId, String aisleCode) {
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        MerChantsRate merChantsRate=new MerChantsRate();
        merChantsRate.setMerType(h.getMerType());
        merChantsRate.setAisleCode(aisleCode);
        merChantsRate.setAppId(h.getAppId());
        MerChantsRate merChantsRates=merchantsRateService.findByObject(merChantsRate);
        h.setMerChantFee(merChantsRates.getRate()+"");
        h.setGenerationFeeRepayment(merChantsRates.getD0Fee()+"");
        return h;
    }

    @RequestMapping(value = "addFaceImgUrl", method = RequestMethod.POST)
    public @ResponseBody
    YJResult insert(String token, String merChantId, String name,
                    @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Jedis jedis=RedisUtils.getJedis();
        if(jedis.exists(token)){
            if(!merChantId.equals(jedis.get(token))){
                RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            String filePath = "";
            // 判断文件是否为空
            if (!file.isEmpty()) {
                try {
                    // 文件保存路径
                    filePath = request.getSession().getServletContext()
                            .getRealPath("/")+ merChantId +name+ file.getOriginalFilename();
                    // 转存文件
                    file.transferTo(new File(filePath));
                } catch (Exception e) {
                    e.printStackTrace();
                    RedisUtils.returnResource(jedis);
                    return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
                }
            }
            String s = MD5Util.getMD5String(filePath);
            String result = "";
            OSSClientUtil clientUtil = new OSSClientUtil();
            Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24
                    * 365 * 10);
            // 上传
            InputStream instream = null;
            if("tx".equals(name)){
                String ossKey = "faceImgUrl/" + s;
                try {
                    instream = new FileInputStream(filePath);
                    clientUtil.uploadFile2OSS(instream, ossKey);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    RedisUtils.returnResource(jedis);
                    return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
                }
                // 获取url
                URL url = clientUtil.createUrl(ossKey, expiration);
                clientUtil.destory();
                result = url.toString();
                MerChants m=new MerChants();
                m.setMerChantId(merChantId);
                MerChants h=merChantsService.findByObject(m);
                h.setFaceImgUrl(result);
                merChantsService.update(h);
                RedisUtils.returnResource(jedis);
                return YJResult.ok(result);
            }
            if("sfzzm".equals(name)){
                String ossKey = "userIDCardA/" + s;
                try {
                    instream = new FileInputStream(filePath);
                    clientUtil.uploadFile2OSS(instream, ossKey);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    RedisUtils.returnResource(jedis);
                    return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
                }
                // 获取url
                URL url = clientUtil.createUrl(ossKey, expiration);
                clientUtil.destory();
                result = url.toString();
                Image n=new Image();
                n.setMerChantId(merChantId);
                Image h=imageService.findByObject(n);
                if(h != null){
                    h.setUserIDCardA(result);
                    imageService.update(h);
                    RedisUtils.returnResource(jedis);
                    return YJResult.ok(result);
                }
                Image v=new Image();
                v.setMerChantId(merChantId);
                v.setUserIDCardA(result);
                imageService.save(v);
                RedisUtils.returnResource(jedis);
                return YJResult.ok(result);
            }
            if("sfzfm".equals(name)){
                String ossKey = "userIDCardB/" + s;
                try {
                    instream = new FileInputStream(filePath);
                    clientUtil.uploadFile2OSS(instream, ossKey);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    RedisUtils.returnResource(jedis);
                    return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
                }
                // 获取url
                URL url = clientUtil.createUrl(ossKey, expiration);
                clientUtil.destory();
                result = url.toString();
                Image n=new Image();
                n.setMerChantId(merChantId);
                Image h=imageService.findByObject(n);
                if(h != null){
                    h.setUserIDCardB(result);
                    imageService.update(h);
                    RedisUtils.returnResource(jedis);
                    return YJResult.ok(result);
                }
                Image v=new Image();
                v.setMerChantId(merChantId);
                v.setUserIDCardB(result);
                imageService.save(v);
                RedisUtils.returnResource(jedis);
                return YJResult.ok(result);
            }
            if("yhkzm".equals(name)){
                String ossKey = "cardImgA/" + s;
                try {
                    instream = new FileInputStream(filePath);
                    clientUtil.uploadFile2OSS(instream, ossKey);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    RedisUtils.returnResource(jedis);
                    return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
                }
                // 获取url
                URL url = clientUtil.createUrl(ossKey, expiration);
                clientUtil.destory();
                result = url.toString();
                Image n=new Image();
                n.setMerChantId(merChantId);
                Image h=imageService.findByObject(n);
                if(h != null){
                    h.setCardImgA(result);
                    imageService.update(h);
                    RedisUtils.returnResource(jedis);
                    return YJResult.ok(result);
                }
                Image v=new Image();
                v.setMerChantId(merChantId);
                v.setCardImgA(result);
                imageService.save(v);
                RedisUtils.returnResource(jedis);
                return YJResult.ok(result);
            }
        }
        RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    @RequestMapping(value = "authentication", method = RequestMethod.POST)
    public @ResponseBody
    YJResult authentication(String token, String merChantId, String merName, String certNo, String cardNumber, String issuingBank) {
        Jedis jedis=RedisUtils.getJedis();
        if(jedis.exists(token)){
            if(!merChantId.equals(jedis.get(token))){
                RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            MerChants m=new MerChants();
            m.setMerChantId(merChantId);
            MerChants h=merChantsService.findByObject(m);
            Map<String, String> map = new HashMap<>();
            map.put("merchantId", merChantId);
            map.put("institutionId", h.getInstitutionId());
            map.put("type", "sys");
            map.put("name", URLDecoder.decode(merName));
            map.put("card", cardNumber);
            map.put("certNo", certNo);
            String resultJsonStr = HttpClientUtils.doPost("http://47.104.4.155:1172/account/check", map);
            JSONObject job = JSONObject.parseObject(resultJsonStr);
            if("0000".equals(job.getString("respCode"))){
                String data = job.getString("data");
                JSONObject jobs = JSONObject.parseObject(data);
                RedisUtils.returnResource(jedis);
                return YJResult.ok(jobs);
            }
            RedisUtils.returnResource(jedis);
            return YJResult.build(job.getString("respCode"), job.getString("respDesc"));
        }
        RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public @ResponseBody
    YJResult check(String token, String merChantId, String certNo, String appId) {
        Jedis jedis=RedisUtils.getJedis();
        if(jedis.exists(token)){
            if(!merChantId.equals(jedis.get(token))){
                RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            MerChants m = new MerChants();
            m.setCertNo(certNo);
            m.setAppId(appId);
            MerChants h = merChantsService.findByObject(m);
            if(h != null){
                RedisUtils.returnResource(jedis);
                return YJResult.ok("Y");
            }
            RedisUtils.returnResource(jedis);
            return YJResult.ok("N");
        }
        RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    @RequestMapping(value = "getMerChantsWithMerMp", method = RequestMethod.POST)
    public @ResponseBody
    YJResult getMerChants(String token, String merChantId, String merMp, String appId) {
        Jedis jedis=RedisUtils.getJedis();
        if(jedis.exists(token)){
            if(!merChantId.equals(jedis.get(token))){
                RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            MerChants m = new MerChants();
            m.setMerMp(merMp);
            m.setAppId(appId);
            MerChants h = merChantsService.findByObject(m);
            if (h != null) {
                RedisUtils.returnResource(jedis);
                return YJResult.ok(h);
            }
            RedisUtils.returnResource(jedis);
            return YJResult.build(Constaint.NONE_MERCHAT, "没有此商户");
        }
        RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    @RequestMapping(value = "setMerChants", method = RequestMethod.POST)
    public @ResponseBody
    YJResult set(String token, String merChantId, String merMp, String appId) {
        Jedis jedis=RedisUtils.getJedis();
        if(jedis.exists(token)){
            if(!merChantId.equals(jedis.get(token))){
                RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            MerChants t = new MerChants();
            t.setMerChantId(merChantId);
            MerChants k= merChantsService.findByObject(t);
            if(merMp.equals(k.getMerMp())){
                RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.NONE_MERCHAT, "绑定失败,推荐人不能为本人");
            }
            MerChants m = new MerChants();
            m.setMerMp(merMp);
            m.setAppId(appId);
            MerChants h = merChantsService.findByObject(m);
            if (h != null) {
                if(null == h.getAgentId() || "".equals(h.getAgentId())){
                    RedisUtils.returnResource(jedis);
                    return YJResult.build(Constaint.NONE_MERCHAT, "绑定失败,该用户未绑定关系");
                }
                k.setStatus("Y");
                k.setStatusDate(System.currentTimeMillis()+"");
                k.setOneMerId(h.getMerChantId());
                k.setTwoMerId(h.getOneMerId());
                k.setThreeMerId(h.getTwoMerId());
                Transaction transaction=new Transaction();
                transaction.setMerChantId(h.getMerChantId());
                Transaction f=transactionService.findByObject(transaction);
                if(f != null){
                    k.setAgentId(f.getMerId());
                    Fee fee=new Fee();
                    fee.setAgentId(f.getMerId());
                    Fee v=feeService.findByObject(fee);
                    if(v != null){
                        k.setMerChantFee(v.getMerchantFee());
                        merChantsService.update(k);
                    }else{
                        Credit credit=new Credit();
                        credit.setId((long) 1);
                        Credit r=creditService.findByObject(credit);
                        k.setMerChantFee(r.getMerchantFee());
                        merChantsService.update(k);
                    }
                }else{
                    k.setAgentId(h.getAgentId());
                    Fee fee=new Fee();
                    fee.setAgentId(h.getAgentId());
                    Fee v=feeService.findByObject(fee);
                    if(v != null){
                        k.setMerChantFee(v.getMerchantFee());
                        merChantsService.update(k);
                    }else{
                        Credit credit=new Credit();
                        credit.setId((long) 1);
                        Credit r=creditService.findByObject(credit);
                        k.setMerChantFee(r.getMerchantFee());
                        merChantsService.update(k);
                    }
                }
                try{
                    merChantsService.bind(k);
                }catch(Exception e){

                }
                RedisUtils.returnResource(jedis);
                return YJResult.ok();
            }
            RedisUtils.returnResource(jedis);
            return YJResult.build(Constaint.NONE_MERCHAT, "没有此商户("+merMp+")");
        }
        RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    @RequestMapping(value = "alterMerType")
    public @ResponseBody
    String alterMerType(String merChantId, String agentId, BigDecimal amount, String payType, String orderNo, String sign) {
        LinkedHashMap<String,Object> merHashMap=new LinkedHashMap<>();
        merHashMap.put("merChantId",merChantId);
        merHashMap.put("agentId",agentId);
        merHashMap.put("amount",amount);
        merHashMap.put("payType",payType);
        merHashMap.put("orderNo",orderNo);
        String merSign= SignUtil.createYJSign(merHashMap);
        merHashMap.put("sign",merSign);
        System.out.println(sign+"--"+merSign);
        if (!merSign.equals(sign)){
            return "error";
        }
        Record record = new Record();
        record.setOrderNo(orderNo);
        Record records = recordService.findByObject(record);
        if(null != records){
            return "success";
        }
        Record s = new Record(merChantId, System.currentTimeMillis()+"", orderNo, "升级费用");
        recordService.save(s);
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        Num n = new Num();
        n.setPayType(payType);
        n.setAppId(h.getAppId());
        Num num = numService.findByObject(n);
        h.setAgentStatus(num.getAgentStatus());
        h.setMerType(num.getMerType());
        h.setFrozen("Y");
        long startDate = System.currentTimeMillis();
        long finishDate = startDate + num.getValidTime()*24*3600*1000;
        h.setStartDate(startDate+"");
        h.setFinishDate(finishDate+"");
        merChantsService.update(h);
        return "success";
    }

    String openAgent(String merChantId){
        Transaction transaction = new Transaction();
        transaction.setMerChantId(merChantId);
        List<Transaction> list = transactionService.queryObjectForList(transaction);
        if(list.size() > 0 ){
            return null;
        }
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants mer = merChantsService.findByObject(m);
        String oldAgentId=mer.getAgentId();
        //2开代理agentService  ADD  绑关系
        Agent n=new Agent(mer.getMerName(), mer.getMerMp(),null,null, null, mer.getAgentId(), System.currentTimeMillis()+"");
        agentService.save(n);
        Agent h=agentService.findByObject(n);
        String s = "C" + YJ.formatDate(new Date())+YJ.formattime(new Date());
        long id = h.getId() + 10000;
        String merId = s + id;//代理商ID
        //代理商信息
        h.setMerId(merId);
        h.setTotalCode("0");
        h.setAssign("0");
        h.setGeneratedCode("0");
        h.setUsed("0");
        h.setNotused("0");
        h.setAccountNumber(mer.getMerMp());

        agentService.update(h);

        //代理商关系
        Transaction t=new Transaction();
        t.setMerChantId(mer.getMerChantId());
        t.setMerId(merId);
        t.setAgentName(mer.getMerName());
        t.setCreatDate(YJ.formatDate(new Date())+YJ.formattime(new Date()));
        t.setAgentStatus(mer.getAgentStatus());
        transactionService.save(t);


        //4分配名额  上级-  //mer.agentId改成自己
        //5修改相关信息
        mer.setAgentId(merId);
        merChantsService.update(mer);

        //后台开户+带关系+设置费率
        openAccountService.openAccount(mer,merId,oldAgentId);
        return null;
    }

    @RequestMapping(value = "getStatistics")
    public @ResponseBody
    YJResult select(String token, String merChantId) {
        Jedis jedis=RedisUtils.getJedis();
        if(jedis.exists(token)){
            if(!merChantId.equals(jedis.get(token))){
                RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            MerChants m = new MerChants();
            m.setOneMerId(merChantId);
            List<MerChants> hList = merChantsService.queryObjectForList(m);
            int first = merChantsService.receive(merChantId);
            int second = merChantsService.find(merChantId);
            List<Statistics> list=new ArrayList<Statistics>();
            Statistics statistics=new Statistics(merChantId, first, second);
            for(MerChants merChants:hList){
                MerChants f=(MerChants)merChants;
                int n=merChantsService.receive(f.getMerChantId());
                int t=merChantsService.find(f.getMerChantId());
                Statistics s=new Statistics(f.getMerChantId(), f.getMerName(), f.getMerMp(), f.getMerType(), f.getAgentStatus(), f.getStatusDate(), n, t);
                list.add(s);
            }
            statistics.setChildren(list);
            RedisUtils.returnResource(jedis);
            return YJResult.ok(statistics);
        }
        RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    @RequestMapping(value = "setMerChantsForHtml", method = RequestMethod.POST)
    public @ResponseBody
    YJResult setMerChants(String merChantId, String merMp, String appId) {
        MerChants t = new MerChants();
        t.setMerChantId(merChantId);
        MerChants k= merChantsService.findByObject(t);
        if(null != k.getOneMerId() && !"".equals(k.getOneMerId())){
            return YJResult.build(Constaint.NONE_MERCHAT, "已绑定推荐关系");
        }
        MerChants m = new MerChants();
        m.setMerMp(merMp);
        m.setAppId(appId);
        MerChants h = merChantsService.findByObject(m);
        if (h != null) {
            if(null == h.getAgentId() || "".equals(h.getAgentId())){
                return YJResult.build(Constaint.NONE_MERCHAT, "绑定失败,该用户未绑定关系");
            }
            k.setStatus("Y");
            k.setStatusDate(System.currentTimeMillis()+"");
            k.setOneMerId(h.getMerChantId());
            k.setTwoMerId(h.getOneMerId());
            k.setThreeMerId(h.getTwoMerId());
            Transaction transaction=new Transaction();
            transaction.setMerChantId(h.getMerChantId());
            Transaction f=transactionService.findByObject(transaction);
            if(f != null){
                k.setAgentId(f.getMerId());
                Fee fee=new Fee();
                fee.setAgentId(f.getMerId());
                Fee v=feeService.findByObject(fee);
                if(v != null){
                    k.setMerChantFee(v.getMerchantFee());
                    merChantsService.update(k);
                }else{
                    Credit credit=new Credit();
                    credit.setId((long) 1);
                    Credit r=creditService.findByObject(credit);
                    k.setMerChantFee(r.getMerchantFee());
                    merChantsService.update(k);
                }
            }else{
                k.setAgentId(h.getAgentId());
                Fee fee=new Fee();
                fee.setAgentId(h.getAgentId());
                Fee v=feeService.findByObject(fee);
                if(v != null){
                    k.setMerChantFee(v.getMerchantFee());
                    merChantsService.update(k);
                }else{
                    Credit credit=new Credit();
                    credit.setId((long) 1);
                    Credit r=creditService.findByObject(credit);
                    k.setMerChantFee(r.getMerchantFee());
                    merChantsService.update(k);
                }
            }
            return YJResult.ok();
        }
        return YJResult.build(Constaint.NONE_MERCHAT, "没有此商户("+merMp+")");
    }

    @RequestMapping(value = "selectAgent", method = RequestMethod.POST)
    public @ResponseBody
    YJResult selectAgent(String token, String merChantId, String agentId) {
        Jedis jedis=RedisUtils.getJedis();
        if(jedis.exists(token)){
            if(!merChantId.equals(jedis.get(token))){
                RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            Transaction transaction=new Transaction();
            transaction.setMerId(agentId);
            Transaction f=transactionService.findByObject(transaction);
            MerChants m = new MerChants();
            m.setMerChantId(f.getMerChantId());
            MerChants h = merChantsService.findByObject(m);

            Agent t = new Agent();
            t.setMerId(agentId);
            Agent agent = agentService.findByObject(t);
            h.setMerName(agent.getMerName());
            RedisUtils.returnResource(jedis);
            return YJResult.ok(h);
        }
        RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    @RequestMapping(value = "selectRateLists")
    public @ResponseBody
    List<Rate> selectRateLists(String merChantId, String aisleCode, String module) {
        if("epos".equals(module)){
            List<Rate> list = new ArrayList();
            return list;
        }
        List<Rate> list=new ArrayList<Rate>();
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        MerChants k = new MerChants();
        MerChants t = null;
        if(!"".equals(h.getOneMerId()) && null != h.getOneMerId()){
            k.setMerChantId(h.getOneMerId());
            t = merChantsService.findByObject(k);
        }
        MerChants r = new MerChants();
        MerChants s = null;
        if(!"".equals(h.getTwoMerId()) && null != h.getTwoMerId()){
            r.setMerChantId(h.getTwoMerId());
            s = merChantsService.findByObject(r);
        }
        MerChants v = new MerChants();
        MerChants n = null;
        if(!"".equals(h.getThreeMerId()) && null != h.getThreeMerId()){
            v.setMerChantId(h.getThreeMerId());
            n = merChantsService.findByObject(v);
        }
        if(t != null){
            MerChantsRate merChantsRate=new MerChantsRate();
            merChantsRate.setMerType(t.getMerType());
            merChantsRate.setAisleCode(aisleCode);
            merChantsRate.setAppId(t.getAppId());
            MerChantsRate merChantsRates=merchantsRateService.findByObject(merChantsRate);
            Rate rate=new Rate(t.getMerChantId(), t.getMerType(), merChantsRates.getRate()+"");
            rate.setBrushrate(merChantsRates.getRate()+"");
            list.add(rate);
            if(s != null){
                MerChantsRate merChants=new MerChantsRate();
                merChants.setMerType(s.getMerType());
                merChants.setAisleCode(aisleCode);
                merChants.setAppId(s.getAppId());
                MerChantsRate merChant=merchantsRateService.findByObject(merChants);
                Rate rates=new Rate(s.getMerChantId(), s.getMerType(), merChant.getRate()+"");
                rates.setBrushrate(merChant.getRate()+"");
                list.add(rates);
            }
            if(n != null){
                MerChantsRate merChants=new MerChantsRate();
                merChants.setMerType(n.getMerType());
                merChants.setAisleCode(aisleCode);
                merChants.setAppId(n.getAppId());
                MerChantsRate merChant=merchantsRateService.findByObject(merChants);
                Rate rates=new Rate(n.getMerChantId(), n.getMerType(), merChant.getRate()+"");
                rates.setBrushrate(merChant.getRate()+"");
                list.add(rates);
            }
        }
        return list;
    }

    @RequestMapping(value = "selectGradeList")
    public @ResponseBody
    List<Mer> selectGradeLists(String merChantId, String payType) {
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        List<MerChants> list = new ArrayList<>();
        list = get(list,h);
        Num num = new Num();
        num.setPayType(payType);
        num.setAppId(h.getAppId());
        num = numService.findByObject(num);
        List<Long> longList = new ArrayList<>();
        longList.add(num.getVisitor());
        longList.add(num.getVip());
        longList.add(num.getHighVip());
        longList.add(num.getChannel());
        longList.add(num.getAgent());
        longList.add(num.getArea());
        longList.add(num.getInstitution());
        List<String> stringList = new ArrayList();
        for(int i = 0; i < list.size();){
            if(stringList.contains(list.get(i).getMerType())){
                list.remove(list.get(i));
            }else{
                if(i == 0){
                    stringList.add(list.get(i).getMerType());
                    i++;
                }else{
                    if(Integer.parseInt(stringList.get(i - 1)) < Integer.parseInt(list.get(i).getMerType())){
                        stringList.add(list.get(i).getMerType());
                        i++;
                    }else{
                        list.remove(list.get(i));
                    }
                }
            }
        }
//        for(MerChants merChants : list){
//
//        }
        List<Mer> merList = new ArrayList();
        Long cash = 0L;
        for(int i = 0; i < list.size(); i++){
            Distribution distribution = new Distribution();
            distribution.setMerType(list.get(i).getMerType());
            distribution = distributionService.findByObject(distribution);
            if(i == 0){
                Mer mer = new Mer();
                mer.setMerChantId(list.get(i).getMerChantId());
                mer.setStatus(distribution.getLevel());
                mer.setProfit(longList.get(Integer.parseInt(list.get(i).getMerType()) - 1));
                merList.add(mer);
                cash += longList.get(Integer.parseInt(list.get(i).getMerType()) - 1);
                continue;
            }
            if(Integer.parseInt(list.get(i).getMerType()) > Integer.parseInt(list.get(i - 1).getMerType())){
                Mer mer = new Mer();
                mer.setMerChantId(list.get(i).getMerChantId());
                mer.setStatus(distribution.getLevel());
                mer.setProfit(longList.get(Integer.parseInt(list.get(i).getMerType()) - 1) - cash);
                merList.add(mer);
                cash += (longList.get(Integer.parseInt(list.get(i).getMerType()) - 1) - cash);
            }
        }
        Long balance = 0L;
        for(Mer mer : merList){
            balance += mer.getProfit();
        }
        Transaction transaction = new Transaction();
        transaction.setMerId(Constaint.AGENT);
        transaction = transactionService.findByObject(transaction);
        MerChants merChants = new MerChants();
        merChants.setMerChantId(transaction.getMerChantId());
        merChants = merChantsService.findByObject(merChants);
        Mer mer = new Mer();
        mer.setMerChantId(merChants.getMerChantId());
        mer.setStatus("institution");
        mer.setProfit(Long.parseLong(num.getNum()) - balance);
        merList.add(mer);
        return merList;
    }

    @RequestMapping(value = "share")
    public @ResponseBody
    YJResult share(String merChantId, String payType, String orderNo) {
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        List<MerChants> list = new ArrayList<>();
        list = get(list,h);
        Num num = new Num();
        num.setPayType(payType);
        num.setAppId(h.getAppId());
        num = numService.findByObject(num);
        List<Long> longList = new ArrayList<>();
        longList.add(num.getVisitor());
        longList.add(num.getVip());
        longList.add(num.getHighVip());
        longList.add(num.getChannel());
        longList.add(num.getAgent());
        longList.add(num.getArea());
        longList.add(num.getInstitution());
        List<String> stringList = new ArrayList();
        for(int i = 0; i < list.size();){
            if(stringList.contains(list.get(i).getMerType())){
                list.remove(list.get(i));
            }else{
                if(i == 0){
                    stringList.add(list.get(i).getMerType());
                    i++;
                }else{
                    if(Integer.parseInt(stringList.get(i - 1)) < Integer.parseInt(list.get(i).getMerType())){
                        stringList.add(list.get(i).getMerType());
                        i++;
                    }else{
                        list.remove(list.get(i));
                    }
                }
            }
        }
//        for(MerChants merChants : list){
//            if(stringList.contains(merChants.getMerType())){
//                list.remove(merChants);
//            }else{
//                stringList.add(merChants.getMerType());
//            }
//        }
        List<Mer> merList = new ArrayList();
        Long cash = 0L;
        for(int i = 0; i < list.size(); i++){
            Distribution distribution = new Distribution();
            distribution.setMerType(list.get(i).getMerType());
            distribution = distributionService.findByObject(distribution);
            if(i == 0){
                Mer mer = new Mer();
                mer.setMerChantId(list.get(i).getMerChantId());
                mer.setStatus(distribution.getLevel());
                mer.setProfit(longList.get(Integer.parseInt(list.get(i).getMerType()) - 1));
                merList.add(mer);
                cash += longList.get(Integer.parseInt(list.get(i).getMerType()) - 1);
                continue;
            }
            if(Integer.parseInt(list.get(i).getMerType()) > Integer.parseInt(list.get(i - 1).getMerType())){
                Mer mer = new Mer();
                mer.setMerChantId(list.get(i).getMerChantId());
                mer.setStatus(distribution.getLevel());
                mer.setProfit(longList.get(Integer.parseInt(list.get(i).getMerType()) - 1) - cash);
                merList.add(mer);
                cash += (longList.get(Integer.parseInt(list.get(i).getMerType()) - 1) - cash);
            }
        }
        Long balance = 0L;
        for(Mer mer : merList){
            balance += mer.getProfit();
        }
        Transaction transaction = new Transaction();
        transaction.setMerId(Constaint.AGENT);
        transaction = transactionService.findByObject(transaction);
        MerChants merChants = new MerChants();
        merChants.setMerChantId(transaction.getMerChantId());
        merChants = merChantsService.findByObject(merChants);
        Mer mer = new Mer();
        mer.setMerChantId(merChants.getMerChantId());
        mer.setStatus("institution");
        mer.setProfit(Long.parseLong(num.getNum()) - balance);
        merList.add(mer);
        try {
            return merChantsService.share(h,num,merList,orderNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return YJResult.build("1111","分润失败，请联系客服");
    }

    public List<MerChants> get(List<MerChants> list,MerChants merChants){
        if("".equals(merChants.getOneMerId()) || null == merChants.getOneMerId()){
            return list;
        }
        if(!"".equals(merChants.getOneMerId()) && null != merChants.getOneMerId()){
            MerChants mer =new MerChants();
            mer.setMerChantId(merChants.getOneMerId());
            mer = merChantsService.findByObject(mer);
            list.add(mer);
            get(list,mer);
        }
        return list;
    }

    @RequestMapping(value = "selectImageList")
    public @ResponseBody
    YJResult getImageList(String merChantId) {
        Image t = new Image();
        t.setMerChantId(merChantId);
        return YJResult.ok(imageService.findByObject(t));
    }

    @RequestMapping(value = "selectMerChantsRate")
    public @ResponseBody
    YJResult alter(String merChantId) {
        MerChants merChants = new MerChants();
        merChants.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(merChants);
        MerChantsRate merChantsRate = new MerChantsRate();
        merChantsRate.setMerType(h.getMerType());
        merChantsRate.setAppId(h.getAppId());
        List<MerChantsRate> t = merchantsRateService.queryObjectForList(merChantsRate);
        return YJResult.ok(t);
    }

    @RequestMapping(value = "send")
    public @ResponseBody
    YJResult sends(String merChantId, String merMp, String type) {
        MerChants merChants = new MerChants();
        merChants.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(merChants);
        if("1".equals(type)){
            String n = (int) (Math.random() * (999999 - 100000 + 1)) + 100000 + "";
            h.setIdentifying(n);
            merChantsService.update(h);
            Map<String, String> param = new HashMap<>();
            param.put("merchantId", merChantId);
            param.put("institutionId", h.getInstitutionId());
            param.put("mobile", h.getMerMp());
            param.put("identifying", n);
            param.put("type", "dx");
            String resultJsonStr = HttpClientUtils.doPost("http://47.104.4.155:1172/account/alterMobile", param);
            System.out.println(resultJsonStr);
            return YJResult.ok();
        }
        String n = (int) (Math.random() * (999999 - 100000 + 1)) + 100000 + "";
        h.setIdentifying(n);
        merChantsService.update(h);
        Map<String, String> param = new HashMap<>();
        param.put("merchantId", merChantId);
        param.put("institutionId", h.getInstitutionId());
        param.put("mobile", merMp);
        param.put("identifying", n);
        param.put("type", "dx");
        String resultJsonStr = HttpClientUtils.doPost("http://47.104.4.155:1172/account/alterMobile", param);
        System.out.println(resultJsonStr);
        return YJResult.ok();
    }

    @RequestMapping(value = "alterMerMp")
    public @ResponseBody
    YJResult alterMerMp(String merChantId, @RequestParam(value="merMp",required=false)String merMp, String identifying, String type) {
        MerChants merChants = new MerChants();
        merChants.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(merChants);
        if("1".equals(type)){
            if(!identifying.equals(h.getIdentifying())){
                return YJResult.build(Constaint.SERVER_ERROR, "验证码有误,请输入正确的验证码");
            }
            return YJResult.ok();
        }
        if(!identifying.equals(h.getIdentifying())){
            return YJResult.build(Constaint.SERVER_ERROR, "验证码有误,请输入正确的验证码");
        }
        MerChants merChant = new MerChants();
        merChant.setMerMp(merMp);
        merChant.setAppId(h.getAppId());
        if(null != merChantsService.findByObject(merChant)){
            return YJResult.build(Constaint.SERVER_ERROR, "账号已存在");
        }
        h.setMerMp(merMp);
        merChantsService.update(h);
        return YJResult.ok();
    }

    @RequestMapping(value = "test")
    public @ResponseBody
    YJResult test(String merMp, @RequestParam(defaultValue = Constaint.AGENT)String institutionId,@RequestParam(defaultValue = "0000") String appId,@RequestParam(defaultValue = "精彩生活") String appName) {
        String n = (int) (Math.random() * (999999 - 100000 + 1)) + 100000 + "";
        MerChants m = new MerChants();
        m.setMerMp(merMp);
        m.setAppId(appId);
        MerChants r = merChantsService.findByObject(m);
        Withdrawals w=new Withdrawals();
        w.setAppId(appId);
        //w.setId((long)1);
        Withdrawals withdrawals=withdrawalsService.findByObject(w);
        if (r != null) {
            r.setIdentifying(n);
            r.setAppName(appName);
            merChantsService.update(r);
            return YJResult.ok(r.getMerChantId());
        }
        MerChants h = new MerChants();
        h.setMerMp(merMp);
        h.setIdentifying(n);
        h.setGenerationFee(withdrawals.getGenerationFee());
        h.setGenerationFeeRepayment(withdrawals.getGenerationFeeRepayment());
        h.setInstitutionId(institutionId);
        h.setMerType("1");
        h.setMerStat("N");
        h.setStatus("N");
        h.setAgentStatus("N");
        h.setFrozen("N");
        h.setAppId(appId);
        h.setAppName(appName);
        h.setRegDate(System.currentTimeMillis()+"");
        h.setStartDate(System.currentTimeMillis()+"");
        h.setFinishDate(System.currentTimeMillis()+"");
        h.setBalance(BigDecimal.ZERO);
        h.setBalanceFrozen(BigDecimal.ZERO);
        h.setBalanceProfit(BigDecimal.ZERO);
        h.setBalanceProfitFrozen(BigDecimal.ZERO);
        h.setIsBind("N");
        merChantsService.save(h);
        MerChants v = new MerChants();
        v.setMerMp(merMp);
        v.setAppId(appId);
        MerChants t = merChantsService.findByObject(v);
        String s = "M" + snowflakeIdWorker.nextId();
        long id = t.getId() + 10000;
        String merChantId = s + id;
        t.setMerChantId(merChantId);
        merChantsService.update(t);
        return YJResult.ok(merChantId);
    }
}
