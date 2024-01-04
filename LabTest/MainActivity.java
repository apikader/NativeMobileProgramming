package com.example.mindsharpener;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView4, textView5, textView6, textView8;
    private EditText editText;
    private Button button;
    private RadioGroup radioGroup;
    private int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView4 = findViewById(R.id.textview4);
        textView5 = findViewById(R.id.textview5);
        textView6 = findViewById(R.id.textview6);
        textView8 = findViewById(R.id.textview8);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        radioGroup = findViewById(R.id.radioGroup);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                updateQuestion();
            }
        });


        updateQuestion();
    }


    private void updateQuestion() {
        int selectedLevelId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedLevelRadioButton = findViewById(selectedLevelId);


        int maxDigits;
        if (selectedLevelRadioButton != null) {
            String selectedLevel = selectedLevelRadioButton.getText().toString();
            switch (selectedLevel) {
                case "i3":
                    maxDigits = 1;
                    break;
                case "i5":
                    maxDigits = 2;
                    break;
                case "i7":
                    maxDigits = 3;
                    break;
                default:
                    maxDigits = 1;
            }


            Random random = new Random();
            int firstNumber = random.nextInt((int) Math.pow(10, maxDigits));
            int secondNumber = random.nextInt((int) Math.pow(10, maxDigits));


            textView4.setText(String.valueOf(firstNumber));
            textView6.setText(String.valueOf(secondNumber));


            int operator = random.nextInt(4);


            switch (operator) {
                case 0:
                    textView5.setText("+");
                    break;
                case 1:
                    textView5.setText("-");
                    break;
                case 2:
                    textView5.setText("*");
                    break;
                case 3:
                    textView5.setText("/");
                    break;
            }


            editText.getText().clear();
        }
    }


    private void checkAnswer() {

        int firstNumber = Integer.parseInt(textView4.getText().toString());
        int secondNumber = Integer.parseInt(textView6.getText().toString());
        int operator = getOperator(textView5.getText().toString());
        int correctAnswer = calculateAnswer(firstNumber, secondNumber, operator);


        String userAnswerString = editText.getText().toString();


        if (!userAnswerString.isEmpty()) {
            int userAnswer = Integer.parseInt(userAnswerString);
            if (userAnswer == correctAnswer) {

                points++;
            } else {

                points--;
            }
        }


        textView8.setText(String.valueOf(points));


        updateQuestion();
    }

    private int getOperator(String operatorString) {
        switch (operatorString) {
            case "+":
                return 0;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 3;
            default:
                return 0;
        }
    }

    private int calculateAnswer(int firstNumber, int secondNumber, int operator) {
        switch (operator) {
            case 0:
                return firstNumber + secondNumber;
            case 1:
                return firstNumber - secondNumber;
            case 2:
                return firstNumber * secondNumber;
            case 3:

                return (secondNumber != 0) ? firstNumber / secondNumber : 0;
            default:
                return 0;
        }
    }
}
