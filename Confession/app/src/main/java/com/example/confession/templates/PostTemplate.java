package com.example.confession.templates;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.confession.R;

public class PostTemplate extends Activity {

	Button btn_asdas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		btn_asdas = findViewById(R.id.btadasda.);

		btn_asdas.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//...
			}
		});

	}
}
