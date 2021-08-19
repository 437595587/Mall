package com.ruoyi.product.mapper;

import java.util.List;
import com.ruoyi.product.domain.PmsSpuComment;

/**
 * 商品评价Mapper接口
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public interface PmsSpuCommentMapper 
{
    /**
     * 查询商品评价
     * 
     * @param id 商品评价主键
     * @return 商品评价
     */
    public PmsSpuComment selectPmsSpuCommentById(Long id);

    /**
     * 查询商品评价列表
     * 
     * @param pmsSpuComment 商品评价
     * @return 商品评价集合
     */
    public List<PmsSpuComment> selectPmsSpuCommentList(PmsSpuComment pmsSpuComment);

    /**
     * 新增商品评价
     * 
     * @param pmsSpuComment 商品评价
     * @return 结果
     */
    public int insertPmsSpuComment(PmsSpuComment pmsSpuComment);

    /**
     * 修改商品评价
     * 
     * @param pmsSpuComment 商品评价
     * @return 结果
     */
    public int updatePmsSpuComment(PmsSpuComment pmsSpuComment);

    /**
     * 删除商品评价
     * 
     * @param id 商品评价主键
     * @return 结果
     */
    public int deletePmsSpuCommentById(Long id);

    /**
     * 批量删除商品评价
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePmsSpuCommentByIds(Long[] ids);
}
