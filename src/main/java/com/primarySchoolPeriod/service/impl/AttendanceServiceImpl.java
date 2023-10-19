package com.primarySchoolPeriod.service.impl;

import com.primarySchoolPeriod.pojo.Attendance;
import com.primarySchoolPeriod.mapper.AttendanceMapper;
import com.primarySchoolPeriod.pojo.User;
import com.primarySchoolPeriod.service.IAttendanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.primarySchoolPeriod.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 考勤管理表 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements IAttendanceService {
    @Resource
    private IUserService iUserService;

    @Override
    public void merger(Attendance attendance) {
        attendance.setUserPojo(iUserService.getById(attendance.getUser()));
        attendance.setUserList(iUserService.list());
    }
}
