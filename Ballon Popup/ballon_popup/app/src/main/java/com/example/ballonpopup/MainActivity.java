package com.example.ballonpopup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.ballonpopup.databinding.ActivityMainBinding;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;
//Add this dependency to your app levele gradle:
     //implementation "com.github.skydoves:balloon:1.1.2"
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        initRec();
    }

    private void initRec()
    {
        activityMainBinding.songsRec.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.songsRec.setAdapter(new SongsAdapter(this));
    }


}
