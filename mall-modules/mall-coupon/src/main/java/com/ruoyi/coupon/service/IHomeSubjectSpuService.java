package com.ruoyi.coupon.service;

import java.util.List;
import com.ruoyi.coupon.domain.HomeSubjectSpu;

/**
 * 专题商品Service接口
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public interface IHomeSubjectSpuService 
{
    /**
     * 查询专题商品
     * 
     * @param id 专题商品主键
     * @return 专题商品
     */
    public HomeSubjectSpu selectHomeSubjectSpuById(Long id);

    /**
     * 查询专题商品列表
     * 
     * @param homeSubjectSpu 专题商品
     * @return 专题商品集合
     */
    public List<HomeSubjectSpu> selectHomeSubjectSpuList(HomeSubjectSpu homeSubjectSpu);

    /**
     * 新增专题商品
     * 
     * @param homeSubjectSpu 专题商品
     * @return 结果
     */
    public int insertHomeSubjectSpu(HomeSubjectSpu homeSubjectSpu);

    /**
     * 修改专题商品
     * 
     * @param homeSubjectSpu 专题商品
     * @return 结果
     */
    public int updateHomeSubjectSpu(HomeSubjectSpu homeSubjectSpu);

    /**
     * 批量删除专题商品
     * 
     * @param ids 需要删除的专题商品主键集合
     * @return 结果
     */
    public int deleteHomeSubjectSpuByIds(Long[] ids);

    /**
     * 删除专题商品信息
     * 
     * @param id 专题商品主键
     * @return 结果
     */
    public int deleteHomeSubjectSpuById(Long id);
}
