package com.example.confession.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.ProfileTabBinder;
import com.example.confession.presenters.ForgotPasswordPresenter;
import com.example.confession.views.activities.SignInActivity;
import com.example.confession.views.bottomsheet.ProfileUsernameBottomSheet;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements ProfileTabBinder.View {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // IN-USE VARIABLES

    private TextView profile_txt_username_click;
    private ImageButton profile_open_post_btn;
    private LinearLayout profile_joined_group, profile_your_group,
            profile_edit_account_btn, profile_change_pass_btn, profile_faq_btn, profile_contact_us_btn;


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_profie.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        InitPresenter();
        InitView();
        InitListener();
    }

    private void InitPresenter() {
        //presenter = new ProfilePresenter(this);
    }

    private void InitView() {
        profile_txt_username_click = getView().findViewById(R.id.profile_txt_username_click);
        profile_open_post_btn = getView().findViewById(R.id.profile_open_post_btn);
        profile_joined_group = getView().findViewById(R.id.profile_joined_group);
        profile_your_group = getView().findViewById(R.id.profile_your_group);
        profile_edit_account_btn = getView().findViewById(R.id.profile_edit_account_btn);
        profile_change_pass_btn = getView().findViewById(R.id.profile_change_pass_btn);
        profile_faq_btn = getView().findViewById(R.id.profile_faq_btn);
        profile_contact_us_btn = getView().findViewById(R.id.profile_contact_us_btn);

    }

    private void InitListener() {

        profile_txt_username_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //presenter.HandleLogin(si_username.getText().toString(), si_password.getText().toString());
                ProfileUsernameBottomSheet bottomSheet = new ProfileUsernameBottomSheet();
                bottomSheet.show(getFragmentManager(), "Username");
            }
        });


        profile_open_post_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "bottom sheet open post", Toast.LENGTH_SHORT).show();

            }
        });

        profile_joined_group.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Joined group", Toast.LENGTH_SHORT).show();

            }
        });

        profile_your_group.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Your group", Toast.LENGTH_SHORT).show();

            }
        });

        profile_edit_account_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Edit account", Toast.LENGTH_SHORT).show();

            }
        });

        profile_change_pass_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Change password", Toast.LENGTH_SHORT).show();

            }
        });

        profile_faq_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "FAQ", Toast.LENGTH_SHORT).show();

            }
        });

        profile_contact_us_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Contact us", Toast.LENGTH_SHORT).show();

            }
        });


    }
}