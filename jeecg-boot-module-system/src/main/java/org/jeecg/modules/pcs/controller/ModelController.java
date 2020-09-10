package org.jeecg.modules.pcs.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.pcs.entity.Model;
import org.jeecg.modules.pcs.entity.ProjectDetailTreeModel;
import org.jeecg.modules.pcs.service.IModelService;
import org.jeecg.modules.system.model.SysDepartTreeModel;
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
 * @Description: 工程数据模型表
 * @Author: jeecg-boot
 * @Date:   2020-09-09
 * @Version: V1.0
 */
@Api(tags="工程数据模型表")
@RestController
@RequestMapping("/model")
@Slf4j
public class ModelController extends JeecgController<Model, IModelService>{
	@Autowired
	private IModelService modelService;


	 @AutoLog(value = "根据项目ID查询工程模型")
	 @ApiOperation(value="根据项目ID查询工程模型", notes="根据项目ID查询工程模型")
	 @RequestMapping(value = "/searchBy", method = RequestMethod.GET)
	 public Result<List<ProjectDetailTreeModel>> searchBy(@RequestParam(name = "keyWord", required = false) String keyWord) {
		 Result<List<ProjectDetailTreeModel>> result = new Result<List<ProjectDetailTreeModel>>();

		 List<ProjectDetailTreeModel> treeList = this.modelService.searchBy(keyWord);

		 result.setResult(treeList);
		 return result;
	 }


	 /**
	  * 查询数据 查出所有部门,并以树结构数据格式响应给前端
	  *
	  * @return
	  */
	 @AutoLog(value = "工程数据模型树列表查询")
	 @ApiOperation(value="工程数据模型树列表查询", notes="工程数据模型树列表查询")
	 @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	 public Result<List<ProjectDetailTreeModel>> queryTreeList(@RequestParam(name = "projectId", required = false) String projectId) {
		 Result<List<ProjectDetailTreeModel>> result = new Result<>();
		 try {
			 List<ProjectDetailTreeModel> list = modelService.queryTreeList(projectId);
			 result.setResult(list);
			 result.setSuccess(true);
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
		 }
		 return result;
	 }

	
	/**
	 * 分页列表查询
	 *
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "工程数据模型表-分页列表查询")
	@ApiOperation(value="工程数据模型表-分页列表查询", notes="工程数据模型表-分页列表查询")
	@GetMapping(value = "/rootList")
	public Result<?> queryPageList(Model model,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String parentId = model.getParentModelId();
		if (oConvertUtils.isEmpty(parentId)) {
			parentId = "0";
		}
		model.setParentModelId(null);
		QueryWrapper<Model> queryWrapper = QueryGenerator.initQueryWrapper(model, req.getParameterMap());
		// 使用 eq 防止模糊查询
		queryWrapper.eq("parent_model_id", parentId);
		Page<Model> page = new Page<Model>(pageNo, 9999);
		IPage<Model> pageList = modelService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
      * 获取子数据
      * @param Model
      * @param req
      * @return
      */
	@AutoLog(value = "工程数据模型表-获取子数据")
	@ApiOperation(value="工程数据模型表-获取子数据", notes="工程数据模型表-获取子数据")
	@GetMapping(value = "/childList")
	public Result<?> queryPageList(Model model,HttpServletRequest req) {
		QueryWrapper<Model> queryWrapper = QueryGenerator.initQueryWrapper(model, req.getParameterMap());
		List<Model> list = modelService.list(queryWrapper);
		IPage<Model> pageList = new Page<>(1, 10, list.size());
        pageList.setRecords(list);
		return Result.ok(pageList);
	}

    /**
      * 批量查询子节点
      * @param parentIds 父ID（多个采用半角逗号分割）
      * @return 返回 IPage
      * @param parentIds
      * @return
      */
	@AutoLog(value = "工程数据模型表-批量获取子数据")
    @ApiOperation(value="工程数据模型表-批量获取子数据", notes="工程数据模型表-批量获取子数据")
    @GetMapping("/getChildListBatch")
    public Result getChildListBatch(@RequestParam("parentIds") String parentIds) {
        try {
            QueryWrapper<Model> queryWrapper = new QueryWrapper<>();
            List<String> parentIdList = Arrays.asList(parentIds.split(","));
            queryWrapper.in("parent_model_id", parentIdList);
            List<Model> list = modelService.list(queryWrapper);
            IPage<Model> pageList = new Page<>(1, 10, list.size());
            pageList.setRecords(list);
            return Result.ok(pageList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("批量查询子节点失败：" + e.getMessage());
        }
    }
	
	/**
	 *   添加
	 *
	 * @param model
	 * @return
	 */
	@AutoLog(value = "工程数据模型表-添加")
	@ApiOperation(value="工程数据模型表-添加", notes="工程数据模型表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Model model) {
		modelService.addModel(model);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param model
	 * @return
	 */
	@AutoLog(value = "工程数据模型表-编辑")
	@ApiOperation(value="工程数据模型表-编辑", notes="工程数据模型表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Model model) {
		modelService.updateModel(model);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工程数据模型表-通过id删除")
	@ApiOperation(value="工程数据模型表-通过id删除", notes="工程数据模型表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		modelService.deleteModel(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "工程数据模型表-批量删除")
	@ApiOperation(value="工程数据模型表-批量删除", notes="工程数据模型表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.modelService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工程数据模型表-通过id查询")
	@ApiOperation(value="工程数据模型表-通过id查询", notes="工程数据模型表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Model model = modelService.getById(id);
		if(model==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(model);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param model
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Model model) {
		return super.exportXls(request, model, Model.class, "工程数据模型表");
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
		return super.importExcel(request, response, Model.class);
    }

}
