package com.primarySchoolPeriod.service.impl;

import com.primarySchoolPeriod.pojo.Financing;
import com.primarySchoolPeriod.mapper.FinancingMapper;
import com.primarySchoolPeriod.service.IAccountService;
import com.primarySchoolPeriod.service.IDepartmentService;
import com.primarySchoolPeriod.service.IFinancingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 财务表 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Service
public class FinancingServiceImpl extends ServiceImpl<FinancingMapper, Financing> implements IFinancingService {
    @Resource
    private IDepartmentService iDepartmentService;

    @Resource
    private IAccountService iAccountService;

    @Override
    public void merger(Financing financing) {
        financing.setDepartmentPojo(iDepartmentService.getById(financing.getDepartment()));
        financing.setAccountPojo(iAccountService.getById(financing.getAccount()));
        financing.setAccountList(iAccountService.list());
        financing.setDepartmentList(iDepartmentService.list());
    }
}
