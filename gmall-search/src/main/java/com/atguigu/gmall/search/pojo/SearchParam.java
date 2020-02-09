package com.atguigu.gmall.search.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-02-01 17:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParam {

    private String key;

    private Long[] catelog3;

    private Long[] brand;

    private Double priceFrom;

    private Double priceTo;

    private List<String> props;

    private String order;

    private Integer pageNum = 1;

    private final Integer pageSize = 64;

    private Boolean store;


}
