package com.ffyc.server.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.DocumentMapper;
import com.ffyc.server.model.Document;
import com.ffyc.server.service.DocumentService;
import com.ffyc.server.utils.PaginationSupport;

@Service("documentService")
public class DocumentServiceImpl extends BaseServiceImpl implements DocumentService {

    @Autowired
    private DocumentMapper documentMapper;
	
	
	@Override
	public void save(Document document) {
		documentMapper.save(document);
	}
	
	@Override
	public void update(Document document) {
		documentMapper.update(document);
	}

	@Override
	public void delete(String id) {
		documentMapper.delete(id);
	}

	@Override
	public Document findById(String id) {
		return documentMapper.findById(id);
	}

	@Override
	public List<Document> findAll() {
		return documentMapper.findAll();
	}

	@Override
	public List<Document> findByDocument(Document document) {
		return documentMapper.findByDocument(document, null, null);
	}

	@Override
	public List<Document> findByDocument(Document document, Integer startIndex,Integer pageSize) {
		return documentMapper.findByDocument(document,startIndex,pageSize);
	}

	@Override
	public int getCountByDocument(Document document) {
		return documentMapper.getCountByDocument(document);
	}

	@Override
	public PaginationSupport findPageByDocument(Document document,
			Integer page, Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.documentMapper.getCountByDocument(document);
		List list = this.documentMapper.findByDocument(document, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}

	@Override
	public List<Document> findByDocumentOrNodePathLike(String locale,
			String doccate,String marker){
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from tb_document as d where 1=1 ");
		if(StringUtils.isNotEmpty(locale)){
			sb.append(" and d.locale = '" + locale + "' ");
		}
		if(StringUtils.isNotEmpty(marker)){
			sb.append(" and d.marker = '" + marker + "' ");
		}
		if(StringUtils.isNotEmpty(marker)){
			sb.append("and ");
			sb.append("  ( ");
			sb.append("     d.doccate = '" + doccate + "' ");
			sb.append("     or  d.doccate in ( ");
			sb.append("     select dc.id from tb_document_category as dc  where dc.nodepath like  concat('%','"+ doccate +"','%') ");
			sb.append("     ) ");
			sb.append("  )");
		}
		String sql = sb.toString();
		String orderby = "order by d.ctime desc";
		return documentMapper.findBySql(sql, orderby, null, null);
	}	

}
