package com.primarySchoolPeriod.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Account;
import com.primarySchoolPeriod.service.IAccountService;
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

@Controller
@RequestMapping("account")
public class AccountController {

    @Resource
    private IAccountService iAccountService;

    @RequestMapping("show")
    @ResponseBody
    public R<Account> show(@RequestParam(value = "id", required = false) Integer id) {
        Account account = new Account();
        if (!ObjectUtils.isNotEmpty(id)) {
            return R.<Account>builder().code(200).data(account).build();
        }
        account = iAccountService.getById(id);
        return R.<Account>builder().code(200).data(account).build();
    }

    @RequestMapping("page")
    @ResponseBody
    public R<Page<Account>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Account> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Account> like = new LambdaQueryWrapper<Account>()
                .like(Account::getName,value)
                .orderByDesc(Account::getUpdateTime);
        Page<Account> page1 = iAccountService.page(objectPage, like);
        return R.<Page<Account>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Account account) {
        LambdaQueryWrapper<Account> eq = new LambdaQueryWrapper<Account>()
                .eq(Account::getId, account.getId());
        iAccountService.update(account,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Account account) {
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
