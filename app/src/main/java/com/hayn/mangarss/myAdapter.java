package com.hayn.mangarss;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class myAdapter extends BaseAdapter {

    private Context context;
    private String[] names;
    private String[] imgUrl;
    private static LayoutInflater inflater = null;

    public myAdapter(Context context, String[] names, String[] imgUrl) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.names = names;
        this.imgUrl = imgUrl;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if(names != null) return names.length;
        return 0;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return imgUrl[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.manga_header_content, null);

        TextView text = vi.findViewById(R.id.textViewEntry);
        ImageView image = vi.findViewById(R.id.imageViewEntry);
        text.setText(names[position]);
        try {
            Picasso.get().load(imgUrl[position]).into(image);
        } catch (Exception e){
            //e.printStackTrace();
        }
        return vi;
    }
}