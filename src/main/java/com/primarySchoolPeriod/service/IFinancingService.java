package com.primarySchoolPeriod.service;

import com.primarySchoolPeriod.pojo.Financing;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.annotation.Resource;

/**
 * <p>
 * 财务表 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
public interface IFinancingService extends IService<Financing> {
    void merger(Financing financing);
}
