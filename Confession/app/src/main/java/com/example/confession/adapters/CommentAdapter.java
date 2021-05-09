package com.example.confession.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confession.R;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.PostCommentInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

	Context context;
	ArrayList<PostCommentInfo> comments;


	public CommentAdapter(Context context, ArrayList<PostCommentInfo> comments) {

		this.context = context;
		this.comments = comments;
	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.layout_comment, parent, false);
		return new CommentAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.InitData(position);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public int getItemCount() {
		return comments.size();
	}



	public class ViewHolder extends RecyclerView.ViewHolder{
		private LinearLayout ll_cmt_cover;
		private ImageView iv_ava_user_cmt, iv_cmt_react;
		private TextView txt_username_message, txt_cmt_time, txt_cmt_likes;
		private AnimatedVectorDrawable empty_heart, fill_heart;
		private Drawable drawable;
		private boolean full = false;
		private ActionBar actionBar;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);

			InitView(itemView);
			InitListener();
		}

		public void InitView(View view) {

			ll_cmt_cover = view.findViewById(R.id.ll_cmt_cover);
			iv_ava_user_cmt = view.findViewById(R.id.iv_ava_user_cmt);
			iv_cmt_react = view.findViewById(R.id.iv_cmt_react);
			txt_username_message = view.findViewById(R.id.txt_username_message);
			txt_cmt_time = view.findViewById(R.id.txt_cmt_time);
			txt_cmt_likes = view.findViewById(R.id.txt_cmt_likes);

			empty_heart = (AnimatedVectorDrawable) context.getResources().getDrawable(R.drawable.avd_heart_empty);
			fill_heart = (AnimatedVectorDrawable) context.getResources().getDrawable(R.drawable.avd_heart_fill);
		}

		private void InitData(int i) {

			PostCommentInfo comment = comments.get(i);
			txt_username_message.setText(
					Html.fromHtml(String.format("<b> %s </b> %s",
							comment.commenter.username,
							comment.content)));


			SimpleDateFormat formatter = new SimpleDateFormat("d");

			Date now = Calendar.getInstance().getTime();
			//formatter.format(comment.time_created.getTime() - now.getTime()) + "d ago"
			txt_cmt_time.setText("1d ago");

			// Todo Add avatar here
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


}
