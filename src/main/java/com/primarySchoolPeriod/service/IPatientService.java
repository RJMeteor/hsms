package com.primarySchoolPeriod.service;

import com.primarySchoolPeriod.pojo.Financing;
import com.primarySchoolPeriod.pojo.Patient;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 患者管理 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
public interface IPatientService extends IService<Patient> {
    void merger(Patient patient);
}
