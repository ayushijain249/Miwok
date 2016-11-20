package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
           if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
           {
                mediaPlayer.pause();
               mediaPlayer.seekTo(0);
           }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
           {
               mediaPlayer.start();
           }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
           {
               ReleaseMedia();
           }
        }
    };
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
        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> count = new ArrayList<Word>();

        count.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        count.add(new Word("Two","otiiko",R.drawable.number_two,R.raw.number_two));
        count.add(new Word("Three","tolookosu",R.drawable.number_three,R.raw.number_three));
        count.add(new Word("Four","oyyisa",R.drawable.number_four,R.raw.number_four));
        count.add(new Word("Five","massokka",R.drawable.number_five,R.raw.number_five));
        count.add(new Word("Six","temmokka",R.drawable.number_six,R.raw.number_six));
        count.add(new Word("Seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        count.add(new Word("Eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        count.add(new Word("Nine","wo’e",R.drawable.number_nine,R.raw.number_nine));
        count.add(new Word("Ten","na’aacha",R.drawable.number_ten,R.raw.number_ten));

        WordAdaptor itemsAdapter = new WordAdaptor(this,count,R.color.category_numbers);
        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Word word = count.get(position);
                ReleaseMedia();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this,word.getmAudio());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mediaCompletion);
                }

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
            mAudioManager.abandonAudioFocus(mOnAudioFocusListener);
        }
    }
}
