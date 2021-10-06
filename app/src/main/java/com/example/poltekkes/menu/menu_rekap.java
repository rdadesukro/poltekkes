package com.example.poltekkes.menu;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poltekkes.R;
import com.example.poltekkes.presenter.history;
import com.github.squti.guru.Guru;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class menu_rekap extends AppCompatActivity {

    private WebView webData;
    com.example.poltekkes.presenter.history history;
    String tgl;
    final Calendar myCalendar = Calendar.getInstance();
    private EditText editTgl;
    private ProgressBar progressBar5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_rekap);
        initView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        tgl = formatter.format(date);
        editTgl.setText(tgl);

        DatePickerDialog.OnDateSetListener tg = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
                webData.loadUrl("https://lmproject.my.id/api/rekap/harian/" + tgl);
                webData.addJavascriptInterface(new JavaScriptInterface(menu_rekap.this), "AndroidFunction");
            }

        };

        editTgl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(menu_rekap.this, tg, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        webData.setWebViewClient(new myWebclient());
        webData.getSettings().setJavaScriptEnabled(true);
        webData.loadUrl("https://lmproject.my.id/api/rekap/harian/" + tgl);
        webData.addJavascriptInterface(new JavaScriptInterface(this), "AndroidFunction");


    }


    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void getDataWebview(String id) {

            Intent intent = new Intent(menu_rekap.this, menu_detail_history.class);
            Guru.putString("id_detail_histiry", id);
            startActivity(intent);
            Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show();
        }
    }


    private void initView() {
        webData = findViewById(R.id.web_data);
        editTgl = findViewById(R.id.edit_tgl);
        progressBar5 = findViewById(R.id.progressBar5);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class myWebclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar5.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tgl = sdf.format(myCalendar.getTime());
        editTgl.setText(sdf.format(myCalendar.getTime()));
    }

}