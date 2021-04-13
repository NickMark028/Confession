package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.confession.R;
import com.example.confession.binders.CommentBinder;
import com.example.confession.presenters.CommentPresenter;

public class CommentActivity extends AppCompatActivity implements CommentBinder.View {

    private CommentPresenter presenter;
    private TextView cmt_txt_post_cmt;
    private EditText cmt_edit_txt_cmt;
    private ImageView iv_cmt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        InitPresenter();
        InitView();
        InitListener();
    }

    public void InitPresenter(){this.presenter = new CommentPresenter();}

    public void InitView(){
        cmt_txt_post_cmt = findViewById(R.id.cmt_txt_post_cmt);
        cmt_edit_txt_cmt = findViewById(R.id.cmt_edit_txt_cmt);
        iv_cmt_back = findViewById(R.id.iv_cmt_back);

        iv_cmt_back.setClickable(false);
    }

    public void InitListener(){
        iv_cmt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_cmt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // action send cmt
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
                if(s.toString().isEmpty()){
                    iv_cmt_back.setClickable(false);
                }
                else{
                    iv_cmt_back.setClickable(true);
                }
            }
        });

    }

    @Override
    public void OnPostCommentSuccess(int code) {

    }

    @Override
    public void OnPostCommentFailure(int error_code) {

    }
}