package com.primarySchoolPeriod.service;

import com.primarySchoolPeriod.pojo.Diagnosis;
import com.baomidou.mybatisplus.extension.service.IService;
import com.primarySchoolPeriod.pojo.Financing;

/**
 * <p>
 * 诊断信息表 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
public interface IDiagnosisService extends IService<Diagnosis> {
    void merger(Diagnosis financing);
}
