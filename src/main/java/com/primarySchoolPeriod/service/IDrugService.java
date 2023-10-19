package com.primarySchoolPeriod.service;

import com.primarySchoolPeriod.pojo.Drug;
import com.baomidou.mybatisplus.extension.service.IService;
import com.primarySchoolPeriod.pojo.Financing;

/**
 * <p>
 * 药品管理表 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
public interface IDrugService extends IService<Drug> {
    void merger(Drug drug);
}
