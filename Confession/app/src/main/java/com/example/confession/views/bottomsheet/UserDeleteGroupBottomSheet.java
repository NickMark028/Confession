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

public class UserDeleteGroupBottomSheet extends BottomSheetDialogFragment{
    BottomSheetListener mListener;
    private AppCompatButton user_confirm_delete_group_btn;
    private int result = -1;

    @Override
    public int getTheme() {
        return R.style.Widget_AppTheme_BottomSheet;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_user_delete_group,container,false);

        user_confirm_delete_group_btn = view.findViewById(R.id.user_confirm_delete_group_btn);

        user_confirm_delete_group_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 0;
                mListener.onButtonClicked("delete group");
            }
        });

        return view;
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
