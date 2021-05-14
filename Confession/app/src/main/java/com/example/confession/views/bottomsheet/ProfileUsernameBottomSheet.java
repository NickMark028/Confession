package com.example.confession.views.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.confession.R;
import com.example.confession.listener.BottomSheetCreateNewListener;
import com.example.confession.listener.BottomSheetListener;
import com.example.confession.listener.BottomSheetLogoutListener;
import com.example.confession.models.behaviors.User;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ProfileUsernameBottomSheet extends BottomSheetDialogFragment{
    BottomSheetLogoutListener mListener;

    private Button profile_logout_btn;
    private TextView add_post_username;
    private ImageView add_post_avatar;
    private int result = -1;

    public ProfileUsernameBottomSheet(BottomSheetLogoutListener listener){
        this.mListener = listener;
    }

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

                result = 0;
                mListener.onButtonLogoutClicked(result);
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

//        try {
//            mListener = (BottomSheetListener) context;
//        }
//        catch(ClassCastException e){
//            throw new ClassCastException(context.toString() + "must implement BottomSheetListener");
//        }
    }

    public int GetResult(){return result;}
}
