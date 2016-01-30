package com.ffyc.server.action.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Manager;
import com.ffyc.server.model.Member;
import com.ffyc.server.utils.FileExt;

public class BaseUploadAction 
extends BaseAction
{
  private static final long serialVersionUID = -4893382008463058952L;
  protected File upload;
  protected String uploadContentType;
  protected String uploadFileName;
  protected String oldFileName;
  protected String savePath;
  protected String fextname;
  protected String attdetail = "";
  protected String attstatus = "";
  protected Integer attsort = Integer.valueOf(10);
  protected String langFileName;
  
  protected Attachment upload()
  {
	Attachment attachment =null;
    if (this.upload != null)
    {
      attachment = new Attachment();
      String tfilepath = ServletActionContext.getServletContext().getRealPath(getSavePath());
      File test = new File(tfilepath);
      if (!test.exists()) {
        test.mkdirs();
      }
      try
      {
        FileOutputStream fos = new FileOutputStream(tfilepath + "/" + getUploadFileName());
        FileInputStream fis = new FileInputStream(getUpload());
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
          fos.write(buffer, 0, len);
        }
        fos.close();
        fis.close();
      }
      catch (FileNotFoundException localFileNotFoundException) {}catch (IOException localIOException) {}
      attachment.setOname(getOldFileName());
      
      attachment.setId(getUuid());
      attachment.setName(getLangFileName());
      attachment.setFtype(this.fextname);
      attachment.setPath(getSavePath());
      Manager manager = getManagerFromSession();
      if(manager!=null){
          attachment.setManager(getManagerFromSession().getId());
      }
      attachment.setDetail(this.attdetail);
      attachment.setCtime(getTimestamp());
      attachment.setStatus(this.attstatus);
      attachment.setSort(this.attsort);
      this.attachmentService.save(attachment);
    }
    return attachment;
  }
  
  public File getUpload()
  {
    return this.upload;
  }
  
  public void setUpload(File upload)
  {
    this.upload = upload;
  }
  
  public String getUploadContentType()
  {
    return this.uploadContentType;
  }
  
  public void setUploadContentType(String uploadContentType)
  {
    this.uploadContentType = uploadContentType;
    System.out.println("文件类型:" + uploadContentType);
  }
  
  public String getUploadFileName()
  {
    return this.uploadFileName;
  }
  
  public void setUploadFileName(String uploadFileName)
  {
    this.oldFileName = uploadFileName;
    String id = getUuid().substring(1, 5);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
    String date = sdf.format(new Date());
    setFextname(FileExt.getExtension(uploadFileName));
    this.uploadFileName = (date + id + "." + FileExt.getExtension(uploadFileName));
    this.langFileName = this.uploadFileName;
  }
  
  public String getLangFileName()
  {
    return this.langFileName;
  }
  
  public void setLangFileName(String langFileName)
  {
    this.langFileName = langFileName;
  }
  
  public String getOldFileName()
  {
    return this.oldFileName;
  }
  
  public void setOldFileName(String oldFileName)
  {
    this.oldFileName = oldFileName;
  }
  
  public String getSavePath()
  {
    return this.savePath;
  }
  
  public void setSavePath(String savePath)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    String date = sdf.format(new Date());
    this.savePath = (savePath + "/" + date);
  }
  
  public String getFextname()
  {
    return this.fextname;
  }
  
  public void setFextname(String fextname)
  {
    this.fextname = fextname;
  }
  
  public String getAttdetail()
  {
    return this.attdetail;
  }
  
  public void setAttdetail(String attdetail)
  {
    this.attdetail = attdetail;
  }
  
  public String getAttstatus()
  {
    return this.attstatus;
  }
  
  public void setAttstatus(String attstatus)
  {
    this.attstatus = attstatus;
  }
  
  public Integer getAttsort()
  {
    return this.attsort;
  }
  
  public void setAttsort(Integer attsort)
  {
    this.attsort = attsort;
  }
}
