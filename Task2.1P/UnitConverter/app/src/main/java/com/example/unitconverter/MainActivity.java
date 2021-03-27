package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Spinner unitSpinner = null;
    EditText valueEditText;
    TextView unit1TextView;
    TextView unit2TextView;
    TextView unit3TextView;
    TextView value1TextView;
    TextView value2TextView;
    TextView value3TextView;
    int unitNumber;
    double basicValue;
    double result;
    DecimalFormat printResult = new DecimalFormat("#.00");

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valueEditText = findViewById(R.id.editText);
        unit1TextView = findViewById(R.id.unit1);
        unit2TextView = findViewById(R.id.unit2);
        unit3TextView = findViewById(R.id.unit3);
        value1TextView = findViewById(R.id.value1);
        value2TextView = findViewById(R.id.value2);
        value3TextView = findViewById(R.id.value3);
        unitSpinner = (Spinner) findViewById(R.id.spinnerUnits);
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                value1TextView.setText("");
                value2TextView.setText("");
                value3TextView.setText("");
                unitNumber = position;
                switch (position){
                    case 0:
                        unit1TextView.setText("Centimetre");
                        unit2TextView.setText("Foot");
                        unit3TextView.setText("Inch");
                        break;
                    case 1:
                        unit1TextView.setText("Gram");
                        unit2TextView.setText("Ounce");
                        unit3TextView.setText("Pound");
                        break;
                    case 2:
                        unit1TextView.setText("Fahrenheit");
                        unit2TextView.setText("Kelvin");
                        unit3TextView.setText("");
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void lengthClick(View view) {
        if (unitNumber == 0){
                basicValue = Double.parseDouble(valueEditText.getText().toString());
                result = basicValue * 100;
                value1TextView.setText(printResult.format(result));
                result = result / 30.48;
                value2TextView.setText(printResult.format(result));
                result = result * 12;
                value3TextView.setText(printResult.format(result));
        }
        else{
           Toast.makeText(MainActivity.this, "Please select the correct conversion icon", Toast.LENGTH_LONG).show();
        }
    }

    public void weightClick(View view) {
        if (unitNumber == 1){
            basicValue = Double.parseDouble(valueEditText.getText().toString());
            result = basicValue * 1000;
            value1TextView.setText(printResult.format(result));
            result = result / 28.35;
            value2TextView.setText(printResult.format(result));
            result = result / 16;
            value3TextView.setText(printResult.format(result));
        }
        else{
            Toast.makeText(MainActivity.this, "Please select the correct conversion icon", Toast.LENGTH_LONG).show();
        }
    }

    public void temperatureClick(View view) {
        if (unitNumber == 2){
            basicValue = Double.parseDouble(valueEditText.getText().toString());
            result = basicValue * 1.8 + 32;
            value1TextView.setText(printResult.format(result));
            result = result +273.15;
            value2TextView.setText(printResult.format(result));
        }
        else{
            Toast.makeText(MainActivity.this, "Please select the correct conversion icon", Toast.LENGTH_LONG).show();
        }
    }


}