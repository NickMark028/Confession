package com.example.confession.views.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.example.confession.R;
import com.example.confession.listener.BottomSheetListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class GroupAdminManageGroupBottomSheet extends BottomSheetDialogFragment {
    BottomSheetListener mListener;

    private AppCompatButton group_admin_join_request_btn,
            group_admin_member_btn,
            group_admin_leave_group_btn,
            group_admin_del_group_btn,
            group_admin_pending_post_btn;
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
        group_admin_join_request_btn = view.findViewById(R.id.group_admin_join_request_btn);
        group_admin_member_btn = view.findViewById(R.id.group_admin_member_btn);
        group_admin_leave_group_btn = view.findViewById(R.id.group_admin_leave_group_btn);
        group_admin_del_group_btn = view.findViewById(R.id.group_admin_del_group_btn);
        group_admin_pending_post_btn = view.findViewById(R.id.group_admin_pending_post_btn);
    }

    public void InitListener(){
        group_admin_join_request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 0;
                mListener.onButtonClicked("action_admin_join_request");
            }
        });

        group_admin_pending_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 1;
                mListener.onButtonClicked("action_admin_pending_post");
            }
        });

        group_admin_member_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 2;
                mListener.onButtonClicked("action_admin_member");
            }
        });

        group_admin_leave_group_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 3;
                mListener.onButtonClicked("action_admin_leave_group");
            }
        });

        group_admin_del_group_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 4;
                mListener.onButtonClicked("action_admin_del_group");
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