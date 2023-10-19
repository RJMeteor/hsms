package com.primarySchoolPeriod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Office;
import com.primarySchoolPeriod.service.IOfficeService;
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

/*
* 会诊科室管理
* */

@Controller
@RequestMapping("/office")
public class OfficeController {

    @Resource
    private IOfficeService iOfficeService;

    @RequestMapping("show")
    @ResponseBody
    public R<Office> show(@RequestParam(value = "id", required = false) Integer id) {
        Office office = new Office();
        if (!ObjectUtils.isNotEmpty(id)) {
            return R.<Office>builder().code(200).data(office).build();
        }
        office = iOfficeService.getById(id);
        return R.<Office>builder().code(200).data(office).build();
    }



    @RequestMapping("page")
    @ResponseBody
    public R<Page<Office>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Office> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Office> like = new LambdaQueryWrapper<Office>()
                .like(Office::getName,value)
                .orderByDesc(Office::getUpdateTime);
        Page<Office> page1 = iOfficeService.page(objectPage, like);
        return R.<Page<Office>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Office account) {
        LambdaQueryWrapper<Office> eq = new LambdaQueryWrapper<Office>()
                .eq(Office::getId, account.getId());
        iOfficeService.update(account,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Office account) {
        iOfficeService.getBaseMapper().insert(account);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iOfficeService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iOfficeService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    public IOfficeService getiOfficeService() {
        return iOfficeService;
    }
}


