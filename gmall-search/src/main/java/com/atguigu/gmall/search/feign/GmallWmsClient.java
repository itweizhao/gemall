package com.atguigu.gmall.search.feign;

import com.atguigu.gmall.wms.api.GmallWmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2020-01-30 13:24
 */
@FeignClient("wms-service")
public interface GmallWmsClient extends GmallWmsApi {
}
