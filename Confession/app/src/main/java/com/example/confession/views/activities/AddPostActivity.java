package com.example.confession.views.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
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
import com.example.confession.models.behaviors.GroupPost;
import com.example.confession.presenters.AddPostPresenter;
import com.example.confession.presenters.SignUpPresenter;
import com.theartofdev.edmodo.cropper.CropImage;

public class AddPostActivity extends AppCompatActivity implements AddPostTabBinder.View {

    AddPostPresenter presenter;

    private Uri imageUri;
    private String myURL = "";
//    private StorageTask uploadTask;
//    private StorageReference storageReference;

    private ImageButton add_post_close_btn;
    private Button ap_get_gr_list_btn;
    private TextView  post_txt_btn;
    private EditText addp_user_status;
    private ImageView add_post_img_added;
    private LinearLayout addp_image_click, addp_camera_click;

    public AddPostActivity() {}

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
        addp_user_status = findViewById(R.id.addp_user_status);
        addp_image_click = findViewById(R.id.addp_image_click);
        addp_camera_click = findViewById(R.id.addp_camera_click);
        add_post_img_added = findViewById(R.id.add_post_img_added);
    }

    private void InitListener() {

        add_post_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Close add post activity
                Intent myIntent = new Intent(getApplicationContext(), HomePageActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        ap_get_gr_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open list group activity
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
                //Open camera
            }
        });

        addp_user_status.addTextChangedListener(new TextWatcher() {
            boolean hint;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            add_post_img_added.setImageURI(imageUri);
        }else{
            Toast.makeText(this, "Something wrong", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, AddPostActivity.class));
            finish();
        }
    }

    @Override
    public void AddPostSuccess(GroupPost post) {

    }

    @Override
    public void AddPostFailure(int error_code) {

    }
}