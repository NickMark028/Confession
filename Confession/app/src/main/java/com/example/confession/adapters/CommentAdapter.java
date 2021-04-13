package com.example.confession.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.confession.R;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.PostComment;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {

	Context context;
	ArrayList<PostComment> comments;

	private LinearLayout ll_cmt_cover;
	private ImageView iv_ava_user_cmt, iv_cmt_react;
	private TextView txt_username_message, txt_cmt_time, txt_cmt_likes;
	private AnimatedVectorDrawable empty_heart, fill_heart;
	private Drawable drawable;
	private boolean full = false;
	private ActionBar actionBar;
	public CommentAdapter(Context context, ArrayList<PostComment> comments) {

		this.context = context;
		this.comments = comments;
	}

	@Override
	public int getCount() {
		return comments.size();
	}

	@Override
	public Object getItem(int i) {
		return comments.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		View row = inflater.inflate(R.layout.layout_comment, null);

		//  Init view
//		ImageView iv_avatar = row.findViewById(R.id.iv_avatar);
//		TextView txt_group_name = row.findViewById(R.id.txt_group_name);
//		TextView txt_member_count = row.findViewById(R.id.txt_member_count);
//		Button btn_join = row.findViewById(R.id.btn_join);
//
		// Init data
//		ConfessionGroup group = groups.get(i);
////		iv_avatar.setBackgroundResource(...);
//		txt_group_name.setText(group.GetGroupInfo().name);
//		txt_member_count.setText(group.GetMemberCount());
//
//		// Init listener
//		btn_join.setOnClickListener(view1 -> {
//			//...
//		});
//

		return row;
	}

	public void InitView(View view){
		ll_cmt_cover = view.findViewById(R.id.ll_cmt_cover);
		iv_ava_user_cmt = view.findViewById(R.id.iv_ava_user_cmt);
		iv_cmt_react = view.findViewById(R.id.iv_cmt_react);
		txt_username_message = view.findViewById(R.id.txt_username_message);
		txt_cmt_time = view.findViewById(R.id.txt_cmt_time);
		txt_cmt_likes = view.findViewById(R.id.txt_cmt_likes);

		empty_heart = (AnimatedVectorDrawable) context.getResources().getDrawable(R.drawable.avd_heart_empty);
		fill_heart = (AnimatedVectorDrawable) context.getResources().getDrawable(R.drawable.avd_heart_fill);
	}

	public void InitListener(){
		iv_cmt_react.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				HeartAnimate(v);
			}
		});

		ll_cmt_cover.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});


	}



	private void HeartAnimate(View view) {
		AnimatedVectorDrawable drawable
				= full
				? empty_heart
				: fill_heart;
		iv_cmt_react.setImageDrawable(drawable);
		drawable.start();
		full = !full;
//		Toast.makeText(context, full ? "HeartFill" : "HeartEmpty", Toast.LENGTH_SHORT).show();
	}
}
