package com.primarySchoolPeriod.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Financing;
import com.primarySchoolPeriod.pojo.User;
import com.primarySchoolPeriod.service.IUserService;
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


@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService iUserService;

    @RequestMapping("show")
    @ResponseBody
    public R<User> show(@RequestParam(value = "id", required = false) Integer id) {
        User user = new User();
        if (ObjectUtils.isNotEmpty(id)) {
            user = iUserService.getById(id);
        }
        iUserService.merger(user);
        return R.<User>builder().code(200).data(user).build();
    }

    @RequestMapping("page")
    @ResponseBody
    public R<Page<User>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<User> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<User> like = new LambdaQueryWrapper<User>()
                .like(User::getName,value)
                .orderByDesc(User::getUpdateTime);
        Page<User> page1 = iUserService.page(objectPage, like);
        for (User record : page1.getRecords()) {
            iUserService.merger(record);
        }
        return R.<Page<User>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated User user) {
        LambdaQueryWrapper<User> eq = new LambdaQueryWrapper<User>()
                .eq(User::getId, user.getId());
        iUserService.update(user,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated User user) {
        user.setPassword("123456");
        iUserService.getBaseMapper().insert(user);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iUserService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iUserService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }
}

