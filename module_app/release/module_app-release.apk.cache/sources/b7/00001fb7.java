package com.tongji.yanluapp.p010ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import com.tongji.lib_base.p009ui.BaseActivity1;
import com.tongji.lib_common.bean.UpdateInfoResponse;
import com.tongji.lib_common.bean.UserInfoResponse;
import com.tongji.lib_common.utils.CacheUtil;
import com.tongji.yanluapp.databinding.ActivityLoginBinding;
import com.tongji.yanluapp.viewmodel.LoginViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import me.hgj.jetpackmvvm.base.KtxKt;
import me.hgj.jetpackmvvm.ext.BaseViewModelExt;
import me.hgj.jetpackmvvm.network.AppException;
import me.hgj.jetpackmvvm.state.ResultState;

/* compiled from: LoginActivity.kt */
@Metadata(m143d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m142d2 = {"Lcom/tongji/yanluapp/ui/activity/LoginActivity;", "Lcom/tongji/lib_base/ui/BaseActivity1;", "Lcom/tongji/yanluapp/viewmodel/LoginViewModel;", "Lcom/tongji/yanluapp/databinding/ActivityLoginBinding;", "()V", "dismissLoading", "", "initView", "savedInstanceState", "Landroid/os/Bundle;", "showLoading", "message", "", "module_app_release"}, m141k = 1, m140mv = {1, 7, 1}, m138xi = 48)
/* renamed from: com.tongji.yanluapp.ui.activity.LoginActivity */
/* loaded from: classes2.dex */
public final class LoginActivity extends BaseActivity1<LoginViewModel, ActivityLoginBinding> {
    @Override // me.hgj.jetpackmvvm.base.activity.BaseVmActivity
    public void dismissLoading() {
    }

    @Override // me.hgj.jetpackmvvm.base.activity.BaseVmActivity
    public void showLoading(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    @Override // com.tongji.lib_base.p009ui.BaseActivity1, me.hgj.jetpackmvvm.base.activity.BaseVmActivity
    public void initView(Bundle bundle) {
        ((ActivityLoginBinding) getMViewBind()).btnLogin.setOnClickListener(new View.OnClickListener() { // from class: com.tongji.yanluapp.ui.activity.LoginActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.m1625initView$lambda0(LoginActivity.this, view);
            }
        });
        ((ActivityLoginBinding) getMViewBind()).toRegister.setOnClickListener(new View.OnClickListener() { // from class: com.tongji.yanluapp.ui.activity.LoginActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.m1626initView$lambda1(LoginActivity.this, view);
            }
        });
        ((LoginViewModel) getMViewModel()).getLoginResult().observe(this, new Observer() { // from class: com.tongji.yanluapp.ui.activity.LoginActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LoginActivity.m1627initView$lambda2(LoginActivity.this, (ResultState) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-0  reason: not valid java name */
    public static final void m1625initView$lambda0(LoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((ActivityLoginBinding) this$0.getMViewBind()).etUsername.getText().toString().length() == 0) {
            Toast.makeText(KtxKt.getAppContext(), "请填写账号", 0).show();
            return;
        }
        if (((ActivityLoginBinding) this$0.getMViewBind()).etPwd.getText().toString().length() == 0) {
            Toast.makeText(KtxKt.getAppContext(), "请填写密码", 0).show();
        } else {
            ((LoginViewModel) this$0.getMViewModel()).loginReq(((ActivityLoginBinding) this$0.getMViewBind()).etUsername.getText().toString(), ((ActivityLoginBinding) this$0.getMViewBind()).etPwd.getText().toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-1  reason: not valid java name */
    public static final void m1626initView$lambda1(LoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, RegisterActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-2  reason: not valid java name */
    public static final void m1627initView$lambda2(final LoginActivity this$0, ResultState resultState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(resultState, "resultState");
        BaseViewModelExt.parseState$default(this$0, resultState, new Function1<UserInfoResponse, Unit>() { // from class: com.tongji.yanluapp.ui.activity.LoginActivity$initView$3$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserInfoResponse userInfoResponse) {
                invoke2(userInfoResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(UserInfoResponse it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(1140883456);
                LoginActivity.this.startActivity(intent);
                CacheUtil.INSTANCE.setUser(it);
                if (!Intrinsics.areEqual(it.getUser_name(), "") && !Intrinsics.areEqual(it.getUser_sign(), "")) {
                    CacheUtil.INSTANCE.setUserInfo(new UpdateInfoResponse(it.getUser_name(), it.getUser_sign()));
                } else {
                    CacheUtil.INSTANCE.setUserInfo(new UpdateInfoResponse("考研人", "加油"));
                }
                CacheUtil.INSTANCE.setIsLogin(true);
            }
        }, new Function1<AppException, Unit>() { // from class: com.tongji.yanluapp.ui.activity.LoginActivity$initView$3$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                invoke2(appException);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(AppException it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Toast.makeText(KtxKt.getAppContext(), it.getErrorMsg(), 0).show();
            }
        }, (Functions) null, 8, (Object) null);
    }
}