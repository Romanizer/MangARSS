package com.hayn.mangarss;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class myAdapter extends BaseAdapter {

    private Context context;
    private String[] names;
    private Image[] img;
    private static LayoutInflater inflater = null;

    myAdapter(Context context, String[] names, Image[] img) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.names = names;
        this.img = img;
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
        return img[position];
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

        TextView text = (TextView) vi.findViewById(R.id.textViewEntry);
        ImageView image = (ImageView) vi.findViewById(R.id.imageViewEntry);
        text.setText(names[position]);
        image.setImageBitmap((Bitmap) img[posiaaation]);
        return vi;
    }
}