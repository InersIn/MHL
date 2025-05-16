package com.mobilehackinglab.postboard;

import android.webkit.JavascriptInterface;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import org.json.JSONArray;

/* compiled from: WebAppInterface.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bH\u0007J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/mobilehackinglab/postboard/WebAppInterface;", "", "()V", "cache", "LWebAppCache;", "clearCache", "", "getMessages", "", "postCowsayMessage", "cowsayMessage", "postMarkdownMessage", "markdownMessage", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class WebAppInterface {
    private final WebAppCache cache = new WebAppCache();

    @JavascriptInterface
    public final String getMessages() {
        List messages = this.cache.getMessages();
        String jSONArray = new JSONArray((Collection) messages).toString();
        Intrinsics.checkNotNullExpressionValue(jSONArray, "toString(...)");
        return jSONArray;
    }

    @JavascriptInterface
    public final void clearCache() {
        this.cache.clearCache();
    }

    @JavascriptInterface
    public final void postMarkdownMessage(String markdownMessage) {
        Intrinsics.checkNotNullParameter(markdownMessage, "markdownMessage");
        String html = new Regex("```(.*?)```", RegexOption.DOT_MATCHES_ALL).replace(markdownMessage, "<pre><code>$1</code></pre>");
        String html2 = new Regex("`([^`]+)`").replace(html, "<code>$1</code>");
        String html3 = new Regex("!\\[(.*?)\\]\\((.*?)\\)").replace(html2, "<img src='$2' alt='$1'/>");
        String html4 = new Regex("###### (.*)").replace(html3, "<h6>$1</h6>");
        String html5 = new Regex("##### (.*)").replace(html4, "<h5>$1</h5>");
        String html6 = new Regex("#### (.*)").replace(html5, "<h4>$1</h4>");
        String html7 = new Regex("### (.*)").replace(html6, "<h3>$1</h3>");
        String html8 = new Regex("## (.*)").replace(html7, "<h2>$1</h2>");
        String html9 = new Regex("# (.*)").replace(html8, "<h1>$1</h1>");
        String html10 = new Regex("\\*\\*(.*?)\\*\\*").replace(html9, "<b>$1</b>");
        String html11 = new Regex("\\*(.*?)\\*").replace(html10, "<i>$1</i>");
        String html12 = new Regex("~~(.*?)~~").replace(html11, "<del>$1</del>");
        String html13 = new Regex("\\[([^\\[]+)\\]\\(([^)]+)\\)").replace(html12, "<a href='$2'>$1</a>");
        String html14 = new Regex("(?m)^(\\* .+)((\\n\\* .+)*)").replace(html13, new Function1<MatchResult, CharSequence>() { // from class: com.mobilehackinglab.postboard.WebAppInterface$postMarkdownMessage$1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult matchResult) {
                Intrinsics.checkNotNullParameter(matchResult, "matchResult");
                return "<ul>" + CollectionsKt.joinToString$default(StringsKt.split$default((CharSequence) matchResult.getValue(), new String[]{"\n"}, false, 0, 6, (Object) null), "", null, null, 0, null, new Function1<String, CharSequence>() { // from class: com.mobilehackinglab.postboard.WebAppInterface$postMarkdownMessage$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final CharSequence invoke(String it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        StringBuilder append = new StringBuilder().append("<li>");
                        String substring = it.substring(2);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                        return append.append(substring).append("</li>").toString();
                    }
                }, 30, null) + "</ul>";
            }
        });
        String html15 = new Regex("(?m)^\\d+\\. .+((\\n\\d+\\. .+)*)").replace(html14, new Function1<MatchResult, CharSequence>() { // from class: com.mobilehackinglab.postboard.WebAppInterface$postMarkdownMessage$2
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult matchResult) {
                Intrinsics.checkNotNullParameter(matchResult, "matchResult");
                return "<ol>" + CollectionsKt.joinToString$default(StringsKt.split$default((CharSequence) matchResult.getValue(), new String[]{"\n"}, false, 0, 6, (Object) null), "", null, null, 0, null, new Function1<String, CharSequence>() { // from class: com.mobilehackinglab.postboard.WebAppInterface$postMarkdownMessage$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final CharSequence invoke(String it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        StringBuilder append = new StringBuilder().append("<li>");
                        String substring = it.substring(StringsKt.indexOf$default((CharSequence) it, '.', 0, false, 6, (Object) null) + 2);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                        return append.append(substring).append("</li>").toString();
                    }
                }, 30, null) + "</ol>";
            }
        });
        String html16 = new Regex("^> (.*)", RegexOption.MULTILINE).replace(html15, "<blockquote>$1</blockquote>");
        this.cache.addMessage(new Regex("^(---|\\*\\*\\*|___)$", RegexOption.MULTILINE).replace(html16, "<hr>"));
    }

    @JavascriptInterface
    public final void postCowsayMessage(String cowsayMessage) {
        Intrinsics.checkNotNullParameter(cowsayMessage, "cowsayMessage");
        String asciiArt = CowsayUtil.Companion.runCowsay(cowsayMessage);
        String html = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(asciiArt, "&", "&amp;", false, 4, (Object) null), "<", "&lt;", false, 4, (Object) null), ">", "&gt;", false, 4, (Object) null), "\"", "&quot;", false, 4, (Object) null), "'", "&#039;", false, 4, (Object) null);
        this.cache.addMessage("<pre>" + StringsKt.replace$default(html, "\n", "<br>", false, 4, (Object) null) + "</pre>");
    }
}