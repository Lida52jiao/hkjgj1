package com.yj.bj.task;

/**
 * Created by bin on 2018/1/25.
 */
public enum BankCode {
    //BCCB("北京银行","BCCB","2"),
    ICBC("工商银行","ICBC","102100099996"), ABC("农业银行","ABC","103100000026"), BOC("中国银行","BOC","104100000004"), CCB("建设银行","CCB","105100000017"),
    CMBCHINA("招商银行","CMBCHINA","308584000013"), POST("邮政储蓄","POST","403100000004"), ECITIC("中信银行","ECITIC","302100011000"), CEB("光大银行","CEB","303100000006"),
    BOCO("交通银行","BOCO","301290000007"), CIB("兴业银行","CIB","309391000011"), CMBC("民生银行","CMBC","305100000013"), PINGAN("平安银行","PINGAN","307584007998"),
    CGB("广发银行","CGB","306331003281"),  HXB("华夏银行","HXB","304100040000"), SPDB("浦发银行","SPDB","310290000013"),
    SHB("上海银行","SHB","325290000012"), CBHB("渤海银行","CBHB","104100000004"), JSB("江苏银行","JSB","104100000004");
    private String name;
    private String yjCode;
    private String easyCode;

    public static String getEasyCode(String yjCode){
        for(BankCode bankCode: BankCode.values()){
            if(bankCode.getYjCode().equals(yjCode)){
                return bankCode.getEasyCode();
            }
        }
        return "";
    }
    public static String getName(String yjCode){
        for(BankCode bankCode: BankCode.values()){
            if(bankCode.getYjCode().equals(yjCode)){
                return bankCode.getName();
            }
        }
        return "";
    }

    private BankCode(String name, String yjCode, String easyCode){
        this.name=name;
        this.yjCode=yjCode;
        this.easyCode=easyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYjCode() {
        return yjCode;
    }

    public void setYjCode(String yjCode) {
        this.yjCode = yjCode;
    }

    public String getEasyCode() {
        return easyCode;
    }

    public void setEasyCode(String easyCode) {
        this.easyCode = easyCode;
    }
}
