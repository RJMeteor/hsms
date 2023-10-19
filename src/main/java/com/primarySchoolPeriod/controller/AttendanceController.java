package com.primarySchoolPeriod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.primarySchoolPeriod.pojo.Attendance;
import com.primarySchoolPeriod.pojo.Financing;
import com.primarySchoolPeriod.service.IAttendanceService;
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
 * 考勤管理表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    @Resource
    private IAttendanceService iAttendanceService;

    @RequestMapping("show")
    @ResponseBody
    public R<Attendance> show(@RequestParam(value = "id", required = false) Integer id) {
        Attendance attendance = new Attendance();
        if (ObjectUtils.isNotEmpty(id)) {
            attendance = iAttendanceService.getById(id);
        }
        iAttendanceService.merger(attendance);
        return R.<Attendance>builder().code(200).data(attendance).build();
    }

    @RequestMapping("page")
    @ResponseBody
    public R<Page<Attendance>> page(@RequestParam("size")Integer size, @RequestParam("current")Integer current, @RequestParam("selectValue")String value) {
        Page<Attendance> objectPage = new Page<>(current,size);
        LambdaQueryWrapper<Attendance> like = new LambdaQueryWrapper<Attendance>()
                .like(Attendance::getName,value)
                .orderByDesc(Attendance::getUpdateTime);
        Page<Attendance> page1 = iAttendanceService.page(objectPage, like);
        for (Attendance record : page1.getRecords()) {
            iAttendanceService.merger(record);
        }
        return R.<Page<Attendance>>builder().data(page1).code(200).build();
    }

    @RequestMapping("edit")
    @ResponseBody
    public R<String> edit(@RequestBody @Validated Attendance attendance) {
        LambdaQueryWrapper<Attendance> eq = new LambdaQueryWrapper<Attendance>()
                .eq(Attendance::getId, attendance.getId());
        iAttendanceService.update(attendance,eq);
        return R.<String>builder().code(200).data("修改成功").build();
    }

    @RequestMapping("add")
    @ResponseBody
    public R<String> add(@RequestBody @Validated Attendance attendance) {
        iAttendanceService.getBaseMapper().insert(attendance);
        return R.<String>builder().code(200).data("添加成功").build();
    }

    @RequestMapping("delete")
    @ResponseBody
    public R<String> delete(@RequestParam("id") Integer id) {
        iAttendanceService.getBaseMapper().deleteById(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

    @RequestMapping("bachDelete")
    @ResponseBody
    public R<String> bachDelete(@RequestBody List<Integer> id) {
        iAttendanceService.removeByIds(id);
        return R.<String>builder().code(200).data("删除成功").build();
    }

}

