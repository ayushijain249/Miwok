package com.example.android.miwok;

import android.media.MediaPlayer;
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

public class PhrasesActivity extends AppCompatActivity {
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
        count.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        count.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        count.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        count.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        count.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        count.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        count.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        count.add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        count.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        count.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));

        WordAdaptor itemsAdapter = new WordAdaptor(this,count,R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = count.get(position);
                ReleaseMedia();
                mediaPlayer = MediaPlayer.create(PhrasesActivity.this,word.getmAudio());
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
