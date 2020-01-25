package com.example.primefactorization_for_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import	android.widget.ProgressBar;

import java.util.Locale;
import static java.lang.Thread.sleep;

import com.example.primefactorization_for_android.PrimeFactorization.PrimeFactorization;
import com.example.primefactorization_for_android.ExLong.*;
import com.example.primefactorization_for_android.ExLong.Out.*;

public class MainActivity extends AppCompatActivity {
    private EditText editTextInputNumeric;
    private Button buttonCalc;
    private ProgressBar progressBar;
    private TextView textViewResult;
    private ScrollView scrollView;
    private PrimeFactorization pf;
    private long n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInputNumeric = findViewById(R.id.edit_text_inputNumeric);
        buttonCalc = findViewById(R.id.button_calc);
        progressBar = findViewById(R.id.progressBar);

        textViewResult = findViewById(R.id.text_view_Result);
        scrollView = findViewById(R.id.scroll_view);

        pf = new PrimeFactorization();

        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display ProgressBar
                progressBar.setVisibility(android.widget.ProgressBar.VISIBLE);

                // Execute Prime Factorization
                primeFactorizationRun();

                while (pf.getResultStr().length() <= 0) {
                    try {
                        sleep(200);
                    } catch (Exception e) {
                        Log.d("DEBUG", "Thread.sleep() error.");
                        if (e.getMessage() != null) Log.d("DEBUG", e.getMessage());
                    }
                }

                textViewResult.append(pf.getResultStr());
                pf.clearResultStr();
                scrollView.fullScroll(View.FOCUS_DOWN);

                // Hide ProgressBar
                progressBar.setVisibility(android.widget.ProgressBar.INVISIBLE);
            }
        });
    }

    private void primeFactorizationRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String inputNumStr = editTextInputNumeric.getText().toString();
                ExLong el = new ExLong();
                Out<Long> n_temp = new Out<Long>();
                long n;
                if (!el.TryParse(inputNumStr, n_temp)) {
                    textViewResult.setText(String.format(Locale.JAPAN, "%s is not numeric.", inputNumStr));
                    return;
                }

                // Read the value you want to factor
                n = n_temp.get();
                pf.Main(n);
            }
        }).start();
    }
}
