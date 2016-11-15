package com.example.android.miwok;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    MediaPlayer.OnCompletionListener mediaCompletion =new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            ReleaseMedia();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        final ArrayList<Word> count = new ArrayList<Word>();

        count.add(new Word("Red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        count.add(new Word("Green","chokokki",R.drawable.color_green,R.raw.color_green));
        count.add(new Word("Brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        count.add(new Word("Gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        count.add(new Word("Black","kululli",R.drawable.color_black,R.raw.color_black));
        count.add(new Word("White","kelelli",R.drawable.color_white,R.raw.color_white));
        count.add(new Word("Dusty Yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        count.add(new Word("Mustard Yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        WordAdaptor itemsAdapter = new WordAdaptor(this,count,R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = count.get(position);
                ReleaseMedia();
                mediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getmAudio());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mediaCompletion);
            }
        });


        // Log.v("NumbersActivity","Word at 0th position is" + count[0]);
    }
    public void ReleaseMedia()
    {
        if(mediaPlayer != null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
