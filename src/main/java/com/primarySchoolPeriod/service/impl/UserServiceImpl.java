package com.primarySchoolPeriod.service.impl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.primarySchoolPeriod.pojo.*;
import com.primarySchoolPeriod.mapper.UserMapper;
import com.primarySchoolPeriod.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private IDepartmentService iDepartmentService;

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private IJobService iJobService;

    @Autowired
    private IInterfaceService iInterfaceService;

    @Override
    public void     merger(User user) {
        user.setDepartmentPojo(iDepartmentService.getById(user.getDepartment()));
        user.setJobPojo(iJobService.getById(user.getJob()));
        user.setRolePojo(iRoleService.getById(user.getRole()));
        user.setRoleList(iRoleService.list());
        user.setJobList(iJobService.list());
        user.setDepartmentList(iDepartmentService.list());
        LambdaQueryWrapper<Interface> interfaceLambdaQueryWrapper = new LambdaQueryWrapper<>();
        interfaceLambdaQueryWrapper.eq(Interface::getRole,user.getRole());
        user.setInterfaceo(iInterfaceService.list(interfaceLambdaQueryWrapper));
    }
}
