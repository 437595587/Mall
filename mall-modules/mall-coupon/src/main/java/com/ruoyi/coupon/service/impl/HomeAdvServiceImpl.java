package com.ruoyi.coupon.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.coupon.mapper.HomeAdvMapper;
import com.ruoyi.coupon.domain.HomeAdv;
import com.ruoyi.coupon.service.IHomeAdvService;

/**
 * 首页轮播广告Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-23
 */
@Service
public class HomeAdvServiceImpl implements IHomeAdvService 
{
    @Autowired
    private HomeAdvMapper homeAdvMapper;

    /**
     * 查询首页轮播广告
     * 
     * @param id 首页轮播广告主键
     * @return 首页轮播广告
     */
    @Override
    public HomeAdv selectHomeAdvById(Long id)
    {
        return homeAdvMapper.selectHomeAdvById(id);
    }

    /**
     * 查询首页轮播广告列表
     * 
     * @param homeAdv 首页轮播广告
     * @return 首页轮播广告
     */
    @Override
    public List<HomeAdv> selectHomeAdvList(HomeAdv homeAdv)
    {
        return homeAdvMapper.selectHomeAdvList(homeAdv);
    }

    /**
     * 新增首页轮播广告
     * 
     * @param homeAdv 首页轮播广告
     * @return 结果
     */
    @Override
    public int insertHomeAdv(HomeAdv homeAdv)
    {
        return homeAdvMapper.insertHomeAdv(homeAdv);
    }

    /**
     * 修改首页轮播广告
     * 
     * @param homeAdv 首页轮播广告
     * @return 结果
     */
    @Override
    public int updateHomeAdv(HomeAdv homeAdv)
    {
        return homeAdvMapper.updateHomeAdv(homeAdv);
    }

    /**
     * 批量删除首页轮播广告
     * 
     * @param ids 需要删除的首页轮播广告主键
     * @return 结果
     */
    @Override
    public int deleteHomeAdvByIds(Long[] ids)
    {
        return homeAdvMapper.deleteHomeAdvByIds(ids);
    }

    /**
     * 删除首页轮播广告信息
     * 
     * @param id 首页轮播广告主键
     * @return 结果
     */
    @Override
    public int deleteHomeAdvById(Long id)
    {
        return homeAdvMapper.deleteHomeAdvById(id);
    }
}
