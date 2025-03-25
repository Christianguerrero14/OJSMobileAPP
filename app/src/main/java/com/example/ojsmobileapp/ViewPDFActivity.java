package com.example.ojsmobileapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ojsmobileapp.R;
import com.example.ojsmobileapp.adaptadores.ArticulosAdaptador;

public class ViewPDFActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter<ArticulosAdaptador.ViewHolder> adapter;

    private String id;
    private String idioma;
    private String url;
    private String titulo;

    private TextView txttitulo;
    private WebView webview;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdfactivity);

        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                idioma = bundle.getString("idioma", "");
                titulo = bundle.getString("title", "");
                id = bundle.getString("id", "");
                url = bundle.getString("url", "");
            }

            txttitulo = findViewById(R.id.txttituloviewpdf);
            txttitulo.setText(titulo);

            webview = findViewById(R.id.webview);
            // Cargar URL del PDF (puedes cambiar esto si usas Google Docs viewer, etc.)
            // webview.loadUrl(url);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setBuiltInZoomControls(true);
            webview.getSettings().setDisplayZoomControls(false);
            String htmlContent = "<iframe src='https://docs.google.com/gview?embedded=true&url=" + url + "' width='100%' height='100%' style='border: none;'></iframe>";
            htmlContent = "<iframe src='" + url + "' width='100%' height='100%' style='border: none;'></iframe>";
            webview.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}