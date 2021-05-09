package com.example.confession.views.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.adapters.GroupSearchAdapter;
import com.example.confession.binders.group.SearchGroupBinder;
import com.example.confession.binders.user.JoinedGroupIDsBinder;
import com.example.confession.presenters.user.JoinedGroupsIDPresenter;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.group.SearchGroupPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GroupSearchFragment extends Fragment implements SearchGroupBinder.View, JoinedGroupIDsBinder.View {

	private final SearchGroupBinder.Presenter search_presenter;
	private final JoinedGroupIDsBinder.Presenter joined_presenter;

	androidx.appcompat.widget.SearchView txt_search;
	TextView txt_search_result;
	ListView lv_search_item;

	private ArrayList<ConfessionGroupInfo> list_group;
	private Set<String> joined_groups;
	private GroupSearchAdapter mGroupSearchAdapter;

	private Thread newThread;

	public GroupSearchFragment() {

		search_presenter = new SearchGroupPresenter(this);
		joined_presenter = new JoinedGroupsIDPresenter(this);

		//joined_groups = new HashSet<>();
		//new Thread(() -> joined_presenter.HandleGetJoinedGroupIDs()).start();
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

				newThread = new Thread(new Runnable() {
					@Override
					public void run() {
						search_presenter.HandleFindGroup(query);
					}
				});

				newThread.start();

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

		});
	}

	private void UpdateListView() {
		if(getActivity() == null){ return; }

		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mGroupSearchAdapter.notifyDataSetChanged();
				lv_search_item.invalidateViews();
				lv_search_item.refreshDrawableState();
			}
		});

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if(newThread!=null && newThread.isAlive()){
			newThread.interrupt();
		}
	}

	@Override
	public void OnFindGroupSuccess(ArrayList<ConfessionGroupInfo> groups) {

		list_group.clear();
		list_group.addAll(groups);
		
		UpdateListView();
	}

	@Override
	public void OnFindGroupFailure(String error) {
		Toast.makeText(getContext(), "Failed to find group", Toast.LENGTH_LONG).show();
	}

	@Override
	public void OnGetJoinedGroupIDsSuccess(Collection<String> groups) {

		this.joined_groups.clear();
		this.joined_groups.addAll(groups);
	}

	@Override
	public void OnGetJoinedGroupIDsFailure(String error) {
		// Do nothing
	}
}
