package cn.itsource.common.client;

import cn.itsource.basic.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

//feign的回调
@Component
public class RedisFallBackFactory implements FallbackFactory<RedisClient> {
    @Override
    public RedisClient create(Throwable throwable) {
        return new RedisClient() {
            @Override
            public AjaxResult set(String key, String value) {
                return AjaxResult.getAjaxResult().setSuccess(false).setMsg("系统异常!");
            }

            @Override
            public AjaxResult get(String key) {
                return AjaxResult.getAjaxResult().setSuccess(false).setMsg("系统异常!");
            }
        };
    }
}
