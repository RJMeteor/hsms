package com.primarySchoolPeriod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Interface;
import com.primarySchoolPeriod.service.IInterfaceService;
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

import static io.swagger.models.properties.PropertyBuilder.build;

/**
 * <p>
 * 接口管理 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Controller
@RequestMapping("/interface")
public class InterfaceController {
    @Resource
    private IInterfaceService iInterfaceService;

    @RequestMapping("show")
    @ResponseBody
    public R<Interface> show(@RequestParam(value = "id", required = false) Integer id) {
        Interface interface1 = new Interface();
        if (ObjectUtils.isNotEmpty(id)) {
            interface1 = iInterfaceService.getById(id);
        }
        iInterfaceService.merger(interface1);
        return R.<Interface>builder().code(200).data(interface1).build();
    }

    @RequestMapping("page")
    @ResponseBody
    public R<Page<Interface>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Interface> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Interface> like = new LambdaQueryWrapper<Interface>()
                .like(Interface::getPathName,value)
                .orderByDesc(Interface::getUpdateTime);
        Page<Interface> page1 = iInterfaceService.page(objectPage, like);
        for (Interface record : page1.getRecords()) {
            iInterfaceService.merger(record);
        }
        return R.<Page<Interface>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Interface interface1) {
        LambdaQueryWrapper<Interface> eq = new LambdaQueryWrapper<Interface>()
                .eq(Interface::getId, interface1.getId());
        iInterfaceService.update(interface1,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Interface interface1) {
        iInterfaceService.getBaseMapper().insert(interface1);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iInterfaceService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iInterfaceService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

}

