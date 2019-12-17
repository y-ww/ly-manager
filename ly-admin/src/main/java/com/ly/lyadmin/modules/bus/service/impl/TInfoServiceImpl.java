package com.ly.lyadmin.modules.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.common.utils.Result;
import com.ly.common.utils.StringUtil;
import com.ly.lyadmin.modules.bus.mapper.TInfoMapper;
import com.ly.lyadmin.modules.bus.model.TInfo;
import com.ly.lyadmin.modules.bus.service.TInfoService;
import com.ly.lyadmin.modules.bus.utils.Constant;
import com.ly.lyadmin.modules.sys.mapper.SysUserRoleMapper;
import com.ly.lyadmin.modules.sys.model.SysUser;
import com.ly.lyadmin.modules.sys.model.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/11/22 12:43 上午
 * @Version V1.0
 */
@Service
public class TInfoServiceImpl extends ServiceImpl<TInfoMapper, TInfo> implements TInfoService {

    @Autowired
    TInfoMapper tInfoMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Result contentlist(Long userId, Integer pageNo, Integer pageSize, String searchKey, String searchValue) {


        //  根据用户编号 查询出 角色id 对应  平台 ID
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        SysUserRole sysUserRole = sysUserRoleMapper.selectOne(wrapper);

        QueryWrapper<TInfo> queryWrapper = new QueryWrapper<TInfo>();

        queryWrapper.eq("platform",sysUserRole.getRoleId());
     //   queryWrapper.eq("personid",userId);
        queryWrapper.eq("isdelete",1);
        queryWrapper.eq("is_fbtype",1);
        queryWrapper.orderByDesc("create_time");
        if(StringUtil.isNotBlank(searchKey)){
            queryWrapper.like(searchKey,searchValue);
        }
        IPage<TInfo> page = new Page<TInfo>(pageNo,pageSize);
        IPage<TInfo> iPage = tInfoMapper.selectPage(page, queryWrapper);
        return Result.ok().put("count",iPage.getTotal()).put("data",iPage.getRecords());

    }

    @Override
    public Result draftlist(Long userId, Integer pageNo, Integer pageSize, String searchKey, String searchValue) {

        //  根据用户编号 查询出  平台 ID
        String ptCode = "0";

        QueryWrapper<TInfo> queryWrapper = new QueryWrapper<TInfo>();

        queryWrapper.eq("platform",ptCode);
        queryWrapper.eq("personid",userId);
        queryWrapper.eq("isdelete",1);
        queryWrapper.eq("is_fbtype",0);
        queryWrapper.orderByDesc("create_time");
        if(StringUtil.isNotBlank(searchKey)){
            queryWrapper.like(searchKey,searchValue);
        }
        IPage<TInfo> page = new Page<TInfo>(pageNo,pageSize);
        IPage<TInfo> iPage = tInfoMapper.selectPage(page, queryWrapper);
        return Result.ok().put("count",iPage.getTotal()).put("data",iPage.getRecords());
    }

    @Override
    public boolean updateInfoByIds(String[] ids) {
        int result = 0;
        result = tInfoMapper.updateInfoByIds(ids);
        return result > 0 ? true:false;
    }




    @Override
    public Result contentTitleList(Integer pageNo, Integer pageSize, String colid, String ptCode) {

        QueryWrapper<TInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","title","summary","min_pic_address");
        queryWrapper.eq("colid",colid);
        queryWrapper.eq("platform",ptCode);
        queryWrapper.eq("isdelete", Constant.STATUS_ISUSER);
        queryWrapper.eq("is_fbtype",Constant.STATUS_ISUSER);
        queryWrapper.orderByDesc("create_time");
        IPage<TInfo> page = new Page<TInfo>(pageNo,pageSize);
        IPage<TInfo> iPage = tInfoMapper.selectPage(page, queryWrapper);
        return Result.ok().put("count",iPage.getTotal()).put("data",iPage.getRecords());

    }

    @Override
    public Result searchTitleList(Integer pageNo, Integer pageSize, String title, String ptCode) {

        QueryWrapper<TInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","title");
        queryWrapper.like("title",title);
        queryWrapper.eq("platform",ptCode);
        queryWrapper.eq("isdelete",Constant.STATUS_ISUSER);
        queryWrapper.eq("is_fbtype",Constant.STATUS_ISUSER);
        queryWrapper.orderByDesc("create_time");
        IPage<TInfo> page = new Page<TInfo>(pageNo,pageSize);
        IPage<TInfo> iPage = tInfoMapper.selectPage(page, queryWrapper);
        return Result.ok().put("count",iPage.getTotal()).put("data",iPage.getRecords());
    }

    @Override
    public TInfo preContent(String id) {
        return tInfoMapper.preContent(id);
    }

    @Override
    public TInfo nextContent(String id) {
        return tInfoMapper.nextContent(id);
    }

}
