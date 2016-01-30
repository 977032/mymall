package com.ffyc.server.utils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.LocaleProvider;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.ValueStack;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;
import org.apache.struts2.views.freemarker.FreemarkerManager;
import org.apache.struts2.views.util.ResourceUtil;

public class FreemarkerResult
  extends StrutsResultSupport
{
  private static final long serialVersionUID = -3689624346450376798L;
  protected ActionInvocation invocation;
  protected Configuration configuration;
  protected ObjectWrapper wrapper;
  protected FreemarkerManager freemarkerManager;
  private Writer writer;
  protected String location;
  private String pContentType = "text/html";
  protected String fileName;
  protected String filePath;
  protected String staticTemplate;
  
  public FreemarkerResult() {}
  
  public FreemarkerResult(String location)
  {
    super(location);
  }
  
  @Inject
  public void setFreemarkerManager(FreemarkerManager mgr)
  {
    this.freemarkerManager = mgr;
  }
  
  public void setContentType(String aContentType)
  {
    this.pContentType = aContentType;
  }
  
  public String getContentType()
  {
    return this.pContentType;
  }
  
  public void doExecute(String location, ActionInvocation invocation)
    throws IOException, TemplateException
  {
    this.location = location;
    this.invocation = invocation;
    this.configuration = getConfiguration();
    this.wrapper = getObjectWrapper();
    
    this.fileName = conditionalParse(this.fileName, invocation);
    this.staticTemplate = conditionalParse(this.staticTemplate, 
      invocation);
    this.filePath = (conditionalParse(this.filePath, invocation) == null ? "" : 
      conditionalParse(this.filePath, invocation));
    if (!location.startsWith("/"))
    {
      ActionContext ctx = invocation.getInvocationContext();
      HttpServletRequest req = (HttpServletRequest)ctx
        .get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
      String base = ResourceUtil.getResourceBase(req);
      location = base + "/" + location;
    }
    Template template = this.configuration.getTemplate(location, deduceLocale());
    
    Template staticTemplate = this.configuration.getTemplate(
      this.staticTemplate, deduceLocale());
    
    TemplateModel model = createModel();
    String path = ServletActionContext.getServletContext()
      .getRealPath(this.filePath) + 
      File.separator;
    File filepath = new File(path);
    if (!filepath.exists()) {
      filepath.mkdirs();
    }
    Writer out = new BufferedWriter(new OutputStreamWriter(
      new FileOutputStream(path + this.fileName), "UTF-8"));
    if (preTemplateProcess(template, model)) {
      try
      {
        staticTemplate.process(model, out);
        template.process(model, getWriter());
      }
      finally
      {
        postTemplateProcess(template, model);
        postTemplateProcess(staticTemplate, model);
      }
    }
  }
  
  protected Configuration getConfiguration()
    throws TemplateException
  {
    Configuration cfg = this.freemarkerManager
      .getConfiguration(ServletActionContext.getServletContext());
    cfg.setDefaultEncoding("UTF-8");
    

    return cfg;
  }
  
  protected ObjectWrapper getObjectWrapper()
  {
    return this.configuration.getObjectWrapper();
  }
  
  public void setWriter(Writer writer)
  {
    this.writer = writer;
  }
  
  protected Writer getWriter()
    throws IOException
  {
    if (this.writer != null) {
      return this.writer;
    }
    return ServletActionContext.getResponse().getWriter();
  }
  
  protected TemplateModel createModel()
    throws TemplateModelException
  {
    ServletContext servletContext = 
      ServletActionContext.getServletContext();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    ValueStack stack = ServletActionContext.getContext().getValueStack();
    
    Object action = null;
    if (this.invocation != null) {
      action = this.invocation.getAction();
    }
    return this.freemarkerManager.buildTemplateModel(stack, action, 
      servletContext, request, response, this.wrapper);
  }
  
  protected Locale deduceLocale()
  {
    if ((this.invocation.getAction() instanceof LocaleProvider)) {
      return ((LocaleProvider)this.invocation.getAction()).getLocale();
    }
    return this.configuration.getLocale();
  }
  
  protected void postTemplateProcess(Template template, TemplateModel data)
    throws IOException
  {}
  
  protected boolean preTemplateProcess(Template template, TemplateModel model)
    throws IOException
  {
    Object attrContentType = template.getCustomAttribute("content_type");
    if (attrContentType != null)
    {
      ServletActionContext.getResponse().setContentType(
        attrContentType.toString());
    }
    else
    {
      String contentType = getContentType();
      if (contentType == null) {
        contentType = "text/html";
      }
      String encoding = template.getEncoding();
      if (encoding != null) {
        contentType = contentType + "; charset=" + encoding;
      }
      ServletActionContext.getResponse().setContentType(contentType);
    }
    return true;
  }
  
  public String getFileName()
  {
    return this.fileName;
  }
  
  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }
  
  public String getFilePath()
  {
    return this.filePath;
  }
  
  public void setFilePath(String filePath)
  {
    this.filePath = filePath;
  }
  
  public String getStaticTemplate()
  {
    return this.staticTemplate;
  }
  
  public void setStaticTemplate(String staticTemplate)
  {
    this.staticTemplate = staticTemplate;
  }
}
