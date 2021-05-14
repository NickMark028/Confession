package com.example.confession.adapters.post;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.example.confession.R;
import com.example.confession.binders.post.AddCommentBinder;
import com.example.confession.binders.post.ReactPostBinder;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.listener.DoubleClickListener;
import com.example.confession.presenters.post.AddCommentPresenter;
import com.example.confession.presenters.post.ReactPostPresenter;
import com.example.confession.utilities.DateTimeFormatter;
import com.example.confession.views.activities.CommentActivity;
import com.example.confession.views.bottomsheet.GroupAdminManagePostBottomSheet;
import com.example.confession.views.fragments.GroupFragment;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>
		implements
		AddCommentBinder.View,
		ReactPostBinder.View {

	Context context;
	ArrayList<GroupPostInfo> posts;
	private AddCommentBinder.Presenter presenter_comment;
	private ReactPostBinder.Presenter presenter_like;
	private String user_role = "ROLE_NORMAL";


	public PostAdapter(Context context, ArrayList<GroupPostInfo> posts) {

		this.context = context;
		this.posts = posts;
		presenter_comment = new AddCommentPresenter(this);
		presenter_like = new ReactPostPresenter(this);
	}

	@NonNull
	@Override
	public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.layout_post, parent, false);
		return new PostAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
		holder.InitData(position);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public int getItemCount() {
		return posts.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		//layout variables
		private EditText edit_txt_cmt;
		private TextView txt_post_cmt, txt_group_name,
				txt_time_post, txt_content, txt_likes,
				post_see_all_cmt;
		private ImageView heart_cover, iv_react, iv_admin_manage_btn, iv_comment;
		private AnimatedVectorDrawableCompat avd;
		private AnimatedVectorDrawable avd2, empty_heart, fill_heart;
		private ConstraintLayout feed_content_layout;
		private Drawable drawable;
		private long position;
		private Thread newThread;
		private boolean waiting_react = false;

//		public ViewHolder(@NonNull View itemView) {
//			super(itemView);
//			position = getLayoutPosition();
//
//			//  Init view
//			InitView(itemView);
//
//			//Init listener
//			InitListener();
//		}

		public void SendComment(String msg) {
			GroupPostInfo gpi = posts.get(getLayoutPosition());

			new Thread(new Runnable() {
				@Override
				public void run() {
					presenter_comment.HandleAddComment(gpi, msg);
				}
			}).start();
		}

		@SuppressLint("UseCompatLoadingForDrawables")
		public void InitView(View view) {

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
			iv_comment = view.findViewById(R.id.iv_comment);

			feed_content_layout = view.findViewById(R.id.feed_content_layout);


			drawable = heart_cover.getDrawable();
			empty_heart = (AnimatedVectorDrawable) context.getResources().getDrawable(R.drawable.avd_heart_empty);
			fill_heart = (AnimatedVectorDrawable) context.getResources().getDrawable(R.drawable.avd_heart_fill);

			if (!user_role.equals("ROLE_ADMIN")) {
				iv_admin_manage_btn.setVisibility(View.GONE);
			}
		}

		public void InitData(int position) {

			GroupPostInfo post_info = posts.get(position);

			txt_group_name.setText(post_info.group.name);
			txt_time_post.setText(DateTimeFormatter.FormatDateTillNow(post_info.time_created));
			txt_content.setText(post_info.content);
			txt_likes.setText(post_info.reaction_count + " likes");

			FillHeartAnimate(post_info.react);
		}

		public void InitListener() {

			txt_group_name.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ConfessionGroupInfo cgi = (ConfessionGroupInfo) posts.get(getLayoutPosition()).group;
					Fragment fragment = GroupFragment.newInstance(cgi);

					((AppCompatActivity) context).getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.fragment_container, fragment, "group_info")
							.addToBackStack("group_info")
							.commit();
				}
			});

			iv_react.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					GroupPostInfo post = posts.get((int) getLayoutPosition());

					try {
						if (newThread != null && newThread.isAlive()) {
							newThread.interrupt();
						}

						newThread = new Thread(new Runnable() {
							@Override
							public void run() {
								presenter_like.HandleReactPost(post);
							}
						});
						newThread.start();

					} catch (Exception e) {Log.e("In New Thread Exception", e.getMessage());}
					finally {

						post.reaction_count += post.react ? -1 : +1;
						post.react = !post.react;
						FillHeartAnimate(post.react);

						txt_likes.setText((post.reaction_count + " likes"));
					}
				}
			});

			iv_comment.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent myIntent = new Intent(context.getApplicationContext(), CommentActivity.class);

					myIntent.putExtra("gpi", posts.get(getLayoutPosition()));

					context.startActivity(myIntent);
				}
			});

			post_see_all_cmt.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent myIntent = new Intent(context.getApplicationContext(), CommentActivity.class);

					myIntent.putExtra("gpi", posts.get(getLayoutPosition()));

					context.startActivity(myIntent);

					//context.startActivity(myIntent, Bundle);
				}
			});

			feed_content_layout.setOnClickListener(new DoubleClickListener() {
				@Override
				public void onDoubleClick() {

					heart_cover.setAlpha(0.70f);
					if (drawable instanceof AnimatedVectorDrawableCompat) {
						avd = (AnimatedVectorDrawableCompat) drawable;
						avd.start();
					} else if (drawable instanceof AnimatedVectorDrawable) {
						avd2 = (AnimatedVectorDrawable) drawable;
						avd2.start();
					}

					GroupPostInfo post = posts.get((int) getLayoutPosition());
					if(!post.react){
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
//						Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

						SendComment(msg);

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
//					Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
					SendComment(msg);

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
					FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
					bottomSheet.show(fm, "action_admin_manage_post");
				}
			});
		}

		private void FillHeartAnimate(boolean fill) {

			AnimatedVectorDrawable drawable = fill ? fill_heart : empty_heart;
			iv_react.setImageDrawable(drawable);
			drawable.start();
		}
	}

	//Presenter methods
	@Override
	public void OnAddCommentSuccess(PostComment Comment) {
		((Activity) context).runOnUiThread(new Runnable() {
			@Override
			public void run() {

				Toast.makeText(context, "Sent Successfully", Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public void OnAddCommentFailure(String error) {
		((Activity) context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(context, error, Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public void OnReactPostSuccess(boolean check) {

	}

	@Override
	public void OnReactPostFailure(String error) {

	}
}
