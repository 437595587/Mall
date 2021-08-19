package com.ruoyi.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.PmsSpuImagesMapper;
import com.ruoyi.product.domain.PmsSpuImages;
import com.ruoyi.product.service.IPmsSpuImagesService;

/**
 * spu图片Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-17
 */
@Service
public class PmsSpuImagesServiceImpl implements IPmsSpuImagesService 
{
    @Autowired
    private PmsSpuImagesMapper pmsSpuImagesMapper;

    /**
     * 查询spu图片
     * 
     * @param id spu图片主键
     * @return spu图片
     */
    @Override
    public PmsSpuImages selectPmsSpuImagesById(Long id)
    {
        return pmsSpuImagesMapper.selectPmsSpuImagesById(id);
    }

    /**
     * 查询spu图片列表
     * 
     * @param pmsSpuImages spu图片
     * @return spu图片
     */
    @Override
    public List<PmsSpuImages> selectPmsSpuImagesList(PmsSpuImages pmsSpuImages)
    {
        return pmsSpuImagesMapper.selectPmsSpuImagesList(pmsSpuImages);
    }

    /**
     * 新增spu图片
     * 
     * @param pmsSpuImages spu图片
     * @return 结果
     */
    @Override
    public int insertPmsSpuImages(PmsSpuImages pmsSpuImages)
    {
        return pmsSpuImagesMapper.insertPmsSpuImages(pmsSpuImages);
    }

    /**
     * 修改spu图片
     * 
     * @param pmsSpuImages spu图片
     * @return 结果
     */
    @Override
    public int updatePmsSpuImages(PmsSpuImages pmsSpuImages)
    {
        return pmsSpuImagesMapper.updatePmsSpuImages(pmsSpuImages);
    }

    /**
     * 批量删除spu图片
     * 
     * @param ids 需要删除的spu图片主键
     * @return 结果
     */
    @Override
    public int deletePmsSpuImagesByIds(Long[] ids)
    {
        return pmsSpuImagesMapper.deletePmsSpuImagesByIds(ids);
    }

    /**
     * 删除spu图片信息
     * 
     * @param id spu图片主键
     * @return 结果
     */
    @Override
    public int deletePmsSpuImagesById(Long id)
    {
        return pmsSpuImagesMapper.deletePmsSpuImagesById(id);
    }
}
