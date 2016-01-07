package com.dongxingrong.animation.propertyanimation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dongxingrong.animation.R;

/**
 * Created by cupcake on 16-1-6.
 */
public class ListAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item, null);
        }
        return convertView;
    }

    public ListAdapter(Context mContext) {
        inflater = LayoutInflater.from(mContext);
    }
}
