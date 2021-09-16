package com.example.poltekkes.menu;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poltekkes.R;

public class menu_tentang extends AppCompatActivity {

    private TextView txtTentang;
    String data;
    public String fileName = "<h2>Pengenalan Aplikasi</h2>\n" +
            "<ul>\n" +
            "    <li>Aplikasi ini adalah alat bantu dalam menilai pertumbuhan dan perkembangan balita</li>\n" +
            "    <li>Materi tentang pertumbuhan dan perkembangan balita</li>\n" +
            "    <li>Rekomendasi hasil penilaian pertumbuhan dan perkembangan balita</li>\n" +
            "</ul>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_tentang);
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtTentang.setText(Html.fromHtml(fileName, Html.FROM_HTML_MODE_LEGACY));
        } else
            txtTentang.setText(Html.fromHtml(fileName));
    }

    private void initView() {
        txtTentang = findViewById(R.id.txt_tentang);
    }
}