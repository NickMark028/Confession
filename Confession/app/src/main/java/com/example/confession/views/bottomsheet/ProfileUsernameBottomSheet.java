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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.confession.R;
import com.example.confession.binders.BottomSheetListener;
import com.example.confession.models.behaviors.User;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ProfileUsernameBottomSheet extends BottomSheetDialogFragment{
    BottomSheetListener mListener;
    private Button profile_logout_btn;
    private TextView add_post_username;
    private ImageView add_post_avatar;
    private int result = 0;

    @Override
    public int getTheme() {
        return R.style.Widget_AppTheme_BottomSheet;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_profile_username,container,false);


        profile_logout_btn = view.findViewById(R.id.profile_logout_btn);
        add_post_username = view.findViewById(R.id.add_post_username);
        add_post_avatar = view.findViewById(R.id.add_post_avatar);

        //SetData
        add_post_username.setText(User.GetInstance().GetBasicUserInfo().username);
        //add_post_avatar.setImageResource(User.GetInstance().GetBasicUserInfo().avatar);

        profile_logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("logout");
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
