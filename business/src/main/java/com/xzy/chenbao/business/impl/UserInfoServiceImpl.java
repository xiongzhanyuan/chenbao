package com.xzy.chenbao.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzy.chenbao.business.domain.UserInfo;
import com.xzy.chenbao.business.mapper.UserInfoMapper;
import com.xzy.chenbao.business.service.UserInfoService;
import com.xzy.cm.common.helper.JOHelper;
import com.xzy.cm.common.helper.UUIDHelper;
import com.xzy.cm.nosql.NosqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiongzhanyuan on 2017/2/21.
 */
@Service(value = "service/user/operate")
public class UserInfoServiceImpl implements UserInfoService {

    public static final String USER_INFO = "_user_";

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    @Qualifier(value = "service/nosql/redis")
    private NosqlService nosqlService;

    @Override
    public JSONObject getInfo(String id) throws Exception {

        JSONObject jsonObject = JSON.parseObject(nosqlService.get(USER_INFO + id), JSONObject.class);
        UserInfo userInfo = null;
        if (!ObjectUtils.isEmpty(jsonObject)) {
            userInfo = JOHelper.jo2class(jsonObject, UserInfo.class);
        }

        if (ObjectUtils.isEmpty(userInfo)) {
            userInfo = userInfoMapper.selectByPrimaryKey(id);
        }
        nosqlService.save(USER_INFO + id, JOHelper.obj2Json(userInfo), 30, TimeUnit.MINUTES);

        return JOHelper.obj2Json(userInfo);
    }

    @Override
    public JSONObject selectList() throws Exception {
        PageHelper.startPage(1, 2); // 核心分页代码
        Page<UserInfo> page =  userInfoMapper.selectList();

        JSONObject result = new JSONObject();
        result.put("list", page.getResult());
        result.put("totalCount", page.getTotal());
        result.put("pageSize", page.getPageSize());
        return result;
    }

    @Override
    public JSONObject insert() throws Exception {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(UUIDHelper.getUUID());
        userInfo.setUserName("xiongzhanyuan");
        userInfo.setPassword("000000");
        userInfo.setCrtTs(new Date());
        userInfo.setUpdateTs(new Date());
        userInfoMapper.insert(userInfo);

        JSONObject result = new JSONObject();
        result.put("data", "添加成功");
        result.put("status", 1);
        return result;
    }
}
