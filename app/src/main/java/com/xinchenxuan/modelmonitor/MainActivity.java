package com.xinchenxuan.modelmonitor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends Activity {
    private static final double COEFFICIENT = 9461.46662842;
    private static final double INTERCEPT = 25746.489383688757;

    private EditText experienceInput;
    private TextView resultView;
    private TextView resultCaption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        experienceInput = findViewById(R.id.experience_input);
        resultView = findViewById(R.id.result_value);
        resultCaption = findViewById(R.id.result_caption);
        Button calculateButton = findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(view -> calculatePrediction());
    }

    private void calculatePrediction() {
        String value = experienceInput.getText().toString().trim();
        if (value.isEmpty()) {
            showError("Enter years of experience first.");
            return;
        }

        double experience;
        try {
            experience = Double.parseDouble(value);
        } catch (NumberFormatException exception) {
            showError("Use a number such as 4.5.");
            return;
        }

        if (experience < 0 || experience > 50) {
            showError("Enter a value between 0 and 50 years.");
            return;
        }

        double prediction = COEFFICIENT * experience + INTERCEPT;
        resultView.setText(String.format(Locale.US, "$%,.0f", prediction));
        resultCaption.setText(String.format(
                Locale.US,
                "Estimated annual salary for %.1f years of experience",
                experience
        ));

        InputMethodManager keyboard =
                (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View focused = getCurrentFocus();
        if (keyboard != null && focused != null) {
            keyboard.hideSoftInputFromWindow(focused.getWindowToken(), 0);
        }
    }

    private void showError(String message) {
        resultView.setText(R.string.no_estimate);
        resultCaption.setText(message);
    }
}
