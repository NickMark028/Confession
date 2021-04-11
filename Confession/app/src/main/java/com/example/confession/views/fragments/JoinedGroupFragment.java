package com.example.confession.views.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.confession.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JoinedGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JoinedGroupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "mPage";
    private static final String ARG_PARAM2 = "mTitle";

    // TODO: Rename and change types of parameters
    private int mPage;
    private String mTitle;

    androidx.appcompat.widget.SearchView joined_gr_search;
    ListView lv_joined_group_item;

    public JoinedGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param page page number.
     * @param title title of page.
     * @return A new instance of fragment YourGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JoinedGroupFragment newInstance(int page, String title) {
        JoinedGroupFragment fragment = new JoinedGroupFragment();
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
        View view =  inflater.inflate(R.layout.fragment_joined_group, container, false);

        joined_gr_search = view.findViewById(R.id.joined_gr_search);;
        lv_joined_group_item = view.findViewById(R.id.lv_joined_group_item);;

        InitListenser();


        return view;
    }

    public void InitListenser(){
        joined_gr_search.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}