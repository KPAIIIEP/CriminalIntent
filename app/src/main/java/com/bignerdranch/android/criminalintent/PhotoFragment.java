package com.bignerdranch.android.criminalintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class PhotoFragment extends DialogFragment {
    private static final String ARG_PHOTO = "DialogPhoto";

    private ImageView mImageView;

    public static PhotoFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO, path);
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String path = (String) getArguments().getSerializable(ARG_PHOTO);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_photo, null);
        mImageView = (ImageView) v.findViewById(R.id.dialog_photo);
        Bitmap bitmap = PictureUtils.getScaledBitmap(path, getActivity());
        mImageView.setImageBitmap(bitmap);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }
}
