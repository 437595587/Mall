package com.ruoyi.member.mapper;

import com.ruoyi.member.domain.Member;

import java.util.List;

/**
 * 会员Mapper接口
 *
 * @author xuxing
 * @date 2021-08-22
 */
public interface MemberMapper
{
    /**
     * 查询会员
     *
     * @param id 会员主键
     * @return 会员
     */
    public Member selectMemberById(Long id);

    /**
     * 查询会员列表
     *
     * @param member 会员
     * @return 会员集合
     */
    public List<Member> selectMemberList(Member member);

    /**
     * 新增会员
     *
     * @param member 会员
     * @return 结果
     */
    public int insertMember(Member member);

    /**
     * 修改会员
     *
     * @param member 会员
     * @return 结果
     */
    public int updateMember(Member member);

    /**
     * 删除会员
     *
     * @param id 会员主键
     * @return 结果
     */
    public int deleteMemberById(Long id);

    /**
     * 批量删除会员
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemberByIds(Long[] ids);

    int selectCountByPhone(String phone);

    int selectCountByUsername(String username);

    Member selectMemberByLoginAccount(String loginAccount);

    Member selectCountBySocialUid(Long id);
}
