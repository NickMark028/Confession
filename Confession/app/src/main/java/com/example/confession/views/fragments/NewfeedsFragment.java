package com.example.confession.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.adapters.PostAdapter;
import com.example.confession.binders.user.GetNewsfeedBinder;

import com.example.confession.models.data.GroupPostInfo;
import com.example.confession.presenters.user.GetNewsfeedPresenter;


import java.util.ArrayList;

public class NewfeedsFragment extends Fragment implements GetNewsfeedBinder.View {

	//IN-USE VARIABLES
	private GetNewsfeedBinder.Presenter presenter;
	private SwipeRefreshLayout srl_refresh;
	private ListView lv_posts;
	private ArrayList<GroupPostInfo> nf_posts;
	private PostAdapter postAdapter;
	private RecyclerView rv_posts;

	public NewfeedsFragment(){
		presenter = new GetNewsfeedPresenter(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_newfeed, container, false);
		InitView(view);
		InitListener();
		CallPresenter();

		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	private void CallPresenter() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				presenter.HandleGetNewsfeed();
			}
		}).start();
		srl_refresh.setRefreshing(true);
		Toast.makeText(getContext(), " Newsfeed on call presenter", Toast.LENGTH_LONG).show();
	}

	private void InitView(View view){
		rv_posts = view.findViewById(R.id.rv_posts);
		srl_refresh = view.findViewById(R.id.srl_refresh);
		LinearLayoutManager llm = new LinearLayoutManager(getContext());

		llm.setReverseLayout(true);
		llm.setStackFromEnd(true);

		rv_posts.setLayoutManager(llm);

		nf_posts = new ArrayList<>();
		postAdapter = new PostAdapter(getContext(), nf_posts);
		rv_posts.setAdapter(postAdapter);
	}

	private void InitListener(){
		srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				//Do sth
				CallPresenter();
			}
		});
	}




	@Override
	public void OnGetNewsfeedSuccess(ArrayList<GroupPostInfo> posts) {
		nf_posts.clear();
		nf_posts.addAll(posts);

		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				srl_refresh.setRefreshing(false);

				postAdapter.notifyDataSetChanged();
				rv_posts.invalidateItemDecorations();
				rv_posts.refreshDrawableState();
			}
		});
	}

	@Override
	public void OnGetNewsfeedFailure(String error) {
		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				srl_refresh.setRefreshing(false);

				Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
			}
		});
	}
}
