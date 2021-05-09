package com.example.confession.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.adapters.CommentAdapter;
import com.example.confession.binders.post.AddCommentBinder;
import com.example.confession.binders.post.GetCommentsBinder;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.models.data.PostCommentInfo;
import com.example.confession.presenters.post.AddCommentPresenter;
import com.example.confession.presenters.post.GetCommentsPresenter;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity
		implements GetCommentsBinder.View, AddCommentBinder.View {

	private AddCommentBinder.Presenter presenterAddCmt;
	private GetCommentsBinder.Presenter presenterGetCmt;

	private ArrayList<PostCommentInfo> gr_comments;
	private CommentAdapter cmtAdapter;

	private SwipeRefreshLayout srl_refresh_cmt;
	private GroupPostInfo gpi;
	private TextView cmt_txt_post_cmt;
	private EditText cmt_edit_txt_cmt;
	private ImageView iv_cmt_back;
	private RecyclerView rv_cmt_item;
	private ItemTouchHelper ith;

	private Thread newThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);

		gpi = (GroupPostInfo)getIntent().getSerializableExtra("gpi");

		InitPresenter();
		InitView();
		InitListener();

		LoadComment();
	}

	public void LoadComment(){
		newThread = new Thread(new Runnable() {
			@Override
			public void run() {
				presenterGetCmt.HandleGetComments(gpi);
			}
		});

		newThread.start();

		srl_refresh_cmt.setRefreshing(true);
		//Toast.makeText(CommentActivity.this, "Loading comment...", Toast.LENGTH_LONG).show();
	}

	public void SendComment(String msg){
		//Log.e("Check ID------------------","Post Position - " + getLayoutPosition());

		newThread = new Thread(new Runnable() {
			@Override
			public void run() {
				presenterAddCmt.HandleAddComment(gpi, msg);
			}
		});

		newThread.start();
	}

	public void InitPresenter() {

		this.presenterAddCmt = new AddCommentPresenter(this);
		this.presenterGetCmt = new GetCommentsPresenter(this);
	}

	public void InitView() {

		cmt_txt_post_cmt = findViewById(R.id.cmt_txt_post_cmt);
		cmt_edit_txt_cmt = findViewById(R.id.cmt_edit_txt_cmt);
		iv_cmt_back = findViewById(R.id.iv_cmt_back);
		srl_refresh_cmt = findViewById(R.id.srl_refresh_cmt);

		rv_cmt_item = findViewById(R.id.rv_cmt_item);

		LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());

		llm.setReverseLayout(true);
		llm.setStackFromEnd(true);

		rv_cmt_item.setLayoutManager(llm);

		gr_comments = new ArrayList<>();
		cmtAdapter = new CommentAdapter(CommentActivity.this, gr_comments);
		rv_cmt_item.setAdapter(cmtAdapter);

		iv_cmt_back.setClickable(false);
	}

	public void InitListener() {
		iv_cmt_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		srl_refresh_cmt.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				//Do sth
				LoadComment();
			}
		});

		cmt_edit_txt_cmt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					cmt_txt_post_cmt.setVisibility(View.VISIBLE);
				} else {
					cmt_txt_post_cmt.setVisibility(View.INVISIBLE);
				}
			}
		});

		cmt_edit_txt_cmt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEND) {
					String msg = cmt_edit_txt_cmt.getText().toString();
//						Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

					SendComment(msg);

					cmt_edit_txt_cmt.setText("");
					cmt_edit_txt_cmt.clearFocus();

					InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

					return true;
				}
				return false;
			}
		});


	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}


	@Override
	public void OnAddCommentSuccess(PostComment comment) {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(CommentActivity.this, "Sent Succesfully", Toast.LENGTH_SHORT).show();
				LoadComment();
			}
		});
	}

	@Override
	public void OnAddCommentFailure(String error) {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(CommentActivity.this, "Sent Failed", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (newThread != null && newThread.isAlive()){newThread.interrupt();}
	}

	@Override
	public void OnGetCommentsSuccess(ArrayList<PostCommentInfo> comments) {
		gr_comments.clear();
		gr_comments.addAll(comments);

		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				srl_refresh_cmt.setRefreshing(false);

				cmtAdapter.notifyDataSetChanged();
				rv_cmt_item.invalidateItemDecorations();
				rv_cmt_item.refreshDrawableState();
			}
		});
	}

	@Override
	public void OnGetCommentsFailure(String error) {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				srl_refresh_cmt.setRefreshing(false);

				Toast.makeText(CommentActivity.this, "Load comment failed", Toast.LENGTH_SHORT).show();
			}
		});
	}
}