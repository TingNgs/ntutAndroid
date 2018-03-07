package com.example.ngs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SubmitFunction(View view){
        EditText sexEditText = (EditText)findViewById(R.id.sex_edit_text);
        String sexInputed = sexEditText.getText().toString().toLowerCase();
        EditText ageEditText = (EditText)findViewById(R.id.age_edit_text);
        int ageInputed = GetAgeInteger(ageEditText.getText().toString());
        GetOutput(sexInputed,ageInputed);
    }

    private int GetAgeInteger( String input ) { //Pass in string
        try { //Try to make the input into an integer
            return Integer.parseInt( input ); //Return true if it age
        }
        catch( Exception e ) {
            return -1; //If it doesn't work return -1 and mean age input error
        }
    }

    private void GetOutput(String sex,int age){
            if(age<0)
                changeResult(getString(R.string.age_input_error));
            else if((sex.equals("male") && age<30) || (sex.equals("female") && age<28))
                changeResult(getString(R.string.not_hurry_result));
            else if((sex.equals("male") && age<=35) || (sex.equals("female") && age<=32))
                changeResult(getString(R.string.get_marry_result));
            else if((sex.equals("male") && age>35) || (sex.equals("female") && age>32))
                changeResult(getString(R.string.find_couple_result));
            else
                changeResult(getString(R.string.sex_input_error));
    }

    private void changeResult(String result){
        TextView resultTextView = (TextView)findViewById(R.id.result_text_view);
        resultTextView.setText("Result: "+result);
    }
}
