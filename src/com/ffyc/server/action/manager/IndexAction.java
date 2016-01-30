package com.ffyc.server.action.manager;

import java.util.List;
import java.util.Properties;

import com.ffyc.server.model.Good;


public class IndexAction
  extends BaseAction
{
  private static final long serialVersionUID = 2941587175713261188L;
  private int good_count = 0;
  private int inventory_count = 0;
  private int new_count = 0;
  private int hot_count = 0;
  private int btq_count = 0;
  private int promote_count = 0;
  private Properties props;
  private Runtime runtime;
  
  public String execute()
    throws Exception
  {
	Good dc = new Good();
	dc.setPromote("yes");
	List<Good> list = goodService.findByGood(dc);
	if(list!=null){
		this.promote_count = list.size();
	}
	
    this.new_count = findByKey("new");
    this.hot_count = findByKey("hot");
    this.btq_count = findByKey("btq");
    
    this.props = System.getProperties();
    this.runtime = Runtime.getRuntime();
    Runtime.getRuntime().maxMemory();
    Runtime.getRuntime().totalMemory();
    Runtime.getRuntime().availableProcessors();
    
    return "success";
  }
  
  private int findByKey(String key)
  {
    int count = 0;
    Good dc = new Good();
    dc.setKeyword(key);
    List<Good> list = goodService.findByGood(dc);
    if(list!=null){
    	count = list.size();
    }
    return count;
  }
  
  public int getGood_count()
  {
    return this.good_count;
  }
  
  public void setGood_count(int goodCount)
  {
    this.good_count = goodCount;
  }
  
  public int getInventory_count()
  {
    return this.inventory_count;
  }
  
  public void setInventory_count(int inventoryCount)
  {
    this.inventory_count = inventoryCount;
  }
  
  public int getNew_count()
  {
    return this.new_count;
  }
  
  public void setNew_count(int newCount)
  {
    this.new_count = newCount;
  }
  
  public int getHot_count()
  {
    return this.hot_count;
  }
  
  public void setHot_count(int hotCount)
  {
    this.hot_count = hotCount;
  }
  
  public int getBtq_count()
  {
    return this.btq_count;
  }
  
  public void setBtq_count(int btqCount)
  {
    this.btq_count = btqCount;
  }
  
  public int getPromote_count()
  {
    return this.promote_count;
  }
  
  public void setPromote_count(int promoteCount)
  {
    this.promote_count = promoteCount;
  }
  
  public Properties getProps()
  {
    return this.props;
  }
  
  public void setProps(Properties props)
  {
    this.props = props;
  }
  
  public Runtime getRuntime()
  {
    return this.runtime;
  }
  
  public void setRuntime(Runtime runtime)
  {
    this.runtime = runtime;
  }
}
