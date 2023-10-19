package com.primarySchoolPeriod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Account;
import com.primarySchoolPeriod.pojo.Department;
import com.primarySchoolPeriod.service.IDepartmentService;
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
 * 部门管理 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private IDepartmentService iDepartmentService;

    @RequestMapping("show")
    @ResponseBody
    public R<Department> show(@RequestParam(value = "id", required = false) Integer id) {
        Department department = new Department();
        if (!ObjectUtils.isNotEmpty(id)) {
            return R.<Department>builder().code(200).data(department).build();
        }
        department = iDepartmentService.getById(id);
        return R.<Department>builder().code(200).data(department).build();
    }

    @RequestMapping("page")
    @ResponseBody
    public R<Page<Department>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Department> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Department> like = new LambdaQueryWrapper<Department>()
                .like(Department::getName,value)
                .orderByDesc(Department::getUpdateTime);
        Page<Department> page1 = iDepartmentService.page(objectPage, like);
        return R.<Page<Department>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Department department) {
        LambdaQueryWrapper<Department> eq = new LambdaQueryWrapper<Department>()
                .eq(Department::getId, department.getId());
        iDepartmentService.update(department,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Department department) {
        iDepartmentService.getBaseMapper().insert(department);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iDepartmentService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iDepartmentService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

}

