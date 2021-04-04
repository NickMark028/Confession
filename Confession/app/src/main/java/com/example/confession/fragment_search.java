package com.example.confession;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.confession.binders.SearchGroupBinder;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.SearchGroupPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_search extends Fragment implements SearchGroupBinder.View {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText txt_search;

    public fragment_search() {
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
    public static fragment_search newInstance(String param1, String param2) {
        fragment_search fragment = new fragment_search();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        EditText txt_search = view.findViewById(R.id.txt_search);
        view.findViewById(R.id.txt_search);

        //txt_search.addTextChangedListener()

        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {

                // Testing
                SearchGroupBinder.Presenter presenter = new SearchGroupPresenter(getContext());
                presenter.FindGroup(txt_search.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void Show(ArrayList<ConfessionGroupInfo> groups) {

        // Display list of content here
    }
}