package com.ffyc.server.service;

import com.ffyc.server.model.Point;
import java.util.List;

public interface PointService
  extends BaseService
{
  public void save(Point point);
  
  public void update(Point point);
  
  public void delete(String id);
  
  public Point openAccount();
  
  public Point findById(String id);
  
  public List<Point> findAll();

}
