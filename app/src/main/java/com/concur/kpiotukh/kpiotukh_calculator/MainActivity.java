package com.concur.kpiotukh.kpiotukh_calculator;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textView
        final TextView textView = (TextView) findViewById(R.id.textViewResult);

        //aleartDialog
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //button "+" click
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(String.valueOf(getNumber1() + getNumber2()));

            }

        });

        //button "-" click
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(String.valueOf(getNumber1() - getNumber2()));

            }

        });

        //button "*" click
        Button buttonMultiple = (Button) findViewById(R.id.buttonMultiple);
        buttonMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(String.valueOf(getNumber1() * getNumber2()));

            }

        });

        //button "/" click
        Button buttonDevide = (Button) findViewById(R.id.buttonDevide);
        buttonDevide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check for deviding to 0
                if (getNumber2() != 0.0) {
                    textView.setText(String.valueOf(getNumber1() / getNumber2()));
                } else {
                    alertDialog
                            .setMessage(R.string.message_devision_to_zerro)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    TextView textView = (TextView) findViewById(R.id.textViewResult);
                                    Random rnd = new Random();
                                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                                    dialog.dismiss();
                                    //change color of TextView
                                    textView.setBackgroundColor(color);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }

        });
    }


    /**
     * Get Number from editTextNumber1
     *
     * @return double
     */
    double getNumber1() {
        return getNumber(R.id.editTextNumber1);
    }

    /**
     * Get Number from editTextNumber2
     *
     * @return double
     */
    double getNumber2() {
        return getNumber(R.id.editTextNumber2);
    }

    /**
     * Get value from the field by Id
     *
     * @param id int
     * @return double
     */
    private double getNumber(@IdRes int id) {
        double number = 0.0;
        EditText editTextNumber = (EditText) findViewById(id);
        String stringNumber = editTextNumber.getText().toString();
        if (stringNumber == null || "".equals(stringNumber)) {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.message_enter_number)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            TextView textView = (TextView) findViewById(R.id.textViewResult);
                            Random rnd = new Random();
                            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                            dialog.dismiss();
                            //change color of TextView
                            textView.setBackgroundColor(color);
                            return;
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            try {
                number = Double.parseDouble(stringNumber);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        return number;
    }

}
