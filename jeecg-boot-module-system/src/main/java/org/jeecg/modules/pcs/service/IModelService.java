package org.jeecg.modules.pcs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.pcs.entity.Model;
import org.jeecg.modules.pcs.entity.ProjectDetailTreeModel;
import org.jeecg.modules.system.model.SysDepartTreeModel;

import java.util.List;

/**
 * @Description: 工程数据模型表
 * @Author: jeecg-boot
 * @Date:   2020-09-09
 * @Version: V1.0
 */
public interface IModelService extends IService<Model> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";
	
	/**树节点有子节点状态值*/
	public static final String HASCHILD = "1";
	
	/**树节点无子节点状态值*/
	public static final String NOCHILD = "0";

	/**新增节点*/
	void addModel(Model model);
	
	/**修改节点*/
	void updateModel(Model model) throws JeecgBootException;
	
	/**删除节点*/
	void deleteModel(String id) throws JeecgBootException;

	/**查询model树*/
    List<ProjectDetailTreeModel> queryTreeList(String projectId);

    /**根据keyword查询树*/
	List<ProjectDetailTreeModel> searchBy(String keyWord);
}
