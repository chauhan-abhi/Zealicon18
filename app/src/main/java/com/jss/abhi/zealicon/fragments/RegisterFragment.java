package com.jss.abhi.zealicon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import com.jss.abhi.zealicon.R;
public class RegisterFragment extends Fragment {

    private WebView webview;
    ProgressBar progressBar;

    public static Fragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_register, container, false);

        webview = (WebView) view.findViewById(R.id.webview_register);
        progressBar = view.findViewById(R.id.progress);

        webview.setVisibility(View.GONE);
        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                webview.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
        webview.loadUrl("http://register.zealicon.in");

        return view;
    }
}
