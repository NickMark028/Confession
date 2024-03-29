package com.example.confession.views.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.confession.R;
import com.example.confession.listener.BottomSheetCreateNewListener;
import com.example.confession.listener.BottomSheetListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ProfileCreateNewBottomSheet extends BottomSheetDialogFragment {
    BottomSheetCreateNewListener mListener;

    private Button profile_create_post_btn, profile_create_group_btn;
    private int result = -1;

    public ProfileCreateNewBottomSheet(BottomSheetCreateNewListener listener){
        this.mListener = listener;
    }

    @Override
    public int getTheme() {
        return R.style.Widget_AppTheme_BottomSheet;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_profile_createnew,container,false);

        InitView(view);
        InitListener();

        return view;
    }

    public void InitView(View view) {
        profile_create_post_btn = view.findViewById(R.id.profile_create_post_btn);
        profile_create_group_btn = view.findViewById(R.id.profile_create_group_btn);
    }

    public void InitListener(){
        profile_create_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 0;
                mListener.onButtonCreatePostClicked(result);
            }
        });

        profile_create_group_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 1;
                mListener.onButtonCreateGroupClicked(result);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

//        try {
//            mListener = (BottomSheetCreateNewListener) context;
//        }
//        catch(ClassCastException e){
//            throw new ClassCastException(context.toString() + "must implement BottomSheetListener");
//        }

    }

    public int GetResult(){return result;}
}
