package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class FamilyActivity extends AppCompatActivity {
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
        count.add(new Word("Father","әpә",R.drawable.family_father,R.raw.family_father));
        count.add(new Word("Mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        count.add(new Word("Son","angsi",R.drawable.family_son,R.raw.family_son));
        count.add(new Word("Daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        count.add(new Word("Old Brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        count.add(new Word("Young Brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        count.add(new Word("Old Sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        count.add(new Word("Younger Sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        count.add(new Word("Grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        count.add(new Word("Grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));
        WordAdaptor itemsAdapter = new WordAdaptor(this,count,R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = count.get(position);
                ReleaseMedia();
                mediaPlayer = MediaPlayer.create(FamilyActivity.this,word.getmAudio());
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
