package com.example.ngoch.sqlite_image;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class HinhAdapter extends BaseAdapter {
    MainActivity context;
    int layout;
    ArrayList<Hinh> listHinh;

    public HinhAdapter(MainActivity context, int layout, ArrayList<Hinh> listHinh) {
        this.context = context;
        this.layout = layout;
        this.listHinh = listHinh;
    }

    @Override
    public int getCount() {
        return listHinh.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        ImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);

            //anh xa
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageListHinh);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Hinh hinh = listHinh.get(position);
        holder.imageView.setImageBitmap(hinh.getBitmap());

        return convertView;
    }
}
