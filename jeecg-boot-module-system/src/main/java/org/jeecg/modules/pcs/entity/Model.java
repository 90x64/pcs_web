package org.jeecg.modules.pcs.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 工程数据模型表
 * @Author: lijie
 * @Date:   2020-09-09
 * @Version: V1.0
 */
@Data
@TableName("\"PCSDataManager\".model")
//@TableName("model")
@ApiModel(value="model对象", description="工程数据模型表")
public class Model implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;
	/**父级节点*/
	@Excel(name = "父级节点", width = 15)
	@ApiModelProperty(value = "父级节点")
	private String parentModelId;
	/**模型id*/
	@Excel(name = "模型id", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
	@ApiModelProperty(value = "模型id")
	private String modelId;
	/**项目id*/
	@Excel(name = "项目id", width = 15)
	@ApiModelProperty(value = "项目id")
	private String projectId;
	/**基础模型id*/
	@Excel(name = "基础模型id", width = 15)
	@ApiModelProperty(value = "基础模型id")
	private String basicModelId;
	/**模型名称*/
	@Excel(name = "模型名称", width = 15)
	@ApiModelProperty(value = "模型名称")
	private String modelName;
	/**是否删除*/
	@Excel(name = "是否删除", width = 15)
	@ApiModelProperty(value = "是否删除")
	private String isDeleted;
	/**删除时间*/
	@Excel(name = "删除时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "删除时间")
	private Date deleteDate;
	/**删除人id*/
	@Excel(name = "删除人id", width = 15)
	@ApiModelProperty(value = "删除人id")
	private String deleteUserId;
	/**模型类型*/
	@Excel(name = "模型类型", width = 15)
	@ApiModelProperty(value = "模型类型")
	private String modelType;
	/**最后版本id*/
	@Excel(name = "最后版本id", width = 15)
	@ApiModelProperty(value = "最后版本id")
	private String latestVersionId;
	/**客户端路径*/
	@Excel(name = "客户端路径", width = 15)
	@ApiModelProperty(value = "客户端路径")
	private String clientPath;
	/**dfs路径*/
	@Excel(name = "dfs路径", width = 15)
	@ApiModelProperty(value = "dfs路径")
	private String dfsPath;
	/**dfs分组*/
	@Excel(name = "dfs分组", width = 15)
	@ApiModelProperty(value = "dfs分组")
	private String dfsGroup;
	/**是否有子节点*/
	@Excel(name = "是否有子节点", width = 15)
	@ApiModelProperty(value = "是否有子节点")
	private String hasChild;
}
