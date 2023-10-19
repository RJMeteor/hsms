package com.primarySchoolPeriod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Job;
import com.primarySchoolPeriod.pojo.Patient;
import com.primarySchoolPeriod.service.IJobService;
import com.primarySchoolPeriod.service.IPatientService;
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
 * 职位管理 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Controller
@RequestMapping("/job")
public class JobController {

    @Resource
    private IJobService iJobService;

    @RequestMapping("show")
    @ResponseBody
    public R<Job> show(@RequestParam(value = "id", required = false) Integer id) {
        Job job = new Job();
        if (!ObjectUtils.isNotEmpty(id)) {
            return R.<Job>builder().code(200).data(job).build();
        }
        job = iJobService.getById(id);
        return R.<Job>builder().code(200).data(job).build();
    }



    @RequestMapping("page")
    @ResponseBody
    public R<Page<Job>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Job> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Job> like = new LambdaQueryWrapper<Job>()
                .like(Job::getName,value)
                .orderByDesc(Job::getUpdateTime);
        Page<Job> page1 = iJobService.page(objectPage, like);
        return R.<Page<Job>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Job account) {
        LambdaQueryWrapper<Job> eq = new LambdaQueryWrapper<Job>()
                .eq(Job::getId, account.getId());
        iJobService.update(account,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Job account) {
        iJobService.getBaseMapper().insert(account);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iJobService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iJobService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

}


