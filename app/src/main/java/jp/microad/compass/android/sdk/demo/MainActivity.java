package jp.microad.compass.android.sdk.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import jp.microad.compass.smartphone.sdk.android.CompassError;
import jp.microad.compass.smartphone.sdk.android.CompassInlineFragment;
import jp.microad.compass.smartphone.sdk.android.CompassInlineViewCallback;
import jp.microad.compass.smartphone.sdk.android.CompassInterstitialFragment;
import jp.microad.compass.smartphone.sdk.android.CompassInterstitialViewCallback;

//COMPASS WebView 単体
import jp.microad.compass.smartphone.sdk.android.CompassWebViewCreator;

public class MainActivity extends AppCompatActivity
        implements CompassInlineViewCallback, CompassInterstitialViewCallback, View.OnClickListener {

    Button buttonShowInterstitial;
    CompassInlineFragment compassInlineFragment;
    CompassInterstitialFragment compassInterstitialFragment;
    CompassWebViewCreator compassWebViewStandalone = new CompassWebViewCreator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //COMPASS インラインフラグメント
        compassInlineFragment = CompassInlineFragment.load(this, R.id.compass_inline_fragment);

        //COMPASS WebView 単体
        WebView webView = (WebView) findViewById(R.id.webview_standalone);
        compassWebViewStandalone.displayAds(webView, 1, this, getString(R.string.inline_spot_id));

        //COMPASS インタースティシャルフラグメントを表示するボタン
        buttonShowInterstitial = (Button)findViewById(R.id.button_show_interstitial);
        buttonShowInterstitial.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (compassInlineFragment != null) {
            compassInlineFragment.onActivityResult(requestCode, resultCode, data);
        }

        if (compassInterstitialFragment != null) {
            compassInterstitialFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        //COMPASS インタースティシャルフラグメント
        compassInterstitialFragment = CompassInterstitialFragment.showAd(this);
    }

    @Override
    public void onDismiss(int i) {

    }

    @Override
    public void onLoadSuccess(int i) {

    }

    @Override
    public void onShow(int i) {

    }

    @Override
    public void onLoadFailure(int i, CompassError.Code code, Throwable throwable) {
    }
}
