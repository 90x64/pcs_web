package org.jeecg.modules.pcs.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.pcs.entity.ProjectRecord;
import org.jeecg.modules.pcs.mapper.ProjectRecordMapper;
import org.jeecg.modules.pcs.service.IProjectRecordService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 工程数据记录表
 * @Author: jeecg-boot
 * @Date:   2020-09-09
 * @Version: V1.0
 */
@Service
//@DS("multi-datasource1")
public class ProjectRecordServiceImpl extends ServiceImpl<ProjectRecordMapper, ProjectRecord> implements IProjectRecordService {

}
