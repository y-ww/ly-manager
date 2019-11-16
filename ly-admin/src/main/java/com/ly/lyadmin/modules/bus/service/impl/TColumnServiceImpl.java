package com.ly.lyadmin.modules.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.lyadmin.modules.bus.mapper.TColumnMapper;
import com.ly.lyadmin.modules.bus.model.TColumn;
import com.ly.lyadmin.modules.bus.service.TColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/11/14 10:13 下午
 * @Version V1.0
 */
@Service
public class TColumnServiceImpl extends ServiceImpl<TColumnMapper,TColumn> implements TColumnService {

    @Autowired
    TColumnMapper tColumnMapper;

    @Override
    public List<TColumn> queryListParentId(String parentId,String ptCode) {
        return tColumnMapper.queryListParentId(parentId,ptCode);
    }
}
