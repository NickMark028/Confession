package com.example.confession.views.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.confession.R;
import com.example.confession.adapters.GroupSearchAdapter;
import com.example.confession.binders.group.SearchGroupBinder;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.group.SearchGroupPresenter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GroupSearchFragment extends Fragment implements SearchGroupBinder.View {

	private final SearchGroupBinder.Presenter presenter;

	androidx.appcompat.widget.SearchView txt_search;
	TextView txt_search_result;
	ListView lv_search_item;

	ArrayList<ConfessionGroupInfo> list_group;
	Set<String> joined_groups;
	GroupSearchAdapter mGroupSearchAdapter;

	Thread thread;

	public GroupSearchFragment() {

		presenter = new SearchGroupPresenter(this);
		presenter.HandleGetJoinedGroups();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_search, container, false);

		InitFragment(view);
		InitListener();

		return view;
	}

	private void InitFragment(View view) {

		txt_search = view.findViewById(R.id.txt_search);
		txt_search_result = view.findViewById(R.id.txt_search_result);
		lv_search_item = view.findViewById(R.id.lv_search_group_item);

		list_group = new ArrayList<>();
		joined_groups = new HashSet<>();
		mGroupSearchAdapter = new GroupSearchAdapter(getContext(), list_group, joined_groups);
		lv_search_item.setAdapter(mGroupSearchAdapter);
	}

	private void InitListener() {

		lv_search_item.setOnItemClickListener((parent, view, position, id) -> {

			view.setSelected(true);

			ConfessionGroupInfo cgi = (ConfessionGroupInfo) parent.getItemAtPosition(position);
			Fragment fragment = GroupFragment.newInstance(cgi);

			getFragmentManager()
					.beginTransaction()
					.replace(R.id.fragment_container, fragment, "group_info")
					.addToBackStack("group_info")
					.commit();
		});

		txt_search.setOnClickListener(v -> txt_search.onActionViewExpanded());
		txt_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {

				presenter.HandleFindGroup(query);
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {

				if (newText.isEmpty()) {
					txt_search_result.setText(String.format("No Results", newText));
					list_group.clear();
					UpdateListView();
					return true;
				}

				txt_search_result.setText(String.format("Searching for %s", newText));

				//presenter.HandleFindGroup(newText);
				return true;
			}
//			@Override
//			public boolean onQueryTextSubmit(String query) {
//
//				UpdateListView();
//				txt_search_result.setText(String.format("Results for %s", query));
//				Toast.makeText(getActivity(), "Search found " + search_item.size(), Toast.LENGTH_SHORT).show();
//
//				return false;
//			}
//
//			@Override
//			public boolean onQueryTextChange(String newText) {
//
//				if (newText.isEmpty()) {
//					txt_search_result.setText(String.format("No Results", newText));
//					search_item.clear();
//					UpdateListView();
//					return true;
//				}
//
//				txt_search_result.setText(String.format("Searching for %s", newText));
//
//				if (search_item.size() > 0) {
//					search_item.clear();
//				}
//				return true;
//			}
		});
	}

	private void UpdateListView() {

		Runnable run = new Runnable() {
			@Override
			public void run() {
				mGroupSearchAdapter.notifyDataSetChanged();
				lv_search_item.invalidateViews();
				lv_search_item.refreshDrawableState();
			}
		};
		run.run();
	}

	@Override
	public void OnFindGroupSuccess(ArrayList<ConfessionGroupInfo> groups) {

		list_group.clear();
		list_group.addAll(groups);

		
		UpdateListView();
	}

	@Override
	public void OnFindGroupFailure(String error) {

	}

	@Override
	public void OnGetJoinedGroupsSuccess(Set<String> joined_groups) {

		this.joined_groups.clear();
		this.joined_groups.addAll(joined_groups);
	}

	@Override
	public void OnGetJoinedGroupsFailure(String error) {

	}

//	@Override
//	public void OnJoinGroupSuccess(ConfessionGroup group) {
//
//	}
//
//	@Override
//	public void OnJoinGroupFailure(String error) {
//
//	}
}
