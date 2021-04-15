package com.example.confession;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class CreatePostGroupListActivity extends AppCompatActivity {

    private ImageView iv_group_list_back;
    private ListView lv_create_post_group_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post_group_list);

        InitView();
        InitListener();
    }

    public void InitView(){
        iv_group_list_back = findViewById(R.id.iv_group_list_back);
        lv_create_post_group_list = findViewById(R.id.lv_create_post_group_list);

    }

    public void InitListener(){
        iv_group_list_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}