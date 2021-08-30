package com.ruoyi.member.service;

import com.ruoyi.mall.api.to.SocialUser;
import com.ruoyi.mall.api.to.UserLoginTo;
import com.ruoyi.member.domain.Member;
import com.ruoyi.member.domain.vo.MemberRegisterVo;
import com.ruoyi.member.exception.PhoneExistException;
import com.ruoyi.member.exception.UsernameExistException;

import java.util.List;

/**
 * 会员Service接口
 *
 * @author xuxing
 * @date 2021-08-22
 */
public interface IMemberService
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
     * 批量删除会员
     *
     * @param ids 需要删除的会员主键集合
     * @return 结果
     */
    public int deleteMemberByIds(Long[] ids);

    /**
     * 删除会员信息
     *
     * @param id 会员主键
     * @return 结果
     */
    public int deleteMemberById(Long id);

    boolean register(MemberRegisterVo vo);

    void checkPhoneUnique(String phone) throws PhoneExistException;

    void checkUsernameUnique(String username) throws UsernameExistException;

    Member login(UserLoginTo to);

    Member login(SocialUser socialUser);
}
