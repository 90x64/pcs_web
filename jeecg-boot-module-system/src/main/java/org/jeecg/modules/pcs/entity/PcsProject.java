package org.jeecg.modules.pcs.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
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
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 项目信息管理
 * @Author: lijie
 * @Date:   2020-09-09
 * @Version: V1.0
 */
@Data
@TableName("\"PCSDataManager\".project")
//@TableName("pcs_project")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="pcs_project对象", description="项目信息管理")
public class PcsProject implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "id")
    private String id;

    /**项目id*/
    @ApiModelProperty(value = "项目id")
    private String projectId;

	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
    private String projectName;
	/**项目编码*/
	@Excel(name = "项目编码", width = 15)
    @ApiModelProperty(value = "项目编码")
    private String projectNo;
	/**工程承建单位*/
	@Excel(name = "工程承建单位", width = 15, dictTable = "sys_dict left join sys_dict_item on sys_dict.id = sys_dict_item.dict_id", dicText = "sys_dict_item.item_value", dicCode = "sys_dict_item.item_value")
	@Dict(dictTable = "sys_dict left join sys_dict_item on sys_dict.id = sys_dict_item.dict_id", dicText = "sys_dict_item.item_value", dicCode = "sys_dict_item.item_value")
    @ApiModelProperty(value = "工程承建单位")
    private String company;
	/**管线*/
	@Excel(name = "管线", width = 15)
    @ApiModelProperty(value = "管线")
    private String pipeline;
	/**站控*/
	@Excel(name = "站控", width = 15)
    @ApiModelProperty(value = "站控")
    private String station;
	/**类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
    private String projectStatus;
	/**施工日期*/
	@Excel(name = "施工日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "施工日期")
    private Date construtionDate;
	/**项目日期*/
	@Excel(name = "项目日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "项目日期")
    private Date startingDate;
	/**工程实施单位*/
	@Excel(name = "工程实施单位", width = 15, dictTable = "sys_dict left join sys_dict_item on sys_dict.id = sys_dict_item.dict_id", dicText = "sys_dict_item.item_value", dicCode = "sys_dict_item.item_value")
	@Dict(dictTable = "sys_dict left join sys_dict_item on sys_dict.id = sys_dict_item.dict_id", dicText = "sys_dict_item.item_value", dicCode = "sys_dict_item.item_value")
    @ApiModelProperty(value = "工程实施单位")
    private String constructionUnit;
	/**项目经理*/
	@Excel(name = "项目经理", width = 15, dictTable = "sys_user left join sys_user_role ON sys_user.id = sys_user_role.user_id left join sys_role on sys_user_role.role_id = sys_role.id", dicText = "sys_user.username", dicCode = "sys_user.username")
	@Dict(dictTable = "sys_user left join sys_user_role ON sys_user.id = sys_user_role.user_id left join sys_role on sys_user_role.role_id = sys_role.id", dicText = "sys_user.username", dicCode = "sys_user.username")
    @ApiModelProperty(value = "项目经理")
    private String projectManager;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
    private String contactNumber;
	/**ip1*/
	@Excel(name = "ip1", width = 15)
    @ApiModelProperty(value = "ip1")
    private String ip1;
	/**ip2*/
	@Excel(name = "ip2", width = 15)
    @ApiModelProperty(value = "ip2")
    private String ip2;
	/**ip3*/
	@Excel(name = "ip3", width = 15)
    @ApiModelProperty(value = "ip3")
    private String ip3;
	/**ip4*/
	@Excel(name = "ip4", width = 15)
    @ApiModelProperty(value = "ip4")
    private String ip4;
}
