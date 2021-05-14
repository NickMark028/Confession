package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.user.CreateGroupBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.presenters.user.CreateGroupPresenter;
import com.example.confession.utilities.Regex;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CreateGroupActivity extends AppCompatActivity implements CreateGroupBinder.View {

    private CreateGroupPresenter presenter;
    private ImageButton create_post_close_btn, create_group_btn;
    private TextInputEditText create_group_name;
    private ProgressBar create_group_progress;
    private TextInputLayout til_create_post_name;
    private Thread newThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        InitPresenter();
        InitView();
        InitListener();
    }

    private void InitPresenter() {
        this.presenter = new CreateGroupPresenter(this);
    }

    public void InitView(){
        til_create_post_name = findViewById(R.id.til_create_post_name);
        create_post_close_btn = findViewById(R.id.create_post_close_btn);
        create_group_name = findViewById(R.id.create_group_name);
        create_group_btn = findViewById(R.id.create_group_btn);
        create_group_progress = findViewById(R.id.create_group_progress);

        create_group_btn.setEnabled(false);
    }

    public void InitListener(){
        create_post_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        create_group_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    create_group_btn.setEnabled(true);
                }
                else {
                    create_group_btn.setEnabled(false);
                }
            }
        });

        create_group_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gName = create_group_name.getText().toString();
                CreateGroup(gName, gName);
            }
        });
    }


    public void ValidateGroupName(){
        String group_name = create_group_name.getText().toString();

        if(group_name.isEmpty()){
            til_create_post_name.setError("Field can't be empty");
        }

        if(!Regex.GROUPNAME_PATTERN.matcher(group_name).matches()){
            til_create_post_name.setError("Invalid group name");
        }

        til_create_post_name.setError(null);
    }

    private void CreateGroup(String shortName, String groupName){
        newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                presenter.HandleCreateGroup(shortName, groupName);
            }
        });

        newThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(newThread!=null && newThread.isAlive()){newThread.interrupt();}
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void OnCreateGroupSuccess(ConfessionGroup group) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
    }

    @Override
    public void OnCreateGroupFailure(String error) {

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Failed to create group", Toast.LENGTH_SHORT).show();
            }
        });
    }
}