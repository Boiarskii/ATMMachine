package com.kaban.titanium.atmmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mMoneyEditText;
    private Button mGetMoneyButton;
    private Button mSettingsButton;
    private TextView mMoneyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoneyEditText = (EditText) findViewById(R.id.money_edit_text);
        mGetMoneyButton = (Button) findViewById(R.id.get_money_button);
        mSettingsButton = (Button) findViewById(R.id.settings_button);
        mMoneyTextView = (TextView) findViewById(R.id.money_text_view);

        mGetMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String moneyString = "";
                if (!mMoneyEditText.getText().toString().equals("")) {
                    moneyString = giveMoney(
                            Integer.parseInt(mMoneyEditText.getText().toString()),
                            AtmModel.getInstance().getBillsArray(),
                            AtmModel.getInstance().getBillsCountArray());
                }
                mMoneyTextView.setText(moneyString);
            }
        });

        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AtmSettingsActivity.class));
            }
        });
    }

    /** Метод для выдачи купюр в банкомате
     *
     * @param cash - сумма, которую хочеть получить пользователь
     * @param bills - массив, содержащий номиналы банкнот
     * @param billsCounts - массив, содержащий количество соответствующих банкнот
     */
    private String giveMoney(int cash, int[] bills, int[] billsCounts) {


        if (bills.length != billsCounts.length) {
            System.out.println("Массив номиналов банкнот должен иметь такой же размер, как массив кол-ва банкнот");
        }

        /** Целое частное при делении суммы на номинал банкнот */
        int quotient;

        String moneyString = "";

        for (int i = 0; i < bills.length; i++) {
            quotient = cash / bills[i];

            if (quotient == 0 || billsCounts[i] == 0) {
                continue;
            }

            if (quotient <= billsCounts[i]) {
                cash -= quotient * bills[i];
                moneyString += (bills[i] + " " + quotient + "\n");
                billsCounts[i] = billsCounts[i] - quotient;
            } else {
                cash -= billsCounts[i] * bills[i];
                moneyString += (bills[i] + " " + billsCounts[i] + "\n");
                billsCounts[i] = 0;
            }
        }

        if (cash != 0) {
            moneyString += ("ATM can\'t give " + cash);
        }

        return moneyString;
    }
}

