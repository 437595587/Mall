package com.ruoyi.ware.mapper;

import java.util.List;
import com.ruoyi.ware.domain.WareInfo;

/**
 * 仓库信息Mapper接口
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public interface WareInfoMapper 
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
     * 删除仓库信息
     * 
     * @param id 仓库信息主键
     * @return 结果
     */
    public int deleteWareInfoById(Long id);

    /**
     * 批量删除仓库信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWareInfoByIds(Long[] ids);
}
