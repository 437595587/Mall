package com.ruoyi.product.service;

import java.util.List;
import com.ruoyi.product.domain.PmsSpuImages;

/**
 * spu图片Service接口
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public interface IPmsSpuImagesService 
{
    /**
     * 查询spu图片
     * 
     * @param id spu图片主键
     * @return spu图片
     */
    public PmsSpuImages selectPmsSpuImagesById(Long id);

    /**
     * 查询spu图片列表
     * 
     * @param pmsSpuImages spu图片
     * @return spu图片集合
     */
    public List<PmsSpuImages> selectPmsSpuImagesList(PmsSpuImages pmsSpuImages);

    /**
     * 新增spu图片
     * 
     * @param pmsSpuImages spu图片
     * @return 结果
     */
    public int insertPmsSpuImages(PmsSpuImages pmsSpuImages);

    /**
     * 修改spu图片
     * 
     * @param pmsSpuImages spu图片
     * @return 结果
     */
    public int updatePmsSpuImages(PmsSpuImages pmsSpuImages);

    /**
     * 批量删除spu图片
     * 
     * @param ids 需要删除的spu图片主键集合
     * @return 结果
     */
    public int deletePmsSpuImagesByIds(Long[] ids);

    /**
     * 删除spu图片信息
     * 
     * @param id spu图片主键
     * @return 结果
     */
    public int deletePmsSpuImagesById(Long id);
}
