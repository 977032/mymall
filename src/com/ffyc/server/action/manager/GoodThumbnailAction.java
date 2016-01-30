package com.ffyc.server.action.manager;

import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Config;
import com.ffyc.server.model.Good;
import com.ffyc.server.utils.ImageUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


public class GoodThumbnailAction
  extends BaseAction
{
  private static final long serialVersionUID = 7389024432374165780L;
  private String gid;
  private Good good;
  private int total = 0;
  private int complete = 0;
  
  public String execute()
    throws Exception
  {
    this.good = this.goodService.findById(this.gid);
    generateDefault(this.good);
    return "success";
  }
  
  public String createAll()
    throws Exception
  {
    return "success";
  }
  
  public String createAllDo()
    throws Exception
  {
    List list = this.goodService.findAll();
    for (int i = 0; i < list.size(); i++)
    {
      Good good = (Good)list.get(i);
      Iterator it = good.getAttachments().iterator();
      generate(good);
      setComplete(i);
    }
    return "success";
  }
  
  private void generateDefault(Good good)
  {
	Attachment dc = new Attachment();
	dc.setGood(good.getId());
	dc.setStatus(Attachment.STATUS_DEFAULT);
	List<Attachment> list = attachmentService.findByAttachment(dc);
    for (int i = 0; i < list.size(); i++)
    {
      Attachment am = (Attachment)list.get(i);
      String ipath = ServletActionContext.getServletContext().getRealPath(am.getPath() + "/" + am.getName());
      File imageFile = new File(ipath);
      String tpath = ServletActionContext.getServletContext().getRealPath(am.getPath() + "/s_" + am.getName());
      File thumbnailFile = new File(tpath);
      Config cmaxWidth = this.configService.findByProperty("good_thumbnail_maxWidth");
      Config cmaxHeight = this.configService.findByProperty("good_thumbnail_maxHeight");
      int maxWidth = Integer.valueOf(cmaxWidth.getValue()).intValue();
      int maxHeight = Integer.valueOf(cmaxHeight.getValue()).intValue();
      if (imageFile.exists()) {
        try
        {
          ImageUtil.createThumbnail(imageFile, thumbnailFile, maxWidth, maxHeight);
          this.logger.debug(good.getName() + ":" + am.getName() + "：生成缩略图。");
        }
        catch (IOException e)
        {
          System.out.println("找不到文件路径：" + imageFile.getAbsolutePath());
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
  }
  
  private void generate(Good good)
  {
    Iterator it = good.getAttachments().iterator();
    while (it.hasNext())
    {
      Attachment am = (Attachment)it.next();
      String ipath = ServletActionContext.getServletContext().getRealPath(am.getPath() + "/" + am.getName());
      File imageFile = new File(ipath);
      String tpath = ServletActionContext.getServletContext().getRealPath(am.getPath() + "/s_" + am.getName());
      File thumbnailFile = new File(tpath);
      Config cmaxWidth = this.configService.findByProperty("good_thumbnail_maxWidth");
      Config cmaxHeight = this.configService.findByProperty("good_thumbnail_maxHeight");
      int maxWidth = Integer.valueOf(cmaxWidth.getValue()).intValue();
      int maxHeight = Integer.valueOf(cmaxHeight.getValue()).intValue();
      if (imageFile.exists()) {
        try
        {
          ImageUtil.createThumbnail(imageFile, thumbnailFile, maxWidth, maxHeight);
          this.logger.debug(good.getName() + ":" + am.getName() + "：生成缩略图。");
        }
        catch (IOException e)
        {
          System.out.println("找不到文件路径：" + imageFile.getAbsolutePath());
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
  }
  
  public String getId()
  {
    return this.gid;
  }
  
  public void setGid(String gid)
  {
    this.gid = gid;
  }
  
  public Good getGood()
  {
    return this.good;
  }
  
  public void setGood(Good good)
  {
    this.good = good;
  }
  
  public int getTotal()
  {
    return this.total;
  }
  
  public void setTotal(int total)
  {
    this.total = total;
  }
  
  public int getComplete()
  {
    return this.complete;
  }
  
  public void setComplete(int complete)
  {
    this.complete = complete;
  }
}
