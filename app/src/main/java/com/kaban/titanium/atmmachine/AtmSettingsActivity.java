package com.kaban.titanium.atmmachine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AtmSettingsActivity extends AppCompatActivity {

    private EditText mSettings200;
    private EditText mSettings100;
    private EditText mSettings50;
    private EditText mSettings20;
    private EditText mSettings10;
    private EditText mSettings5;
    private EditText mSettings1;
    private Button mSetSettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm_settings);

        mSettings200 = (EditText) findViewById(R.id.settings_200);
        mSettings100 = (EditText) findViewById(R.id.settings_100);
        mSettings50 = (EditText) findViewById(R.id.settings_50);
        mSettings20 = (EditText) findViewById(R.id.settings_20);
        mSettings10 = (EditText) findViewById(R.id.settings_10);
        mSettings5 = (EditText) findViewById(R.id.settings_5);
        mSettings1 = (EditText) findViewById(R.id.settings_1);
        mSetSettingsButton = (Button) findViewById(R.id.set_settings);

        setSettingsText();

        mSetSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAtmSettings();
                finish();
            }
        });
    }

    /**
     * Метод для установки текста во все EditText
     */
    private void setSettingsText() {
        int[] billsCountArray = AtmModel.getInstance().getBillsCountArray();

        mSettings200.setText(billsCountArray[0]);
        mSettings100.setText(billsCountArray[1]);
        mSettings50.setText(billsCountArray[2]);
        mSettings20.setText(billsCountArray[3]);
        mSettings10.setText(billsCountArray[4]);
        mSettings5.setText(billsCountArray[5]);
        mSettings1.setText(billsCountArray[6]);
    }

    /**
     * Метод для задания кол-ва купюр каждого номинала
     */
    private void setAtmSettings() {
        int[] billsCountArray = new int[7];

        billsCountArray[0] = getInt(mSettings200.getText().toString());
        billsCountArray[1] = getInt(mSettings100.getText().toString());
        billsCountArray[2] = getInt(mSettings50.getText().toString());
        billsCountArray[3] = getInt(mSettings20.getText().toString());
        billsCountArray[4] = getInt(mSettings10.getText().toString());
        billsCountArray[5] = getInt(mSettings5.getText().toString());
        billsCountArray[6] = getInt(mSettings1.getText().toString());

        AtmModel.getInstance().setBillsCountArray(billsCountArray);
    }

    /**
     * Метод для получения числа из строки и замены пустых строк на 0
     */
    private int getInt(String string) {
        if (string.equals("")) {
            return 0;
        }
        return Integer.parseInt(string);
    }
}
