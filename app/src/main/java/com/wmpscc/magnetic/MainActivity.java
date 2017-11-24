package com.wmpscc.magnetic;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Time;

import static android.util.Half.EPSILON;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView tv;
    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tvContext);
        mTextView1 = (TextView) findViewById(R.id.tv1);
        mTextView2 = (TextView) findViewById(R.id.tv2);
        mTextView3 = (TextView) findViewById(R.id.tv3);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        tv.setText(mSensor.toString());
//        tv.setText("123");


    }


    private SensorEventListener mSensorListener = new SensorEventListener(){
        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {}                 //重写onAccuracyChanged方法
        @Override
        public void onSensorChanged(SensorEvent arg0) {                 //重写onSensorChanged方法
            if(arg0.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) { //只检查磁场的变化
                mTextView1.setText("x方向的磁场分量为：" + arg0.values[0]);                 //将数据显示到TextView
                mTextView2.setText("y方向的磁场分量为：" + arg0.values[1]);                 //将数据显示到TextView
                mTextView3.setText("z方向的磁场分量为：" + arg0.values[2]);                 //将数据显示到TextView
            }
        }
    };
    @Override
    protected void onResume() {      //重写的onResume方法
        mSensorManager.registerListener(    //注册监听
                mSensorListener,      //监听器SensorListener对象
                mSensor,       //传感器的类型为读取
                SensorManager.SENSOR_DELAY_UI   //频率
        );
        super.onResume();
    }

}
