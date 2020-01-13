package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.ProductAttrValueEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author shkstart
 * @data 2020/1/4-21:39
 */
public class BaseAttrValueVO extends ProductAttrValueEntity {


    public void setValueSelected(List<String> valueSelected) {
        if (!CollectionUtils.isEmpty(valueSelected)){

            setAttrValue(StringUtils.join(valueSelected,","));
        }else {
            setAttrValue(null);
        }
    }
}
