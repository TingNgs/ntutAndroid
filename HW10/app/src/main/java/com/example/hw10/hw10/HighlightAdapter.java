package com.example.hw10.hw10;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HighlightAdapter<T> extends ArrayAdapter<T> {
    private ArrayList<DataModel> dataList;
    private ArrayList<Integer> indexList;
    private int longClickedItemIndex;
    public HighlightAdapter(Context context, int resource) {
        super(context, resource);
        dataList = new ArrayList<>();
        indexList = new ArrayList<>();
        longClickedItemIndex = 0;
    }
    @Override
    public int getCount() {
        return dataList.size();
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DataModel data = dataList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.info_list_item, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.txvName)).setText(data.name);
        ((TextView) convertView.findViewById(R.id.txvPhoneNumber)).setText(data.phoneNumber);
        ((TextView) convertView.findViewById(R.id.txvPhoneType)).setText(data.phoneType);
        if (indexList.contains(position)) {
            convertView.setBackgroundResource(R.color.selected);
        }
        else {
            convertView.setBackgroundResource(R.color.normal);
        }
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longClickedItemIndex = position;
                return false;
            }
        });
        return convertView;
    }
    public void clearAllData() {
        dataList.clear();
    }
    public void addData(int id, String name, String phoneNumber, String phoneType) {
        dataList.add(new DataModel(id, name, phoneNumber, phoneType));
    }
    public DataModel getLongPressedData() { return dataList.get(longClickedItemIndex); }
    public void removeLongPressedData() {
        dataList.remove(longClickedItemIndex);
        this.notifyDataSetChanged();
    }
    public void setHighlightList() { indexList.clear(); }
    public void setHighlightList(ArrayList<Integer> newIndexList) {
        int length = dataList.size();
        indexList.clear();
        for (int id : newIndexList) {
            for (int index = 0; index < length; index++) {
                if (id == dataList.get(index).id) {
                    indexList.add(index);
                    break;
                }
            }
        }
    }
    class DataModel {
        protected int id;
        protected String name;
        protected String phoneNumber;
        protected String phoneType;

        public DataModel(int id, String name, String phoneNumber, String phoneType) {
            this.id = id;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.phoneType = phoneType;
        }
        public ContentValues getContentValues() {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("phoneNumber", phoneNumber);
            values.put("phoneType", phoneType);
            return values;
        }
    }
}
