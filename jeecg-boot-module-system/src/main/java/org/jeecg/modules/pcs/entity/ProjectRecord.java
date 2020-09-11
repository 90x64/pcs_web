package org.jeecg.modules.pcs.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description: 工程数据记录表
 * @Author: lijie
 * @Date:   2020-09-09
 * @Version: V1.0
 */
@Data
@TableName("\"PCSDataManager\".project_record")
//@TableName("project_record")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="project_record对象", description="工程数据记录表")
public class ProjectRecord implements Serializable {
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
	/**文件id*/
	@Excel(name = "文件id", width = 15)
    @ApiModelProperty(value = "文件id")
    private String fileId;
	/**项目id*/
	@Excel(name = "项目id", width = 15)
    @ApiModelProperty(value = "项目id")
    private String projectId;
	/**模型id*/
	@Excel(name = "模型id", width = 15)
    @ApiModelProperty(value = "模型id")
    private String modelId;
	/**文件名称*/
	@Excel(name = "文件名称", width = 15)
    @ApiModelProperty(value = "文件名称")
    private String fileName;
	/**文件版本号*/
	@Excel(name = "文件版本号", width = 15)
    @ApiModelProperty(value = "文件版本号")
    private String fileVersion;
	/**文件md5*/
	@Excel(name = "文件md5", width = 15)
    @ApiModelProperty(value = "文件md5")
    private String fileMd5;
	/**文件存储路径*/
	@Excel(name = "文件存储路径", width = 15)
    @ApiModelProperty(value = "文件存储路径")
    private String fileUrl;
	/**更新人id*/
	@Excel(name = "更新人id", width = 15)
    @ApiModelProperty(value = "更新人id")
    private String updateUserId;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
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

    @TableField(exist = false)
    private String noQueryLike;
}
