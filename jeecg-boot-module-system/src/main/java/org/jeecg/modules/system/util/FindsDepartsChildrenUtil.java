package org.jeecg.modules.system.util;

import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.pcs.entity.Model;
import org.jeecg.modules.pcs.entity.ModelIDVo;
import org.jeecg.modules.pcs.entity.ProjectDetailTreeModel;
import org.jeecg.modules.system.entity.SysDepart;
import org.jeecg.modules.system.model.DepartIdModel;
import org.jeecg.modules.system.model.SysDepartTreeModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>
 * 对应部门的表,处理并查找树级数据
 * <P>
 * 
 * @Author: Steve
 * @Date: 2019-01-22
 */
public class FindsDepartsChildrenUtil {

	//部门树信息-树结构
	//private static List<SysDepartTreeModel> sysDepartTreeList = new ArrayList<SysDepartTreeModel>();
	
	//部门树id-树结构
    //private static List<DepartIdModel> idList = new ArrayList<>();


    /**
     * queryTreeList的子方法 ====1=====
     * 该方法是s将SysDepart类型的list集合转换成SysDepartTreeModel类型的集合
     */
    public static List<SysDepartTreeModel> wrapTreeDataToTreeList(List<SysDepart> recordList) {
        // 在该方法每请求一次,都要对全局list集合进行一次清理
        //idList.clear();
    	List<DepartIdModel> idList = new ArrayList<DepartIdModel>();
        List<SysDepartTreeModel> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            SysDepart depart = recordList.get(i);
            records.add(new SysDepartTreeModel(depart));
        }
        List<SysDepartTreeModel> tree = findChildren(records, idList);
        setEmptyChildrenAsNull(tree);
        return tree;
    }

    /**
     * 获取 DepartIdModel
     * @param recordList
     * @return
     */
    public static List<DepartIdModel> wrapTreeDataToDepartIdTreeList(List<SysDepart> recordList) {
        // 在该方法每请求一次,都要对全局list集合进行一次清理
        //idList.clear();
        List<DepartIdModel> idList = new ArrayList<DepartIdModel>();
        List<SysDepartTreeModel> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            SysDepart depart = recordList.get(i);
            records.add(new SysDepartTreeModel(depart));
        }
        findChildren(records, idList);
        return idList;
    }

    /**
     * queryTreeList的子方法 ====2=====
     * 该方法是找到并封装顶级父类的节点到TreeList集合
     */
    private static List<SysDepartTreeModel> findChildren(List<SysDepartTreeModel> recordList,
                                                         List<DepartIdModel> departIdList) {

        List<SysDepartTreeModel> treeList = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            SysDepartTreeModel branch = recordList.get(i);
            if (oConvertUtils.isEmpty(branch.getParentId())) {
                treeList.add(branch);
                DepartIdModel departIdModel = new DepartIdModel().convert(branch);
                departIdList.add(departIdModel);
            }
        }
        getGrandChildren(treeList,recordList,departIdList);
        
        //idList = departIdList;
        return treeList;
    }

    /**
     * queryTreeList的子方法====3====
     *该方法是找到顶级父类下的所有子节点集合并封装到TreeList集合
     */
    private static void getGrandChildren(List<SysDepartTreeModel> treeList,List<SysDepartTreeModel> recordList,List<DepartIdModel> idList) {

        for (int i = 0; i < treeList.size(); i++) {
            SysDepartTreeModel model = treeList.get(i);
            DepartIdModel idModel = idList.get(i);
            for (int i1 = 0; i1 < recordList.size(); i1++) {
                SysDepartTreeModel m = recordList.get(i1);
                if (m.getParentId()!=null && m.getParentId().equals(model.getId())) {
                    model.getChildren().add(m);
                    DepartIdModel dim = new DepartIdModel().convert(m);
                    idModel.getChildren().add(dim);
                }
            }
            getGrandChildren(treeList.get(i).getChildren(), recordList, idList.get(i).getChildren());
        }

    }
    

    /**
     * queryTreeList的子方法 ====4====
     * 该方法是将子节点为空的List集合设置为Null值
     */
    private static void setEmptyChildrenAsNull(List<SysDepartTreeModel> treeList) {

        for (int i = 0; i < treeList.size(); i++) {
            SysDepartTreeModel model = treeList.get(i);
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

    public static List<ProjectDetailTreeModel> wrapTreeDataToTreeList2(List<Model> recordList, String projectId) {
        List<ModelIDVo> idList = new ArrayList<ModelIDVo>();
        List<ProjectDetailTreeModel> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            Model model = recordList.get(i);
            records.add(new ProjectDetailTreeModel(model));
        }
        List<ProjectDetailTreeModel> tree = findChildren2(records, idList, projectId);
        setEmptyChildrenAsNull2(tree);
        return tree;
    }

    private static List<ProjectDetailTreeModel> findChildren2(List<ProjectDetailTreeModel> recordList,
                                                         List<ModelIDVo> modelIDVoList, String projectId) {

        List<ProjectDetailTreeModel> treeList = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            ProjectDetailTreeModel branch = recordList.get(i);
            if (oConvertUtils.isEmpty(branch.getParentModelId()) || "0".equalsIgnoreCase(branch.getParentModelId()) || branch.getParentModelId().equals(projectId)) {
                treeList.add(branch);
                ModelIDVo modelIDVo = new ModelIDVo().convert(branch);
                modelIDVoList.add(modelIDVo);
            }
        }
        getGrandChildren2(treeList,recordList,modelIDVoList);

        return treeList;
    }

    private static void getGrandChildren2(List<ProjectDetailTreeModel> treeList,List<ProjectDetailTreeModel> recordList,List<ModelIDVo> idList) {

        for (int i = 0; i < treeList.size(); i++) {
            ProjectDetailTreeModel model = treeList.get(i);
            ModelIDVo idModel = idList.get(i);
            for (int i1 = 0; i1 < recordList.size(); i1++) {
                ProjectDetailTreeModel m = recordList.get(i1);
                if (m.getParentModelId()!=null && m.getParentModelId().equals(model.getId())) {
                    model.getChildren().add(m);
                    ModelIDVo dim = new ModelIDVo().convert(m);
                    idModel.getChildren().add(dim);
                }
            }
            getGrandChildren2(treeList.get(i).getChildren(), recordList, idList.get(i).getChildren());
        }
    }

    private static void setEmptyChildrenAsNull2(List<ProjectDetailTreeModel> treeList) {

        for (int i = 0; i < treeList.size(); i++) {
            ProjectDetailTreeModel model = treeList.get(i);
            if (model.getChildren().size() == 0) {
                model.setChildren(null);
                model.setIsLeaf(true);
            }else{
                setEmptyChildrenAsNull2(model.getChildren());
                model.setIsLeaf(false);
            }
        }
        // sysDepartTreeList = treeList;
    }

}
