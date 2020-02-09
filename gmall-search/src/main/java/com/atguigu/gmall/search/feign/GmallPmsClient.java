package com.atguigu.gmall.search.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2020-01-30 13:24
 */
@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {
}
