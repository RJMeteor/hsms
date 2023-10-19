package com.primarySchoolPeriod.service.impl;

import com.primarySchoolPeriod.pojo.Drug;
import com.primarySchoolPeriod.mapper.DrugMapper;
import com.primarySchoolPeriod.service.IClassificationService;
import com.primarySchoolPeriod.service.IDrugService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.primarySchoolPeriod.service.IVendorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 药品管理表 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Service
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug> implements IDrugService {

    @Resource
    private IClassificationService iClassificationService;
    @Resource
    private IVendorService iVendorService;

    @Override
    public void merger(Drug drug) {
        drug.setClassificationPojo(iClassificationService.getById(drug.getClassification()));
        drug.setVendorPojo(iVendorService.getById(drug.getVendor()));
        drug.setVendorList(iVendorService.list());
        drug.setClassificationList(iClassificationService.list());
    }
}
