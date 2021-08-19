package com.ruoyi.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.PmsAttrGroupMapper;
import com.ruoyi.product.domain.PmsAttrGroup;
import com.ruoyi.product.service.IPmsAttrGroupService;

/**
 * 属性分组Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-17
 */
@Service
public class PmsAttrGroupServiceImpl implements IPmsAttrGroupService 
{
    @Autowired
    private PmsAttrGroupMapper pmsAttrGroupMapper;

    /**
     * 查询属性分组
     * 
     * @param attrGroupId 属性分组主键
     * @return 属性分组
     */
    @Override
    public PmsAttrGroup selectPmsAttrGroupByAttrGroupId(Long attrGroupId)
    {
        return pmsAttrGroupMapper.selectPmsAttrGroupByAttrGroupId(attrGroupId);
    }

    /**
     * 查询属性分组列表
     * 
     * @param pmsAttrGroup 属性分组
     * @return 属性分组
     */
    @Override
    public List<PmsAttrGroup> selectPmsAttrGroupList(PmsAttrGroup pmsAttrGroup)
    {
        return pmsAttrGroupMapper.selectPmsAttrGroupList(pmsAttrGroup);
    }

    /**
     * 新增属性分组
     * 
     * @param pmsAttrGroup 属性分组
     * @return 结果
     */
    @Override
    public int insertPmsAttrGroup(PmsAttrGroup pmsAttrGroup)
    {
        return pmsAttrGroupMapper.insertPmsAttrGroup(pmsAttrGroup);
    }

    /**
     * 修改属性分组
     * 
     * @param pmsAttrGroup 属性分组
     * @return 结果
     */
    @Override
    public int updatePmsAttrGroup(PmsAttrGroup pmsAttrGroup)
    {
        return pmsAttrGroupMapper.updatePmsAttrGroup(pmsAttrGroup);
    }

    /**
     * 批量删除属性分组
     * 
     * @param attrGroupIds 需要删除的属性分组主键
     * @return 结果
     */
    @Override
    public int deletePmsAttrGroupByAttrGroupIds(Long[] attrGroupIds)
    {
        return pmsAttrGroupMapper.deletePmsAttrGroupByAttrGroupIds(attrGroupIds);
    }

    /**
     * 删除属性分组信息
     * 
     * @param attrGroupId 属性分组主键
     * @return 结果
     */
    @Override
    public int deletePmsAttrGroupByAttrGroupId(Long attrGroupId)
    {
        return pmsAttrGroupMapper.deletePmsAttrGroupByAttrGroupId(attrGroupId);
    }
}
