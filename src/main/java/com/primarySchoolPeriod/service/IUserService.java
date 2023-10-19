package com.primarySchoolPeriod.service;

import com.primarySchoolPeriod.pojo.Financing;
import com.primarySchoolPeriod.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户管理 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
public interface IUserService extends IService<User> {
    void merger(User user);
}
