package com.ffyc.server.action.manager;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.struts2.ServletActionContext;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Channel;
import com.ffyc.server.utils.FileExt;

public class ChannelUpdateAction extends BaseUploadAction {
	private static final long serialVersionUID = -1441686332340309751L;
	private String cid;
	private Channel channel;
	private List tplist;
	private List localelist;
	private String type = "template";
	private String localeid = "zh_CN";
	private List vfiles = new ArrayList();
	private List viewfiles = new ArrayList();

	public String execute() throws Exception {
		this.channel = this.channelService.findById(this.cid);
		this.localelist = this.localeService.findAll();
		this.tplist = this.attachmentService.findAllTemplate();
		this.channel.getTemplatee();
		this.channel.getVtemplatee();
		try {
			String path = this.channel.getTemplatee().getPath();
			String tfilepath = ServletActionContext.getServletContext()
					.getRealPath(path);
			File test = new File(tfilepath);
			if ((test.exists()) && (test.isDirectory())) {
				FileFilter fileFilter = new RegexFileFilter(".*_" + this.cid
						+ ".vm");

				File[] files = test.listFiles(fileFilter);
				for (int i = 0; i < files.length; i++) {
					System.out.println(files[i].getName());
					this.vfiles.add(files[i].getName());
				}
				fileFilter = new RegexFileFilter(".*_" + this.cid + "_view.vm");

				files = test.listFiles(fileFilter);
				for (int i = 0; i < files.length; i++) {
					this.viewfiles.add(files[i].getName());
				}
			}
		} catch (Exception localException) {
		}
		return "success";
	}

	public String update() throws Exception {
		Channel uca = this.channelService.findById(this.channel.getId());
		if (this.type.equals("vtemplate")) {
			uca.setManager(getManagerFromSession().getId());
			Attachment attachment = upload();
			Attachment ovtemplate = uca.getVtemplatee();
			if (attachment != null) {
				uca.setVtemplatee(attachment);
				uca.setVtemplate(attachment.getId());
			}
			this.channelService.update(uca);
			if (ovtemplate != null) {
				this.attachmentService.delete(ovtemplate.getId());
			}
		} else {
			uca.setIntro(this.channel.getIntro());
			uca.setTitle(this.channel.getTitle());
			uca.setManager(getManagerFromSession().getId());
			Attachment attachment = upload();
			Attachment otemplate = uca.getTemplatee();
			if (attachment != null) {
				uca.setTemplate(attachment.getId());
				uca.setTemplatee(attachment);
			}
			try{
				this.channelService.update(uca);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if (otemplate != null) {
				this.attachmentService.delete(otemplate.getId());
			}
		}
		return "success";
	}

	public void setUploadFileName(String uploadFileName) {
		this.oldFileName = uploadFileName;
		setFextname(FileExt.getExtension(uploadFileName));
		if (this.type.equals("vtemplate")) {
			this.langFileName = (this.channel.getId() + "_view." + FileExt
					.getExtension(uploadFileName));
			this.uploadFileName = (this.localeid + "_" + this.channel.getId()
					+ "_view." + FileExt.getExtension(uploadFileName));
		} else {
			this.langFileName = (this.channel.getId() + "." + FileExt
					.getExtension(uploadFileName));
			this.uploadFileName = (this.localeid + "_" + this.channel.getId()
					+ "." + FileExt.getExtension(uploadFileName));
		}
	}

	public void setSavePath(String savePath) {
		this.savePath = (savePath + "/custom");
	}

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Channel getChannel() {
		return this.channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public List getTplist() {
		return this.tplist;
	}

	public void setTplist(List tplist) {
		this.tplist = tplist;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List getLocalelist() {
		return this.localelist;
	}

	public void setLocalelist(List localelist) {
		this.localelist = localelist;
	}

	public String getLocaleid() {
		return this.localeid;
	}

	public void setLocaleid(String localeid) {
		this.localeid = localeid;
	}

	public List getVfiles() {
		return this.vfiles;
	}

	public void setVfiles(List vfiles) {
		this.vfiles = vfiles;
	}

	public List getViewfiles() {
		return this.viewfiles;
	}

	public void setViewfiles(List viewfiles) {
		this.viewfiles = viewfiles;
	}
}
