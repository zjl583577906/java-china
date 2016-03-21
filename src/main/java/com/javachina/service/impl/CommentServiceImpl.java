package com.javachina.service.impl;

import java.util.List;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.ioc.annotation.Service;
import com.javachina.model.Comment;
import com.javachina.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Override
	public Comment getComment(Long cid) {
		return AR.findById(Comment.class, cid);
	}
		
	@Override
	public List<Comment> getCommentList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(Comment.class);
		}
		return null;
	}
	
	@Override
	public Page<Comment> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).page(Comment.class);
		}
		return null;
	}
	
	@Override
	public boolean save( Long uid, Long toUid, Long tid, String content, Long createTime) {
		return false;
	}
	
	@Override
	public boolean delete(Long cid) {
		if(null != cid){
			AR.update("delete from t_comment where cid = ?", cid).executeUpdate();
			return true;
		}
		return false;
	}

	@Override
	public Comment getTopicLastComment(Long cid) {
		// TODO Auto-generated method stub
		return null;
	}
		
}
