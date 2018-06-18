package com.example.hw10.hw10;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchContact extends Fragment {

    private ListView lvContactList;
    private HighlightAdapter<String> dataAdapter;
    AlertDialog actionAskingDialog;
    AlertDialog confirmDeleteDialog;
    AlertDialog dataModifyDialog;
    EditText edtName_Modify, edtPhoneNumber_Modify;
    Spinner spnPhoneType_Modify;
    public SearchContact() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Delete?");
        builder.setPositiveButton("Delete", eventDeleteEntry);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        confirmDeleteDialog = builder.create();
        View view = View.inflate(getContext(), R.layout.data_modify_dialog, null);
        edtName_Modify = view.findViewById(R.id.edtName_Modify);
        edtPhoneNumber_Modify = view.findViewById(R.id.edtPhoneNumber_Modify);
        spnPhoneType_Modify = view.findViewById(R.id.spinPhoneType_Modify);
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Update contact");
        builder.setView(view);
        builder.setPositiveButton("Update", eventModifyData);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dataModifyDialog = builder.create();
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Option");
        builder.setMessage("Update or Delete");
        builder.setPositiveButton("Update", eventModifyAction);
        builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmDeleteDialog.show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        actionAskingDialog = builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_contact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        lvContactList = view.findViewById(R.id.lstContactList);
        lvContactList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvContactList.clearChoices();
        lvContactList.setOnItemLongClickListener(lsvContactList_OnLongClick);

        dataAdapter = new HighlightAdapter<>(this.getContext(), android.R.layout.simple_list_item_1);
        lvContactList.setAdapter(dataAdapter);
        Cursor cursor = MainActivity.mContRes.query(ContactProvider.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                dataAdapter.addData(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                cursor.moveToNext();
            }
            cursor.close();
        }
        dataAdapter.notifyDataSetChanged();
    }
    private final DialogInterface.OnClickListener eventModifyData = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            HighlightAdapter.DataModel data = dataAdapter.getLongPressedData();
            data.name = edtName_Modify.getText().toString();
            data.phoneNumber = edtPhoneNumber_Modify.getText().toString();
            data.phoneType = spnPhoneType_Modify.getSelectedItem().toString();
            MainActivity.mContRes.update(ContactProvider.CONTENT_URI, data.getContentValues(),
                    "_id = " + String.valueOf(data.id), null);
            dataAdapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "Update Successfully!", Toast.LENGTH_LONG).show();
        }
    };
    private final DialogInterface.OnClickListener eventModifyAction = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            HighlightAdapter.DataModel data = dataAdapter.getLongPressedData();
            edtName_Modify.setText(data.name);
            edtPhoneNumber_Modify.setText(data.phoneNumber);
            String[] phoneType = getResources().getStringArray(R.array.phone_type);
            if (data.phoneType.equals(phoneType[0])) {
                spnPhoneType_Modify.setSelection(0);
            }
            else if (data.phoneType.equals(phoneType[1])) {
                spnPhoneType_Modify.setSelection(1);
            }
            else {
                spnPhoneType_Modify.setSelection(2);
            }
            dataModifyDialog.show();
        }
    };
    private final DialogInterface.OnClickListener eventDeleteEntry = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            HighlightAdapter.DataModel data = dataAdapter.getLongPressedData();
            MainActivity.mContRes.delete(ContactProvider.CONTENT_URI, "_id = " + data.id, null);
            dataAdapter.removeLongPressedData();
            Toast.makeText(getContext(), "Delete successfully!", Toast.LENGTH_LONG).show();
        }
    };
    private final ListView.OnItemLongClickListener lsvContactList_OnLongClick = new ListView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            actionAskingDialog.show();
            return true;
        }
    };
    public void updateListData() {
        Cursor cursor = MainActivity.mContRes.query(ContactProvider.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            dataAdapter.clearAllData();
            while (!cursor.isAfterLast()) {
                dataAdapter.addData(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                cursor.moveToNext();
            }
            cursor.close();
            dataAdapter.notifyDataSetChanged();
            dataAdapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(getContext(), "Cannot update!", Toast.LENGTH_LONG).show();
        }
    }
    public void setListHighlight() {
        dataAdapter.setHighlightList();
    }
    public void setListHighlight(ArrayList<Integer> list) {
        dataAdapter.setHighlightList(list);
    }
}

