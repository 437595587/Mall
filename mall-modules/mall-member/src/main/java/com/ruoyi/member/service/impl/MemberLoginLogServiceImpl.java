package com.ruoyi.member.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.member.mapper.MemberLoginLogMapper;
import com.ruoyi.member.domain.MemberLoginLog;
import com.ruoyi.member.service.IMemberLoginLogService;

/**
 * 会员登录记录Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-22
 */
@Service
public class MemberLoginLogServiceImpl implements IMemberLoginLogService 
{
    @Autowired
    private MemberLoginLogMapper memberLoginLogMapper;

    /**
     * 查询会员登录记录
     * 
     * @param id 会员登录记录主键
     * @return 会员登录记录
     */
    @Override
    public MemberLoginLog selectMemberLoginLogById(Long id)
    {
        return memberLoginLogMapper.selectMemberLoginLogById(id);
    }

    /**
     * 查询会员登录记录列表
     * 
     * @param memberLoginLog 会员登录记录
     * @return 会员登录记录
     */
    @Override
    public List<MemberLoginLog> selectMemberLoginLogList(MemberLoginLog memberLoginLog)
    {
        return memberLoginLogMapper.selectMemberLoginLogList(memberLoginLog);
    }

    /**
     * 新增会员登录记录
     * 
     * @param memberLoginLog 会员登录记录
     * @return 结果
     */
    @Override
    public int insertMemberLoginLog(MemberLoginLog memberLoginLog)
    {
        memberLoginLog.setCreateTime(DateUtils.getNowDate());
        return memberLoginLogMapper.insertMemberLoginLog(memberLoginLog);
    }

    /**
     * 修改会员登录记录
     * 
     * @param memberLoginLog 会员登录记录
     * @return 结果
     */
    @Override
    public int updateMemberLoginLog(MemberLoginLog memberLoginLog)
    {
        return memberLoginLogMapper.updateMemberLoginLog(memberLoginLog);
    }

    /**
     * 批量删除会员登录记录
     * 
     * @param ids 需要删除的会员登录记录主键
     * @return 结果
     */
    @Override
    public int deleteMemberLoginLogByIds(Long[] ids)
    {
        return memberLoginLogMapper.deleteMemberLoginLogByIds(ids);
    }

    /**
     * 删除会员登录记录信息
     * 
     * @param id 会员登录记录主键
     * @return 结果
     */
    @Override
    public int deleteMemberLoginLogById(Long id)
    {
        return memberLoginLogMapper.deleteMemberLoginLogById(id);
    }
}
