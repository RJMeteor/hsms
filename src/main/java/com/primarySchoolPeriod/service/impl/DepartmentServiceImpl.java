package com.primarySchoolPeriod.service.impl;

import com.primarySchoolPeriod.pojo.Department;
import com.primarySchoolPeriod.mapper.DepartmentMapper;
import com.primarySchoolPeriod.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
