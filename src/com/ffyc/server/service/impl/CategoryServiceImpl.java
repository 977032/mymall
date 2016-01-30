package com.ffyc.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.mapper.CategoryMapper;
import com.ffyc.server.mapper.LocaleMapper;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Category;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.Locale;
import com.ffyc.server.service.CategoryService;
import com.ffyc.server.utils.PaginationSupport;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl implements
		CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private LocaleMapper localeMapper;

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Override
	public void save(Category category) {
		categoryMapper.save(category);
	}

	@Override
	public void update(Category category) {
		categoryMapper.update(category);
	}

	@Override
	public void delete(String id) {
		Category cate = categoryMapper.findById(id);
		categoryMapper.delete(id);
		if (cate.getPid() != null) {
			Category pcate = categoryMapper.findById(cate.getPid());
			pcate.getCategories().remove(cate);
			if (pcate.getCategories().size() <= 0) {
				pcate.setNodetype("F");
				this.categoryMapper.update(pcate);
			}
		}
	}

	@Override
	public Category findById(String id) {
		return categoryMapper.findById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryMapper.findAll();
	}

	@Override
	public List<Category> findByCategory(Category category) {
		return categoryMapper.findByCategory(category, null, null);
	}

	@Override
	public List<Category> findByCategory(Category category, Integer startIndex,
			Integer pageSize) {
		return categoryMapper.findByCategory(category, startIndex, pageSize);
	}

	@Override
	public int getCountByCategory(Category category) {
		return categoryMapper.getCountByCategory(category);
	}

	@Override
	public PaginationSupport findPageByCategory(Category category,
			Integer page, Integer pageSize) {
		if (page <= 0)
			page = 1;
		int startIndex = (page - 1) * pageSize;
		int totalCount = this.categoryMapper.getCountByCategory(category);
		List list = this.categoryMapper.findByCategory(category, startIndex,
				pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,
				startIndex, pageSize);
		return ps;
	}

	@Override
	public PaginationSupport findPageBySql(String sql, String orderby,
			Integer page, Integer pageSize) {
		if (page <= 0)
			page = 1;
		int startIndex = (page - 1) * pageSize;
		int totalCount = this.categoryMapper.getCountBySql(sql);
		List list = this.categoryMapper.findBySql(sql, orderby, startIndex,
				pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,
				startIndex, pageSize);
		return ps;
	}

	@Override
	public List<Category> findBySql(String sql, String orderby) {
		List list = this.categoryMapper.findBySql(sql, orderby, null, null);
		return list;
	}

	@Override
	public List<Category> findBySql(String sql, String orderby, Integer startIndex,
			Integer pageSize) {
		List list = this.categoryMapper.findBySql(sql, orderby, startIndex,
				pageSize);
		return null;
	}

	@Override
	public int getCountBySql(String sql) {
		return this.categoryMapper.getCountBySql(sql);
	}

	@Override
	public ArrayList<Category> findAllTree() {
		ArrayList<Category> list = new ArrayList();
		Category cate = new Category();
		cate.setPid("null");
		List categorys = this.findByCategory(cate);

		for (int i = 0; i < categorys.size(); i++) {
			Category category = (Category) categorys.get(i);
			findSubTree(list, category);
		}
		return list;
	}

	@Override
	public ArrayList<Category> findSubTree(ArrayList<Category> list,
			Category category) {
		int lg = category.getNodepath().split(">").length - 1;

		String nodestr = "├";
		for (int j = 0; j < lg; j++) {
			nodestr = nodestr + "─";
			if (j == lg - 1) {
				nodestr = nodestr + "┼";
			}
		}
		category.setNodestr(nodestr);
		list.add(category);
		if (category.getNodetype().equals("D")) {
			Category cate = new Category();
			cate.setPid(category.getId());
			List cl = this.findByCategory(cate);
			for (int i = 0; i < cl.size(); i++) {
				Category cg = (Category) cl.get(i);
				list = findSubTree(list, cg);
			}
		}
		return list;
	}

	@Override
	public ArrayList<Category> findAllTree(Locale local) {
		ArrayList<Category> list = new ArrayList();
		Category cate = new Category();
		cate.setPid("null");
		cate.setLocale(local.getId());
		List categorys = this.findByCategory(cate);
		for (int i = 0; i < categorys.size(); i++) {
			Category category = (Category) categorys.get(i);
			findSubTree(list, category);
		}
		return list;
	}

	@Override
	public List<Category> findAllRoot() {
		Category dc = new Category();
		dc.setPid("null");
		List categorys = this.findByCategory(dc);
		return categorys;
	}

	@Override
	public List<Category> findAllRoot(Locale locale) {
		Category dc = new Category();
		dc.setPid("null");
		dc.setLocale(locale.getId());
		List categorys = this.findByCategory(dc);
		return categorys;
	}

	@Override
	public void getLocales(List<Category> list) {
		if (list.size() == 0)
			return;
		Set<String> idSet = new HashSet<String>();
		for (Category category : list) {
			if (StringUtils.isNotEmpty(category.getLocale())) {
				idSet.add(category.getLocale());
			}
		}
		if (idSet.size() == 0)
			return;
		List<String> ids = new ArrayList<String>(idSet);
		List<Locale> localeList = localeMapper.findByIds(ids);
		HashMap<String, Locale> map = new HashMap<String, Locale>();
		for (Locale locale : localeList) {
			map.put(locale.getId(), locale);
		}
		for (Category category : list) {
			if (map.containsKey(category.getLocale())) {
				category.setLocalee(map.get(category.getLocale()));
			}
		}
	}

	@Override
	public void getImagees(List<Category> list) {
		if (list.size() == 0)
			return;
		Set<String> idSet = new HashSet<String>();
		for (Category category : list) {
			if (StringUtils.isNotEmpty(category.getImage())) {
				idSet.add(category.getImage());
			}
		}
		if (idSet.size() == 0)
			return;
		List<String> ids = new ArrayList<String>(idSet);
		List<Attachment> attachmentList = attachmentMapper.findByIds(ids);
		HashMap<String, Attachment> map = new HashMap<String, Attachment>();
		for (Attachment attachment : attachmentList) {
			map.put(attachment.getId(), attachment);
		}
		for (Category category : list) {
			if (map.containsKey(category.getImage())) {
				category.setImagee(map.get(category.getImage()));
			}
		}
	}

}
