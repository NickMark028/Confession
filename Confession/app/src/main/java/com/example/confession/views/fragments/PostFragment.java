package com.example.confession.views.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

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

import com.example.confession.R;
import com.example.confession.presenters.DoubleClickListener;

public class PostFragment extends Fragment {

	private EditText edit_txt_cmt;
	private TextView txt_post_cmt;
	private ImageView heart_cover, iv_react;
	private AnimatedVectorDrawableCompat avd;
	private AnimatedVectorDrawable avd2, empty_heart, fill_heart;
	private ConstraintLayout feed_content_layout;
	private Drawable drawable;
	private boolean full = false;

	public PostFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@SuppressLint("UseCompatLoadingForDrawables")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_post, container, false);

		edit_txt_cmt = view.findViewById(R.id.edit_txt_cmt);
		txt_post_cmt = view.findViewById(R.id.txt_post_cmt);

		heart_cover = view.findViewById(R.id.heart_cover);
		iv_react = view.findViewById(R.id.iv_react);
		feed_content_layout = view.findViewById(R.id.feed_content_layout);

		drawable = heart_cover.getDrawable();

		empty_heart = (AnimatedVectorDrawable) getContext().getResources().getDrawable(R.drawable.avd_heart_empty);
		fill_heart = (AnimatedVectorDrawable) getContext().getResources().getDrawable(R.drawable.avd_heart_fill);

		InitListener();

		return view;
	}

	private void HeartAnimate(View view) {
		AnimatedVectorDrawable drawable
				= full
				? empty_heart
				: fill_heart;
		iv_react.setImageDrawable(drawable);
		drawable.start();
		full = !full;
		Toast.makeText(getContext(), full ? "HeartFill" : "HeartEmpty", Toast.LENGTH_SHORT).show();
	}

	private void InitListener() {
		iv_react.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				HeartAnimate(v);
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

				iv_react.performClick();
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
					Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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
				Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

				edit_txt_cmt.setText("");
				edit_txt_cmt.clearFocus();

				InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			}
		});
	}
}