package com.example.confession.views.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.example.confession.R;
import com.example.confession.binders.BottomSheetListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class GroupAdminManagePostBottomSheet extends BottomSheetDialogFragment {
    BottomSheetListener mListener;

    private AppCompatButton group_admin_pin_post_btn, group_admin_delpost_btn;
    private int result = 0;
    @Override
    public int getTheme() {
        return R.style.Widget_AppTheme_BottomSheet;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_group_admin_manage_post,container,false);

        InitView(view);
        InitListener();

        return view;
    }

    public void InitView(View view) {
        group_admin_pin_post_btn = view.findViewById(R.id.group_admin_pin_post_btn);
        group_admin_delpost_btn = view.findViewById(R.id.group_admin_delpost_btn);
    }

    public void InitListener(){
        group_admin_pin_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 0;
                mListener.onButtonClicked("action_admin_pin");
            }
        });

        group_admin_delpost_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 1;
                mListener.onButtonClicked("action_admin_delete");
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

    public int GetResult(){
        return result;
    }
}