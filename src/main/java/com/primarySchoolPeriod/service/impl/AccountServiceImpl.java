package com.primarySchoolPeriod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.primarySchoolPeriod.mapper.AccountMapper;
import com.primarySchoolPeriod.mapper.AttendanceMapper;
import com.primarySchoolPeriod.pojo.Account;
import com.primarySchoolPeriod.pojo.Attendance;
import com.primarySchoolPeriod.service.IAccountService;
import com.primarySchoolPeriod.service.IAttendanceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 考勤管理表 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
