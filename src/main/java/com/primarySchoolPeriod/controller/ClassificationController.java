package com.primarySchoolPeriod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Attendance;
import com.primarySchoolPeriod.pojo.Classification;
import com.primarySchoolPeriod.service.IAttendanceService;
import com.primarySchoolPeriod.service.IClassificationService;
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
 * 药品分类表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Controller
@RequestMapping("classification")
public class ClassificationController {
    @Resource
    private IClassificationService iClassificationService;

    @RequestMapping("show")
    @ResponseBody
    public R<Classification> show(@RequestParam(value = "id", required = false) Integer id) {
        Classification classification = new Classification();
        if (!ObjectUtils.isNotEmpty(id)) {
            return R.<Classification>builder().code(200).data(classification).build();
        }
        classification = iClassificationService.getById(id);
        return R.<Classification>builder().code(200).data(classification).build();
    }

    @RequestMapping("page")
    @ResponseBody
    public R<Page<Classification>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Classification> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Classification> like = new LambdaQueryWrapper<Classification>()
                .like(Classification::getName,value)
                .orderByDesc(Classification::getUpdateTime);
        Page<Classification> page1 = iClassificationService.page(objectPage, like);
        return R.<Page<Classification>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Classification classification) {
        LambdaQueryWrapper<Classification> eq = new LambdaQueryWrapper<Classification>()
                .eq(Classification::getId, classification.getId());
        iClassificationService.update(classification,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Classification classification) {
        iClassificationService.getBaseMapper().insert(classification);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iClassificationService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iClassificationService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

}

