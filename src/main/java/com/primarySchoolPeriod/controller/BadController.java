package com.primarySchoolPeriod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Account;
import com.primarySchoolPeriod.pojo.Bad;
import com.primarySchoolPeriod.service.IAccountService;
import com.primarySchoolPeriod.service.IBadService;
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
 * 住院床表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Controller
@RequestMapping("bad")
public class BadController {

    @Resource
    private IBadService iBadService;

    @RequestMapping("show")
    @ResponseBody
    public R<Bad> show(@RequestParam(value = "id", required = false) Integer id) {
        Bad account = new Bad();
        if (!ObjectUtils.isNotEmpty(id)) {
            return R.<Bad>builder().code(200).data(account).build();
        }
        account = iBadService.getById(id);
        return R.<Bad>builder().code(200).data(account).build();
    }

    @RequestMapping("page")
    @ResponseBody
    public R<Page<Bad>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Bad> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Bad> like = new LambdaQueryWrapper<Bad>()
                .like(Bad::getName,value)
                .orderByDesc(Bad::getUpdateTime);
        Page<Bad> page1 = iBadService.page(objectPage, like);
        return R.<Page<Bad>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Bad account) {
        LambdaQueryWrapper<Bad> eq = new LambdaQueryWrapper<Bad>()
                .eq(Bad::getId, account.getId());
        iBadService.update(account,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Bad account) {
        iBadService.getBaseMapper().insert(account);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iBadService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iBadService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

}

