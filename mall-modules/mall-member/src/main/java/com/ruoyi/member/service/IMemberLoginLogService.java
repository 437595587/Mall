package com.ruoyi.member.service;

import java.util.List;
import com.ruoyi.member.domain.MemberLoginLog;

/**
 * 会员登录记录Service接口
 * 
 * @author xuxing
 * @date 2021-08-22
 */
public interface IMemberLoginLogService 
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
     * 批量删除会员登录记录
     * 
     * @param ids 需要删除的会员登录记录主键集合
     * @return 结果
     */
    public int deleteMemberLoginLogByIds(Long[] ids);

    /**
     * 删除会员登录记录信息
     * 
     * @param id 会员登录记录主键
     * @return 结果
     */
    public int deleteMemberLoginLogById(Long id);
}
