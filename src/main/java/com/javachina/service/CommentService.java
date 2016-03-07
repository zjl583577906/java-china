package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Comment;

public interface CommentService {
	
	public Comment getComment(Integer cid);
	
	public List<Comment> getCommentList(QueryParam queryParam);
	
	public Page<Comment> getPageList(QueryParam queryParam);
	
	public boolean save( Integer uid, Integer toUid, Integer tid, String content, Integer createTime );
	
	public boolean delete(Integer cid);
		
}
