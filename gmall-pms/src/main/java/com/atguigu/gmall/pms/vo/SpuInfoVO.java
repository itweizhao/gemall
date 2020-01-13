package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.SpuInfoEntity;
import lombok.Data;

import java.util.List;

/**
 * @author shkstart
 * @data 2020/1/4-21:47
 */
@Data
public class SpuInfoVO extends SpuInfoEntity {
    private List<String> spuImages;//图片信息

    private List<BaseAttrValueVO> baseAttrs;//基本参数规格

    private List<SkuInfoVO> skus;//sku信息
}
