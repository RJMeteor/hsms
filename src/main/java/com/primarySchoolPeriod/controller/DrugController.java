package com.primarySchoolPeriod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Account;
import com.primarySchoolPeriod.pojo.Drug;
import com.primarySchoolPeriod.pojo.Patient;
import com.primarySchoolPeriod.service.IAccountService;
import com.primarySchoolPeriod.service.IDrugService;
import com.primarySchoolPeriod.service.IPatientService;
import com.primarySchoolPeriod.util.R;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
* 药品管理
* */

@Controller
@RequestMapping("/drug")
public class DrugController {

    @Resource
    private IDrugService iDrugService;

    @RequestMapping("show")
    @ResponseBody
    public R<Drug> show(@RequestParam(value = "id", required = false) Integer id) {
        Drug drug = new Drug();
        if (ObjectUtils.isNotEmpty(id)) {
            drug = iDrugService.getById(id);
        }
        iDrugService.merger(drug);
        return R.<Drug>builder().code(200).data(drug).build();
    }



    @RequestMapping("page")
    @ResponseBody
    public R<Page<Drug>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Drug> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Drug> like = new LambdaQueryWrapper<Drug>()
                .like(Drug::getName,value)
                .orderByDesc(Drug::getUpdateTime);
        Page<Drug> page1 = iDrugService.page(objectPage, like);
        for (Drug record : page1.getRecords()) {
            iDrugService.merger(record);
        }
        return R.<Page<Drug>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Drug account) {
        LambdaQueryWrapper<Drug> eq = new LambdaQueryWrapper<Drug>()
                .eq(Drug::getId, account.getId());
        iDrugService.update(account,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Drug account) {
        iDrugService.getBaseMapper().insert(account);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iDrugService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iDrugService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    public IDrugService getiDrugService() {
        return iDrugService;
    }
}

