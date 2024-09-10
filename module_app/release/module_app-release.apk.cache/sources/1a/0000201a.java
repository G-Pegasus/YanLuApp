package com.tongji.yanluapp.p010ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.tencent.mmkv.MMKV;
import com.tongji.lib_base.p009ui.BaseFragment1;
import com.tongji.lib_common.bean.UpdateInfoResponse;
import com.tongji.lib_common.bean.UserInfoResponse;
import com.tongji.lib_common.network.NetworkApi;
import com.tongji.lib_common.utils.CacheUtil;
import com.tongji.yanluapp.C1848R;
import com.tongji.yanluapp.databinding.FragmentMeBinding;
import com.tongji.yanluapp.p010ui.activity.LikeActivity;
import com.tongji.yanluapp.p010ui.activity.LoginActivity;
import com.tongji.yanluapp.p010ui.activity.SchoolInfoActivity;
import com.tongji.yanluapp.p010ui.activity.SelfPostActivity;
import com.tongji.yanluapp.p010ui.fragment.dialog.AboutAuthor;
import com.tongji.yanluapp.p010ui.fragment.dialog.RewardAuthor;
import com.tongji.yanluapp.p010ui.fragment.dialog.SetUserInfo;
import com.tongji.yanluapp.utils.FuncExt;
import com.tongji.yanluapp.utils.GlideEngine;
import com.tongji.yanluapp.viewmodel.MeViewModel;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.jvm.internal.Intrinsics;
import me.hgj.jetpackmvvm.util.ActivityMessengerKt;

/* compiled from: MeFragment.kt */
@Metadata(m143d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m142d2 = {"Lcom/tongji/yanluapp/ui/fragment/MeFragment;", "Lcom/tongji/lib_base/ui/BaseFragment1;", "Lcom/tongji/yanluapp/viewmodel/MeViewModel;", "Lcom/tongji/yanluapp/databinding/FragmentMeBinding;", "()V", "mmkv", "Lcom/tencent/mmkv/MMKV;", "dismissLoading", "", "initView", "savedInstanceState", "Landroid/os/Bundle;", "showLoading", "message", "", "module_app_release"}, m141k = 1, m140mv = {1, 7, 1}, m138xi = 48)
/* renamed from: com.tongji.yanluapp.ui.fragment.MeFragment */
/* loaded from: classes2.dex */
public final class MeFragment extends BaseFragment1<MeViewModel, FragmentMeBinding> {
    private final MMKV mmkv;

    @Override // me.hgj.jetpackmvvm.base.fragment.BaseVmFragment
    public void dismissLoading() {
    }

