package com.example.confession.views.bottomsheet;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.confession.R;
import com.example.confession.binders.BottomSheetListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ProfileCreateNewBottomSheet extends BottomSheetDialogFragment {
    BottomSheetListener mListener;

    private Button profile_create_post_btn, profile_create_group_btn;
    private int result = 0;

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
                mListener.onButtonClicked("create_post");
            }
        });

        profile_create_group_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 1;
                mListener.onButtonClicked("create_group");
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        }
        catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement BottomSheetListener");
        }

    }

    public int GetResult(){return result;}
}
