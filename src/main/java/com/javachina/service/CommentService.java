package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import com.javachina.model.Comment;

public interface CommentService {
	
	public Comment getComment(Long cid);
		
	public Comment getTopicLastComment(Long tid);
	
	public List<Comment> getCommentList(QueryParam queryParam);
	
	public Page<Comment> getPageList(QueryParam queryParam);
	
	public boolean save( Long uid, Long toUid, Long tid, String content, Long createTime );
	
	public boolean delete(Long cid);
		
}
