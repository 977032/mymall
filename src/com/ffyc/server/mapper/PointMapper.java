package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Member;
import com.ffyc.server.model.Point;

public interface PointMapper
{
  public void save(Point point);
  
  public void update(Point point);
  
  public void delete(@Param("id") String id);
  
  public Point findById(@Param("id") String id);
  
  public List<Point> findAll();
  
  public List<Member> getMembers(@Param("id") String id);
}
