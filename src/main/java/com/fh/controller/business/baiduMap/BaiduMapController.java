package com.fh.controller.business.baiduMap;

import com.fh.util.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cuizongqiang on 2017/4/14.
 */

@Controller
@RequestMapping(value = "/baiduMap")
public class BaiduMapController {

    String menuUrl = "baiduMap/mapDemo.do"; //菜单地址(权限用)

    /**
     * 去新增页面
     */
    @RequestMapping(value="/mapDemo")
    public ModelAndView mapDemo(){
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("business/baiduMap/mapDemo");
            mv.addObject("msg", "ok");
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return mv;
    }
}

