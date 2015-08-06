package com.example.kskhom.mygalleryviewer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by kskhom on 03.08.2015.
 */
public class PageFragment extends Fragment {
    private static final String IMAGE_DATA_EXTRA = "resId";
    private String mImageNum;
    private ImageView mImageView;


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
        }
        return v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

    @Override
    public void onSaveInstanceState(Bundle instState)
    {
        instState.putString(IMAGE_DATA_EXTRA, mImageNum);
        super.onSaveInstanceState(instState);
    }
}