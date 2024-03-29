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
import com.example.confession.listener.BottomSheetListener;
import com.example.confession.listener.BottomSheetManageGroupListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class GroupUserManageBottomSheet extends BottomSheetDialogFragment {
    BottomSheetManageGroupListener mListener;

    private Button group_user_leave_btn, group_user_member_btn;
    private int result = -1;

    public GroupUserManageBottomSheet(BottomSheetManageGroupListener listener){
        mListener = listener;
    }

    @Override
    public int getTheme() {
        return R.style.Widget_AppTheme_BottomSheet;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_group_user_setting,container,false);

        InitView(view);
        InitListener();

        return view;
    }

    public void InitView(View view) {
        group_user_leave_btn = view.findViewById(R.id.group_user_leave_btn);
        group_user_member_btn = view.findViewById(R.id.group_user_member_btn);
    }

    public void InitListener(){
        group_user_leave_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 1;
                mListener.onButtonUserClicked(result);
            }
        });

        group_user_member_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 0;
                mListener.onButtonUserClicked(result);
            }
        });
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        try {
//            mListener = (BottomSheetManageGroupListener) context;
//        }
//        catch(ClassCastException e){
//            throw new ClassCastException(context.toString() + "must implement BottomSheetListener");
//        }
//
//    }

    public int GetResult(){return result;}
}
