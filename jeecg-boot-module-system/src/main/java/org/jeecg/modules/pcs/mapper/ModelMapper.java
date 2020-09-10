package org.jeecg.modules.pcs.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.pcs.entity.Model;

/**
 * @Description: 工程数据模型表
 * @Author: jeecg-boot
 * @Date:   2020-09-09
 * @Version: V1.0
 */
public interface ModelMapper extends BaseMapper<Model> {

	/**
	 * 编辑节点状态
	 * @param id
	 * @param status
	 */
	void updateTreeNodeStatus(@Param("id") String id, @Param("status") String status);

}
