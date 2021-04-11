package com.example.confession.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileGroupsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileGroupsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mTag;
    private ViewPager profile_group_viewpager;
    private ImageButton profile_group_back_btn;
    private TabLayout group_tab_layout;

    private FragmentStatePagerAdapter adapterViewPager;

    public ProfileGroupsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileGroupsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileGroupsFragment newInstance(String param1, String param2) {
        ProfileGroupsFragment fragment = new ProfileGroupsFragment();
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

        mTag = this.getTag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile_groups, container, false);

        profile_group_back_btn = view.findViewById(R.id.profile_group_back_btn);
        group_tab_layout =  view.findViewById(R.id.group_tab_layout);
        profile_group_viewpager = view.findViewById(R.id.profile_group_viewpager);

        assert getFragmentManager() != null;
        adapterViewPager = new ViewPagerAdapter(getChildFragmentManager());
        profile_group_viewpager.setAdapter(adapterViewPager);
        profile_group_viewpager.setOffscreenPageLimit(2);

        group_tab_layout.setupWithViewPager(profile_group_viewpager);

        InitListener();
        InitTabLayout();

        return view;
    }

    public void InitListener(){
        profile_group_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getContext(), "Frag-Pos-"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        group_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getContext(), tab.getText(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        profile_group_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();

            }
        });
    }

    public void InitTabLayout(){
        if(mTag.equals("TAG_JOINED_GROUP")){
            group_tab_layout.getTabAt(1).select();
        }
    }
}