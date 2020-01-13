package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.SkuInfoEntity;
import com.atguigu.gmall.pms.entity.SkuSaleAttrValueEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author shkstart
 * @data 2020/1/4-22:05
 */
@Data
public class SkuInfoVO extends SkuInfoEntity {

    private BigDecimal growBounds;
    private BigDecimal buyBounds;
    private List<String> work;

    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer fullAddOther;

    private Integer fullCount;
    private BigDecimal discount;
    private Integer ladderAddOther;

    private List<SkuSaleAttrValueEntity> saleAttrs;

    private List<String> images;

}
