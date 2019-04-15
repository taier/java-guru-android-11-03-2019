package com.example.den.lesson6.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.den.lesson6.Interfaces.PhotoItem;
import com.example.den.lesson6.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {

    public interface ShareFragmentListener {
        void onInfoPress();
        void onSharePress();
    }

    public PhotoItem photoItem;
    private ShareFragmentListener listener;

    @BindView(R.id.imageViewImage) ImageView imageViewImage;
    @BindView(R.id.buttonShare) Button buttonShare;
    @BindView(R.id.buttonInfo) Button buttonInfo;


    public ShareFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ShareFragmentListener) {
            listener = (ShareFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement ShareFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(photoItem.getImgUrl()).into(imageViewImage);

        buttonShare.setOnClickListener(button -> {
            listener.onSharePress();
        });

        buttonInfo.setOnClickListener(button -> {
            listener.onInfoPress();
        });

        return view;
    }

}
