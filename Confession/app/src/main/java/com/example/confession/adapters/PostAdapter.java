package com.example.confession.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.example.confession.R;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.listener.DoubleClickListener;
import com.example.confession.views.activities.CommentActivity;
import com.example.confession.views.bottomsheet.GroupAdminManagePostBottomSheet;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {

	Context context;
	ArrayList<GroupPost> posts;

	//layout variables
	private EditText edit_txt_cmt;
	private TextView txt_post_cmt, txt_group_name,
			txt_time_post, txt_content, txt_likes,
			post_see_all_cmt;
	private ImageView heart_cover, iv_react,iv_admin_manage_btn;
	private AnimatedVectorDrawableCompat avd;
	private AnimatedVectorDrawable avd2, empty_heart, fill_heart;
	private ConstraintLayout feed_content_layout;
	private Drawable drawable;
	private boolean full = false;

	private String user_role = "ROLE_ADMIN";

	public PostAdapter(Context context, ArrayList<GroupPost> posts) {
		this.context = context;
		this.posts = posts;
	}

	@Override
	public int getCount() {
		return posts.size();
	}

	@Override
	public Object getItem(int i) {
		return posts.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		@SuppressLint("ViewHolder")
		View row = inflater.inflate(R.layout.layout_post, null);

		//  Init view
		InitView(row);
		//Init listener
		InitListener();
		// Init data
		InitData(i);

		// TODO background here + react
//		iv_content.setBackgroundResource(post_info.time_created.toString());

		return row;
	}

	@SuppressLint("UseCompatLoadingForDrawables")
	public void InitView(View view){
		edit_txt_cmt = view.findViewById(R.id.edit_txt_cmt);
		txt_post_cmt = view.findViewById(R.id.txt_post_cmt);
		txt_group_name = view.findViewById(R.id.txt_group_name);
		txt_time_post = view.findViewById(R.id.txt_time_post);
		txt_content = view.findViewById(R.id.txt_content);
		txt_likes = view.findViewById(R.id.txt_likes);
		post_see_all_cmt = view.findViewById(R.id.post_see_all_cmt);

		heart_cover = view.findViewById(R.id.heart_cover);
		iv_react = view.findViewById(R.id.iv_react);
		iv_admin_manage_btn = view.findViewById(R.id.iv_admin_manage_btn);

		feed_content_layout = view.findViewById(R.id.feed_content_layout);

		drawable = heart_cover.getDrawable();
		empty_heart = (AnimatedVectorDrawable) context.getResources().getDrawable(R.drawable.avd_heart_empty);
		fill_heart = (AnimatedVectorDrawable) context.getResources().getDrawable(R.drawable.avd_heart_fill);

		if(!user_role.equals("ROLE_ADMIN")){
			iv_admin_manage_btn.setVisibility(View.GONE);
		}

	}

	public void InitData(int position){
		GroupPost post = posts.get(position);
		GroupPostInfo post_info = post.GetPostInfo();
		txt_group_name.setText(post.GetGroup().name);
		txt_time_post.setText(post_info.time_created.toString());
		txt_content.setText(post_info.content.toString());
	}

	public void InitListener(){
		iv_react.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				HeartAnimate(v);
			}
		});

		post_see_all_cmt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(context.getApplicationContext(), CommentActivity.class);
				context.startActivity(myIntent);
				//context.startActivity(myIntent, Bundle);
			}
		});

		feed_content_layout.setOnClickListener(new DoubleClickListener() {
			@Override
			public void onDoubleClick() {
				if(!full){
					heart_cover.setAlpha(0.70f);

					if (drawable instanceof AnimatedVectorDrawableCompat) {
						avd = (AnimatedVectorDrawableCompat) drawable;
						avd.start();
					} else if (drawable instanceof AnimatedVectorDrawable) {
						avd2 = (AnimatedVectorDrawable) drawable;
						avd2.start();
					}

					iv_react.performClick();
				}
			}
		});


		edit_txt_cmt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					txt_post_cmt.setVisibility(View.VISIBLE);
				} else {
					txt_post_cmt.setVisibility(View.INVISIBLE);
				}
			}
		});

		edit_txt_cmt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEND) {
					String msg = edit_txt_cmt.getText().toString();
					Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
					edit_txt_cmt.setText("");
					edit_txt_cmt.clearFocus();

					InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

					return true;
				}
				return false;
			}
		});

		txt_post_cmt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String msg = edit_txt_cmt.getText().toString();
				Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

				edit_txt_cmt.setText("");
				edit_txt_cmt.clearFocus();

				InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			}
		});

		iv_admin_manage_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				GroupAdminManagePostBottomSheet bottomSheet = new GroupAdminManagePostBottomSheet();
				FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
				bottomSheet.show(fm, "action_admin_manage_post");
			}
		});
	}

	private void HeartAnimate(View view) {
		AnimatedVectorDrawable drawable
				= full
				? empty_heart
				: fill_heart;
		iv_react.setImageDrawable(drawable);
		drawable.start();
		full = !full;
//		Toast.makeText(context, full ? "HeartFill" : "HeartEmpty", Toast.LENGTH_SHORT).show();
	}
}
