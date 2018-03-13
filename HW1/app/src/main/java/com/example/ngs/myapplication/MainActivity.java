package com.example.ngs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText sexEditText,ageEditText;
    private TextView resultTextView;
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sexEditText = (EditText)findViewById(R.id.sex_edit_text);
        ageEditText = (EditText)findViewById(R.id.age_edit_text);
        submitButton = (Button)findViewById(R.id.submit_button);
        resultTextView = (TextView)findViewById(R.id.result_text_view);

        submitButton.setOnClickListener(submitButtonOnClick);
    }

    private View.OnClickListener submitButtonOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            String sexInputed = sexEditText.getText().toString().toLowerCase();
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
            String maleInput = getString(R.string.sex_male);
            String femaleInput = getString(R.string.sex_female);
            String suggestionsTextView = getString(R.string.suggestions_text_view);
            if(age<0)
                resultTextView.setText(suggestionsTextView+getString(R.string.age_input_error));
            else if((sex.equals(maleInput) && age<30) || (sex.equals(femaleInput) && age<28))
                resultTextView.setText(suggestionsTextView+getString(R.string.not_hurry_result));
            else if((sex.equals(maleInput) && age<=35) || (sex.equals(femaleInput) && age<=32))
                resultTextView.setText(suggestionsTextView+getString(R.string.get_marry_result));
            else if((sex.equals(maleInput) && age>35) || (sex.equals(femaleInput) && age>32))
                resultTextView.setText(suggestionsTextView+getString(R.string.find_couple_result));
            else
                resultTextView.setText(suggestionsTextView+getString(R.string.sex_input_error));
        }
    };


}
