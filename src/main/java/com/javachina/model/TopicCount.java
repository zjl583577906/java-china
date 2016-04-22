package com.javachina.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

@Table(value = "t_topiccount", PK = "tid")
public class TopicCount implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long tid;
	private Long views;
	private Long loves;
	private Long favorites;
	private Long comments;
	
	public TopicCount() {
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

	public Long getLoves() {
		return loves;
	}

	public void setLoves(Long loves) {
		this.loves = loves;
	}

	public Long getFavorites() {
		return favorites;
	}

	public void setFavorites(Long favorites) {
		this.favorites = favorites;
	}

	public Long getComments() {
		return comments;
	}

	public void setComments(Long comments) {
		this.comments = comments;
	}
	
}