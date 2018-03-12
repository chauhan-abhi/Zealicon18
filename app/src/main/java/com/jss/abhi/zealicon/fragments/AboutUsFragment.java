package com.jss.abhi.zealicon.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import com.jss.abhi.zealicon.R;
import java.util.List;

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
        instaImage.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Uri uri = Uri.parse("https://instagram.com/zealicon/");
                Intent insta = new Intent(Intent.ACTION_VIEW, uri);
                insta.setPackage("com.instagram.android");
                if (isIntentAvailable(context, insta)){
                    startActivity(insta);
                } else{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/zealicon")));
                }


            }
        });

        youtubeImage.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dLdmEiMv5GM"));
                startActivity(intent);
            }
        });

        return view;
    }

    private boolean isIntentAvailable(Context context, Intent insta) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(insta, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
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
