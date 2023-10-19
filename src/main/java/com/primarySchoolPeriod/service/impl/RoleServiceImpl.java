package com.primarySchoolPeriod.service.impl;

import com.primarySchoolPeriod.pojo.Role;
import com.primarySchoolPeriod.mapper.RoleMapper;
import com.primarySchoolPeriod.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色管理表 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
