package com.ruoyi.member.mapper;

import java.util.List;
import com.ruoyi.member.domain.MemberLoginLog;

/**
 * 会员登录记录Mapper接口
 * 
 * @author xuxing
 * @date 2021-08-22
 */
public interface MemberLoginLogMapper 
{
    /**
     * 查询会员登录记录
     * 
     * @param id 会员登录记录主键
     * @return 会员登录记录
     */
    public MemberLoginLog selectMemberLoginLogById(Long id);

    /**
     * 查询会员登录记录列表
     * 
     * @param memberLoginLog 会员登录记录
     * @return 会员登录记录集合
     */
    public List<MemberLoginLog> selectMemberLoginLogList(MemberLoginLog memberLoginLog);

    /**
     * 新增会员登录记录
     * 
     * @param memberLoginLog 会员登录记录
     * @return 结果
     */
    public int insertMemberLoginLog(MemberLoginLog memberLoginLog);

    /**
     * 修改会员登录记录
     * 
     * @param memberLoginLog 会员登录记录
     * @return 结果
     */
    public int updateMemberLoginLog(MemberLoginLog memberLoginLog);

    /**
     * 删除会员登录记录
     * 
     * @param id 会员登录记录主键
     * @return 结果
     */
    public int deleteMemberLoginLogById(Long id);

    /**
     * 批量删除会员登录记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemberLoginLogByIds(Long[] ids);
}
