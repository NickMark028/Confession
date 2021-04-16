package com.example.confession.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.confession.R;
import com.example.confession.binders.CreateGroupBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.presenters.CreateGroupPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class CreateGroupActivity extends AppCompatActivity implements CreateGroupBinder.View {

    private CreateGroupPresenter presenter;
    private ImageButton create_post_close_btn;
    private TextInputEditText create_group_name;
    private AppCompatButton create_group_btn;

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

        create_post_close_btn = findViewById(R.id.create_post_close_btn);
        create_group_name = findViewById(R.id.create_group_name);
        create_group_btn = findViewById(R.id.create_group_btn);

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
                presenter.HandleCreateGroup(gName, gName);
            }
        });
    }

    @Override
    public void OnCreateGroupSuccess(ConfessionGroup group) {

        // Todo navigate to new group

        finish();

    }

    @Override
    public void OnCreateGroupFail(int error_code) {

        Toast.makeText(this, "Failed to create group", Toast.LENGTH_LONG).show();
    }
}