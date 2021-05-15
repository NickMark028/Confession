//package com.example.confession.adapters.group;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatButton;
//import androidx.fragment.app.FragmentManager;
//
//import com.example.confession.R;
//import com.example.confession.models.behaviors.ConfessionGroup;
//import com.example.confession.models.data.ConfessionGroupInfo;
//import com.example.confession.views.bottomsheet.UserDeleteGroupBottomSheet;
//import com.example.confession.views.bottomsheet.UserLeaveGroupBottomSheet;
//
//import java.util.ArrayList;
//
//public class MyJoinedGroupAdapter extends BaseAdapter {
//
//    Context context;
//
//    ArrayList<ConfessionGroupInfo> groups;
//    private ImageView iv_joined_group_gr_avatar;
//    private  TextView joined_group_gr_name;
//    private AppCompatButton joined_group_leave_bn;
//
//    public MyJoinedGroupAdapter(Context context, ArrayList<ConfessionGroupInfo> groups){
//        this.context = context;
//        this.groups = groups;
//    }
//
//    @Override
//    public int getCount() {
//        return groups.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return groups.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//        @SuppressLint("ViewHolder")
//        View row = inflater.inflate(R.layout.layout_joined_group, null);
//
//        InitView(row);
//        InitListener();
//        InitData(position);
//
//        return row;
//    }
//
//    public void InitView(View row){
//        iv_joined_group_gr_avatar = row.findViewById(R.id.iv_joined_group_gr_avatar);
//        joined_group_gr_name = row.findViewById(R.id.joined_group_gr_name);
//        joined_group_leave_bn = row.findViewById(R.id.joined_group_leave_bn);
//    }
//
//    public void InitListener(){
//        joined_group_leave_bn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UserLeaveGroupBottomSheet bottomSheet = new UserLeaveGroupBottomSheet();
//                FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
//                bottomSheet.show(fm, "LeaveGroup");
//
//                if(bottomSheet.GetResult() == 0){
//                    //... LeavePost
//                }
//
//            }
//        });
//    }
//
//    public void InitData(int pos){
//
//        ConfessionGroupInfo cgi = groups.get(pos);
//
//        joined_group_gr_name.setText(cgi.name);
//        //iv_joined_group_gr_avatar.setImageResource(cgi.avatar); int # String - not match
//    }
//}
