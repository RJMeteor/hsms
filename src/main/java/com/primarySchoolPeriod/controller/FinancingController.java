package com.primarySchoolPeriod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Financing;
import com.primarySchoolPeriod.service.IFinancingService;
import com.primarySchoolPeriod.util.R;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 财务表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Controller
@RequestMapping("financing")
public class FinancingController {

    @Resource
    private IFinancingService iFinancingService;

    @RequestMapping("show")
    @ResponseBody
    public R<Financing> show(@RequestParam(value = "id", required = false) Integer id) {
        Financing financing = new Financing();
        if (ObjectUtils.isNotEmpty(id)) {
            financing = iFinancingService.getById(id);
        }
        iFinancingService.merger(financing);
        return R.<Financing>builder().code(200).data(financing).build();
    }

    @RequestMapping("page")
    @ResponseBody
    public R<Page<Financing>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Financing> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Financing> like = new LambdaQueryWrapper<Financing>()
                .like(Financing::getName,value)
                .orderByDesc(Financing::getUpdateTime);
        Page<Financing> page1 = iFinancingService.page(objectPage, like);
        for (Financing record : page1.getRecords()) {
            iFinancingService.merger(record);
        }
        return R.<Page<Financing>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Financing financing) {
        LambdaQueryWrapper<Financing> eq = new LambdaQueryWrapper<Financing>()
                .eq(Financing::getId, financing.getId());
        iFinancingService.update(financing,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Financing financing) {
        iFinancingService.getBaseMapper().insert(financing);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iFinancingService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iFinancingService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

}

