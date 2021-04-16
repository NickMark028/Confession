package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.confession.R;
import com.example.confession.adapters.GroupListAdapter;
import com.example.confession.models.behaviors.User;

public class CreatePostGroupListActivity extends AppCompatActivity {


    private ImageView iv_group_list_back;
    private ListView lv_create_post_group_list;
    private GroupListAdapter myAdapter;

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
        myAdapter = new GroupListAdapter(getApplicationContext(), User.GetInstance().GetFollowedGroups());
        lv_create_post_group_list.setAdapter(myAdapter);

    }

    public void InitListener(){
        iv_group_list_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lv_create_post_group_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);

            }
        });
    }
}