package com.ruoyi.member.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.mall.api.to.SocialUser;
import com.ruoyi.mall.api.to.UserLoginTo;
import com.ruoyi.member.domain.Member;
import com.ruoyi.member.domain.MemberLevel;
import com.ruoyi.member.domain.vo.MemberRegisterVo;
import com.ruoyi.member.exception.PhoneExistException;
import com.ruoyi.member.exception.UsernameExistException;
import com.ruoyi.member.mapper.MemberMapper;
import com.ruoyi.member.service.IMemberLevelService;
import com.ruoyi.member.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * 会员Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-22
 */
@Service
public class MemberServiceImpl implements IMemberService
{
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private IMemberLevelService memberLevelService;

    /**
     * 查询会员
     *
     * @param id 会员主键
     * @return 会员
     */
    @Override
    public Member selectMemberById(Long id)
    {
        return memberMapper.selectMemberById(id);
    }

    /**
     * 查询会员列表
     *
     * @param member 会员
     * @return 会员
     */
    @Override
    public List<Member> selectMemberList(Member member)
    {
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增会员
     *
     * @param member 会员
     * @return 结果
     */
    @Override
    public int insertMember(Member member)
    {
        member.setCreateTime(DateUtils.getNowDate());
        return memberMapper.insertMember(member);
    }

    /**
     * 修改会员
     *
     * @param member 会员
     * @return 结果
     */
    @Override
    public int updateMember(Member member)
    {
        return memberMapper.updateMember(member);
    }

    /**
     * 批量删除会员
     *
     * @param ids 需要删除的会员主键
     * @return 结果
     */
    @Override
    public int deleteMemberByIds(Long[] ids)
    {
        return memberMapper.deleteMemberByIds(ids);
    }

    /**
     * 删除会员信息
     *
     * @param id 会员主键
     * @return 结果
     */
    @Override
    public int deleteMemberById(Long id)
    {
        return memberMapper.deleteMemberById(id);
    }

    @Override
    public boolean register(MemberRegisterVo vo) {
        Member member = new Member();
        //设置默认等级
        MemberLevel level = memberLevelService.selectDefaultLevel();
        member.setLevelId(level.getId());
        //设置
        //检查用户名和手机号是否唯一
        checkPhoneUnique(vo.getPhone());
        checkUsernameUnique(vo.getUsername());

        member.setMobile(vo.getPhone());
        member.setUsername(vo.getUsername());
        member.setNickname(vo.getUsername());
        //加密存储
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(vo.getPassword());
        member.setPassword(encode);
        return memberMapper.insertMember(member) > 0;

    }
    @Override
    public void checkPhoneUnique(String phone) throws PhoneExistException {
        int count = memberMapper.selectCountByPhone(phone);
        if (count > 0) {
            throw new PhoneExistException();
        }
    }

    @Override
    public void checkUsernameUnique(String username) throws UsernameExistException {
        int count =memberMapper.selectCountByUsername(username);
        if (count > 0) {
            throw new UsernameExistException();
        }
    }

    @Override
    public Member login(UserLoginTo to) {
        String loginAccount = to.getLoginAccount();
        String password = to.getPassword();
        Member member = memberMapper.selectMemberByLoginAccount(loginAccount);
        if (member == null) {
            return null;
        } else {
            String passwordDb = member.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //密码匹配
            boolean matches = passwordEncoder.matches(password, passwordDb);
            if (matches) {
                return member;
            } else {
                return null;
            }
        }
    }

    @Override
    public Member login(SocialUser socialUser) {
        Long id = socialUser.getId();
        Member memberDb = memberMapper.selectCountBySocialUid(id);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.getNowDate());
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expiresIn = df.format(calendar.getTime());
        if (memberDb != null) {
            Member member = new Member();
            member.setId(memberDb.getId());
            member.setAccessToken(socialUser.getAccess_token());
            member.setExpiresIn(expiresIn);
            memberMapper.updateMember(member);
            memberDb.setAccessToken(socialUser.getAccess_token());
            member.setExpiresIn(expiresIn);
            return memberDb;
        } else {
            Member member = new Member();
            member.setNickname(socialUser.getName());
            member.setSocialUid(id.toString());
            member.setAccessToken(socialUser.getAccess_token());
            member.setExpiresIn(expiresIn);
            member.setHeader(socialUser.getAvatar_url());
            member.setEmail(socialUser.getEmail());
            memberMapper.insertMember(member);
            return member;
        }
    }
}
