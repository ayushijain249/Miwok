package com.example.android.miwok;

/**
 * Word class is used to store Default and Miwok Translation
 * Created by Golu on 8/26/2016.
 */
public class Word {
    /** default Translation of the app */
    private String mDefaultTranslation;

    /** Miwok Translation of the app */
    private String mMiwokTranslation;

    /**Resource Id of the Image */
    private int mImageResourceId = -1;

    private int mAudio;

    /** Construct to set mDefaultTranslation and mMiwokTranslation */
    public Word(String defaultTranslation , String miwokTranslation, int Audio )
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudio = Audio;
    }

    /** Construct to set mDefaultTranslation and mMiwokTranslation */
    public Word(String defaultTranslation , String miwokTranslation ,int ImageResourceId, int Audio )
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = ImageResourceId;
        mAudio = Audio;
    }

    /** return the default Translation of the app */
    public String getDefaultTranslation()
    {
        return mDefaultTranslation;
    }

    /** return the Miwok Translation of the app */
    public String getMiwokTranslation()
    {
        return mMiwokTranslation;
    }

    /** return the Image Resource id of the app */
    public int getImageResourceId(){ return mImageResourceId; }

    public boolean isImageAvailable()
    {
        return mImageResourceId != -1;
    }

    public int getmAudio() { return  mAudio;}
}
