package com.primarySchoolPeriod.service;

import com.primarySchoolPeriod.pojo.Diagnosis;
import com.primarySchoolPeriod.pojo.Interface;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 接口管理 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
public interface IInterfaceService extends IService<Interface> {
    void merger(Interface financing);
}
