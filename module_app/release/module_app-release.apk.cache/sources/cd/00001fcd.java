package com.tongji.yanluapp.p010ui.activity;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import com.tongji.yanluapp.C1848R;
import com.tongji.yanluapp.utils.FuncExt;
import java.net.URISyntaxException;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SchoolInfoActivity.kt */
@Metadata(m143d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0015¨\u0006\u0007"}, m142d2 = {"Lcom/tongji/yanluapp/ui/activity/SchoolInfoActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "module_app_release"}, m141k = 1, m140mv = {1, 7, 1}, m138xi = 48)
/* renamed from: com.tongji.yanluapp.ui.activity.SchoolInfoActivity */
/* loaded from: classes2.dex */
public final class SchoolInfoActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1848R.C1852layout.activity_school_info);
        WebView webView = (WebView) findViewById(C1848R.C1851id.webView);
        Bundle extras = getIntent().getExtras();
        String string = extras != null ? extras.getString("data") : null;
        Intrinsics.checkNotNull(string, "null cannot be cast to non-null type kotlin.String");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() { // from class: com.tongji.yanluapp.ui.activity.SchoolInfoActivity$onCreate$1
            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView2, SslErrorHandler sslErrorHandler, SslError sslError) {
                Intrinsics.checkNotNull(sslErrorHandler);
                sslErrorHandler.proceed();
            }

            @Override // android.webkit.WebViewClient
            @Deprecated(message = "Deprecated in Java")
            public boolean shouldOverrideUrlLoading(WebView view, String newurl) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(newurl, "newurl");
                try {
                    if (StringsKt.startsWith$default(newurl, "intent://", false, 2, (Object) null)) {
                        try {
                            Intent parseUri = Intent.parseUri(newurl, 1);
                            Intrinsics.checkNotNullExpressionValue(parseUri, "parseUri(newurl, Intent.URI_INTENT_SCHEME)");
                            parseUri.addCategory("android.intent.category.BROWSABLE");
                            parseUri.setComponent(null);
                            if (Build.VERSION.SDK_INT >= 15) {
                                parseUri.setSelector(null);
                            }
                            List<ResolveInfo> queryIntentActivities = SchoolInfoActivity.this.getPackageManager().queryIntentActivities(parseUri, 0);
                            Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "this@SchoolInfoActivity.…tentActivities(intent, 0)");
                            if (!queryIntentActivities.isEmpty()) {
                                SchoolInfoActivity.this.startActivityIfNeeded(parseUri, -1);
                            }
                            return true;
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                    if (!StringsKt.startsWith$default(newurl, "http", false, 2, (Object) null)) {
                        try {
                            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(newurl));
                            intent.setFlags(805306368);
                            SchoolInfoActivity.this.startActivity(intent);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            FuncExt.showToast(SchoolInfoActivity.this, "您所打开的第三方App未安装！");
                        }
                        return true;
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                return super.shouldOverrideUrlLoading(view, newurl);
            }
        });
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setCacheMode(1);
        webView.loadUrl(string);
    }
}