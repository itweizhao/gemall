package com.atguigu.gmall.sms.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author shkstart
 * @data 2020/1/5-10:09
 */
@Data
public class SaleVO {


    private long skuId;

    private BigDecimal growBounds;
    private BigDecimal buyBounds;
    private List<String> work;

    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer fullAddOther;

    private Integer fullCount;
    private BigDecimal discount;
    private Integer ladderAddOther;
}
