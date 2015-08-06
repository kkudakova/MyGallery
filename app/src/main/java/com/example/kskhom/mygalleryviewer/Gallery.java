package com.example.kskhom.mygalleryviewer;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Gallery extends FragmentActivity {
    static final String IMAGE_COLUMN_URI = "uri";
    private ImagePagerAdapter mAdapter;
    private ViewPager mPager;
    private PageFragment fr;

    final Uri IMAGE_URI = Uri
            .parse("content://media/external/images/media/");

    // A static dataset to back the ViewPager adapter
    public final static ArrayList<String> mImageUrls = new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery); // Contains just a ViewPager

        if(mImageUrls.isEmpty()){
        String pictureCols[] = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
        Cursor cursor = getBaseContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, pictureCols,
                null, null, null);
        cursor.moveToFirst();

        try {
            // Iterate the cursor for Image urls
            for (int index = 0; index < cursor.getCount(); index++) {

                cursor.moveToPosition(index);
                int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                mImageUrls.add(cursor.getString(dataColumnIndex));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }}
        mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), mImageUrls.size());
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

//        fr = (PageFragment) mAdapter.getItem(0);
//
//        // create the fragment and data the first time
//        if (fr != null) {
//            mImageUrls.addAll(fr.getMImageUrls());
//        }
    }

//    public void onDestroy() {
//        super.onDestroy();
//        if (mAdapter.getCount() > 0) {
//            fr = (PageFragment) mAdapter.getItem(0);
//            fr.setMImageUrls(mImageUrls);
//        }
//    }


    public static class ImagePagerAdapter extends FragmentPagerAdapter {
        private final int mSize;

        public ImagePagerAdapter(FragmentManager fm, int size) {
            super(fm);
            mSize = size;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(mImageUrls.get(position));
        }
    }
}