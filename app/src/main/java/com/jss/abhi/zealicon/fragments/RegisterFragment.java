package com.jss.abhi.zealicon.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.activities.ZealIDActivity;

import static android.util.Patterns.EMAIL_ADDRESS;
import static android.util.Patterns.PHONE;

public class RegisterFragment extends Fragment {

    private EditText nameView, emailView, collegeView, contactView;
    private Button register;
    private WebView webview;
    ProgressBar progressBar;
    private Spinner yearView, branchView, courseView;

    /*String year="1",branch="CSE",course="btech";
    String yeararray[]={"1","2","3","4"};
    String brancharray[]={"CSE","IT","ME","ECE","EE","CE","IC","EEE"};
    String coursearray[]={"btech","mca","mba"};
    */
    private String name, email, college, contact, year, branch, course, token_id;

    private TextView mtitle;


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


        //mtitle= view.findViewById(R.id.textViewTitle);


        //yeararray=getResources().getStringArray(R.array.year);
        //brancharray=getResources().getStringArray(R.array.branch);

        nameView = (EditText) view.findViewById(R.id.name);
        emailView = (EditText) view.findViewById(R.id.email);
        collegeView = (EditText) view.findViewById(R.id.college);
        contactView = (EditText) view.findViewById(R.id.contact);
        register = (Button) view.findViewById(R.id.register);
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
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("App", "started");
                name = ((EditText) view.findViewById(R.id.name)).getText().toString();
                email = ((EditText) view.findViewById(R.id.email)).getText().toString();
                college = ((EditText) view.findViewById(R.id.college)).getText().toString();
                contact = ((EditText) view.findViewById(R.id.contact)).getText().toString();
                course = courseView.getSelectedItem().toString();
                branch = branchView.getSelectedItem().toString();
                year = yearView.getSelectedItem().toString();

                Log.v("Register Fragment", name + email + contact + year + branch);
                /*if (name.equals("") || email.equals("") || college.equals("") || contact.equals("") || course.equals("") || branch.equals("") || year.equals(""))
                    Toast.makeText(getActivity(), "Sorry..Please Enter All Fields", Toast.LENGTH_SHORT).show();
                else {
                    // post data to api
                    registertask();
                }
*/
                if(name.length()==0 || !isValidMail(email) || contact.length()!= 10 || college.length()==0
                    || branch.equals("") || year.equals(" ") || course.equals("")) {
                    Toast.makeText(getActivity(), "Sorry..Invalid Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    registertask();
                }
            }
        });

        courseView = view.findViewById(R.id.course);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.course, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseView.setAdapter(adapter1);

        branchView = view.findViewById(R.id.branch);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.branch, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchView.setAdapter(adapter2);

        yearView = view.findViewById(R.id.year);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(),
                R.array.year, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearView.setAdapter(adapter3);
        return view;

    }

    private boolean isValidMail(String email) {
        return EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidMobile(String phone) {
        return PHONE.matcher(phone).matches();
    }

    void registertask() {

        Intent i = new Intent(this.getActivity(),ZealIDActivity.class);
        startActivity(i);
    }

    @Override public void onDetach() {
        super.onDetach();
        setEmpty();
    }

    void setEmpty(){
        nameView.setText("");
        collegeView.setText("");
        contactView.setText("");
        emailView.setText("");
    }
}
