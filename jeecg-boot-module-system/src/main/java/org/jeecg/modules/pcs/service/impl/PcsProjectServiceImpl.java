package org.jeecg.modules.pcs.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.pcs.entity.PcsProject;
import org.jeecg.modules.pcs.mapper.PcsProjectMapper;
import org.jeecg.modules.pcs.service.IPcsProjectService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 项目信息管理
 * @Author: jeecg-boot
 * @Date:   2020-09-09
 * @Version: V1.0
 */
@Service
//@DS("multi-datasource1")
public class PcsProjectServiceImpl extends ServiceImpl<PcsProjectMapper, PcsProject> implements IPcsProjectService {

}
