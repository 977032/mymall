package com.ffyc.server.action.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Config;
import com.ffyc.server.model.Good;
import com.ffyc.server.utils.FileExt;
import com.ffyc.server.utils.ImageUtil;

public class AttachmentCreateAction 
extends BaseAction
{
 private static final long serialVersionUID = -3663446363851970396L;
 private String gid;
 private String d;
 private Good good;
 private Attachment attachment = new Attachment();
 private File upload;
 private String uploadContentType;
 private String uploadFileName;
 private String oldFileName;
 private String savePath;
 private String fextname;
 
 public String execute()
   throws Exception
 {
   this.good = this.goodService.findById(this.gid);
   return "success";
 }
 
 public String create()
   throws Exception
 {
   upload();
   if (this.attachment.getGoodd() != null)
   {
     setGid(this.attachment.getGoodd().getId());
     return "success";
   }
   setD(this.attachment.getDocumentt().getId());
   return "document";
 }
 
 private Attachment upload()
   throws Exception
 {
   if (this.upload != null)
   {
     System.out.println(getUploadContentType());
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
     this.attachment.setOname(getOldFileName());
     this.attachment.setId(getUuid());
     this.attachment.setName(getUploadFileName());
     this.attachment.setFtype(this.fextname);
     this.attachment.setPath(getSavePath());
     this.attachment.setManager(getManagerFromSession().getId());
     this.attachment.setCtime(getTimestamp());
     if (this.attachment.getGoodd() != null){
    	 this.attachment.setGood(this.attachment.getGoodd().getId());
     }
     if (this.attachment.getDocumentt() != null){
    	 this.attachment.setDocument(this.attachment.getDocumentt().getId());
     }
     this.attachmentService.save(this.attachment);
     if ((this.attachment.getGoodd() != null) && (this.attachment.getStatus().equals("default")))
     {
       Attachment dc = new Attachment();
       dc.setGood(this.attachment.getGood());
       dc.setStatus("default");
       this.attachmentService.findByAttachment(dc);
       List list = this.attachmentService.findByAttachment(dc);
       for (int i = 0; i < list.size(); i++)
       {
         Attachment am = (Attachment)list.get(i);
         if (!am.equals(this.attachment))
         {
           am.setStatus(Attachment.STATUS_NORMAL);
           this.attachmentService.update(am);
         }
       }
     }
     if ((this.attachment.getDocumentt() != null) && (this.attachment.getStatus().equals("default")))
     {
       Attachment dc = new Attachment();
       dc.setDocument(this.attachment.getDocumentt().getId());
       dc.setStatus("default");
       List list = this.attachmentService.findByAttachment(dc);
       for (int i = 0; i < list.size(); i++)
       {
         Attachment am = (Attachment)list.get(i);
         if (!am.equals(this.attachment))
         {
           am.setStatus(Attachment.STATUS_NORMAL);
           this.attachmentService.update(am);
         }
       }
     }
     if (this.attachment.getStatus().equals("default"))
     {
       File imageFile = new File(tfilepath + "/" + getUploadFileName());
       File thumbnailFile = new File(tfilepath + "/s_" + getUploadFileName());
       
       Config cmaxWidth = this.configService.findByProperty("good_thumbnail_maxWidth");
       Config cmaxHeight = this.configService.findByProperty("good_thumbnail_maxHeight");
       if (this.attachment.getDocumentt() != null)
       {
         cmaxWidth = this.configService.findByProperty("doc_thumbnail_maxWidth");
         cmaxHeight = this.configService.findByProperty("doc_thumbnail_maxHeight");
       }
       int maxWidth = Integer.valueOf(cmaxWidth.getValue()).intValue();
       int maxHeight = Integer.valueOf(cmaxHeight.getValue()).intValue();
       ImageUtil.createThumbnail(imageFile, thumbnailFile, maxWidth, maxHeight);
     }
   }
   return this.attachment;
 }
 
 public File getUpload()
 {
   return this.upload;
 }
 
 public void setUpload(File upload)
 {
   System.out.println(upload);
   this.upload = upload;
 }
 
 public String getUploadContentType()
 {
   return this.uploadContentType;
 }
 
 public void setUploadContentType(String uploadContentType)
 {
   this.uploadContentType = uploadContentType;
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
 
 public String getGid()
 {
   return this.gid;
 }
 
 public void setGid(String gid)
 {
   this.gid = gid;
 }
 
 public String getD()
 {
   return this.d;
 }
 
 public void setD(String d)
 {
   this.d = d;
 }
 
 public Good getGood()
 {
   return this.good;
 }
 
 public void setGood(Good good)
 {
   this.good = good;
 }
 
 public Attachment getAttachment()
 {
   return this.attachment;
 }
 
 public void setAttachment(Attachment attachment)
 {
   this.attachment = attachment;
 }
}
