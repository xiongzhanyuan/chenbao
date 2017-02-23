package com.xzy.chenbao.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.xzy.chenbao.business.service.UserInfoService;
import com.xzy.cm.mvc.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserInfoController extends BaseController{
    @Autowired
    @Qualifier(value = "service/user/operate")
    private UserInfoService userInfoService;

    @RequestMapping(value = "/user/fetch")
    @ResponseBody
    public JSONObject userInfo() throws Exception{
        return userInfoService.selectList();
    }

    @RequestMapping(value = "/user/get_info/{id}")
    @ResponseBody
    public JSONObject getInfo(@PathVariable String id) throws Exception{
        return userInfoService.getInfo(id);
    }

    @RequestMapping(value = "/user/insert")
    @ResponseBody
    public JSONObject insert() throws Exception{
        return userInfoService.insert();
    }

}
