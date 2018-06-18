package com.example.hw10.hw10;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

public class AddContact extends Fragment {
    EditText edtName, edtPhoneNumber;
    Spinner spnPhoneNumberType;
    public AddContact() { }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_contact, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        edtName = view.findViewById(R.id.edt_Name);
        edtPhoneNumber = view.findViewById(R.id.edt_PhoneNumber);
        spnPhoneNumberType = view.findViewById(R.id.spn_PhoneNumberType);
    }
    public ContentValues getContentValues() {
        ContentValues newData = new ContentValues();
        newData.put("name", edtName.getText().toString());
        newData.put("phoneNumber", edtPhoneNumber.getText().toString());
        newData.put("phoneType", spnPhoneNumberType.getSelectedItem().toString());
        return newData;
    }
}
