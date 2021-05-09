package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.adapters.GroupListAdapter;
import com.example.confession.binders.CreatePostGroupListBinder;
import com.example.confession.binders.user.JoinedGroupsBinder;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.CreatePostGroupListPresenter;
import com.example.confession.presenters.user.JoinedGroupsPresenter;
import com.example.confession.presenters.user.SignInPresenter;

import java.util.ArrayList;

public class CreatePostGroupListActivity extends AppCompatActivity implements CreatePostGroupListBinder.View {

	private CreatePostGroupListBinder.Presenter presenter;
	private ImageView iv_group_list_back;
	private ListView lv_create_post_group_list;
    private GroupListAdapter myAdapter;
	private ArrayList<ConfessionGroupInfo> list_groups;
	private Thread newThread;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_post_group_list);

		InitView();
		InitListener();
		InitPresenter();

		//Get joined groups in new thread
		newThread = new Thread(new Runnable() {
			@Override
			public void run() {
				presenter.HandleGetGroups();
			}
		});
		newThread.start();

		Toast.makeText(this, "After call HandleGetGroups", Toast.LENGTH_LONG).show();
	}

	private void InitPresenter() {

		presenter = new CreatePostGroupListPresenter(this);
	}

	public void InitView() {

		iv_group_list_back = findViewById(R.id.iv_group_list_back);
		lv_create_post_group_list = findViewById(R.id.lv_create_post_group_list);

//		list_groups = new ArrayList<>();
//		myAdapter = new GroupListAdapter(getApplicationContext(), list_groups);
//		lv_create_post_group_list.setAdapter(myAdapter);

		//presenter.HandleGetGroups();
	}

	public void InitListener() {

		lv_create_post_group_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Intent data = new Intent();
//				ConfessionGroup group = (ConfessionGroup)adapterView.getSelectedItem();
//				group.AddTo(data);
				//ConfessionGroupInfo cgi = (ConfessionGroupInfo) adapterView.getSelectedItem();

				view.setSelected(true);
				ConfessionGroupInfo cgi = (ConfessionGroupInfo) adapterView.getItemAtPosition(i);
				data.putExtra("group_info", cgi);
				setResult(RESULT_OK, data);
				finish();
				//Toast.makeText(getApplicationContext(), cgi.name, Toast.LENGTH_LONG).show();
			}
		});

		iv_group_list_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if(newThread != null && newThread.isAlive()){newThread.interrupt();}
	}

	@Override
	public void OnGetGroupsSuccess(ArrayList<ConfessionGroupInfo> groups) {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				lv_create_post_group_list.setAdapter(new GroupListAdapter(CreatePostGroupListActivity.this, groups));
			}
		});
	}

	@Override
	public void OnGetGroupsFailure(String error) {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
			}
		});
	}
}