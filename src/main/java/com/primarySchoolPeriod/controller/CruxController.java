package com.primarySchoolPeriod.controller;

import com.alibaba.fastjson2.util.BeanUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.primarySchoolPeriod.filter.WebFilter;
import com.primarySchoolPeriod.pojo.Account;
import com.primarySchoolPeriod.pojo.Login;
import com.primarySchoolPeriod.pojo.User;
import com.primarySchoolPeriod.service.IUserService;
import com.primarySchoolPeriod.util.JWTUtil;
import com.primarySchoolPeriod.util.R;
import com.primarySchoolPeriod.util.exceptions.GrantException;
import com.primarySchoolPeriod.util.exceptions.LogingException;
import com.primarySchoolPeriod.util.interfaces.FilterInterface;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("crux")
public class CruxController extends FilterInterface {


    @Resource
    private IUserService iUserService;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping("login")
    @ResponseBody
    public R request(@RequestBody Login login) throws LoginException, GrantException {
        if (StringUtils.isEmpty(login.getPassword()) || StringUtils.isEmpty(login.getPhone())) {
            throw new GrantException();
        }
        User user = new User();
        user.setPhone(login.getPhone());
        user.setPassword(DigestUtils.md5Hex(login.getPassword() == null ? "" : login.getPassword()));
        QueryWrapper<User> objectLambdaQueryWrapper = new QueryWrapper<>();
        objectLambdaQueryWrapper.eq("phone", user.getPhone()).eq("password", login.getPassword());
        User one = iUserService.getOne(objectLambdaQueryWrapper);
        if (one == null) {
            throw new LoginException();
        }
        iUserService.merger(one);
        String encodeKey = new StringBuffer(user.getPhone()).append(user.getPassword()).toString();
        String encode = jwtUtil.encodeToken(encodeKey);
        userToken.remove(encodeKey);
        userToken.put(encodeKey, encode);
        one.setToken(encode);
        one.setUserId(encodeKey);
        return R.<User>builder().code(200).data(one).build();

    }

    @RequestMapping("logout")
    @ResponseBody
    public R logout(@RequestBody Login login) {
        if (StringUtils.isEmpty(login.getPassword()) || StringUtils.isEmpty(login.getPhone())) {
            return R.<String>builder().code(500).data("退出登录失败").build();
        }
        User user = new User();
        user.setPhone(login.getPhone());
        user.setPassword(DigestUtils.md5Hex(login.getPassword()));
        String encode = new StringBuffer(user.getPhone()).append(user.getPassword()).toString();
        if (!WebFilter.userToken.containsKey(encode)) {
            return R.<String>builder().code(500).data("退出登录失败").build();
        }

        WebFilter.userToken.remove(encode);

        return R.<String>builder().code(200).data("退出登录成功").build();
    }

}
