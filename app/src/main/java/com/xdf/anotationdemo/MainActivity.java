package com.xdf.anotationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.xdf.annotation_lib.InjectTool;
import com.xdf.annotation_lib.annotation.BindView;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_1)
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectTool.Inject(this);
        Toast.makeText(this,btn1.toString(),Toast.LENGTH_LONG).show();
    }
}