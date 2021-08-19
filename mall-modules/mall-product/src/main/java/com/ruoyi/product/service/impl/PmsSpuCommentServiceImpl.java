package com.ruoyi.product.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.PmsSpuCommentMapper;
import com.ruoyi.product.domain.PmsSpuComment;
import com.ruoyi.product.service.IPmsSpuCommentService;

/**
 * 商品评价Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-17
 */
@Service
public class PmsSpuCommentServiceImpl implements IPmsSpuCommentService 
{
    @Autowired
    private PmsSpuCommentMapper pmsSpuCommentMapper;

    /**
     * 查询商品评价
     * 
     * @param id 商品评价主键
     * @return 商品评价
     */
    @Override
    public PmsSpuComment selectPmsSpuCommentById(Long id)
    {
        return pmsSpuCommentMapper.selectPmsSpuCommentById(id);
    }

    /**
     * 查询商品评价列表
     * 
     * @param pmsSpuComment 商品评价
     * @return 商品评价
     */
    @Override
    public List<PmsSpuComment> selectPmsSpuCommentList(PmsSpuComment pmsSpuComment)
    {
        return pmsSpuCommentMapper.selectPmsSpuCommentList(pmsSpuComment);
    }

    /**
     * 新增商品评价
     * 
     * @param pmsSpuComment 商品评价
     * @return 结果
     */
    @Override
    public int insertPmsSpuComment(PmsSpuComment pmsSpuComment)
    {
        pmsSpuComment.setCreateTime(DateUtils.getNowDate());
        return pmsSpuCommentMapper.insertPmsSpuComment(pmsSpuComment);
    }

    /**
     * 修改商品评价
     * 
     * @param pmsSpuComment 商品评价
     * @return 结果
     */
    @Override
    public int updatePmsSpuComment(PmsSpuComment pmsSpuComment)
    {
        return pmsSpuCommentMapper.updatePmsSpuComment(pmsSpuComment);
    }

    /**
     * 批量删除商品评价
     * 
     * @param ids 需要删除的商品评价主键
     * @return 结果
     */
    @Override
    public int deletePmsSpuCommentByIds(Long[] ids)
    {
        return pmsSpuCommentMapper.deletePmsSpuCommentByIds(ids);
    }

    /**
     * 删除商品评价信息
     * 
     * @param id 商品评价主键
     * @return 结果
     */
    @Override
    public int deletePmsSpuCommentById(Long id)
    {
        return pmsSpuCommentMapper.deletePmsSpuCommentById(id);
    }
}
