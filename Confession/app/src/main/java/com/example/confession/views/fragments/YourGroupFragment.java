package com.example.confession.views.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.adapters.group.MyJoinedGroupAdapter;
import com.example.confession.adapters.group.MyOwnGroupAdapter;
import com.example.confession.binders.user.CreatedGroupsBinder;
import com.example.confession.presenters.user.CreatedGroupsPresenter;
import com.example.confession.models.data.ConfessionGroupInfo;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YourGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YourGroupFragment extends Fragment implements CreatedGroupsBinder.View {

	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "mPage";
	private static final String ARG_PARAM2 = "mTitle";

	private int mPage;
	private String mTitle;
	private Thread newThread;
	private CreatedGroupsBinder.Presenter presenter;
	private MyOwnGroupAdapter mAdapter;
	private ArrayList<ConfessionGroupInfo> groups_list;

	private ArrayList<ConfessionGroupInfo> origin_item;
	private ArrayList<ConfessionGroupInfo> search_item;

	androidx.appcompat.widget.SearchView your_gr_search;
	ListView lv_your_group_item;
	LinearLayout ll_your_group_progress;


	public YourGroupFragment() {
		// Required empty public constructor
		presenter = new CreatedGroupsPresenter(this);
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param page  page number.
	 * @param title title of page.
	 * @return A new instance of fragment YourGroupFragment.
	 */
	public static YourGroupFragment newInstance(int page, String title) {

		YourGroupFragment fragment = new YourGroupFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_PARAM1, page);
		args.putString(ARG_PARAM2, title);
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mPage = getArguments().getInt(ARG_PARAM1);
			mTitle = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_your_group, container, false);

		your_gr_search = view.findViewById(R.id.your_gr_search);
		lv_your_group_item = view.findViewById(R.id.lv_your_group_item);
		ll_your_group_progress = view.findViewById(R.id.ll_your_group_progress);

		search_item = new ArrayList<>();
		origin_item = new ArrayList<>();
		groups_list = new ArrayList<>();
		mAdapter = new MyOwnGroupAdapter(getContext(), groups_list);
		lv_your_group_item.setAdapter(mAdapter);

		InitListenser();
		CallPresenter();

		return view;
	}



	private void CallPresenter() {
		newThread = new Thread(new Runnable() {
			@Override
			public void run() {
				presenter.HandleGetCreatedGroups();
			}
		});

		newThread.start();

//		Toast.makeText(getContext(), " Your Group on call presenter", Toast.LENGTH_LONG).show();
	}

	public void InitListenser() {

		lv_your_group_item.setOnItemClickListener(((parent, view, position, id) -> {
			view.setSelected(true);

//			Toast.makeText(getContext(), "check click", Toast.LENGTH_SHORT).show();

			ConfessionGroupInfo cgi = (ConfessionGroupInfo) parent.getItemAtPosition(position);
			Fragment fragment = GroupFragment.newInstance(cgi);

			((AppCompatActivity)getContext()).getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.fragment_container, fragment, "group_info")
					.addToBackStack("group_info")
					.commit();
		}));

		your_gr_search.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				groups_list.clear();
				origin_item.forEach(e -> {
					if(e.name.contains(query)){
						groups_list.add(e);
					}
				});

				mAdapter.notifyDataSetChanged();
				lv_your_group_item.invalidateViews();
				lv_your_group_item.refreshDrawableState();

				return false;
			}

			@Override
				public boolean onQueryTextChange(String newText) {
					if(newText.isEmpty()){
						search_item.clear();
						UpdateOriginalListView();
						return true;
					}
					return true;
			}
		});

		your_gr_search.setOnClickListener(v -> your_gr_search.onActionViewExpanded());
	}

	private void UpdateOriginalListView() {
		groups_list.clear();
		groups_list.addAll(origin_item);

		mAdapter.notifyDataSetChanged();
		lv_your_group_item.invalidateViews();
		lv_your_group_item.refreshDrawableState();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if(newThread.isAlive()){
			newThread.interrupt();
		}
	}

	@Override
	public void OnGetCreatedGroupsSuccess(ArrayList<ConfessionGroupInfo> groups) {
		//newThread.interrupt();

		if(getActivity() == null){
			return;
		}

		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				//Set visibility
				ll_your_group_progress.setVisibility(View.GONE);
				lv_your_group_item.setVisibility(View.VISIBLE);

				mAdapter.notifyDataSetChanged();
				lv_your_group_item.invalidateViews();
				lv_your_group_item.refreshDrawableState();
			}
		});

	}

	@Override
	public void OnGetCreatedGroupsFailure(String error) {

		if(getActivity() == null){
			return;
		}

		this.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
				//Set visibility
				ll_your_group_progress.setVisibility(View.GONE);
				lv_your_group_item.setVisibility(View.VISIBLE);
			}
		});
	}
}
