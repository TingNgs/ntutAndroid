package edu.ntut.user.myhw3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private RadioButton mRadBtnSexMale;
    private RadioButton mRadBtnSexFemale;
    private Spinner mSpnAgeRange;
    private Button mBtnOK;
    private TextView mTxtSug;
    private TextView mTxtHob;
    private CheckBox mChkBoxMusic;
    private CheckBox mChkBoxSing;
    private CheckBox mChkBoxDance;
    private CheckBox mChkBoxTravel;
    private CheckBox mChkBoxReading;
    private CheckBox mChkBoxWriting;
    private CheckBox mChkBoxClimbing;
    private CheckBox mChkBoxSwim;
    private CheckBox mChkBoxEating;
    private CheckBox mChkBoxDrawing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadBtnSexMale = (RadioButton) findViewById(R.id.radBtnSexMale);
        mRadBtnSexFemale = (RadioButton) findViewById(R.id.radBtnSexFemale);
        mSpnAgeRange = (Spinner) findViewById(R.id.spnAgeRange);
        mBtnOK = (Button) findViewById(R.id.btnOK);
        mTxtSug = (TextView) findViewById(R.id.txtSug);
        mTxtHob = (TextView) findViewById(R.id.txtHobbies);
        mChkBoxClimbing = (CheckBox) findViewById(R.id.chkBoxClimbing);
        mChkBoxDance = (CheckBox) findViewById(R.id.chkBoxDance);
        mChkBoxDrawing = (CheckBox) findViewById(R.id.chkBoxDrawing);
        mChkBoxEating = (CheckBox) findViewById(R.id.chkBoxEating);
        mChkBoxMusic = (CheckBox) findViewById(R.id.chkBoxMusic);
        mChkBoxReading = (CheckBox) findViewById(R.id.chkBoxReading);
        mChkBoxSing = (CheckBox) findViewById(R.id.chkBoxSing);
        mChkBoxSwim = (CheckBox) findViewById(R.id.chkBoxSwim);
        mChkBoxTravel = (CheckBox) findViewById(R.id.chkBoxTravel);
        mChkBoxWriting = (CheckBox) findViewById(R.id.chkBoxWriting);

        ArrayAdapter<CharSequence> maleList = ArrayAdapter.createFromResource(MainActivity.this, R.array.maleAgeRange, android.R.layout.simple_spinner_dropdown_item);
        mSpnAgeRange.setAdapter(maleList);
        mRadBtnSexMale.setOnClickListener(radBtnMaleOnLick);
        mRadBtnSexFemale.setOnClickListener(radBtnFemaleOnLick);
        mBtnOK.setOnClickListener(btnOKOnClick);
        /*mRadGrp = (RadioGroup) findViewById(R.id.radGrpAge);
        mTxtNumFamily = (TextView) findViewById(R.id.txtNumFamily);
        mNumPkrFamily = (NumberPicker) findViewById(R.id.numPkrFamply);
        mNumPkrFamily.setMinValue(0);
        mNumPkrFamily.setMaxValue(20);
        mNumPkrFamily.setValue(3);

        mSpnSex.setOnItemSelectedListener(spnOnItemSelect);
        mNumPkrFamily.setOnValueChangedListener(numPkrFamilyOnValueChange);
        */
    }

    /*private AdapterView.OnItemSelectedListener spnOnItemSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedSex = parent.getSelectedItem().toString();

            switch (selectedSex) {
                case "male": mRadBtnAgeRange1.setText(getString(R.string.maleAgeRange1));
                    mRadBtnAgeRange2.setText(getString(R.string.maleAgeRange2));
                    mRadBtnAgeRange3.setText(getString(R.string.maleAgeRange3));
                    break;
                case "female":
                    mRadBtnAgeRange1.setText(getString(R.string.femaleAgeRange1));
                    mRadBtnAgeRange2.setText(getString(R.string.femaleAgeRange2));
                    mRadBtnAgeRange3.setText(getString(R.string.femaleAgeRange3));
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private NumberPicker.OnValueChangeListener numPkrFamilyOnValueChange = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mTxtNumFamily.setText(String.valueOf(newVal));
        }
    };
    */
    private View.OnClickListener btnOKOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MarriageSuggestion ms = new MarriageSuggestion();
            String strSex;
            String strHob = getString(R.string.hobbies);
           if(mRadBtnSexMale.isChecked())
                strSex = mRadBtnSexMale.toString().toLowerCase();
            else
                strSex = mRadBtnSexFemale.toString().toLowerCase();
            int iAgeRange = mSpnAgeRange.getSelectedItemPosition()+1;

            if(mChkBoxMusic.isChecked()) strHob+=getString(R.string.music)+" ";
            if(mChkBoxSing.isChecked()) strHob+=getString(R.string.sing)+" ";
            if(mChkBoxDance.isChecked()) strHob+=getString(R.string.dance)+" ";
            if(mChkBoxTravel.isChecked()) strHob+=getString(R.string.travel)+" ";
            if(mChkBoxReading.isChecked()) strHob+=getString(R.string.reading)+" ";
            if(mChkBoxWriting.isChecked()) strHob+=getString(R.string.writing)+" ";
            if(mChkBoxClimbing.isChecked()) strHob+=getString(R.string.climbing)+" ";
            if(mChkBoxSwim.isChecked()) strHob+=getString(R.string.swim)+" ";
            if(mChkBoxEating.isChecked()) strHob+=getString(R.string.eating)+" ";
            if(mChkBoxDrawing.isChecked()) strHob+=getString(R.string.drawing)+" ";
            mTxtSug.setText(ms.getSuggestion(strSex, iAgeRange));
            mTxtHob.setText(strHob);

        }
    };
    private View.OnClickListener radBtnMaleOnLick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ArrayAdapter<CharSequence> maleList = ArrayAdapter.createFromResource(MainActivity.this, R.array.maleAgeRange, android.R.layout.simple_spinner_dropdown_item);
            mSpnAgeRange.setAdapter(maleList);
        }
    };
    private View.OnClickListener radBtnFemaleOnLick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ArrayAdapter<CharSequence> femaleList = ArrayAdapter.createFromResource(MainActivity.this, R.array.femaleAgeRange, android.R.layout.simple_spinner_dropdown_item);
            mSpnAgeRange.setAdapter(femaleList);
        }
    };
}
