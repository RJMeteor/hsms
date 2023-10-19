package com.primarySchoolPeriod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Diagnosis;
import com.primarySchoolPeriod.service.IDiagnosisService;
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
 * 诊断信息表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Controller
@RequestMapping("diagnosis")
public class DiagnosisController {
    @Resource
    private IDiagnosisService iAccountService;

    @RequestMapping("show")
    @ResponseBody
    public R<Diagnosis> show(@RequestParam(value = "id", required = false) Integer id) {
        Diagnosis account = new Diagnosis();
        if (ObjectUtils.isNotEmpty(id)) {
            account = iAccountService.getById(id);
        }
        iAccountService.merger(account);
        return R.<Diagnosis>builder().code(200).data(account).build();
    }

    @RequestMapping("page")
    @ResponseBody
    public R<Page<Diagnosis>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Diagnosis> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Diagnosis> like = new LambdaQueryWrapper<Diagnosis>()
                .like(Diagnosis::getName,value)
                .orderByDesc(Diagnosis::getUpdateTime);
        Page<Diagnosis> page1 = iAccountService.page(objectPage, like);
        for (Diagnosis record : page1.getRecords()) {
            iAccountService.merger(record);
        }
        return R.<Page<Diagnosis>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Diagnosis account) {
        LambdaQueryWrapper<Diagnosis> eq = new LambdaQueryWrapper<Diagnosis>()
                .eq(Diagnosis::getId, account.getId());
        iAccountService.update(account,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Diagnosis account) {
        iAccountService.getBaseMapper().insert(account);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iAccountService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iAccountService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

}


