package com.primarySchoolPeriod.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.primarySchoolPeriod.pojo.User;
import com.primarySchoolPeriod.pojo.Vendor;
import com.primarySchoolPeriod.service.IVendorService;
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

/*
* 供应商管理
* */

@Controller
@RequestMapping("/vendor")
public class VendorController {
    @Resource
    private IVendorService iVendorService;

    @RequestMapping("show")
    @ResponseBody
    public R<Vendor> show(@RequestParam(value = "id", required = false) Integer id) {
        Vendor vendor = new Vendor();
        if (ObjectUtils.isNotEmpty(id)) {
            vendor = iVendorService.getById(id);
        }
        return R.<Vendor>builder().code(200).data(vendor).build();
    }

    @RequestMapping("page")
    @ResponseBody
    public R<Page<Vendor>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Vendor> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Vendor> like = new LambdaQueryWrapper<Vendor>()
                .like(Vendor::getName,value)
                .orderByDesc(Vendor::getUpdateTime);
        Page<Vendor> page1 = iVendorService.page(objectPage, like);
        return R.<Page<Vendor>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Vendor vendor) {
        LambdaQueryWrapper<Vendor> eq = new LambdaQueryWrapper<Vendor>()
                .eq(Vendor::getId, vendor.getId());
        iVendorService.update(vendor,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Vendor vendor) {
        iVendorService.getBaseMapper().insert(vendor);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iVendorService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iVendorService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

}

