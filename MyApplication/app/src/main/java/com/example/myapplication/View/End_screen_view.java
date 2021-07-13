package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class End_screen_view extends AppCompatActivity {

    TextView delPoints;
    TextView totalPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        delPoints = findViewById(R.id.textView_delPoints);
        totalPoints = findViewById(R.id.textView_totalPoints);
        String[] arr = getIntent().getStringArrayExtra("valpoints");

        int initvalue = getIntent().getIntExtra("totalpoints",0);
        totalPoints.setText(String.valueOf(initvalue));
        totalPoints.setTextSize(30);
        delPoints.setText(build(arr));
        delPoints.setTextSize(20);
    }
    public StringBuilder build(String [] arr){
        StringBuilder sb = new StringBuilder();
        sb.append("Points for each val: ");
        sb.append("\n");
        for(String s : arr){
            sb.append(s);
            sb.append("\n");
        }
        return sb;
    }
}