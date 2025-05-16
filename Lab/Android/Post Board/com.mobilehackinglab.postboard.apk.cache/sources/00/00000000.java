package defpackage;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: CowsayUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"LCowsayUtil;", "", "()V", "Companion", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: CowsayUtil  reason: default package */
/* loaded from: classes2.dex */
public final class CowsayUtil {
    public static final Companion Companion = new Companion(null);
    private static final String SCRIPT_NAME = "cowsay.sh";
    private static String scriptPath;

    /* compiled from: CowsayUtil.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"LCowsayUtil$Companion;", "", "()V", "SCRIPT_NAME", "", "scriptPath", "initialize", "", "context", "Landroid/content/Context;", "runCowsay", "message", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: CowsayUtil$Companion */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void initialize(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            File file = new File(context.getFilesDir(), CowsayUtil.SCRIPT_NAME);
            InputStream open = context.getAssets().open(CowsayUtil.SCRIPT_NAME);
            try {
                InputStream inputStream = open;
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                FileOutputStream outputStream = fileOutputStream;
                Intrinsics.checkNotNull(inputStream);
                ByteStreamsKt.copyTo$default(inputStream, outputStream, 0, 2, null);
                CloseableKt.closeFinally(fileOutputStream, null);
                CloseableKt.closeFinally(open, null);
                file.setExecutable(true);
                CowsayUtil.scriptPath = file.getAbsolutePath();
            } finally {
            }
        }

        public final String runCowsay(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            try {
                String[] command = {"/bin/sh", "-c", CowsayUtil.scriptPath + ' ' + message};
                Process process = Runtime.getRuntime().exec(command);
                StringBuilder output = new StringBuilder();
                InputStream inputStream = process.getInputStream();
                Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                BufferedReader reader = bufferedReader;
                while (true) {
                    String it = reader.readLine();
                    if (it == null) {
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(bufferedReader, null);
                        process.waitFor();
                        String sb = output.toString();
                        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
                        return sb;
                    }
                    output.append(it).append("\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "cowsay: " + e.getMessage();
            }
        }
    }
}