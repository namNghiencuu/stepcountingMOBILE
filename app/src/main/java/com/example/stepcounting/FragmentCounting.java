package com.example.stepcounting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.common.internal.Constants;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import static android.content.Context.SENSOR_SERVICE;

public class FragmentCounting extends Fragment implements StepListener, SensorEventListener {
    private String TAG = MainActivity.class.getSimpleName();
    BroadcastReceiver broadcastReceiver;

<<<<<<< Updated upstream
    private TextView txtActivity, txtConfidence;
    private ImageView imgActivity;
    private Button btnStartTrcking, btnStopTracking;
=======
    TextView txtActivity, txtConfidence;
    ImageView imgActivity;
    Button btnStartTracking, btnStopTracking;
    private Context context;
>>>>>>> Stashed changes

    View view;
    Database myDB;
    private TextView textView;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private  Context mcontext;
    public FragmentCounting(Context context) {
        this.mcontext = context;
    }


    public FragmentCounting(){}

    TextView TvSteps;
    Button BtnStart;
    Button BtnStop;
    Button BtnSave;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_counting, container, false);
        TvSteps = (TextView)view.findViewById(R.id.tv_steps) ;
        BtnStart = (Button)view.findViewById(R.id.btn_start);
        BtnStop = (Button)view.findViewById(R.id.btn_stop);
        BtnSave = (Button)view.findViewById(R.id.btn_save);

        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);
        AddData();
        setAllOnClick(view);
        myDB = new Database(getActivity());

<<<<<<< Updated upstream



=======
        txtActivity = (TextView)view.findViewById(R.id.txt_activity);
        txtConfidence =(TextView)view.findViewById(R.id.txt_confidence);
        imgActivity = (ImageView)view.findViewById(R.id.img_activity);
        btnStartTracking = (Button)view.findViewById(R.id.btn_start_tracking);
        btnStopTracking = (Button)view.findViewById(R.id.btn_stop_tracking);

        btnStartTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTracking();
            }
        });

        btnStopTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTracking();
            }
        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Constants.BROADCAST_DETECTED_ACTIVITY)) {
                    int type = intent.getIntExtra("type", -1);
                    int confidence = intent.getIntExtra("confidence", 0);
                    handleUserActivity(type, confidence);
                }
            }
        };
>>>>>>> Stashed changes

//
//        BtnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//
//                numSteps = 0;
//                sensorManager.registerListener(FragmentCounting.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
//
//            }
//        });
//
//
//        BtnStop.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                sensorManager.unregisterListener(FragmentCounting.this);
//
//            }
//        });
        return view;

    };

    public void setAllOnClick(View view){

        BtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                numSteps = 0;
                sensorManager.registerListener(FragmentCounting.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });


        BtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                sensorManager.unregisterListener(FragmentCounting.this);

            }
        });
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }
    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }
    public void AddData(){
        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(TvSteps.getText().toString());
                if (isInserted = true)
                    Toast.makeText(getContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getContext(), "Data not Inserted", Toast.LENGTH_LONG).show();
            }
        });
    }


<<<<<<< Updated upstream
=======
        Log.e(TAG, "User activity: " + label + ", Confidence: " + confidence);

        if (confidence > com.example.stepcounting.Constants.CONFIDENCE) {
            txtActivity.setText(label);
            txtConfidence.setText("Confidence: " + confidence);
            imgActivity.setImageResource(icon);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(FragmentCounting.this.getActivity()).registerReceiver(broadcastReceiver,
                new IntentFilter(com.example.stepcounting.Constants.BROADCAST_DETECTED_ACTIVITY));
    }

    @Override
    public void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(FragmentCounting.this.getActivity()).unregisterReceiver(broadcastReceiver);
    }
    public void startTracking() {


                Intent intent = new Intent(getActivity(), BackgroundDetectedActivitiesService.class);
                getActivity().startService(intent);
    }

    public void stopTracking() {

                Intent intent = new Intent(getActivity(), BackgroundDetectedActivitiesService.class);
                getActivity().stopService(intent);
    }


>>>>>>> Stashed changes
}
