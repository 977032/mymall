package com.ffyc.server.action.publicer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Category;
import com.ffyc.server.model.Channel;
import com.ffyc.server.model.ChannelContent;
import com.ffyc.server.model.Config;
import com.ffyc.server.model.Document;
import com.ffyc.server.model.DocumentCategory;
import com.ffyc.server.model.Good;
import com.ffyc.server.utils.PaginationSupport;

public class ChannelAction 
extends BaseAction
{
  private static final long serialVersionUID = -8818779998128517195L;
  private String c = "home";
  private Channel channel;
  private int page = 1;
  private String t = "";
  private String d;
  private Document document;
  private List brandlist;
  private String cg;
  private Category category;
  private String dg;
  private DocumentCategory doccate;
  private String keyword;
  
  public String execute()
    throws Exception
  {
    
    Config siteClose = this.configService.findByProperty("siteClose");
    if ((siteClose != null) && (siteClose.getValue() != null) && (siteClose.getValue().equals("enable"))) {
      return "siteclose";
    }
    System.out.println(getDefLocale().getName());
    
    this.brandlist = this.brandService.findAll();
    this.channel = this.channelService.findById(this.c);
    if (this.channel != null)
    {
      GlobalChannelContent(this.map);
      this.template = (this.channel.getTemplatee().getPath() + "/" + getDefLocale().getId() + "_" + this.channel.getTemplatee().getName());
      
      ChannelContent dcc = new ChannelContent();
      dcc.setChannel(this.channel.getId());
      dcc.setLocale(getDefLocale().getId());
      List listc = this.channelContentService.findByChannelContent(dcc);
      Iterator it = listc.iterator();
      while (it.hasNext())
      {
        ChannelContent chcontent = (ChannelContent)it.next();
        if ((this.t.equals("v")) && (!chcontent.getDetail().equals("yes")))
        {
          this.logger.debug("t=v，chcontent.getDetail()!=yes，显示详细页，不包括类型为no的频道内容。");
        }
        else if (chcontent.getType().equals(ChannelContent.TYPE_DMENU))
        {
          List<DocumentCategory> list = new ArrayList();
          if (chcontent.getDoccate() != null)
          {
        	DocumentCategory dc = new DocumentCategory();
        	dc.setPid(chcontent.getDoccate());
        	dc.setLocale(getDefLocale().getId());
            list = this.documentCategoryService.findByDocumentCategory(dc);
          }
          else
          {
            list = this.documentCategoryService.findAllRoot(getDefLocale());
          }
          this.map.put(chcontent.getMarker(), list);
          this.map.put(chcontent.getMarker() + "_CurDoccate", chcontent.getDoccate());
        }
        else if (chcontent.getType().equals(ChannelContent.TYPE_DLIST))
        {
          if (StringUtils.isNotEmpty(this.dg))
          {
            this.doccate = this.documentCategoryService.findById(this.dg);
            this.logger.debug("dg is not NULL, Doccate is " + this.doccate.getName());
          }
          else
          {
            this.doccate = chcontent.getDoccatee();
            this.logger.debug("dg is NULL, Doccate is " + this.doccate.getName());
          }

          List<Document> list = this.documentService.findByDocumentOrNodePathLike(getDefLocale().getId(), doccate.getId(), null);
          int totalCount = list.size();
          int startIndex = chcontent.getOffset().intValue();
          int pageSize = chcontent.getNumber().intValue();
          PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
          this.map.put(chcontent.getMarker(), ps);
          this.map.put("CurDoccate", this.doccate);
        }
        else if (chcontent.getType().equals(ChannelContent.TYPE_DRCLIST))
        {
          if (StringUtils.isNotEmpty(this.dg))
          {
            this.doccate = this.documentCategoryService.findById(this.dg);
            this.logger.debug("dg is not NULL, Doccate is " + this.doccate.getName());
          }
          else
          {
            this.doccate = chcontent.getDoccatee();
            this.logger.debug("dg is NULL, Doccate is " + this.doccate.getName());
          }
          
          List<Document> list = this.documentService.findByDocumentOrNodePathLike(getDefLocale().getId(), doccate.getId(), Document.MARKER_RECOMMAND);
          int totalCount = list.size();
          int startIndex = chcontent.getOffset().intValue();
          int pageSize = chcontent.getNumber().intValue();
          PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
          
          this.map.put(chcontent.getMarker(), ps);
          this.map.put("CurDoccate", this.doccate);
        }
        else if (chcontent.getType().equals(ChannelContent.TYPE_DPAGELIST))
        {
          if (StringUtils.isNotEmpty(this.dg))
          {
            this.doccate = this.documentCategoryService.findById(this.dg);
          }
          else
          {
            this.doccate = chcontent.getDoccatee();
          }

          List<Document> list = this.documentService.findByDocumentOrNodePathLike(getDefLocale().getId(), doccate.getId(), null);
          int totalCount = list.size();
          int startIndex = chcontent.getOffset().intValue();
          int pageSize = chcontent.getNumber().intValue();
          PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
          
          this.map.put(chcontent.getMarker(), ps);
          this.map.put("CurDoccate", this.doccate);
        }
        else if (chcontent.getType().equals(ChannelContent.TYPE_GMENU))
        {
          List<Category> list = new ArrayList();
          if (chcontent.getCategory() != null)
          {
        	Category dc = new Category();
        	dc.setPid(chcontent.getCategory());
        	dc.setLocale(getDefLocale().getId());
            list = this.categoryService.findByCategory(dc);
          }
          else
          {
            list = this.categoryService.findAllRoot(getDefLocale());
          }
          this.map.put(chcontent.getMarker(), list);
          this.map.put(chcontent.getMarker() + "_CurCategory", chcontent.getCategory());
        }
        else if (chcontent.getType().equals(ChannelContent.TYPE_GLIST))
        {
          if (StringUtils.isNotEmpty(this.cg))
          {
            this.category = this.categoryService.findById(this.cg);
          }
          else if (chcontent.getCategory() != null)
          {
            this.category = chcontent.getCategoryy();
          }
          Set<String> keySet = new HashSet<String>();
          String[] keys = chcontent.getKeys();
          if ((keys != null) && (keys.length > 0))
          {
            for (int i = 0; i < keys.length; i++)
            {
              String key = keys[i];
              keySet.add(key);
            }
          }
          if (this.keyword != null) {
        	  keySet.add(this.keyword);
          }

          String[] keywords = null;
          if(keySet.size()>0){
        	  keywords = (String[])keySet.toArray();
          }
          String locale = getDefLocale().getId();
          List<Good> list = this.goodService.findByLocalAndCategory(locale, category.getId(), null, keywords);
          int totalCount = list.size();
          int startIndex = chcontent.getOffset().intValue();
          int pageSize = chcontent.getNumber().intValue();
          PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
          this.map.put(chcontent.getMarker(), ps);
          this.map.put(chcontent.getMarker() + "_CurCategory", this.category);
        }
        else if (chcontent.getType().equals(ChannelContent.TYPE_GRCLIST))
        {
          if (StringUtils.isNotEmpty(this.cg))
          {
            this.category = this.categoryService.findById(this.cg);
          }
          else if (chcontent.getCategory() != null)
          {
            this.category = chcontent.getCategoryy();
          }
          Set<String> keySet = new HashSet<String>();
          String[] keys = chcontent.getKeys();
          if ((keys != null) && (keys.length > 0))
          {
            for (int i = 0; i < keys.length; i++)
            {
              String key = keys[i];
              keySet.add(key);
            }
          }
          if (this.keyword != null) {
        	  keySet.add(this.keyword);
          }
          
          String[] keywords = null;
          if(keySet.size()>0){
        	  keywords = (String[])keySet.toArray();
          }
          String locale = getDefLocale().getId();
          List<Good> list = this.goodService.findByLocalAndCategory(locale, category.getId(), Good.STATUS_RECOMMAND, keywords);
          int totalCount = list.size();
          int startIndex = chcontent.getOffset().intValue();
          int pageSize = chcontent.getNumber().intValue();
          PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);          
          this.map.put(chcontent.getMarker(), ps);
          this.map.put("CurCategory", this.category);
        }
        else if (chcontent.getType().equals(ChannelContent.TYPE_GPAGELIST))
        {
          if (StringUtils.isNotEmpty(this.cg))
          {
            this.category = this.categoryService.findById(this.cg);
          }
          else if (chcontent.getCategory() != null)
          {
            this.category = chcontent.getCategoryy();
          }

          Set<String> keySet = new HashSet<String>();
          String[] keys = chcontent.getKeys();
          if ((keys != null) && (keys.length > 0))
          {
            for (int i = 0; i < keys.length; i++)
            {
              String key = keys[i];
              keySet.add(key);
            }
          }
          if (this.keyword != null) {
        	  keySet.add(this.keyword);
          }

          
          String[] keywords = null;
          if(keySet.size()>0){
        	  keywords = (String[])keySet.toArray();
          }
          String locale = getDefLocale().getId();
          List<Good> list = this.goodService.findByLocalAndCategory(locale, category.getId(), null, keywords);
          int totalCount = list.size();
          int startIndex = this.page;
          int pageSize = chcontent.getNumber().intValue();
          PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
          this.map.put(chcontent.getMarker(), ps);
          this.map.put("CurCategory", this.category);
        }
        else if (chcontent.getType().equals(ChannelContent.TYPE_DSINGLE))
        {
          Document document = chcontent.getDocumentt();
          this.map.put(chcontent.getMarker(), document);
        }
      }
      if (StringUtils.isNotEmpty(this.d)) {
        if (this.channel.getVtemplate() != null)
        {
          this.template = (this.channel.getVtemplatee().getPath() + "/" + getDefLocale().getId() + "_" + this.channel.getVtemplatee().getName());
          this.document = this.documentService.findById(this.d);
        }
        else
        {
          this.notice.setStatus(false);
          this.notice.setCode("channel vtemplate not exist");
          this.notice.setTitle("错误：");
          this.notice.setBody("找不到频道详细页模板。");
          return "error";
        }
      }
      return "success";
    }
    this.notice.setStatus(false);
    this.notice.setCode("channel not exist");
    this.notice.setTitle("错误：");
    this.notice.setBody("找不到频道。");
    return "error";
  }
  
  public String getC()
  {
    return this.c;
  }
  
  public void setC(String c)
  {
    this.c = c;
  }
  
  public Channel getChannel()
  {
    return this.channel;
  }
  
  public void setChannel(Channel channel)
  {
    this.channel = channel;
  }
  
  public int getPage()
  {
    return this.page;
  }
  
  public void setPage(int page)
  {
    this.page = page;
  }
  
  public Map getMap()
  {
    return this.map;
  }
  
  public void setMap(Map map)
  {
    this.map = map;
  }
  
  public String getT()
  {
    return this.t;
  }
  
  public void setT(String t)
  {
    this.t = t;
  }
  
  public String getD()
  {
    return this.d;
  }
  
  public void setD(String d)
  {
    this.d = d;
  }
  
  public Document getDocument()
  {
    return this.document;
  }
  
  public void setDocument(Document document)
  {
    this.document = document;
  }
  
  public String getCg()
  {
    return this.cg;
  }
  
  public void setCg(String cg)
  {
    this.cg = cg;
  }
  
  public Category getCategory()
  {
    return this.category;
  }
  
  public void setCategory(Category category)
  {
    this.category = category;
  }
  
  public String getDg()
  {
    return this.dg;
  }
  
  public void setDg(String dg)
  {
    this.dg = dg;
  }
  
  public DocumentCategory getDoccate()
  {
    return this.doccate;
  }
  
  public void setDoccate(DocumentCategory doccate)
  {
    this.doccate = doccate;
  }
  
  public List getBrandlist()
  {
    return this.brandlist;
  }
  
  public void setBrandlist(List brandlist)
  {
    this.brandlist = brandlist;
  }
  
  public String getKeyword()
  {
    return this.keyword;
  }
  
  public void setKeyword(String keyword)
  {
    this.keyword = keyword;
  }
}

