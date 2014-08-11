package com.example.SimpleCalcultor;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mobimaks on 28.03.2014.
 */

public class MainActivity extends Activity implements OnClickListener {

    private Button add, sub, mult, div;
    private EditText firstText, secondText;
    private TextView result;

    private final int MENU_RESET_ID = 1;
    private final int MENU_QUIT_ID = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        add = (Button) findViewById(R.id.btnAdd);
        sub = (Button) findViewById(R.id.btnSub);
        mult = (Button) findViewById(R.id.btnMult);
        div = (Button) findViewById(R.id.btnDiv);

        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mult.setOnClickListener(this);
        div.setOnClickListener(this);

        firstText  = (EditText) findViewById(R.id.etNum1);
        secondText = (EditText) findViewById(R.id.etNum2);

        result = (TextView) findViewById(R.id.tvResult);

    }

    @Override
    public void onClick(View v) {
        float num1;
        float num2;
        if (TextUtils.isEmpty(firstText.getText().toString()) || TextUtils.isEmpty(secondText.getText().toString())){
            Toast.makeText(this, "Заповніть всі поля!", Toast.LENGTH_SHORT).show();
        }else {
            num1 = Float.parseFloat(firstText.getText().toString());
            num2 = Float.parseFloat(secondText.getText().toString());
            boolean norm = true;
            float res = 0;
            char operation = ' ';
            switch (v.getId()){
                case R.id.btnAdd:
                    operation = '+';
                    res = num1 + num2;
                    break;
                case R.id.btnSub:
                    operation = '-';
                    res = num1 - num2;
                    break;
                case R.id.btnMult:
                    operation = '*';
                    res = num1 * num2;
                    break;
                case R.id.btnDiv:
                    if (num2 != 0){
                        operation = '/';
                        res = num1 / num2;
                    }else {
                        Toast.makeText(this, "Помилка. Ділення на 0", Toast.LENGTH_SHORT).show();
                        norm = false;
                    }
                    break;
            }
            if (norm)
                result.setText(num1 + " " + operation + " " + num2 + " = " + res);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0, MENU_RESET_ID, 0, "Reset");
        menu.add(0, MENU_QUIT_ID, 1, "Exit");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case MENU_RESET_ID:
                firstText.setText("");
                secondText.setText("");
                result.setText("");
                break;
            case MENU_QUIT_ID:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
