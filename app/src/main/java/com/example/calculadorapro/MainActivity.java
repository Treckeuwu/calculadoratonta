package com.example.calculadorapro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private TextView screen;
    private boolean lastNumeric;
    private boolean stateError;
    private boolean lastDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById(R.id.screen);

        // Number buttons
        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);
        Button numDot = findViewById(R.id.num);

        // Operator buttons
        Button on = findViewById(R.id.on);
        Button off = findViewById(R.id.OFF);
        Button ac = findViewById(R.id.AC);
        Button del = findViewById(R.id.DEL);
        Button plus = findViewById(R.id.plus);
        Button minus = findViewById(R.id.minus);
        Button multiply = findViewById(R.id.x);
        Button divide = findViewById(R.id.diagonal);
        Button equal = findViewById(R.id.igual);

        // Set click listeners for number buttons
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDigit(((Button) v).getText().toString());
            }
        };
        num0.setOnClickListener(numberClickListener);
        num1.setOnClickListener(numberClickListener);
        num2.setOnClickListener(numberClickListener);
        num3.setOnClickListener(numberClickListener);
        num4.setOnClickListener(numberClickListener);
        num5.setOnClickListener(numberClickListener);
        num6.setOnClickListener(numberClickListener);
        num7.setOnClickListener(numberClickListener);
        num8.setOnClickListener(numberClickListener);
        num9.setOnClickListener(numberClickListener);


        // Set click listeners for operator buttons
        on.setOnClickListener(v -> onEqual());
        off.setOnClickListener(v -> onEqual());
        ac.setOnClickListener(v -> onEqual());
        del.setOnClickListener(v -> onEqual());
        plus.setOnClickListener(v -> onOperator("+"));
        minus.setOnClickListener(v -> onOperator("-"));
        multiply.setOnClickListener(v -> onOperator("*"));
        divide.setOnClickListener(v -> onOperator("/"));
        equal.setOnClickListener(v -> onEqual());
        numDot.setOnClickListener(v -> onDecimalPoint());
    }

    private void onDigit(String digit) {
        if (stateError) {
            screen.setText(digit);
            stateError = false;
        } else {
            screen.append(digit);
        }

        lastNumeric = true;
    }

    private void onOperator(String operator) {
        if (lastNumeric && !stateError) {
            screen.append(operator);
            lastNumeric = false;
            lastDot = false;    // Reset the DOT flag
        }
    }

    private void onDecimalPoint() {
        if (lastNumeric && !stateError && !lastDot) {
            screen.append(".");
            lastNumeric = false;
            lastDot = true;
        }
    }

    private void onEqual() {
        if (lastNumeric && !stateError) {
            String txt = screen.getText().toString();
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression expression = new ExpressionBuilder(txt).build();
            try {
                double result = expression.evaluate();
                screen.setText(Double.toString(result));
                lastDot = true;
            } catch (ArithmeticException ex) {
                screen.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}
}