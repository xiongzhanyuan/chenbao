package com.xzy.chenbao.business.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzy.chenbao.business.domain.UserInfo;
import com.xzy.chenbao.business.mapper.UserInfoMapper;
import com.xzy.cm.common.exception.BussinessException;
import com.xzy.cm.common.exception.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by dell on 2016/10/10.
 */
public interface UserInfoService {

    public JSONObject getInfo(String id) throws Exception;

    public JSONObject selectList() throws Exception;

    public JSONObject insert() throws Exception;

}
