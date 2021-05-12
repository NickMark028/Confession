package com.example.confession.adapters.group;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.confession.R;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.views.bottomsheet.UserDeleteGroupBottomSheet;
import com.example.confession.views.fragments.GroupFragment;

import java.util.ArrayList;

public class MyOwnGroupAdapter extends BaseAdapter {

    Context context;

    ArrayList<ConfessionGroupInfo> groups;
    private ImageView iv_your_group_gr_avatar;
    private  TextView your_group_gr_name;
    private AppCompatButton your_group_del_btn;

    public MyOwnGroupAdapter(Context context, ArrayList<ConfessionGroupInfo> groups){
        this.context = context;
        this.groups = groups;
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        @SuppressLint("ViewHolder")
        View row = inflater.inflate(R.layout.layout_your_group, null);

        InitView(row);
        //InitListener();
        InitData(position);

        return row;
    }

    public void InitView(View row){
        iv_your_group_gr_avatar = row.findViewById(R.id.iv_your_group_gr_avatar);
        your_group_gr_name = row.findViewById(R.id.your_group_gr_name);
        your_group_del_btn = row.findViewById(R.id.your_group_del_btn);
    }

    public void InitListener(){
        your_group_del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDeleteGroupBottomSheet bottomSheet = new UserDeleteGroupBottomSheet();
                FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
                bottomSheet.show(fm, "DeleteGroup");

                if(bottomSheet.GetResult() == 0){
                    //... DeletePost
                }

            }
        });


    }

    public void InitData(int pos){

        ConfessionGroupInfo cgi = groups.get(pos);

        your_group_gr_name.setText(cgi.name);
        //iv_your_group_gr_avatar.setImageResource(cgi.avatar); int # String - not match
    }
}