    @Override // me.hgj.jetpackmvvm.base.fragment.BaseVmFragment
    public void showLoading(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    public MeFragment() {
        MMKV defaultMMKV = MMKV.defaultMMKV();
        Intrinsics.checkNotNullExpressionValue(defaultMMKV, "defaultMMKV()");
        this.mmkv = defaultMMKV;
    }

    @Override // com.tongji.lib_base.p009ui.BaseFragment1, me.hgj.jetpackmvvm.base.fragment.BaseVmFragment
    public void initView(Bundle bundle) {
        boolean isLogin = CacheUtil.INSTANCE.isLogin();
        Integer valueOf = Integer.valueOf((int) C1848R.mipmap.portrait);
        if (isLogin) {
            UserInfoResponse user = CacheUtil.INSTANCE.getUser();
            if (!Intrinsics.areEqual(user != null ? user.getUser_head() : null, "")) {
                RequestManager with = Glide.with(requireContext());
                UserInfoResponse user2 = CacheUtil.INSTANCE.getUser();
                with.load(user2 != null ? user2.getUser_head() : null).into(((FragmentMeBinding) getMViewBind()).ivUserPortrait);
            } else {
                Glide.with(requireContext()).load(valueOf).into(((FragmentMeBinding) getMViewBind()).ivUserPortrait);
            }
            UpdateInfoResponse userInfo = CacheUtil.INSTANCE.getUserInfo();
            if (!Intrinsics.areEqual(userInfo != null ? userInfo.getUserName() : null, "")) {
                TextView textView = ((FragmentMeBinding) getMViewBind()).tvUserName;
                UpdateInfoResponse userInfo2 = CacheUtil.INSTANCE.getUserInfo();
                textView.setText(userInfo2 != null ? userInfo2.getUserName() : null);
            } else {
                ((FragmentMeBinding) getMViewBind()).tvUserName.setText("考研人");
            }
            UpdateInfoResponse userInfo3 = CacheUtil.INSTANCE.getUserInfo();
            if (!Intrinsics.areEqual(userInfo3 != null ? userInfo3.getUserSign() : null, "")) {
                TextView textView2 = ((FragmentMeBinding) getMViewBind()).tvUserDes;
                UpdateInfoResponse userInfo4 = CacheUtil.INSTANCE.getUserInfo();
                textView2.setText(userInfo4 != null ? userInfo4.getUserSign() : null);
            } else {
                ((FragmentMeBinding) getMViewBind()).tvUserDes.setText("加油！");
            }
            ((FragmentMeBinding) getMViewBind()).tvLogin.setText("退出登录");
        } else {
            Glide.with(requireContext()).load(valueOf).into(((FragmentMeBinding) getMViewBind()).ivUserPortrait);
            ((FragmentMeBinding) getMViewBind()).tvUserName.setText("考研人");
            ((FragmentMeBinding) getMViewBind()).tvUserDes.setText("加油！");
        }
        ((FragmentMeBinding) getMViewBind()).tvSelfPost.setOnClickListener(new View.OnClickListener() { // from class: com.tongji.yanluapp.ui.fragment.MeFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeFragment.m1661initView$lambda0(MeFragment.this, view);
            }
        });
        ((FragmentMeBinding) getMViewBind()).refreshLayout.setRefreshHeader(new BezierRadarHeader(requireContext()).setEnableHorizontalDrag(true).setPrimaryColorId(C1848R.C1849color.colorPrimary)).setHeaderHeight(60.0f);
        ((FragmentMeBinding) getMViewBind()).refreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.tongji.yanluapp.ui.fragment.MeFragment$$ExternalSyntheticLambda9
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                MeFragment.m1662initView$lambda1(MeFragment.this, refreshLayout);
            }
        });
        ((FragmentMeBinding) getMViewBind()).ivUserPortrait.setOnClickListener(new View.OnClickListener() { // from class: com.tongji.yanluapp.ui.fragment.MeFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeFragment.m1663initView$lambda2(MeFragment.this, view);
            }
        });
        ((FragmentMeBinding) getMViewBind()).rootLogin.setOnClickListener(new View.OnClickListener() { // from class: com.tongji.yanluapp.ui.fragment.MeFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeFragment.m1664initView$lambda3(MeFragment.this, view);
            }
        });
        ((FragmentMeBinding) getMViewBind()).rootAuthorInfo.setOnClickListener(new View.OnClickListener() { // from class: com.tongji.yanluapp.ui.fragment.MeFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeFragment.m1665initView$lambda4(MeFragment.this, view);
            }
        });
        if (CacheUtil.INSTANCE.isLogin()) {
            ((FragmentMeBinding) getMViewBind()).rootSetInfo.setOnClickListener(new View.OnClickListener() { // from class: com.tongji.yanluapp.ui.fragment.MeFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MeFragment.m1666initView$lambda5(MeFragment.this, view);
                }
            });
        } else {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            FuncExt.showToast(requireContext, "您需要先登录哦");
        }
        ((MeViewModel) getMViewModel()).getUserInfo().observe(this, new Observer() { // from class: com.tongji.yanluapp.ui.fragment.MeFragment$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MeFragment.m1667initView$lambda6(MeFragment.this, (UpdateInfoResponse) obj);
            }
        });
        ((FragmentMeBinding) getMViewBind()).rootMoney.setOnClickListener(new View.OnClickListener() { // from class: com.tongji.yanluapp.ui.fragment.MeFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeFragment.m1668initView$lambda7(MeFragment.this, view);
            }
        });
        ((FragmentMeBinding) getMViewBind()).rootUserLike.setOnClickListener(new View.OnClickListener() { // from class: com.tongji.yanluapp.ui.fragment.MeFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeFragment.m1669initView$lambda8(MeFragment.this, view);
            }
        });
        ((FragmentMeBinding) getMViewBind()).btnStar.setOnClickListener(new View.OnClickListener() { // from class: com.tongji.yanluapp.ui.fragment.MeFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeFragment.m1670initView$lambda9(MeFragment.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-0  reason: not valid java name */
    public static final void m1661initView$lambda0(MeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Tuples[] tuplesArr = new Tuples[0];
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.startActivity(ActivityMessengerKt.putExtras(new Intent(activity, SelfPostActivity.class), (Tuples[]) Arrays.copyOf(tuplesArr, 0)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00cc  */
    /* renamed from: initView$lambda-1  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m1662initView$lambda1(com.tongji.yanluapp.p010ui.fragment.MeFragment r4, com.scwang.smart.refresh.layout.api.RefreshLayout r5) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tongji.yanluapp.p010ui.fragment.MeFragment.m1662initView$lambda1(com.tongji.yanluapp.ui.fragment.MeFragment, com.scwang.smart.refresh.layout.api.RefreshLayout):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-2  reason: not valid java name */
    public static final void m1663initView$lambda2(MeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PictureSelector.create(this$0.requireContext()).openGallery(SelectMimeType.ofImage()).setImageEngine(GlideEngine.createGlideEngine()).setMaxSelectNum(1).forResult(new MeFragment$initView$3$1(this$0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-3  reason: not valid java name */
    public static final void m1664initView$lambda3(MeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (CacheUtil.INSTANCE.isLogin()) {
            CacheUtil.INSTANCE.setIsLogin(false);
            NetworkApi.Companion.getINSTANCE().getCookieJar().clear();
            CacheUtil.INSTANCE.setUser(null);
            ((FragmentMeBinding) this$0.getMViewBind()).refreshLayout.autoRefresh();
            ((FragmentMeBinding) this$0.getMViewBind()).tvLogin.setText("登录 / 注册");
            return;
        }
        Tuples[] tuplesArr = new Tuples[0];
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.startActivity(ActivityMessengerKt.putExtras(new Intent(activity, LoginActivity.class), (Tuples[]) Arrays.copyOf(tuplesArr, 0)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-4  reason: not valid java name */
    public static final void m1665initView$lambda4(MeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new AboutAuthor().show(this$0.getChildFragmentManager(), "AuthorAbout");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-5  reason: not valid java name */
    public static final void m1666initView$lambda5(MeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new SetUserInfo().show(this$0.getChildFragmentManager(), "SetUserInfo");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-6  reason: not valid java name */
    public static final void m1667initView$lambda6(MeFragment this$0, UpdateInfoResponse updateInfoResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((FragmentMeBinding) this$0.getMViewBind()).tvUserName.setText(updateInfoResponse.getUserName());
        ((FragmentMeBinding) this$0.getMViewBind()).tvUserDes.setText(updateInfoResponse.getUserSign());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-7  reason: not valid java name */
    public static final void m1668initView$lambda7(MeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new RewardAuthor().show(this$0.getChildFragmentManager(), "RewardAuthor");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-8  reason: not valid java name */
    public static final void m1669initView$lambda8(MeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.requireContext(), LikeActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-9  reason: not valid java name */
    public static final void m1670initView$lambda9(MeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0.getContext(), SchoolInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("data", "https://github.com/G-Pegasus/YanLuApp");
        intent.putExtras(bundle);
        this$0.startActivity(intent);
    }
}