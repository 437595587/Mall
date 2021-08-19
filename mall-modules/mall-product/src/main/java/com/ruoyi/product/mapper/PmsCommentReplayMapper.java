package com.ruoyi.product.mapper;

import java.util.List;
import com.ruoyi.product.domain.PmsCommentReplay;

/**
 * 商品评价回复关系Mapper接口
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public interface PmsCommentReplayMapper 
{
    /**
     * 查询商品评价回复关系
     * 
     * @param id 商品评价回复关系主键
     * @return 商品评价回复关系
     */
    public PmsCommentReplay selectPmsCommentReplayById(Long id);

    /**
     * 查询商品评价回复关系列表
     * 
     * @param pmsCommentReplay 商品评价回复关系
     * @return 商品评价回复关系集合
     */
    public List<PmsCommentReplay> selectPmsCommentReplayList(PmsCommentReplay pmsCommentReplay);

    /**
     * 新增商品评价回复关系
     * 
     * @param pmsCommentReplay 商品评价回复关系
     * @return 结果
     */
    public int insertPmsCommentReplay(PmsCommentReplay pmsCommentReplay);

    /**
     * 修改商品评价回复关系
     * 
     * @param pmsCommentReplay 商品评价回复关系
     * @return 结果
     */
    public int updatePmsCommentReplay(PmsCommentReplay pmsCommentReplay);

    /**
     * 删除商品评价回复关系
     * 
     * @param id 商品评价回复关系主键
     * @return 结果
     */
    public int deletePmsCommentReplayById(Long id);

    /**
     * 批量删除商品评价回复关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePmsCommentReplayByIds(Long[] ids);
}
