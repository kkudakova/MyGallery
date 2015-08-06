package com.example.kskhom.mygalleryviewer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by kskhom on 03.08.2015.
 */
public class PageFragment extends Fragment {
    private static final String IMAGE_DATA_EXTRA = "resId";
    static final int GALLERY_REQUEST = 1;
    private String mImageNum;
    private ImageView mImageView;
    private final static ArrayList<String> mImageUrls = new ArrayList<String>();
    private static int i=0;


    static PageFragment newInstance(String imageNum) {
        final PageFragment f = new PageFragment();
        final Bundle args = new Bundle();
        args.putString(IMAGE_DATA_EXTRA, imageNum);
        f.setArguments(args);
        return f;
    }

    // Empty constructor, required as per Fragment docs
    public PageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mImageNum = this.getArguments().getString(IMAGE_DATA_EXTRA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // image_fragment.xml contains just an ImageView
        final View v = inflater.inflate(R.layout.image_fragment, container, false);
        mImageView = (ImageView) v.findViewById(R.id.imagePage);
        if (savedInstanceState != null) {
            mImageNum = savedInstanceState.getString(IMAGE_DATA_EXTRA);
            if (mImageNum==null && i<((Gallery)getActivity()).mImageUrls.size()) {mImageNum = ((Gallery)getActivity()).mImageUrls.get(i); i++;}
        }
        return v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        getActivity().startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (Gallery.class.isInstance(getActivity())) {
            mImageView.setImageURI(Uri.parse(mImageNum));
        }
    }

    public void onStop()
    {
        super.onStop();
        mImageView.setImageURI(null);
    }
    public void onDestroy()
    {
        super.onDestroy();
        final Bundle args = new Bundle();
        args.putString(IMAGE_DATA_EXTRA, mImageNum);
        this.setArguments(args);
    }

    public ArrayList<String> getMImageUrls()
    {
        return mImageUrls;
    }

    public void setMImageUrls(ArrayList<String> urls)
    {
        mImageUrls.addAll(urls);
    }
}