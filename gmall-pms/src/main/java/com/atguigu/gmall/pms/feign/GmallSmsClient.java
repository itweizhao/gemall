package com.atguigu.gmall.pms.feign;

import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @data 2020/1/5-10:28
 */
@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {


}
