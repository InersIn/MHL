package com.mobilehackinglab.postboard.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mobilehackinglab.postboard.R;

/* loaded from: classes3.dex */
public final class ActivityMainBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final WebView webView;

    private ActivityMainBinding(ConstraintLayout rootView, WebView webView) {
        this.rootView = rootView;
        this.webView = webView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityMainBinding bind(View rootView) {
        int id = R.id.webView;
        WebView webView = (WebView) ViewBindings.findChildViewById(rootView, id);
        if (webView != null) {
            return new ActivityMainBinding((ConstraintLayout) rootView, webView);
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}