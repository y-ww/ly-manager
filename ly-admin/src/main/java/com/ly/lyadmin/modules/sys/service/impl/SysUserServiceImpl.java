package com.ly.lyadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.common.utils.Result;
import com.ly.common.utils.StringUtil;
import com.ly.lyadmin.shiro.ShiroUtils;
import com.ly.lyadmin.modules.sys.mapper.SysUserMapper;
import com.ly.lyadmin.modules.sys.model.SysUser;
import com.ly.lyadmin.modules.sys.service.SysUserService;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * 
 * @Author: SLIGHTLEE
 * @Date: 2019/10/15 10:51 下午
 * 
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserMapper.queryAllMenuId(userId);
    }

    @Override
    public Result getAllUser(Integer pageNo, Integer pageSize, String searchKey, String searchValue) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
        if(StringUtil.isNotBlank(searchKey)){
            wrapper.like(searchKey,searchValue);
        }
        IPage<SysUser> page = new Page<SysUser>(pageNo,pageSize);
        IPage<SysUser> iPage = sysUserMapper.selectPage(page, wrapper);
        return Result.ok().put("count",iPage.getTotal()).put("data",iPage.getRecords());
    }

    @Override
    public void add(SysUser sysUser) {
        sysUser.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        sysUser.setSalt(salt);
        sysUser.setPassword(ShiroUtils.sha256(sysUser.getPassword(),salt));
        sysUser.setCreateBy(ShiroUtils.getUserId());
        this.save(sysUser);
    }
}
