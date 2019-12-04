package com.yj.bj.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by bin on 2017/12/22.
 */
@Table(name = "acc_count")
public class CountT1Entity implements java.io.Serializable{
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "createTime")
    private String createTime;
    @Column(name = "merchantId")
    private String merchantId;
    @Column(name = "merName")
    private String merName;
    @Column(name = "cardNumber")
    private String cardNumber;
    @Column(name = "fee")
    private Long fee;
    @Column(name = "transactionTime")
    private String transactionTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }
}
