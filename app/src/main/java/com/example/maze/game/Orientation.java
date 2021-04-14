package com.example.maze.game;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class Orientation implements SensorEventListener {
    private final SensorManager manager;
    private final Sensor accelerometer;
    private final Sensor magnetometer;

    private float[] accelOutput;
    private float[] magOutput;


    private float[] orientation;
    public float[] getOrientation(){
        return orientation;
    }
    private float[] startOrientation;
    public float[] getStartOrientation(){
        return startOrientation;
    }

    public Orientation(Context context){
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        orientation = new float[9];
    }

    public void register(){
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
    }

    public void pause(){
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            accelOutput = event.values;
        }
        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            magOutput = event.values;
        }
        if(magOutput != null && accelOutput != null){
            float[] R = new float[9];
            float[] I = new float[9];
            boolean result = SensorManager.getRotationMatrix(R, I, accelOutput, magOutput);
            if(result){
                SensorManager.getOrientation(R, orientation);
                if(startOrientation == null){
                    startOrientation = new float[orientation.length];
                    System.arraycopy(orientation, 0, startOrientation, 0, orientation.length);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
