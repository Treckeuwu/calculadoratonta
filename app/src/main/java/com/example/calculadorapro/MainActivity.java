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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Double firstnum;
    String operation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Button plus = findViewById(R.id.plus);
        Button minus = findViewById(R.id.minus);
        Button diagonal = findViewById(R.id.diagonal);
        Button x = findViewById(R.id.x);
        Button igual = findViewById(R.id.igual);
        Button puntito = findViewById(R.id.puntito);
        Button AC = findViewById(R.id.AC);
        Button DEL = findViewById(R.id.DEL);
        Button OFF = findViewById(R.id.OFF);
        Button ON = findViewById(R.id.ON);

        TextView screen = findViewById(R.id.screen);


        AC.setOnClickListener(view ->
                {
                    screen.setText("0");
                    firstnum = 0.0;

                }

                );
        OFF.setOnClickListener(view -> screen.setVisibility(View.GONE));
        ON.setOnClickListener(view -> {

            screen.setVisibility(View.VISIBLE);
            screen.setText("0");
                }

        );
    ArrayList<Button> nums = new ArrayList<>();
    nums.add(num0);
    nums.add(num1);
    nums.add(num2);
    nums.add(num3);
    nums.add(num4);
    nums.add(num5);
    nums.add(num6);
    nums.add(num7);
    nums.add(num8);
    nums.add(num9);
    for(Button b : nums)
    {
        b.setOnClickListener(view -> {
            if(!screen.getText().toString().equals("0"))
            {

                screen.setText(screen.getText().toString() + b.getText().toString());
            }
            else{
                screen.setText(b.getText().toString());
            }

        });


    }



    ArrayList<Button> ops = new ArrayList<>();
    ops.add(plus);
    ops.add(minus);
    ops.add(diagonal);
    ops.add(x);
    ops.add(igual);
    ops.add(puntito);
    ops.add(AC);
    ops.add(DEL);
    for (Button b : ops) {
        b.setOnClickListener(view -> {
        firstnum = Double.parseDouble(screen.getText().toString());
        operation = b.getText().toString();
        screen.setText("0");

        });
    }
    DEL.setOnClickListener(view -> {
        String num = screen.getText().toString();
        if (num.length() > 1) {
            screen.setText(num.substring(0, num.length() - 1));
        } else {
            screen.setText("0");
        }
    });
    puntito.setOnClickListener(view -> {
        if(!screen.getText().toString().contains("."))
        {
            screen.setText(screen.getText().toString() + ".");
        }
    });
    igual.setOnClickListener(view -> {
        Double secondnum = Double.parseDouble(screen.getText().toString());
        double result;
        switch(operation) {
            case "/":
                result= firstnum/secondnum;
                break;
            case "X":
                result= firstnum*secondnum;
                break;
            case "-":
                result= firstnum-secondnum;
                break;
            case "+":
                result= firstnum+secondnum;
                break;
            default:
                result= firstnum+secondnum;
                break;
        }
        screen.setText(String.valueOf(result));
        firstnum = result;
    });




    }
}