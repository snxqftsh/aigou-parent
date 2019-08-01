package cn.itsource.common.controller;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.basic.util.RedisUtils;
import cn.itsource.common.client.RedisClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController implements RedisClient{

    /**
     * 缓存数据
     * @param key
     * @param value
     * @return
     */
    @PostMapping("/redis")
    public AjaxResult set(@RequestParam("key") String key, @RequestParam("value")String value) {
        try {
            //添加缓存
            RedisUtils.INSTANCE.set(key,value);
            return AjaxResult.getAjaxResult().setSuccess(true).setMsg("添加缓存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(true).setMsg("添加缓存失败："+e.getMessage());
        }
    }

    /**
     * 获取缓存数据
     * @param key
     * @return
     */
    @GetMapping("/redis")
    public AjaxResult get(@RequestParam("key")String key) {
        try {
            //获取缓存
            RedisUtils.INSTANCE.get(key);
            return AjaxResult.getAjaxResult().setSuccess(true).setMsg("获取缓存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(true).setMsg("获取缓存失败："+e.getMessage());
        }
    }
}
