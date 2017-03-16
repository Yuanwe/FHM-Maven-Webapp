package com.fh.controller.house;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.house.HouseService;
import com.fh.util.Const;
import com.fh.util.PageData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 说明：房屋管理controller
 * Created by cuizongqiang on 2017/3/10.
 */
@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {

    String menuUrl = "house/list.do"; //菜单地址(权限用)

    @Resource(name="houseService")
    private HouseService houseService;

    //分页查询房屋列表
    @RequestMapping(value = "/list")
    public ModelAndView list(Page page) throws Exception{
        logBefore(logger, "分页查询HouseInfo列表");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            String fwmc = pd.getString("fwmc");
            if(null != fwmc && !"".equals(fwmc)){
                pd.put("fwmc", fwmc.trim());
            }
            page.setPd(pd);
            List<PageData> list = houseService.list(page);
            mv.setViewName("house/houseinfo_list");
            mv.addObject("varList", list);
            mv.addObject("pd", pd);
            mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
        }catch (Exception e){
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

}
