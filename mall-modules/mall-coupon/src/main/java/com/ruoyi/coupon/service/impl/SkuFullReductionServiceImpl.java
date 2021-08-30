package com.ruoyi.coupon.service.impl;

import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.coupon.domain.SkuFullReduction;
import com.ruoyi.coupon.domain.SkuLadder;
import com.ruoyi.coupon.mapper.MemberPriceMapper;
import com.ruoyi.coupon.mapper.SkuFullReductionMapper;
import com.ruoyi.coupon.service.ISkuFullReductionService;
import com.ruoyi.coupon.service.ISkuLadderService;
import com.ruoyi.mall.api.to.MemberPrice;
import com.ruoyi.mall.api.to.SkuReductionTo;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品满减信息Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-23
 */
@Service
public class SkuFullReductionServiceImpl implements ISkuFullReductionService
{
    @Autowired
    private SkuFullReductionMapper skuFullReductionMapper;

    @Autowired
    private ISkuLadderService skuLadderService;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询商品满减信息
     *
     * @param id 商品满减信息主键
     * @return 商品满减信息
     */
    @Override
    public SkuFullReduction selectSkuFullReductionById(Long id)
    {
        return skuFullReductionMapper.selectSkuFullReductionById(id);
    }

    /**
     * 查询商品满减信息列表
     *
     * @param skuFullReduction 商品满减信息
     * @return 商品满减信息
     */
    @Override
    public List<SkuFullReduction> selectSkuFullReductionList(SkuFullReduction skuFullReduction)
    {
        return skuFullReductionMapper.selectSkuFullReductionList(skuFullReduction);
    }

    /**
     * 新增商品满减信息
     *
     * @param skuFullReduction 商品满减信息
     * @return 结果
     */
    @Override
    public int insertSkuFullReduction(SkuFullReduction skuFullReduction)
    {
        return skuFullReductionMapper.insertSkuFullReduction(skuFullReduction);
    }

    /**
     * 修改商品满减信息
     *
     * @param skuFullReduction 商品满减信息
     * @return 结果
     */
    @Override
    public int updateSkuFullReduction(SkuFullReduction skuFullReduction)
    {
        return skuFullReductionMapper.updateSkuFullReduction(skuFullReduction);
    }

    /**
     * 批量删除商品满减信息
     *
     * @param ids 需要删除的商品满减信息主键
     * @return 结果
     */
    @Override
    public int deleteSkuFullReductionByIds(Long[] ids)
    {
        return skuFullReductionMapper.deleteSkuFullReductionByIds(ids);
    }

    /**
     * 删除商品满减信息信息
     *
     * @param id 商品满减信息主键
     * @return 结果
     */
    @Override
    public int deleteSkuFullReductionById(Long id)
    {
        return skuFullReductionMapper.deleteSkuFullReductionById(id);
    }

    /**
     * 保存商品的优惠信息
     */
    @Override
    public int addSkuSmsInfo(SkuReductionTo skuReductionTo) {
        int result = 0;
        //sku的优惠、满减等信息：mall_sms.sms_sku_ladder\sms_sku_full_reduction\sms_member_price
        //sms_sku_ladder
        Long skuId = skuReductionTo.getSkuId();
        SkuLadder skuLadder = new SkuLadder();
        skuLadder.setSkuId(skuId);
        skuLadder.setFullCount(skuReductionTo.getFullCount());
        skuLadder.setDiscount(skuReductionTo.getDiscount());
        skuLadder.setAddOther(skuReductionTo.getCountStatus());
        if (skuReductionTo.getFullCount() > 0) {
            result = skuLadderService.insertSkuLadder(skuLadder);
        }
        //sms_sku_full_reduction
        SkuFullReduction skuFullReduction = new SkuFullReduction();
        BeanUtils.copyBeanProp(skuFullReduction, skuReductionTo);
        if (skuFullReduction.getFullPrice().compareTo(new BigDecimal(0)) > 0) {
            result += insertSkuFullReduction(skuFullReduction);
        }
        //sms_member_price
        List<MemberPrice> memberPriceList = skuReductionTo.getMemberPrice();
        List<com.ruoyi.coupon.domain.MemberPrice> collect = memberPriceList.stream().map(item -> {
            com.ruoyi.coupon.domain.MemberPrice memberPrice = new com.ruoyi.coupon.domain.MemberPrice();
            memberPrice.setSkuId(skuId);
            memberPrice.setMemberLevelName(item.getName());
            memberPrice.setMemberLevelId(item.getId());
            memberPrice.setMemberPrice(item.getPrice());
            memberPrice.setAddOther(1);
            return memberPrice;
        }).filter(item -> item.getMemberPrice().compareTo(new BigDecimal(0)) > 0).collect(Collectors.toList());
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        MemberPriceMapper memberPriceMapper = sqlSession.getMapper(MemberPriceMapper.class);
        for (com.ruoyi.coupon.domain.MemberPrice memberPrice : collect) {
            result += memberPriceMapper.insertMemberPrice(memberPrice);
        }
        sqlSession.flushStatements();
        return result;
    }
}
