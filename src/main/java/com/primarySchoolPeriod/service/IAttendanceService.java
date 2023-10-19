package com.primarySchoolPeriod.service;

import com.primarySchoolPeriod.pojo.Attendance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.primarySchoolPeriod.pojo.User;

/**
 * <p>
 * 考勤管理表 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
public interface IAttendanceService extends IService<Attendance> {
    void merger(Attendance user);
}
