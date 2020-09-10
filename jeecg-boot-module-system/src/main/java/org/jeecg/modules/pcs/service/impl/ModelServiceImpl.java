package org.jeecg.modules.pcs.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.netty.util.internal.StringUtil;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.pcs.entity.Model;
import org.jeecg.modules.pcs.entity.ModelIDVo;
import org.jeecg.modules.pcs.entity.ProjectDetailTreeModel;
import org.jeecg.modules.pcs.mapper.ModelMapper;
import org.jeecg.modules.pcs.service.IModelService;
import org.jeecg.modules.system.entity.SysDepart;
import org.jeecg.modules.system.model.DepartIdModel;
import org.jeecg.modules.system.model.SysDepartTreeModel;
import org.jeecg.modules.system.util.FindsDepartsChildrenUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 工程数据模型表
 * @Author: jeecg-boot
 * @Date:   2020-09-09
 * @Version: V1.0
 */
@Service
//@DS("multi-datasource1")
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements IModelService {


	@Override
	public List<ProjectDetailTreeModel> searchBy(String keyWord) {

		LambdaQueryWrapper<Model> query = new LambdaQueryWrapper<Model>();
		List<ProjectDetailTreeModel> newList = new ArrayList<>();
		query.eq(Model::getProjectId, keyWord);
		ProjectDetailTreeModel model = new ProjectDetailTreeModel();
		List<Model> modelList = this.list(query);
		if(modelList.size() > 0) {
			for(Model m : modelList) {
				model = new ProjectDetailTreeModel(m);
				model.setChildren(null);
				newList.add(model);
			}
		}
		return newList;
	}

	@Override
	public List<ProjectDetailTreeModel> queryTreeList(String projectId) {
		LambdaQueryWrapper<Model> query = new LambdaQueryWrapper<Model>();
		//query.eq(Model::getIsDeleted, CommonConstant.DEL_FLAG_0.toString());
		//query.eq(Model::getProjectId, projectId);
		List<Model> list = this.list(query);
		List<ProjectDetailTreeModel> listResult = FindsDepartsChildrenUtil.wrapTreeDataToTreeList2(list);
		return listResult;
	}



	private static void setEmptyChildrenAsNull(List<ProjectDetailTreeModel> treeList) {

		for (int i = 0; i < treeList.size(); i++) {
			ProjectDetailTreeModel model = treeList.get(i);
			if (model.getChildren().size() == 0) {
				model.setChildren(null);
				model.setIsLeaf(true);
			}else{
				setEmptyChildrenAsNull(model.getChildren());
				model.setIsLeaf(false);
			}
		}
		// sysDepartTreeList = treeList;
	}

	@Override
	public void addModel(Model model) {
		if(oConvertUtils.isEmpty(model.getParentModelId())){
			model.setParentModelId(IModelService.ROOT_PID_VALUE);
		}else{
			//如果当前节点父ID不为空 则设置父节点的hasChildren 为1
			Model parent = baseMapper.selectById(model.getParentModelId());
			if(parent!=null && !"1".equals(parent.getHasChild())){
				parent.setHasChild("1");
				baseMapper.updateById(parent);
			}
		}
		baseMapper.insert(model);
	}
	
	@Override
	public void updateModel(Model model) {
		Model entity = this.getById(model.getId());
		if(entity==null) {
			throw new JeecgBootException("未找到对应实体");
		}
		String old_pid = entity.getParentModelId();
		String new_pid = model.getParentModelId();
		if(!old_pid.equals(new_pid)) {
			updateOldParentNode(old_pid);
			if(oConvertUtils.isEmpty(new_pid)){
				model.setParentModelId(IModelService.ROOT_PID_VALUE);
			}
			if(!IModelService.ROOT_PID_VALUE.equals(model.getParentModelId())) {
				baseMapper.updateTreeNodeStatus(model.getParentModelId(), IModelService.HASCHILD);
			}
		}
		baseMapper.updateById(model);
	}
	
	@Override
	public void deleteModel(String id) throws JeecgBootException {
		Model model = this.getById(id);
		if(model==null) {
			throw new JeecgBootException("未找到对应实体");
		}
		updateOldParentNode(model.getParentModelId());
		baseMapper.deleteById(id);
	}




	/**
	 * 根据所传pid查询旧的父级节点的子节点并修改相应状态值
	 * @param pid
	 */
	private void updateOldParentNode(String pid) {
		if(!IModelService.ROOT_PID_VALUE.equals(pid)) {
			Integer count = baseMapper.selectCount(new QueryWrapper<Model>().eq("parent_model_id", pid));
			if(count==null || count<=1) {
				baseMapper.updateTreeNodeStatus(pid, IModelService.NOCHILD);
			}
		}
	}

}
