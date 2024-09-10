package me.hgj.jetpackmvvm.ext.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.umeng.analytics.pro.UMCommonContent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.hgj.jetpackmvvm.ext.view.ViewExt;

@Metadata(m144bv = {1, 0, 3}, m143d1 = {"\u0000L\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a7\u0010\u0007\u001a\u00020\b2\u0016\u0010\t\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b0\n\"\u0004\u0018\u00010\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\r¢\u0006\u0002\u0010\u000e\u001aA\u0010\u000f\u001a\u00020\b2\u0016\u0010\t\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b0\n\"\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\r¢\u0006\u0002\u0010\u0012\u001a\u0012\u0010\u0013\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016\u001a\u001c\u0010\u0017\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u0016\u001a\u0012\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010\u001a\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0001\u001a@\u0010\u001c\u001a\u00020\b\"\u0006\b\u0000\u0010\u001d\u0018\u0001*\u0004\u0018\u0001H\u001d2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u00020\b0\r2\u000e\b\u0002\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0 H\u0086\b¢\u0006\u0002\u0010!\u001a\u0012\u0010\"\u001a\u00020\u0001*\u00020\u00022\u0006\u0010#\u001a\u00020\u0001\u001a\u0012\u0010\"\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010#\u001a\u00020\u0001\u001a\u0014\u0010$\u001a\u00020%*\u00020\u00162\b\b\u0002\u0010&\u001a\u00020\u0001\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006'"}, m142d2 = {"screenHeight", "", "Landroid/content/Context;", "getScreenHeight", "(Landroid/content/Context;)I", "screenWidth", "getScreenWidth", "setOnclick", "", "views", "", "Landroid/view/View;", "onClick", "Lkotlin/Function1;", "([Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "setOnclickNoRepeat", UMCommonContent.f425aT, "", "([Landroid/view/View;JLkotlin/jvm/functions/Function1;)V", "checkAccessibilityServiceEnabled", "", "serviceName", "", "copyToClipboard", "text", "label", "dp2px", "dp", "notNull", ExifInterface.GPS_DIRECTION_TRUE, "notNullAction", "nullAction", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "px2dp", "px", "toHtml", "Landroid/text/Spanned;", "flag", "JetpackMvvm_release"}, m141k = 2, m140mv = {1, 1, 16})
/* renamed from: me.hgj.jetpackmvvm.ext.util.CommonExtKt */
/* loaded from: classes2.dex */
public final class CommonExt {
    public static final int getScreenWidth(Context screenWidth) {
        Intrinsics.checkParameterIsNotNull(screenWidth, "$this$screenWidth");
        Resources resources = screenWidth.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return resources.getDisplayMetrics().widthPixels;
    }

    public static final int getScreenHeight(Context screenHeight) {
        Intrinsics.checkParameterIsNotNull(screenHeight, "$this$screenHeight");
        Resources resources = screenHeight.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return resources.getDisplayMetrics().heightPixels;
    }

    public static /* synthetic */ void notNull$default(Object obj, Function1 notNullAction, Functions nullAction, int i, Object obj2) {
        if ((i & 2) != 0) {
            nullAction = new Functions<Unit>() { // from class: me.hgj.jetpackmvvm.ext.util.CommonExtKt$notNull$1
                @Override // kotlin.jvm.functions.Functions
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        Intrinsics.checkParameterIsNotNull(notNullAction, "notNullAction");
        Intrinsics.checkParameterIsNotNull(nullAction, "nullAction");
        if (obj != null) {
            notNullAction.invoke(obj);
        } else {
            nullAction.invoke();
        }
    }

    public static final /* synthetic */ <T> void notNull(T t, Function1<? super T, Unit> notNullAction, Functions<Unit> nullAction) {
        Intrinsics.checkParameterIsNotNull(notNullAction, "notNullAction");
        Intrinsics.checkParameterIsNotNull(nullAction, "nullAction");
        if (t != null) {
            notNullAction.invoke(t);
        } else {
            nullAction.invoke();
        }
    }

    public static final int dp2px(Context dp2px, int i) {
        Intrinsics.checkParameterIsNotNull(dp2px, "$this$dp2px");
        Resources resources = dp2px.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return (int) ((i * resources.getDisplayMetrics().density) + 0.5f);
    }

    public static final int px2dp(Context px2dp, int i) {
        Intrinsics.checkParameterIsNotNull(px2dp, "$this$px2dp");
        Resources resources = px2dp.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return (int) ((i / resources.getDisplayMetrics().density) + 0.5f);
    }

    public static final int dp2px(View dp2px, int i) {
        Intrinsics.checkParameterIsNotNull(dp2px, "$this$dp2px");
        Resources resources = dp2px.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return (int) ((i * resources.getDisplayMetrics().density) + 0.5f);
    }

    public static final int px2dp(View px2dp, int i) {
        Intrinsics.checkParameterIsNotNull(px2dp, "$this$px2dp");
        Resources resources = px2dp.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return (int) ((i / resources.getDisplayMetrics().density) + 0.5f);
    }

    public static /* synthetic */ void copyToClipboard$default(Context context, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = LogExtKt.TAG;
        }
        copyToClipboard(context, str, str2);
    }

    public static final void copyToClipboard(Context copyToClipboard, String text, String label) {
        Intrinsics.checkParameterIsNotNull(copyToClipboard, "$this$copyToClipboard");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(label, "label");
        ClipData newPlainText = ClipData.newPlainText(label, text);
        ClipboardManager clipboardManager = SystemServiceExt.getClipboardManager(copyToClipboard);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(newPlainText);
        }
    }

    public static final boolean checkAccessibilityServiceEnabled(Context checkAccessibilityServiceEnabled, String serviceName) {
        Intrinsics.checkParameterIsNotNull(checkAccessibilityServiceEnabled, "$this$checkAccessibilityServiceEnabled");
        Intrinsics.checkParameterIsNotNull(serviceName, "serviceName");
        Context applicationContext = checkAccessibilityServiceEnabled.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
        Settings.Secure.getString(applicationContext.getContentResolver(), "enabled_accessibility_services");
        TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
        while (simpleStringSplitter.hasNext()) {
            if (StringsKt.equals(simpleStringSplitter.next(), serviceName, true)) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ void setOnclickNoRepeat$default(View[] viewArr, long j, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 500;
        }
        setOnclickNoRepeat(viewArr, j, function1);
    }

    public static /* synthetic */ Spanned toHtml$default(String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return toHtml(str, i);
    }

    public static final Spanned toHtml(String toHtml, int i) {
        Intrinsics.checkParameterIsNotNull(toHtml, "$this$toHtml");
        if (Build.VERSION.SDK_INT >= 24) {
            Spanned fromHtml = Html.fromHtml(toHtml, i);
            Intrinsics.checkExpressionValueIsNotNull(fromHtml, "Html.fromHtml(this, flag)");
            return fromHtml;
        }
        Spanned fromHtml2 = Html.fromHtml(toHtml);
        Intrinsics.checkExpressionValueIsNotNull(fromHtml2, "Html.fromHtml(this)");
        return fromHtml2;
    }

    public static final void setOnclick(View[] views, final Function1<? super View, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(views, "views");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        for (View view : views) {
            if (view != null) {
                view.setOnClickListener(new View.OnClickListener() { // from class: me.hgj.jetpackmvvm.ext.util.CommonExtKt$setOnclick$$inlined$forEach$lambda$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        Function1 function1 = Function1.this;
                        Intrinsics.checkExpressionValueIsNotNull(view2, "view");
                        function1.invoke(view2);
                    }
                });
            }
        }
    }

    public static final void setOnclickNoRepeat(View[] views, final long j, final Function1<? super View, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(views, "views");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        for (View view : views) {
            if (view != null) {
                ViewExt.clickNoRepeat(view, j, new Function1<View, Unit>() { // from class: me.hgj.jetpackmvvm.ext.util.CommonExtKt$setOnclickNoRepeat$$inlined$forEach$lambda$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                        invoke2(view2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    public final void invoke2(View view2) {
                        Intrinsics.checkParameterIsNotNull(view2, "view");
                        onClick.invoke(view2);
                    }
                });
            }
        }
    }
}