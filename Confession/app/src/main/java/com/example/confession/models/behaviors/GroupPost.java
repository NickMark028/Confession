package com.example.confession.models.behaviors;

import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.PostCommentInfo;

import java.util.ArrayList;

public class GroupPost {

	protected final GroupPost post_info;
	protected final ConfessionGroupInfo group;
	protected ArrayList<PostComment> comments;

	public GroupPost(GroupPost post_info, ConfessionGroupInfo group) {
		this.post_info = post_info;
		this.group = group;
	}
	public String GetID(){
		return null;
	}
	 public PostComment addComment(PostCommentInfo comment, BasicUserInfo user){
		return null;
	}
	public boolean RemoveComment(PostComment comment, BasicUserInfo from){
		return false;
	}
	public boolean React(BasicUserInfo user){
		return false;
	}
	public PostComment[] GetComment(){
		return null;
	}
	public int GetReactionCount(){
		return 0;
	}

}
