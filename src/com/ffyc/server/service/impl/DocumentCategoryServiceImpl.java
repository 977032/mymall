package com.ffyc.server.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.DocumentCategoryMapper;
import com.ffyc.server.model.DocumentCategory;
import com.ffyc.server.model.Locale;
import com.ffyc.server.service.DocumentCategoryService;
import com.ffyc.server.utils.PaginationSupport;

@Service("documentCategoryService")
public class DocumentCategoryServiceImpl extends BaseServiceImpl implements DocumentCategoryService {

    @Autowired
    private DocumentCategoryMapper documentCategorMapper;
	

	@Override
	public void save(DocumentCategory documentCategory) {
		documentCategorMapper.save(documentCategory);
	}
	
	@Override
	public void update(DocumentCategory documentCategory) {
		documentCategorMapper.update(documentCategory);
	}


	@Override
	public void delete(String id) {
		documentCategorMapper.delete(id);
	}

	@Override
	public DocumentCategory findById(String id) {
		return documentCategorMapper.findById(id);
	}

	@Override
	public List<DocumentCategory> findAll() {
		return documentCategorMapper.findAll();
	}
	
	@Override
	public ArrayList<DocumentCategory> findAllTree() {
		ArrayList<DocumentCategory> list = new ArrayList();
		DocumentCategory dc = new DocumentCategory();
		dc.setPid("null");
	    List categorys = documentCategorMapper.findByDocumentCategory(dc);
	    dc = null;
	    for (int i = 0; i < categorys.size(); i++)
	    {
	      DocumentCategory category = (DocumentCategory)categorys.get(i);
	      findSubTree(list, category);
	    }
	    return list;
	}
	

	@Override
	public ArrayList<DocumentCategory> findSubTree(
			ArrayList<DocumentCategory> list, DocumentCategory doccate) {
		int lg = doccate.getNodepath().split(">").length - 1;
	    
	    String nodestr = "├";
	    for (int j = 0; j < lg; j++)
	    {
	      nodestr = nodestr + "─";
	      if (j == lg - 1) {
	        nodestr = nodestr + "┼";
	      }
	    }
	    doccate.setNodestr(nodestr);
	    list.add(doccate);
	    if (doccate.getNodetype().equals("D"))
	    {
	      DocumentCategory dc = new DocumentCategory();
	      dc.setPid(doccate.getId());
	      List cl = documentCategorMapper.findByDocumentCategory(dc);
	      for (int i = 0; i < cl.size(); i++)
	      {
	        DocumentCategory cg = (DocumentCategory)cl.get(i);
	        list = findSubTree(list, cg);
	      }
	    }
	    return list;
	}

	@Override
	public List<DocumentCategory> findAllRoot() {
		DocumentCategory doccate = new DocumentCategory();
		doccate.setPid("null");
		List list = documentCategorMapper.findByDocumentCategory(doccate);
	    return list;
	}

	@Override
	public List<DocumentCategory> findAllRoot(Locale locale) {
		DocumentCategory doccate = new DocumentCategory();
		doccate.setPid("null");
		doccate.setLocale(locale.getId());
	    List list = documentCategorMapper.findByDocumentCategory(doccate);
	    return list;
	}

	@Override
	public List findByDocumentCategory(DocumentCategory doccate) {
		return documentCategorMapper.findByDocumentCategory(doccate);
	}

}
