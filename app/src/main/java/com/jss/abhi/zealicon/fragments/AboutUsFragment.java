package com.jss.abhi.zealicon.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import com.jss.abhi.zealicon.R;


public class AboutUsFragment extends Fragment {

    private Context context;
    private ImageView fbImage;
    private ImageView instaImage;
    private ImageView youtubeImage;

    public static String FACEBOOK_URL = "https://www.facebook.com/zealicon/";
    public static String FACEBOOK_PAGE_ID = "zealicon";

    public static AboutUsFragment newInstance() {
        AboutUsFragment fragment = new AboutUsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        fbImage = view.findViewById(R.id.fb_icon);
        instaImage = view.findViewById(R.id.insta_icon);
        youtubeImage = view.findViewById(R.id.youtube_icon);

        fbImage.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                String fbUrl = getFacebookPageURL(context);
                Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fbUrl));
                startActivity(fbIntent);
            }
        });

        // insta click listner
        //TODO

        youtubeImage.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dLdmEiMv5GM"));
                startActivity(intent);
            }
        });

        return view;
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }


}
