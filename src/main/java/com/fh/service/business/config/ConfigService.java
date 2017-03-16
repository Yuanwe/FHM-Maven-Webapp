package com.fh.service.business.config;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cuizongqiang on 2017/3/12.
 */
@Service("configService")
public class ConfigService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /**
    *列表
    */
    public List<PageData> list(Page page)throws Exception{
        return (List<PageData>)dao.findForList("ConfigMapper.datalistPage", page);
    }

    /**
    * 新增
    */
    public void save(PageData pd)throws Exception{
        dao.save("ConfigMapper.save", pd);
    }

    public Object findByConfigKey(PageData pd) throws Exception {
        return (PageData)dao.findForObject("ConfigMapper.findByConfigKey", pd);
    }

    /**
    * 通过id获取数据
    */
    public PageData findById(PageData pd)throws Exception{
        return (PageData)dao.findForObject("ConfigMapper.findById", pd);
    }
  /**
  * 修改
  */
    public void edit(PageData pd)throws Exception{
        dao.update("ConfigMapper.edit", pd);
    }

    /**
    * 删除
    */
    public void delete(PageData pd)throws Exception{
        dao.delete("ConfigMapper.delete", pd);
    }


    /**
    * 批量删除
    */
    public void deleteMany(String[] ArrayIds)throws Exception{
        dao.delete("ConfigMapper.deleteMany", ArrayIds);
    }

    /**
    *列表(全部)
    */
    public List<PageData> listAll(PageData pd)throws Exception{
        return (List<PageData>)dao.findForList("ConfigMapper.listAll", pd);
    }



}
