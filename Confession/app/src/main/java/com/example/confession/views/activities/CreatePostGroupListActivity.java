package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.adapters.group.GroupListAdapter;
import com.example.confession.binders.group.CreatePostGroupListBinder;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.group.CreatePostGroupListPresenter;

import java.util.ArrayList;

public class CreatePostGroupListActivity extends AppCompatActivity implements CreatePostGroupListBinder.View {

	private CreatePostGroupListBinder.Presenter presenter;
	private ImageView iv_group_list_back;
	private TextView txt_get_gr_notice;
	private ListView lv_create_post_group_list;
	private LinearLayout ll_create_post_gr_loading;
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

//		Toast.makeText(this, "After call HandleGetGroups", Toast.LENGTH_LONG).show();
	}

	private void InitPresenter() {

		presenter = new CreatePostGroupListPresenter(this);
	}

	public void InitView() {

		txt_get_gr_notice = findViewById(R.id.txt_get_gr_notice);
		iv_group_list_back = findViewById(R.id.iv_group_list_back);
		lv_create_post_group_list = findViewById(R.id.lv_create_post_group_list);
		ll_create_post_gr_loading = findViewById(R.id.ll_create_post_gr_loading);

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
				//data.putExtra("group_info", cgi);
				cgi.AddDataTo(data);
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
				ll_create_post_gr_loading.setVisibility(View.GONE);
				lv_create_post_group_list.setVisibility(View.VISIBLE);
				lv_create_post_group_list.setAdapter(new GroupListAdapter(CreatePostGroupListActivity.this, groups));
			}
		});
	}

	@Override
	public void OnGetGroupsFailure(String error) {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ll_create_post_gr_loading.setVisibility(View.GONE);
				txt_get_gr_notice.setVisibility(View.VISIBLE);

				Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
			}
		});
	}
}