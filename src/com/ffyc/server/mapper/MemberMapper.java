package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Member;
import com.ffyc.server.model.User;
import com.ffyc.server.model.UserBasicInfo;
import com.ffyc.server.vo.LoginVO;

public interface MemberMapper
{
    public Member findById(@Param("id") String id);
    
    public Member findByAccount(@Param("account") String account);

    public void save(Member member);

    public void update(Member member);
    
    public void delete(String id);
    
    public List<UserBasicInfo> searchMemberByName(@Param("name") String name,@Param("start") int start, @Param("count") int count);

	public int searchMemberByNameCount(@Param("name") String name);
    
    public int isExists(@Param("account") String account, @Param("password") String password);

    public void updatePassword(@Param("id") String id, @Param("password") String password);

    public List<Member> findAll();
    
    public List<Member> findByMember(@Param("member") Member member,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
    
    public int getCountByMember(@Param("member") Member member);
}
