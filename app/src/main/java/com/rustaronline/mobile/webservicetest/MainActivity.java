package com.rustaronline.mobile.webservicetest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    static EditText celsius;
    Button calculate;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celsius = (EditText) findViewById(R.id.celsius);
        result = (TextView) findViewById(R.id.result);
        calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncCallSoap().execute();
            }
        });
    }

    public class AsyncCallSoap extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... parameter) {
            CallSoap soap = new CallSoap();
            String[] params = { MainActivity.celsius.getText().toString() };
            String[] names = { "Celsius" };
            return soap.getXmlFromSoap(params, names, "http://www.w3schools.com/xml/CelsiusToFahrenheit",
                    "CelsiusToFahrenheit","http://www.w3schools.com/xml/", "<?xml version=\"1.0\" encoding=\"utf-8\"?>",
                    "http://www.w3schools.com/xml/tempconvert.asmx");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            result.setText(ParseXMLString.xmlAsString(s, "CelsiusToFahrenheitResponse", "CelsiusToFahrenheitResult")[0]);
        }
    }
}
