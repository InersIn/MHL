package com.mobilehackinglab.postboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.mobilehackinglab.postboard.databinding.ActivityMainBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/mobilehackinglab/postboard/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/mobilehackinglab/postboard/databinding/ActivityMainBinding;", "handleIntent", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupWebView", "webView", "Landroid/webkit/WebView;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding inflate = ActivityMainBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        ActivityMainBinding activityMainBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        CowsayUtil.Companion.initialize(this);
        ActivityMainBinding activityMainBinding2 = this.binding;
        if (activityMainBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding = activityMainBinding2;
        }
        WebView webView = activityMainBinding.webView;
        Intrinsics.checkNotNullExpressionValue(webView, "webView");
        setupWebView(webView);
        handleIntent();
    }

    private final void setupWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebAppChromeClient());
        webView.addJavascriptInterface(new WebAppInterface(), "WebAppInterface");
        webView.loadUrl("file:///android_asset/index.html");
    }

    private final void handleIntent() {
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        if (!Intrinsics.areEqual("android.intent.action.VIEW", action) || data == null || !Intrinsics.areEqual(data.getScheme(), "postboard") || !Intrinsics.areEqual(data.getHost(), "postmessage")) {
            return;
        }
        ActivityMainBinding activityMainBinding = null;
        try {
            String path = data.getPath();
            byte[] decode = Base64.decode(path != null ? StringsKt.drop(path, 1) : null, 8);
            Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
            String message = StringsKt.replace$default(new String(decode, Charsets.UTF_8), "'", "\\'", false, 4, (Object) null);
            ActivityMainBinding activityMainBinding2 = this.binding;
            if (activityMainBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding2 = null;
            }
            activityMainBinding2.webView.loadUrl("javascript:WebAppInterface.postMarkdownMessage('" + message + "')");
        } catch (Exception e) {
            ActivityMainBinding activityMainBinding3 = this.binding;
            if (activityMainBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding = activityMainBinding3;
            }
            activityMainBinding.webView.loadUrl("javascript:WebAppInterface.postCowsayMessage('" + e.getMessage() + "')");
        }
    }
}