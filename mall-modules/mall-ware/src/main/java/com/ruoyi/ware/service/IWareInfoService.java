package com.ruoyi.ware.service;

import com.ruoyi.ware.domain.WareInfo;
import com.ruoyi.ware.domain.vo.FareVo;

import java.util.List;

/**
 * 仓库信息Service接口
 *
 * @author xuxing
 * @date 2021-08-23
 */
public interface IWareInfoService
{
    /**
     * 查询仓库信息
     *
     * @param id 仓库信息主键
     * @return 仓库信息
     */
    public WareInfo selectWareInfoById(Long id);

    /**
     * 查询仓库信息列表
     *
     * @param wareInfo 仓库信息
     * @return 仓库信息集合
     */
    public List<WareInfo> selectWareInfoList(WareInfo wareInfo);

    /**
     * 新增仓库信息
     *
     * @param wareInfo 仓库信息
     * @return 结果
     */
    public int insertWareInfo(WareInfo wareInfo);

    /**
     * 修改仓库信息
     *
     * @param wareInfo 仓库信息
     * @return 结果
     */
    public int updateWareInfo(WareInfo wareInfo);

    /**
     * 批量删除仓库信息
     *
     * @param ids 需要删除的仓库信息主键集合
     * @return 结果
     */
    public int deleteWareInfoByIds(Long[] ids);

    /**
     * 删除仓库信息信息
     *
     * @param id 仓库信息主键
     * @return 结果
     */
    public int deleteWareInfoById(Long id);

    /**
     * 根据用户的收获地址计算运费
     * @param addrId 地址id
     * @return 运费
     */
    FareVo selectFare(Long addrId);
}
