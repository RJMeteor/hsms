package com.primarySchoolPeriod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Role;
import com.primarySchoolPeriod.service.IRoleService;
import com.primarySchoolPeriod.util.R;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色管理表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService iRoleService;

    @RequestMapping("show")
    @ResponseBody
    public R<Role> show(@RequestParam(value = "id", required = false) Integer id) {
        Role role = new Role();
        if (!ObjectUtils.isNotEmpty(id)) {
            return R.<Role>builder().code(200).data(role).build();
        }
        role = iRoleService.getById(id);
        return R.<Role>builder().code(200).data(role).build();
    }

    @RequestMapping("page")
    @ResponseBody
    public R<Page<Role>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Role> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Role> like = new LambdaQueryWrapper<Role>()
                .like(Role::getName,value)
                .orderByDesc(Role::getUpdateTime);
        Page<Role> page1 = iRoleService.page(objectPage, like);
        return R.<Page<Role>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Role role) {
        LambdaQueryWrapper<Role> eq = new LambdaQueryWrapper<Role>()
                .eq(Role::getId, role.getId());
        iRoleService.update(role,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Role role) {
        iRoleService.getBaseMapper().insert(role);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iRoleService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iRoleService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

}

