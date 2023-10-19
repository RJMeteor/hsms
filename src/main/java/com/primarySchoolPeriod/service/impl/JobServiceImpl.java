package com.primarySchoolPeriod.service.impl;

import com.primarySchoolPeriod.pojo.Job;
import com.primarySchoolPeriod.mapper.JobMapper;
import com.primarySchoolPeriod.service.IJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 职位管理 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

}
