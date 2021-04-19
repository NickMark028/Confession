package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.confession.R;
import com.example.confession.adapters.GroupListAdapter;
import com.example.confession.binders.CreatePostGroupListBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.CreatePostGroupListPresenter;

import java.util.ArrayList;

public class CreatePostGroupListActivity extends AppCompatActivity implements CreatePostGroupListBinder.View {

	private User user;

	private ImageView iv_group_list_back;
	private ListView lv_create_post_group_list;
	private CreatePostGroupListBinder.Presenter presenter;
    private GroupListAdapter myAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_post_group_list);

		presenter = new CreatePostGroupListPresenter(this);
		InitView();
		InitListener();
	}

	public void InitView() {

		iv_group_list_back = findViewById(R.id.iv_group_list_back);
		lv_create_post_group_list = findViewById(R.id.lv_create_post_group_list);

		presenter.HandleGetGroups();
	}

	public void InitListener() {

		lv_create_post_group_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Intent data = new Intent();
				ConfessionGroup group = (ConfessionGroup)adapterView.getSelectedItem();
				group.AddTo(data);
				setResult(101, data);
				finish();
			}
		});

		iv_group_list_back.setOnClickListener(view -> {
			finish();
		});
	}

	@Override
	public void OnGetGroupsSuccess(ArrayList<ConfessionGroupInfo> groups) {

		GroupListAdapter adapter = new GroupListAdapter(this, groups);
		lv_create_post_group_list.setAdapter(adapter);
	}

	@Override
	public void OnGetGroupsFailure(String error) {

	}
}