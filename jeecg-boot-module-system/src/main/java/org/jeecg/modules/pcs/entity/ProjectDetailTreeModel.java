package org.jeecg.modules.pcs.entity;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description: TODO
 * @author: lijie
 * @date: 2020年09月09日 17:14
 */
public class ProjectDetailTreeModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /** id字段,前端数据树中的key*/
    private String key;

    /** id字段,前端数据树中的value*/
    private String value;

    /** name字段,前端数据树中的title*/
    private String title;

    private boolean isLeaf;


    private String id;
    private String modelName;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String sysOrgCode;
    private String parentModelId;
    private String modelId;
    private String projectId;
    private String basicModelId;
    private String isDeleted;
    private Date deleteDate;
    private String deleteUserId;
    private String modelType;
    private String latestVersionId;
    private String clientPath;
    private String dfsPath;
    private String dfsGroup;
    private String hasChild;

    private List<ProjectDetailTreeModel> children = new ArrayList<>();


    /**
     * 将Model对象转换成ProjectDetailTreeModel对象
     * @param model
     */
    public ProjectDetailTreeModel(Model model) {
        this.key = model.getId();
        this.value = model.getId();
        this.title = model.getModelName();
        this.id = model.getId();
        this.parentModelId = model.getParentModelId();
        this.modelName = model.getModelName();

        this.createBy = model.getCreateBy();
        this.createTime = model.getCreateTime();
        this.updateBy = model.getUpdateBy();
        this.updateTime = model.getUpdateTime();
        this.sysOrgCode = model.getSysOrgCode();
        this.modelId = model.getModelId();
        this.projectId = model.getProjectId();
        this.basicModelId = model.getBasicModelId();
        this.isDeleted = model.getIsDeleted();
        this.deleteDate = model.getDeleteDate();
        this.deleteUserId = model.getDeleteUserId();
        this.modelType = model.getModelType();
        this.latestVersionId = model.getLatestVersionId();
        this.clientPath = model.getClientPath();
        this.dfsPath = model.getDfsPath();
        this.dfsGroup = model.getDfsGroup();
        this.hasChild = model.getHasChild();
    }

    public boolean getIsLeaf() {
        return isLeaf;
    }
    public void setIsLeaf(boolean isleaf) {
        this.isLeaf = isleaf;
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


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public List<ProjectDetailTreeModel> getChildren() {
        return children;
    }
    public void setChildren(List<ProjectDetailTreeModel> children) {
        if (children==null){
            this.isLeaf=true;
        }
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    public String getParentModelId() {
        return parentModelId;
    }

    public void setParentModelId(String parentModelId) {
        this.parentModelId = parentModelId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBasicModelId() {
        return basicModelId;
    }

    public void setBasicModelId(String basicModelId) {
        this.basicModelId = basicModelId;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getLatestVersionId() {
        return latestVersionId;
    }

    public void setLatestVersionId(String latestVersionId) {
        this.latestVersionId = latestVersionId;
    }

    public String getClientPath() {
        return clientPath;
    }

    public void setClientPath(String clientPath) {
        this.clientPath = clientPath;
    }

    public String getDfsPath() {
        return dfsPath;
    }

    public void setDfsPath(String dfsPath) {
        this.dfsPath = dfsPath;
    }

    public String getDfsGroup() {
        return dfsGroup;
    }

    public void setDfsGroup(String dfsGroup) {
        this.dfsGroup = dfsGroup;
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public ProjectDetailTreeModel() { }

    /**
     * 重写equals方法
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectDetailTreeModel model = (ProjectDetailTreeModel) o;
        return Objects.equals(id, model.id) &&
                Objects.equals(modelName, model.modelName) &&
                Objects.equals(createBy, model.createBy) &&
                Objects.equals(createTime, model.createTime) &&
                Objects.equals(updateBy, model.updateBy) &&
                Objects.equals(updateTime, model.updateTime) &&
                Objects.equals(sysOrgCode, model.sysOrgCode) &&
                Objects.equals(parentModelId, model.parentModelId) &&
                Objects.equals(modelId, model.modelId) &&
                Objects.equals(projectId, model.projectId) &&
                Objects.equals(basicModelId, model.basicModelId) &&
                Objects.equals(isDeleted, model.isDeleted) &&
                Objects.equals(deleteDate, model.deleteDate) &&
                Objects.equals(deleteUserId, model.deleteUserId) &&
                Objects.equals(modelType, model.modelType) &&
                Objects.equals(latestVersionId, model.latestVersionId) &&
                Objects.equals(clientPath, model.clientPath) &&
                Objects.equals(dfsPath, model.dfsPath) &&
                Objects.equals(dfsGroup, model.dfsGroup) &&
                Objects.equals(hasChild, model.hasChild) ;
    }

    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {

        return Objects.hash(id, modelName, createBy, createTime, updateBy, updateTime, sysOrgCode, parentModelId, modelId, projectId, basicModelId, isDeleted,
                deleteDate, deleteUserId, modelType, latestVersionId, clientPath, dfsPath, dfsGroup, hasChild ,children);
    }

}

