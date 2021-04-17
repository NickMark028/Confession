package com.example.confession.views.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.confession.R;
import com.example.confession.binders.AddPostTabBinder;
import com.example.confession.models.behaviors.ConfessionGroup;
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.models.behaviors.User;
import com.example.confession.models.data.ConfessionGroupInfo;
import com.example.confession.presenters.AddPostPresenter;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.IOException;

public class AddPostActivity extends AppCompatActivity implements AddPostTabBinder.View {

    private AddPostPresenter presenter;

    private Uri imageUri;
    private String myURL = "";
//    private StorageTask uploadTask;
//    private StorageReference storageReference;

    private ImageButton add_post_close_btn;
    private Button ap_get_gr_list_btn;
    private TextView post_txt_btn, addp_post_username;
    private EditText addp_user_status;
    private ImageView addp_post_img_added;
    private LinearLayout addp_image_click, addp_camera_click;
    private File imgOut = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        InitPresenter();
        InitView();
        InitListener();
    }

    private void InitPresenter() {
        presenter = new AddPostPresenter(this);
    }

    private void InitView() {

        add_post_close_btn = findViewById(R.id.add_post_close_btn);
        ap_get_gr_list_btn = findViewById(R.id.ap_get_gr_list_btn);
        post_txt_btn = findViewById(R.id.post_txt_btn);
        addp_post_username = findViewById(R.id.addp_post_username);
        addp_user_status = findViewById(R.id.addp_user_status);
        addp_image_click = findViewById(R.id.addp_image_click);
        addp_camera_click = findViewById(R.id.addp_camera_click);
        addp_post_img_added = findViewById(R.id.addp_post_img_added);

        addp_post_username.setText(User.GetInstance().GetBasicUserInfo().name);
    }

    private void InitListener() {

        add_post_close_btn.setOnClickListener(view -> {
            //Close add post activity

            finish();
        });

        //If user don't join any group, we will set disable for this button
        //Only call when user has already joined a group
        ap_get_gr_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open list group activity

                Intent intent = new Intent(getApplicationContext(), CreatePostGroupListActivity.class);
                Bundle return_data = new Bundle();
                startActivityForResult(intent, 0, return_data);

            }
        });

        post_txt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Post a post action
            }
        });

        addp_image_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pick image
                CropImage.activity()
                        .setAspectRatio(16,9)
                        .start(AddPostActivity.this);
            }
        });

        addp_camera_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your camera");

                imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                //Open camera
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(camera, 304);
            }
        });

        addp_user_status.addTextChangedListener(new TextWatcher() {
            boolean hint;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    hint = true;
                    addp_user_status.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
                }
                else if(hint){
                    hint = false;
                    addp_user_status.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                    addp_user_status.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Do nothing
            }
        });
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();
            addp_post_img_added.setImageURI(imageUri);

        }
        else if(requestCode == 304 && resultCode == RESULT_OK){
            Bitmap selectedImage = null;
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                addp_post_img_added.setImageBitmap(selectedImage);
                myURL = getRealPathFromURI(imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == 101 && resultCode == RESULT_OK)
        {
            ConfessionGroupInfo group = ConfessionGroupInfo.From(data.getExtras());
            ap_get_gr_list_btn.setText(group.name);
        }
        else{
            Toast.makeText(this, "Something wrong", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, AddPostActivity.class));
            finish();
        }
    }

    @Override
    public void AddPostSuccess(GroupPost post) {

    }

    @Override
    public void AddPostFailure(String error) {

    }
}
