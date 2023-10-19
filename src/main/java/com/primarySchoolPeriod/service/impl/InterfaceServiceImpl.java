package com.primarySchoolPeriod.service.impl;

import com.primarySchoolPeriod.pojo.Interface;
import com.primarySchoolPeriod.mapper.InterfaceMapper;
import com.primarySchoolPeriod.service.IInterfaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.primarySchoolPeriod.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 接口管理 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Service
public class InterfaceServiceImpl extends ServiceImpl<InterfaceMapper, Interface> implements IInterfaceService {
    @Resource
    private IRoleService iRoleService;

    @Override
    public void merger(Interface financing) {
        financing.setRolePojo(iRoleService.getById(financing.getRole()));
        financing.setRoleList(iRoleService.list());
    }
}
