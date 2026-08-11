package com.cabinetsoudani.superdentiste;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.WindowCompat;

/**
 * Super Dentiste — le jeu officiel du Cabinet Soudani.
 *
 * Le jeu est un fichier HTML5 autonome embarqué dans assets/index.html.
 * Cette activité l'affiche en plein écran dans une WebView optimisée pour le jeu :
 *  - aucun accès réseau nécessaire (le jeu tourne hors ligne)
 *  - les liens externes (rendez-vous, téléphone) s'ouvrent hors de l'app
 *  - la sauvegarde de progression utilise le localStorage (DOM storage)
 */
public class MainActivity extends AppCompatActivity {

    private static final String GAME_URL = "file:///android_asset/index.html";
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Splash screen natif (Android 12+ et rétro-compatible)
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);

        // Plein écran immersif, contenu sous les barres système
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().setStatusBarColor(Color.parseColor("#0b6e78"));
        getWindow().setNavigationBarColor(Color.parseColor("#7fd8de"));
        // L'écran ne s'éteint pas pendant une partie
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        webView = new WebView(this);
        webView.setBackgroundColor(Color.parseColor("#0b6e78"));
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        setContentView(webView);

        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);          // sauvegarde de la progression
        s.setMediaPlaybackRequiresUserGesture(false); // sons du jeu
        s.setAllowFileAccess(false);
        s.setAllowContentAccess(false);
        s.setSupportZoom(false);
        s.setBuiltInZoomControls(false);
        s.setDisplayZoomControls(false);
        s.setCacheMode(WebSettings.LOAD_DEFAULT);
        s.setTextZoom(100);                    // ignore la taille de police système

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            webView.setRendererPriorityPolicy(WebView.RENDERER_PRIORITY_IMPORTANT, false);
        }

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                String scheme = uri.getScheme();
                if (scheme == null) return false;
                // Le jeu lui-même reste dans la WebView
                if ("file".equals(scheme)) return false;
                // Liens rendez-vous / téléphone / WhatsApp → applications système
                openExternally(uri);
                return true;
            }
        });

        webView.loadUrl(GAME_URL);

        // Bouton retour : navigue dans le jeu, sinon quitte
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }

    private void openExternally(Uri uri) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.no_app_found, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null) webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webView != null) webView.onResume();
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadUrl("about:blank");
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
