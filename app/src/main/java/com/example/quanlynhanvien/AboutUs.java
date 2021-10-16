package com.example.quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        ViewGroup viewGroup = findViewById(R.id.activity_about_us);
        TextView text = viewGroup.findViewById(R.id.text);
        Button button = viewGroup.findViewById(R.id.button_click);
//button = new Button(null);
        button.setOnClickListener(new View.OnClickListener() {
            boolean visible = false;
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(viewGroup);
                visible = !visible;
                text.setVisibility(visible ? View.VISIBLE : View.GONE);

//                if (!visible) {
//                    text.setVisibility(View.VISIBLE);
//                    visible = true;
//
//                } else {
//                    text.setVisibility(View.GONE);
//                    visible = false;
//                }
            }

        });
    }
}