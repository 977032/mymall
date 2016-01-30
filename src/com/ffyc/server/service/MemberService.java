package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.model.Member;
import com.ffyc.server.utils.Notice;
import com.ffyc.server.utils.PaginationSupport;

public interface MemberService
  extends BaseService
{
  public Notice register(Member member, String validateCode);
  
  public Notice login(String account, String password, String validateCode);
  
  public void save(Member member);
  
  public void update(Member member);
  
  public void delete(String id);
  
  public Member findById(String id);
  
  public Member findByAccount(String account);
  
  public List<Member> findAll();
  
  public List<Member> findByMember(Member dc); 
  
  public List<Member> findByMember(Member dc,Integer startIndex,Integer pageSize); 
  
  public int getCountByMember(Member dc);
  
  public PaginationSupport findPageByMember(Member dc,Integer page,Integer pageSize);
  
  
}