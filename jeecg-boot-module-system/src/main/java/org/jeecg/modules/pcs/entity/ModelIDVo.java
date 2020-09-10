package org.jeecg.modules.pcs.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @author: lijie
 * @date: 2020年09月09日 18:27
 */
public class ModelIDVo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID
    private String key;

    // 主键ID
    private String value;

    // 名称
    private String title;

    List<ModelIDVo> children = new ArrayList<>();

    /**
     * @param proModel
     * @return
     */
    public ModelIDVo convert(ProjectDetailTreeModel proModel) {
        this.key = proModel.getId();
        this.value = proModel.getId();
        this.title = proModel.getModelName();
        return this;
    }

    /**
     * 该方法为用户部门的实现类所使用
     * @param model
     * @return
     */
    public ModelIDVo convertByUserDepart(Model model) {
        this.key = model.getId();
        this.value = model.getId();
        this.title = model.getModelName();
        return this;
    }

    public List<ModelIDVo> getChildren() {
        return children;
    }

    public void setChildren(List<ModelIDVo> children) {
        this.children = children;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
