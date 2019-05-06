package com.songdehuai.commonexample.ui.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.songdehuai.commonexample.R;
import com.songdehuai.commonexample.ui.Myentity;

import java.util.ArrayList;
import java.util.List;

public class MainListAdapter extends BaseAdapter {

    private Activity activity;
    private List<Myentity> stringList;

    public MainListAdapter(Activity activity) {
        this.activity = activity;
        stringList = new ArrayList<>();
    }

    public void addList(List<Myentity> list) {
        stringList.addAll(list);
        notifyDataSetChanged();
    }

    public void setList(List<Myentity> strings) {
        stringList.clear();
        stringList.addAll(strings);
        notifyDataSetChanged();
    }

    public void addData(String data){
        this.stringList.add(data);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Myentity getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = View.inflate(activity, R.layout.item_test, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (stringList.get(position).isSelect()) {
            viewHolder.textView.setTextColor(Color.RED);
        } else {
            viewHolder.textView.setTextColor(Color.BLACK);
        }
        viewHolder.textView.setText(stringList.get(position).getStr());
        return convertView;
    }

    class ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            textView = itemView.findViewById(R.id.item_tv);
        }
    }

}
