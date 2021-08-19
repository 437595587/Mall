package com.ruoyi.product.service.impl;

import com.ruoyi.product.domain.PmsCategory;
import com.ruoyi.product.mapper.PmsCategoryMapper;
import com.ruoyi.product.service.IPmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类管理Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-18
 */
@Service
public class PmsCategoryServiceImpl implements IPmsCategoryService
{
    @Autowired
    private PmsCategoryMapper pmsCategoryMapper;

    /**
     * 查询分类管理
     *
     * @param catId 分类管理主键
     * @return 分类管理
     */
    @Override
    public PmsCategory selectPmsCategoryByCatId(Long catId)
    {
        return pmsCategoryMapper.selectPmsCategoryByCatId(catId);
    }

    /**
     * 查询分类管理列表
     *
     * @param pmsCategory 分类管理
     * @return 分类管理
     */
    @Override
    public List<PmsCategory> selectPmsCategoryList(PmsCategory pmsCategory)
    {
        List<PmsCategory> pmsCategories = pmsCategoryMapper.selectPmsCategoryList(pmsCategory);
        //过滤掉showStatus为0的数据
        List<PmsCategory> filterList = pmsCategories.stream().filter(item -> item.getShowStatus() == 0).collect(Collectors.toList());
        for (int i = 0; i < pmsCategories.size(); i++) {
            PmsCategory item = pmsCategories.get(i);
            for (PmsCategory filter : filterList) {
                if (filter.getCatId().equals(item.getCatId())) {
                    pmsCategories.remove(i);
                    i--;
                    removeFilterParent(item, pmsCategories);
                }
            }
        }
        return pmsCategories;
    }

    private void removeFilterParent(PmsCategory parent, List<PmsCategory> pmsCategories) {
        for (int i = 0; i < pmsCategories.size(); i++) {
            PmsCategory item = pmsCategories.get(i);
            if (item.getParentCid().equals(parent.getCatId())) {
                pmsCategories.remove(i);
                i--;
                removeFilterParent(item, pmsCategories);
            }
        }
    }

    /**
     * 新增分类管理
     *
     * @param pmsCategory 分类管理
     * @return 结果
     */
    @Override
    public int insertPmsCategory(PmsCategory pmsCategory)
    {
        return pmsCategoryMapper.insertPmsCategory(pmsCategory);
    }

    /**
     * 修改分类管理
     *
     * @param pmsCategory 分类管理
     * @return 结果
     */
    @Override
    public int updatePmsCategory(PmsCategory pmsCategory)
    {
        return pmsCategoryMapper.updatePmsCategory(pmsCategory);
    }

    /**
     * 批量删除分类管理
     *
     * @param catIds 需要删除的分类管理主键
     * @return 结果
     */
    @Override
    public int deletePmsCategoryByCatIds(Long[] catIds)
    {
        return pmsCategoryMapper.deletePmsCategoryByCatIds(catIds);
    }

    /**
     * 删除分类管理信息
     *
     * @param catId 分类管理主键
     * @return 结果
     */
    @Override
    public int deletePmsCategoryByCatId(Long catId)
    {
        return pmsCategoryMapper.deletePmsCategoryByCatId(catId);
    }
}
