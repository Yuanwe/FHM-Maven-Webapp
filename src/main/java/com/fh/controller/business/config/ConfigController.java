package com.fh.controller.business.config;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.business.config.ConfigService;
import com.fh.util.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cuizongqiang on 2017/3/12
 * 系统通用配置
 */
@Controller
@RequestMapping(value = "/config")
public class ConfigController extends BaseController{

    String menuUrl = "config/list.do"; //菜单地址(权限用)
    @Resource(name="configService")
    private ConfigService configService;

    /**
     * 列表
     */
    @RequestMapping(value="/list")
    public ModelAndView list(Page page){
        logBefore(logger, "按条件分页查询通用配置表t_sys_config");
        //if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try{
            pd = this.getPageData();
            String configKey = pd.getString("configKey");
            if(null != configKey && !"".equals(configKey)){
                pd.put("configKey", configKey.trim());
            }
            String configValue = pd.getString("configValue");
            if(null != configValue && !"".equals(configValue)){
                pd.put("configValue", configValue.trim());
            }
            page.setPd(pd);
            List<PageData> varList = configService.list(page);
            mv.setViewName("business/config/config_list");
            mv.addObject("varList", varList);
            mv.addObject("pd", pd);
            mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
        } catch(Exception e){
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 去新增页面
     */
    @RequestMapping(value="/goAdd")
    public ModelAndView goAdd(){
        logBefore(logger, "打开通用配置新增页面config_edit.jsp");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            mv.setViewName("business/config/config_edit");
            mv.addObject("msg", "save");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 新增
     */
    @RequestMapping(value="/save")
    public ModelAndView save() throws Exception{
        logBefore(logger, "保存通用配置新增记录到t_sys_config");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;}
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("createTime", Tools.date2Str(new Date())); //创建时间
        configService.save(pd);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 去修改页面
     */
    @RequestMapping(value="/goEdit")
    public ModelAndView goEdit(){
        logBefore(logger, "打开通用配置修改页面config_edit.jsp");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            pd = configService.findById(pd);	//根据ID读取
            mv.setViewName("business/config/config_edit");
            mv.addObject("msg", "edit");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 修改
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit() throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;}
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("updateTime", Tools.date2Str(new Date())); //修改时间
        configService.edit(pd);
        mv.addObject("msg","success");
        mv.setViewName("save_result");

        logBefore(logger, "保存通用配置修改记录id="+pd.getString("id"));
        return mv;
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete")
    public void delete(PrintWriter out){
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;}
        PageData pd = new PageData();
        try{
            pd = this.getPageData();
            configService.delete(pd);
            out.write("success");
            out.close();
            logBefore(logger, "删除通用配置记录id="+pd.getString("id"));

        } catch(Exception e){
            logger.error(e.toString(), e);
        }

    }


    /**
     * 批量删除
     */
    @RequestMapping(value="/deleteMany")
    @ResponseBody
    public Object deleteMany() {
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;}
        PageData pd = new PageData();
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            pd = this.getPageData();
            List<PageData> pdList = new ArrayList<PageData>();
            String ids = pd.getString("ids");
            if(null != ids && !"".equals(ids)){
                String ArrayIds[] = ids.split(",");
                configService.deleteMany(ArrayIds);
                pd.put("msg", "ok");
            }else{
                pd.put("msg", "no");
            }
            pdList.add(pd);
            map.put("list", pdList);
            logBefore(logger, "多项删除通用配置t_sys_config记录,ids="+ids);

        } catch (Exception e) {
            logger.error(e.toString(), e);
        } finally {
            logAfter(logger);
        }
        return AppUtil.returnObject(pd, map);
    }

    /**
     * 判断关键词是否存在
     */
    @RequestMapping(value="/hasK")
    @ResponseBody
    public Object hasK(){
        Map<String,String> map = new HashMap<String,String>();
        String errInfo = "success";
        PageData pd = new PageData();
        try{
            pd = this.getPageData();
            pd.put("status", "3");
            if(configService.findByConfigKey(pd) != null){
                errInfo = "error";
            }
        } catch(Exception e){
            logger.error(e.toString(), e);
        }
        map.put("result", errInfo);				//返回结果
        return AppUtil.returnObject(new PageData(), map);
    }

    /**
     * 导出到excel
     * @return
     */
    @RequestMapping(value="/toExcel")
    public ModelAndView exportExcel(){
        logBefore(logger, "导出通用配置t_sys_config记录");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try{
            Map<String,Object> dataMap = new HashMap<String,Object>();
            List<String> titles = new ArrayList<String>();
            titles.add("系统编号");	//1
            titles.add("配置key");	//2
            titles.add("配置value");	//3
            titles.add("状态");	//4
            titles.add("描述");	//5
            titles.add("最后一次修改时间"); //6
            dataMap.put("titles", titles);
            List<PageData> varOList = configService.listAll(pd);
            List<PageData> varList = new ArrayList<PageData>();
            for(int i=0;i<varOList.size();i++){
                PageData vpd = new PageData();
                vpd.put("var1", varOList.get(i).get("id").toString());	//1
                vpd.put("var2", varOList.get(i).getString("configKey"));	//2
                vpd.put("var3", varOList.get(i).getString("configValue"));	//3
                vpd.put("var4", varOList.get(i).get("status").toString());	//4
                vpd.put("var5", varOList.get(i).getString("configDesc"));	//5
                vpd.put("var6", varOList.get(i).getString("updateTime"));	//6
                varList.add(vpd);
            }
            dataMap.put("varList", varList);
            dataMap.put("sheetName", "通用配置信息");
            ObjectExcelView erv = new ObjectExcelView();
            mv = new ModelAndView(erv,dataMap);
        } catch(Exception e){
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /* ===============================权限================================== */
    public Map<String, String> getHC(){
        Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
        Session session = currentUser.getSession();
        return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
    }
	/* ===============================权限================================== */

    @InitBinder
    public void initBinder(WebDataBinder binder){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
    }
}
