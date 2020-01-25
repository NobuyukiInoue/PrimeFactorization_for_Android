package com.example.primefactorization_for_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import	android.widget.ProgressBar;

import com.example.primefactorization_for_android.PrimeFactorization.PrimeFactorization;
import com.example.primefactorization_for_android.ExLong.*;
import com.example.primefactorization_for_android.ExLong.Out.*;

import java.util.Locale;

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

                // Display ProgressBar
                progressBar.setVisibility(android.widget.ProgressBar.VISIBLE);

                pf.Main(n);
            //  textViewResult.setText(pf.getResultStr());

                // Hide ProgressBar
                progressBar.setVisibility(android.widget.ProgressBar.INVISIBLE);

                textViewResult.append(pf.getResultStr());
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}
