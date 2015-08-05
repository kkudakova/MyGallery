package com.example.kskhom.mygalleryviewer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by kskhom on 03.08.2015.
 */
public class PageFragment extends Fragment {
    private static final String IMAGE_DATA_EXTRA = "resId";
    static final int GALLERY_REQUEST = 1;
    private int mImageNum;
    private ImageView mImageView;


    static PageFragment newInstance(int imageNum) {
        final PageFragment f = new PageFragment();
        final Bundle args = new Bundle();
        args.putInt(IMAGE_DATA_EXTRA, imageNum);
        f.setArguments(args);
        return f;
    }

    // Empty constructor, required as per Fragment docs
    public PageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageNum = this.getArguments().getInt(IMAGE_DATA_EXTRA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // image_fragment.xml contains just an ImageView
        final View v = inflater.inflate(R.layout.image_fragment, container, false);
        mImageView = (ImageView) v.findViewById(R.id.imagePage);
        if (savedInstanceState != null) {
            mImageNum = savedInstanceState.getInt(IMAGE_DATA_EXTRA);
        }
        return v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Gallery.class.isInstance(getActivity())) {
            final Uri resId = Uri.parse(Gallery.mImageUrls.get(mImageNum));
            // Call out to Gallery to load the bitmap in a background thread
            ((Gallery) getActivity()).loadBitmap(resId, mImageView);
        }
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        getActivity().startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }
}
