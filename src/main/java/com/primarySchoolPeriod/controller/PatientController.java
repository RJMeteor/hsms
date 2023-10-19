package com.primarySchoolPeriod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Account;
import com.primarySchoolPeriod.pojo.Patient;
import com.primarySchoolPeriod.service.IPatientService;
import com.primarySchoolPeriod.service.impl.PatientServiceImpl;
import com.primarySchoolPeriod.util.R;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * <p>
 * 患者管理 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */

/*
* 1: pojo vue名相同的类
* 2：确定有pojo和list结尾的属性要在类相关联service进行实现接口中merge方法
* 3：后端标红的地方都要改
* */
@Controller
@RequestMapping("/patient")
public class PatientController {

    @Resource
    private IPatientService iPatientService;

    @RequestMapping("show")
    @ResponseBody
    public R<Patient> show(@RequestParam(value = "id", required = false) Integer id) {
        Patient patient = new Patient();
        if (ObjectUtils.isNotEmpty(id)) {
            patient = iPatientService.getById(id);
        }
        iPatientService.merger(patient);
        return R.<Patient>builder().code(200).data(patient).build();
    }



    @RequestMapping("page")
    @ResponseBody
    public R<Page<Patient>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Patient> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Patient> like = new LambdaQueryWrapper<Patient>()
                .like(Patient::getName,value)
                .orderByDesc(Patient::getUpdateTime);
        Page<Patient> page1 = iPatientService.page(objectPage, like);
        for (Patient record : page1.getRecords()) {
            iPatientService.merger(record);
        }
        return R.<Page<Patient>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Patient account) {
        LambdaQueryWrapper<Patient> eq = new LambdaQueryWrapper<Patient>()
                .eq(Patient::getId, account.getId());
        iPatientService.update(account,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Patient account) {
        iPatientService.getBaseMapper().insert(account);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iPatientService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iPatientService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

}

