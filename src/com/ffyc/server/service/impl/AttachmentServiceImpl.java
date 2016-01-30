package com.ffyc.server.service.impl;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.service.AttachmentService;
import com.ffyc.server.utils.PaginationSupport;

@Service("attachmentService")
public class AttachmentServiceImpl extends BaseServiceImpl implements AttachmentService {


    @Autowired
    private AttachmentMapper attachmentMapper;
	
	@Override
	public void save(Attachment attachment) {
		attachmentMapper.save(attachment);
	}
	
	@Override
	public void update(Attachment attachment) {
		attachmentMapper.update(attachment);
	}

	@Override
	public void delete(String id) {
		attachmentMapper.delete(id);
	}

	@Override
	public Attachment findById(String id) {
		return attachmentMapper.findById(id);
	}
	
	@Override
	public List<Attachment> findByAttachment(Attachment dc) {
		return attachmentMapper.findByAttachment(dc,null,null);
	}
	
	

	@Override
	public List<Attachment> findByAttachment(Attachment dc, Integer startIndex,
			Integer pageSize) {
		return attachmentMapper.findByAttachment(dc, startIndex, pageSize);
	}

	@Override
	public int getCountByAttachment(Attachment dc) {
		return attachmentMapper.getCountByAttachment(dc);
	}
	
	

	@Override
	public PaginationSupport findPageByAttachment(Attachment dc, Integer page,
			Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.attachmentMapper.getCountByAttachment(dc);
		List list = this.attachmentMapper.findByAttachment(dc, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}

	@Override
	public List<Attachment> findAll() {
		return attachmentMapper.findAll();
	}

	@Override
	public List<Attachment> findAllTemplate() {
		return attachmentMapper.findAllTemplate();
	}

	@Override
	public void deleteAndFile(Attachment attachment) {
		attachmentMapper.delete(attachment.getId());
		String dfilepath = ServletActionContext.getServletContext().getRealPath(attachment.getPath() + "/" + attachment.getName());
	    File dfile = new File(dfilepath);
	    
	    String dtfilepath = ServletActionContext.getServletContext().getRealPath(attachment.getPath() + "/s_" + attachment.getName());
	    File dtfile = new File(dtfilepath);
	    if (dfile.exists()) {
	      dfile.delete();
	    }
	    if (dtfile.exists()) {
	      dtfile.delete();
	    }
	}
	
	
	
}
