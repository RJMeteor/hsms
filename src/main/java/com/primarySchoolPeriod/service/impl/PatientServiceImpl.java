package com.primarySchoolPeriod.service.impl;

import com.primarySchoolPeriod.pojo.Financing;
import com.primarySchoolPeriod.pojo.Patient;
import com.primarySchoolPeriod.mapper.PatientMapper;
import com.primarySchoolPeriod.service.IPatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.primarySchoolPeriod.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 患者管理 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements IPatientService {

    @Resource
    private IUserService iUserService;

    @Override
    public void merger(Patient patient) {
     patient.setUserPojo(iUserService.getById(patient.getUser()));
     patient.setUserList(iUserService.list());
    }
}
