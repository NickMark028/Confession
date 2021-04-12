package com.example.confession.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.adapters.GroupAdapter;
import com.example.confession.binders.SearchTabBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.SearchGroupPresenter;

import java.security.acl.Group;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements SearchTabBinder.View {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    androidx.appcompat.widget.SearchView txt_search;
    TextView txt_search_result;
    ListView lv_search_item;
    ArrayList<ConfessionGroupInfo> list_group;
    ArrayList<ConfessionGroupInfo> search_item;
    GroupAdapter mGroupAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_search.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        createDemoGroupInfo();

    }

    //For testing only
    public void createDemoGroupInfo(){
        list_group = new ArrayList<>();
        search_item = new ArrayList<>();

        list_group.add(new ConfessionGroupInfo("111", "lih", "loveishurt", "url"));
        list_group.add(new ConfessionGroupInfo("112", "lih", "loveishurt1", "url"));
        list_group.add(new ConfessionGroupInfo("113", "lih", "loveishurt2", "url"));
        list_group.add(new ConfessionGroupInfo("114", "lih", "loveishurt3", "url"));
        list_group.add(new ConfessionGroupInfo("115", "lih", "loveishurt4", "url"));
        list_group.add(new ConfessionGroupInfo("116", "lih", "helloishurt", "url"));
        list_group.add(new ConfessionGroupInfo("117", "lih", "helloisgood", "url"));
        list_group.add(new ConfessionGroupInfo("118", "lih", "googleishurt", "url"));
        list_group.add(new ConfessionGroupInfo("119", "lih", "androidishurt", "url"));
        list_group.add(new ConfessionGroupInfo("120", "lih", "lifeishard", "url"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        txt_search = view.findViewById(R.id.txt_search);
        txt_search_result = view.findViewById(R.id.txt_search_result);
        lv_search_item = view.findViewById(R.id.lv_search_group_item);
        mGroupAdapter = new GroupAdapter(getContext(), search_item);
        lv_search_item.setAdapter(mGroupAdapter);

        InitListener();

        return view;
    }

    public void InitListener(){
        lv_search_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);

                Fragment fragment = new GroupFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment , "TAQ_GROUP_NOT_JOIN")
                        .addToBackStack("TAQ_GROUP_NOT_JOIN")
                        .commit();
            }
        });

        txt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_search.onActionViewExpanded();
            }
        });

        txt_search.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Testing
//                SearchTabBinder.Presenter presenter = new SearchGroupPresenter(getContext());
//                presenter.HandleFindGroup(query);

                for(ConfessionGroupInfo group: list_group){
                    if(group.name.contains(query)){
                        search_item.add(group);
                    }
                }

                UpdateListView();
                txt_search_result.setText(String.format("Results for %s", query));
                Toast.makeText(getActivity(), "Search found " + search_item.size(), Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty()){
                    txt_search_result.setText(String.format("No Results", newText));
                    search_item.clear();
                    UpdateListView();
                    return true;
                }

                txt_search_result.setText(String.format("Searching for %s", newText));

                if(search_item.size() > 0){
                    search_item.clear();
                }
                return true;
            }
        });
    }


    private void UpdateListView(){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                mGroupAdapter.notifyDataSetChanged();
                lv_search_item.invalidateViews();
                lv_search_item.refreshDrawableState();
            }
        };

        run.run();
    }


    @Override
    public void OnFindGroupSuccess(ArrayList<ConfessionGroupInfo> groups) {

    }

    @Override
    public void OnFindGroupFailure(int error_code) {

    }

    @Override
    public void OnJoinGroupSuccess(ConfessionGroup group) {

    }

    @Override
    public void OnJoinGroupFailure(int error_code) {

    }
}