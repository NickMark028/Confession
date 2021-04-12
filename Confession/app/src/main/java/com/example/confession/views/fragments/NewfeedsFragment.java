package com.example.confession.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.confession.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewfeedsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewfeedsFragment extends Fragment {

    public NewfeedsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newfeed, container, false);
    }
}
