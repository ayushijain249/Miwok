package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Golu on 8/26/2016.
 */
public class WordAdaptor extends ArrayAdapter<Word> {
    private int background_color;
    public WordAdaptor(Context context, ArrayList<Word> count, int backgroundColor) {
        super(context, 0, count);
        background_color = backgroundColor;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Word count = getItem(position);
        View listView = convertView;
        if(listView == null)
        {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        TextView defaultTextView = (TextView)listView.findViewById(R.id.text);
        defaultTextView.setText(count.getMiwokTranslation());
        TextView miwokTextView = (TextView)listView.findViewById(R.id.text2);
        miwokTextView.setText(count.getDefaultTranslation());
        ImageView imageResource = (ImageView)listView.findViewById(R.id.image_view);
        if(count.isImageAvailable())
        {
            imageResource.setImageResource(count.getImageResourceId());
        }
        else
        {
            imageResource.setVisibility(View.GONE);
        }
        LinearLayout linearLayout = (LinearLayout)listView.findViewById(R.id.llayout);
        linearLayout.setBackgroundColor(ContextCompat.getColor(getContext(),background_color));
        return listView;
    }
}
