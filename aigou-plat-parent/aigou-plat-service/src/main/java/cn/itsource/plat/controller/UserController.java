package cn.itsource.plat.controller;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.plat.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/login")//写法等同于：@RequestMapping(value = "/login",method = RequestMethod.POST)
    //@RequestBody 接收json体形式的请求参数封装到实体类对象中
    public AjaxResult login(@RequestBody User user){
        //判断登录
        if("admin".equals(user.getUsername())&&"123456".equals(user.getPassword())){
            return AjaxResult.getAjaxResult().setSuccess(true).setMsg("登录成功!").setRestObj(user);
        }
        return AjaxResult.getAjaxResult().setSuccess(false).setMsg("登录失败！");
    }

}
