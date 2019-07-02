package myapp.com.jnidemo;

import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Set;

public class WebViewActivity extends AppCompatActivity {
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webview = findViewById(R.id.webview);

        webview.loadUrl("file:///android_asset/Test.html");

        webview.addJavascriptInterface(this, "test");

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                return call2(uri);
            }
        });
    }

    @JavascriptInterface
    public void call1(String param){
        Toast.makeText(this,param,Toast.LENGTH_SHORT).show();
    }

    public boolean call2(Uri uri){
        if(uri.getScheme().equals("js")){
            if(uri.getAuthority().equals("webview")){
                Set<String> queryParameterNames = uri.getQueryParameterNames();
                for(String key: queryParameterNames){
                    Log.e("****WEBVIEW TEST****",key+" "+uri.getQueryParameter(key));
                    Toast.makeText(this,key+" "+uri.getQueryParameter(key),Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        }
        return false;
    }
}
