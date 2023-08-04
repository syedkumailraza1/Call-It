package com.example.call_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceConfig;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceFragment;

public class conf_activity extends AppCompatActivity {

    TextView meeting_id_txt;
    ImageView sharebtn;

    String meetingID,name,userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf);

        meeting_id_txt=findViewById(R.id.meeting_id_txt);
        sharebtn=findViewById(R.id.sharebtn);

        meetingID=getIntent().getStringExtra("meetingid");
       name= getIntent().getStringExtra("name");
       userid=getIntent().getStringExtra("userid");

       meeting_id_txt.setText("Meeting ID :"+ meetingID);

       sharebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =new Intent();
               intent.setAction(Intent.ACTION_SEND);
               intent.setType("text/plain");
               intent.putExtra(Intent.EXTRA_TEXT,"Join The Meeting In Call-It App \n Meeting ID : "+meetingID);
               startActivity(Intent.createChooser(intent,"Share Via"));
           }
       });

       addFragment();

    }

    public void addFragment() {
        long appID = AppConstants.appid;
        String appSign = AppConstants.appsign;



        ZegoUIKitPrebuiltVideoConferenceConfig config = new ZegoUIKitPrebuiltVideoConferenceConfig();
        ZegoUIKitPrebuiltVideoConferenceFragment fragment = ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(appID, appSign, userid, name,meetingID,config);

        config.turnOnCameraWhenJoining=false;
        config.turnOnMicrophoneWhenJoining=false;
        config.useSpeakerWhenJoining=false;


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }
}