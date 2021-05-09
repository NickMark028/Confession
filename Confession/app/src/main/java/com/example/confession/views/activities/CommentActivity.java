package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.confession.R;
import com.example.confession.adapters.CommentAdapter;
import com.example.confession.binders.post.AddCommentBinder;
import com.example.confession.models.behaviors.PostComment;
import com.example.confession.models.data.BasicUserInfo;
import com.example.confession.models.data.PostCommentInfo;
import com.example.confession.presenters.post.AddCommentPresenter;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity implements AddCommentBinder.View {

    private ArrayList<PostCommentInfo> comments;

	private AddCommentBinder.Presenter presenter;
	private TextView cmt_txt_post_cmt;
	private EditText cmt_edit_txt_cmt;
	private ImageView iv_cmt_back;
	private ListView lv_cmt_item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);

		// Todo Testing only
		SampleData();

		InitPresenter();
		InitView();
		InitListener();
	}

	private void SampleData()
    {
        comments = new ArrayList<>(100);

//        comments.add(new PostCommentInfo(new BasicUserInfo("user #1" , "Nguyen Minh A1" ), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #2" , "Nguyen Minh A2" ), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #3" , "Nguyen Minh A3" ), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #4" , "Nguyen Minh A4" ), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #5" , "Nguyen Minh A5" ), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #6" , "Nguyen Minh A6" ), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #7" , "Nguyen Minh A7" ), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #8" , "Nguyen Minh A8" ), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #9" , "Nguyen Minh A9" ), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #10", "Nguyen Minh A10"), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #11", "Nguyen Minh A11"), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #12", "Nguyen Minh A12"), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #13", "Nguyen Minh A13"), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #14", "Nguyen Minh A14"), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
//        comments.add(new PostCommentInfo(new BasicUserInfo("user #15", "Nguyen Minh A15"), "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."));
   }

	public void InitPresenter() {

		this.presenter = new AddCommentPresenter(this);
	}

	public void InitView() {

		cmt_txt_post_cmt = findViewById(R.id.cmt_txt_post_cmt);
		cmt_edit_txt_cmt = findViewById(R.id.cmt_edit_txt_cmt);
		iv_cmt_back = findViewById(R.id.iv_cmt_back);
		lv_cmt_item = findViewById(R.id.lv_cmt_item);

		lv_cmt_item.setAdapter(new CommentAdapter(this, comments));

		iv_cmt_back.setClickable(false);
	}

	public void InitListener() {
		iv_cmt_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});



		cmt_edit_txt_cmt.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.toString().isEmpty()) {
					iv_cmt_back.setClickable(false);
				} else {
					iv_cmt_back.setClickable(true);
				}
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

	}

	@Override
	public void OnAddCommentFailure(String error) {

	}
}