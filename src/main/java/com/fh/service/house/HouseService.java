package com.fh.service.house;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cuizongqiang on 2017/3/10.
 */
@Service("houseService")
public class HouseService {

    @Resource(name = "daoSupport")
    private DaoSupport daoSupport;

    public List<PageData> getHouseInfoByPage(Page page) throws Exception{
        return (List<PageData>) daoSupport.findForList("HouseInfoMapper.getHouseInfoByPage", page);

    }
}
