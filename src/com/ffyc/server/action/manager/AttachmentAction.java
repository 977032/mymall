package com.ffyc.server.action.manager;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.utils.PaginationSupport;

public class AttachmentAction 
extends BaseAction
{

 private static final long serialVersionUID = 1614228529340777647L;
 private PaginationSupport ps;
 private int pagesize = 12;
 private int page = 1;
 private String keyword;
 private String[] checkbox;
 private String gid;
 private String dm;
 private String d = "";
 private ArrayList<File> dirs;
 private String CKEditor;
 private String CKEditorFuncNum;
 private String langCode;
 
 public String execute()
   throws Exception
 {
   Attachment dc = new Attachment();
   String dir;
   if (!this.d.equals("")) {
     dir = "/upload/" + this.d;
   } else {
     dir = "/upload/";
   }
   dc.setPath(dir);
   if (StringUtils.isNotEmpty(this.keyword)) {
	   dc.setDetail(this.keyword);
   }
   this.ps = this.attachmentService.findPageByAttachment(dc, this.page,this.pagesize);
   this.dirs = getDir();
   return "success";
 }
 
 private ArrayList<File> getDir()
 {
   ArrayList<File> dirs = new ArrayList();
   String uploaddir = "upload";
   String ruploaddir = ServletActionContext.getServletContext().getRealPath(uploaddir);
   ruploaddir = ruploaddir.replace("\\", "/");
   File d = new File(ruploaddir);
   if ((!d.exists()) && (!d.isDirectory())) {
     d.mkdir();
   }
   File[] files = d.listFiles();
   for (int i = 0; i < files.length; i++)
   {
     File file = files[i];
     if ((file.exists()) && (file.isDirectory())) {
       dirs.add(file);
     }
   }
   return dirs;
 }
 
 public String delete()
   throws Exception
 {
   String type = "goods";
   if ((this.checkbox != null) && (this.checkbox.length > 0)) {
	   for (int i = 0; i < this.checkbox.length; i++)
	   {
	     Attachment da = this.attachmentService.findById(this.checkbox[i]);
	     if (da.getGood() != null)
	     {
	       setGid(da.getGoodd().getId());
	     }
	     else
	     {
	       type = "document";
	       setDm(da.getDocumentt().getId());
	     }
	     this.attachmentService.delete(da.getId());
	   }
   }
   
   if (type.equals("document")) {
     return "document";
   }
   return "success";
 }
 
 public String files_delete()
   throws Exception
 {
	 if ((this.checkbox != null) && (this.checkbox.length > 0)){
		 for (int i = 0; i < this.checkbox.length; i++)
		   {
		     Attachment da = this.attachmentService.findById(this.checkbox[i]);
		     if (da != null) {
		       this.attachmentService.delete(da.getId());
		     }
		 }
	 }
   
   return "success";
 }
 
 public PaginationSupport getPs()
 {
   return this.ps;
 }
 
 public void setPs(PaginationSupport ps)
 {
   this.ps = ps;
 }
 
 public String getDm()
 {
   return this.dm;
 }
 
 public void setDm(String dm)
 {
   this.dm = dm;
 }
 
 public int getPagesize()
 {
   return this.pagesize;
 }
 
 public void setPagesize(int pagesize)
 {
   this.pagesize = pagesize;
 }
 
 public int getPage()
 {
   return this.page;
 }
 
 public void setPage(int page)
 {
   this.page = page;
 }
 
 public String getKeyword()
 {
   return this.keyword;
 }
 
 public void setKeyword(String keyword)
 {
   this.keyword = keyword;
 }
 
 public String[] getCheckbox()
 {
   return this.checkbox;
 }
 
 public void setCheckbox(String[] checkbox)
 {
   this.checkbox = checkbox;
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
 
 public ArrayList<File> getDirs()
 {
   return this.dirs;
 }
 
 public void setDirs(ArrayList<File> dirs)
 {
   this.dirs = dirs;
 }
 
 public String getCKEditor()
 {
   return this.CKEditor;
 }
 
 public void setCKEditor(String cKEditor)
 {
   this.CKEditor = cKEditor;
 }
 
 public String getCKEditorFuncNum()
 {
   return this.CKEditorFuncNum;
 }
 
 public void setCKEditorFuncNum(String cKEditorFuncNum)
 {
   this.CKEditorFuncNum = cKEditorFuncNum;
 }
 
 public String getLangCode()
 {
   return this.langCode;
 }
 
 public void setLangCode(String langCode)
 {
   this.langCode = langCode;
 }
}
