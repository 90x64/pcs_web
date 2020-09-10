package org.jeecg.modules.pcs.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.pcs.entity.ProjectRecord;
import org.jeecg.modules.pcs.service.IProjectRecordService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 工程数据记录表
 * @Author: jeecg-boot
 * @Date:   2020-09-09
 * @Version: V1.0
 */
@Api(tags="工程数据记录表")
@RestController
@RequestMapping("/projectRecord")
@Slf4j
public class ProjectRecordController extends JeecgController<ProjectRecord, IProjectRecordService> {
	@Autowired
	private IProjectRecordService projectRecordService;
	
	/**
	 * 分页列表查询
	 *
	 * @param projectRecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "工程数据记录表-分页列表查询")
	@ApiOperation(value="工程数据记录表-分页列表查询", notes="工程数据记录表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ProjectRecord projectRecord,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ProjectRecord> queryWrapper = QueryGenerator.initQueryWrapper(projectRecord, req.getParameterMap());
		Page<ProjectRecord> page = new Page<ProjectRecord>(pageNo, pageSize);
		IPage<ProjectRecord> pageList = projectRecordService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param projectRecord
	 * @return
	 */
	@AutoLog(value = "工程数据记录表-添加")
	@ApiOperation(value="工程数据记录表-添加", notes="工程数据记录表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ProjectRecord projectRecord) {
		projectRecordService.save(projectRecord);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param projectRecord
	 * @return
	 */
	@AutoLog(value = "工程数据记录表-编辑")
	@ApiOperation(value="工程数据记录表-编辑", notes="工程数据记录表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ProjectRecord projectRecord) {
		projectRecordService.updateById(projectRecord);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工程数据记录表-通过id删除")
	@ApiOperation(value="工程数据记录表-通过id删除", notes="工程数据记录表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		projectRecordService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "工程数据记录表-批量删除")
	@ApiOperation(value="工程数据记录表-批量删除", notes="工程数据记录表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.projectRecordService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工程数据记录表-通过id查询")
	@ApiOperation(value="工程数据记录表-通过id查询", notes="工程数据记录表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ProjectRecord projectRecord = projectRecordService.getById(id);
		if(projectRecord==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(projectRecord);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param projectRecord
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProjectRecord projectRecord) {
        return super.exportXls(request, projectRecord, ProjectRecord.class, "工程数据记录表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ProjectRecord.class);
    }

}
