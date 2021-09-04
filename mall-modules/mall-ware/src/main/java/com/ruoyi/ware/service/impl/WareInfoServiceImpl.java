package com.ruoyi.ware.service.impl;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.RemoteMemberService;
import com.ruoyi.mall.api.vo.MemberReceiveAddress;
import com.ruoyi.ware.domain.WareInfo;
import com.ruoyi.ware.domain.vo.FareVo;
import com.ruoyi.ware.mapper.WareInfoMapper;
import com.ruoyi.ware.service.IWareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 仓库信息Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-23
 */
@Service
public class WareInfoServiceImpl implements IWareInfoService
{
    @Autowired
    private WareInfoMapper wareInfoMapper;

    @Autowired
    RemoteMemberService remoteMemberService;

    /**
     * 查询仓库信息
     *
     * @param id 仓库信息主键
     * @return 仓库信息
     */
    @Override
    public WareInfo selectWareInfoById(Long id)
    {
        return wareInfoMapper.selectWareInfoById(id);
    }

    /**
     * 查询仓库信息列表
     *
     * @param wareInfo 仓库信息
     * @return 仓库信息
     */
    @Override
    public List<WareInfo> selectWareInfoList(WareInfo wareInfo)
    {
        return wareInfoMapper.selectWareInfoList(wareInfo);
    }

    /**
     * 新增仓库信息
     *
     * @param wareInfo 仓库信息
     * @return 结果
     */
    @Override
    public int insertWareInfo(WareInfo wareInfo)
    {
        return wareInfoMapper.insertWareInfo(wareInfo);
    }

    /**
     * 修改仓库信息
     *
     * @param wareInfo 仓库信息
     * @return 结果
     */
    @Override
    public int updateWareInfo(WareInfo wareInfo)
    {
        return wareInfoMapper.updateWareInfo(wareInfo);
    }

    /**
     * 批量删除仓库信息
     *
     * @param ids 需要删除的仓库信息主键
     * @return 结果
     */
    @Override
    public int deleteWareInfoByIds(Long[] ids)
    {
        return wareInfoMapper.deleteWareInfoByIds(ids);
    }

    /**
     * 删除仓库信息信息
     *
     * @param id 仓库信息主键
     * @return 结果
     */
    @Override
    public int deleteWareInfoById(Long id)
    {
        return wareInfoMapper.deleteWareInfoById(id);
    }

    @Override
    public FareVo selectFare(Long addrId) {
        R<MemberReceiveAddress> r = remoteMemberService.getAddressInfo(addrId);
        if (r.getCode() == 200) {
            MemberReceiveAddress data = r.getData();
            String phone = data.getPhone();
            String substring = phone.substring(phone.length() - 1);
            BigDecimal fare = new BigDecimal(substring);
            FareVo fareVo = new FareVo();
            fareVo.setAddress(data);
            fareVo.setFare(fare);
            return fareVo;
        }
        return null;
    }
}
