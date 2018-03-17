/*
*   CIT 243 - Midterm Exam
*   I need to create a standalone Android program called
*   “TipCalculator” that will calculate a tip for a restaurant meal,
*   or other event where one needs to calculate a tip.
*
*   @Created by Olga Gavrylchenko, 02/26/2018
* */

package com.example.android.tipcalculator;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASED_VALUE = "BasedValue";
    private static final String PERCENT_VALUE = "PercentValue";
    private static final String TOTAL_VALUE = "TotalAmount";
    private static final String TIPS_VALUE = "TipsValue";

    private EditText mBasedAmount, mTipAmount, mTotalAmount;
    private SeekBar mTipAmountPercent;
    private TextView mTipPercentLabel, mApiLevel;

    private Tips mTips;

    double mUserInput, mTipsAmount, mTotal;
    int mPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //instantiate an object to calculate tips
        this.mTips = new Tips();

        //
        this.mTipAmountPercent = (SeekBar) findViewById(R.id.seek_bar);

        this.mTipAmount = (EditText) findViewById(R.id.tip_amount);

        this.mTotalAmount = (EditText) findViewById(R.id.total_amount);

        this.mBasedAmount = (EditText) findViewById(R.id.based_amount);



        this.mTipAmountPercent.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                int percent = seekBar.getProgress();

                mTipPercentLabel.setText(percent+"%");
                Log.d("PER:", String.valueOf(percent));

                //set data to object
                mTips.setmTipPercent(percent);

                //calculate tips and total amount
                mTips.setmTipAmount();
                mTips.setmTotalAmount();

                //display result
                mTipAmount.setText(formatData(mTips.getmTipAmount()));
                mTotalAmount.setText(formatData(mTips.getmTotalAmount()));
            }

            public void onStartTrackingTouch(SeekBar seekBar){
                int percent = seekBar.getProgress();

                mTipPercentLabel.setText(percent+"%");


                //set data to object
                mTips.setmTipPercent(percent);

            }

            public void onStopTrackingTouch(SeekBar seekBar){
                int percent = seekBar.getProgress();

                mTipPercentLabel.setText(percent+"%");


                //set data to object
                mTips.setmTipPercent(percent);

                //calculate tips and total amount
                mTips.setmTipAmount();
                mTips.setmTotalAmount();

                //display result
                mTipAmount.setText(formatData(mTips.getmTipAmount()));
                mTotalAmount.setText(formatData(mTips.getmTotalAmount()));
            }

        });


        this. mTipPercentLabel = (TextView) findViewById(R.id.tip_percent);
        // Initialize the textview with '0'.
        this. mTipPercentLabel.setText(this.mTipAmountPercent.getProgress()+"%");




        this.mBasedAmount.addTextChangedListener(new TextWatcher(){

            public void onTextChanged(CharSequence s, int start, int before, int count){
                //if editbox is not empty
                if(s.length() != 0){

                    String dbl = String.valueOf(mBasedAmount.getText());
                    Log.d("CALC RESULT: ", dbl);

                    try {
                        mUserInput = Double.parseDouble(String.valueOf(mBasedAmount.getText()));

                        mTips.setmUserAmount(mUserInput);

                        //calculate tips and total amount
                        mTips.setmTipAmount();
                        mTips.setmTotalAmount();

                        //display result
                        mTipAmount.setText(formatData(mTips.getmTipAmount()));
                        mTotalAmount.setText(formatData(mTips.getmTotalAmount()));

                    }catch(Exception e){

                        mTipAmount.setText("0.00");
                        mTotalAmount.setText("0.00");
                    }

                }else{
                    mTipAmount.setText("");
                    mTotalAmount.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            @Override
            public void afterTextChanged(Editable s){

            }
        });


        //check if bundle store key
        if(savedInstanceState != null) {
            //assign key-value to mCurrentIndex
            mUserInput = savedInstanceState.getDouble(BASED_VALUE, 0);
            mTotal = savedInstanceState.getDouble(TOTAL_VALUE, 0);
            mTipsAmount = savedInstanceState.getDouble(TIPS_VALUE, 0);
            mPercent = savedInstanceState.getInt(PERCENT_VALUE, 0);
        }

        //get resource id
        mApiLevel = (TextView) findViewById(R.id.api_index);
        //get API level
        mApiLevel.setText("API Level " + String.valueOf(Build.VERSION.SDK_INT));
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        //store key value in a bundle
        savedInstanceState.putDouble(BASED_VALUE, mUserInput);
        savedInstanceState.putDouble(TOTAL_VALUE, mTotal);
        savedInstanceState.putDouble(TIPS_VALUE, mTipsAmount);
        savedInstanceState.putInt(PERCENT_VALUE, mPercent);
    }//onSaveInstanceState


    private String formatData(double value){
        return String.format("$"+"%.2f", value);
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d(TAG, "onStart() called");

    }//onStart

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }//onPause

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }//onStop

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }//onResume

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }//onDestroy

}
