package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.CommentMapper;
import com.ffyc.server.model.Comment;
import com.ffyc.server.service.CommentService;
import com.ffyc.server.utils.PaginationSupport;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
	
	@Override
	public void save(Comment comment) {
		commentMapper.save(comment);
	}
	
	@Override
	public void update(Comment comment) {
		commentMapper.update(comment);
	}

	@Override
	public void delete(String id) {
		commentMapper.delete(id);
	}

	@Override
	public Comment findById(String id) {
		return commentMapper.findById(id);
	}
	
	@Override
	public List<Comment> findAll() {
		return commentMapper.findAll();
	}

	@Override
	public List<Comment> findByComment(Comment dc) {
		return commentMapper.findByComment(dc, null, null);
	}

	@Override
	public List<Comment> findByComment(Comment dc, Integer startIndex,
			Integer pageSize) {
		return commentMapper.findByComment(dc, startIndex, pageSize);
	}

	@Override
	public int getCountByComment(Comment dc) {
		return commentMapper.getCountByComment(dc);
	}

	@Override
	public PaginationSupport findPageByComment(Comment dc, Integer page,
			Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.commentMapper.getCountByComment(dc);
		List list = this.commentMapper.findByComment(dc, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}

}
