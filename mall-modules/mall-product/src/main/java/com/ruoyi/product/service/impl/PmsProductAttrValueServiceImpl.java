package com.ruoyi.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.PmsProductAttrValueMapper;
import com.ruoyi.product.domain.PmsProductAttrValue;
import com.ruoyi.product.service.IPmsProductAttrValueService;

/**
 * spu属性值Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-17
 */
@Service
public class PmsProductAttrValueServiceImpl implements IPmsProductAttrValueService 
{
    @Autowired
    private PmsProductAttrValueMapper pmsProductAttrValueMapper;

    /**
     * 查询spu属性值
     * 
     * @param id spu属性值主键
     * @return spu属性值
     */
    @Override
    public PmsProductAttrValue selectPmsProductAttrValueById(Long id)
    {
        return pmsProductAttrValueMapper.selectPmsProductAttrValueById(id);
    }

    /**
     * 查询spu属性值列表
     * 
     * @param pmsProductAttrValue spu属性值
     * @return spu属性值
     */
    @Override
    public List<PmsProductAttrValue> selectPmsProductAttrValueList(PmsProductAttrValue pmsProductAttrValue)
    {
        return pmsProductAttrValueMapper.selectPmsProductAttrValueList(pmsProductAttrValue);
    }

    /**
     * 新增spu属性值
     * 
     * @param pmsProductAttrValue spu属性值
     * @return 结果
     */
    @Override
    public int insertPmsProductAttrValue(PmsProductAttrValue pmsProductAttrValue)
    {
        return pmsProductAttrValueMapper.insertPmsProductAttrValue(pmsProductAttrValue);
    }

    /**
     * 修改spu属性值
     * 
     * @param pmsProductAttrValue spu属性值
     * @return 结果
     */
    @Override
    public int updatePmsProductAttrValue(PmsProductAttrValue pmsProductAttrValue)
    {
        return pmsProductAttrValueMapper.updatePmsProductAttrValue(pmsProductAttrValue);
    }

    /**
     * 批量删除spu属性值
     * 
     * @param ids 需要删除的spu属性值主键
     * @return 结果
     */
    @Override
    public int deletePmsProductAttrValueByIds(Long[] ids)
    {
        return pmsProductAttrValueMapper.deletePmsProductAttrValueByIds(ids);
    }

    /**
     * 删除spu属性值信息
     * 
     * @param id spu属性值主键
     * @return 结果
     */
    @Override
    public int deletePmsProductAttrValueById(Long id)
    {
        return pmsProductAttrValueMapper.deletePmsProductAttrValueById(id);
    }
}
