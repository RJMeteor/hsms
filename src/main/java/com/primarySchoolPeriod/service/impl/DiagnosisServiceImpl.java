package com.primarySchoolPeriod.service.impl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.primarySchoolPeriod.pojo.*;
import com.primarySchoolPeriod.mapper.DiagnosisMapper;
import com.primarySchoolPeriod.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 诊断信息表 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Service
public class DiagnosisServiceImpl extends ServiceImpl<DiagnosisMapper, Diagnosis> implements IDiagnosisService {

    @Resource
    private IBadService iBadService;
    @Resource
    private IPatientService iPatientService;
    @Resource
    private IOfficeService iOfficeService;
    @Resource
    private IDrugService iDrugService;

    @Override
    public void merger(Diagnosis financing) {
        LambdaQueryWrapper<Bad> badQueryWrapper = new LambdaQueryWrapper<Bad>();
        badQueryWrapper.eq(Bad::getStatus,1).eq(Bad::getId,financing.getId());
        financing.setBadPojo(iBadService.getOne(badQueryWrapper));
        financing.setPatientPojo(iPatientService.getById(financing.getPatient()));
        financing.setOfficePojo(iOfficeService.getById(financing.getOffice()));
        financing.setDrugPojo(iDrugService.getById(financing.getDrug()));
        financing.setBadList(iBadService.list());
        financing.setPatientList(iPatientService.list());
        financing.setOfficeList(iOfficeService.list());
        financing.setDrugList(iDrugService.list());
    }
}
