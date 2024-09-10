package com.tongji.yanluapp.p010ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tongji.lib_base.p009ui.BaseFragment1;
import com.tongji.yanluapp.C1848R;
import com.tongji.yanluapp.app.data.SchoolData;
import com.tongji.yanluapp.databinding.FragmentInfoBinding;
import com.tongji.yanluapp.p010ui.activity.SchoolInfoActivity;
import com.tongji.yanluapp.p010ui.activity.SearchResultActivity;
import com.tongji.yanluapp.p010ui.adapter.SchoolAdapter;
import com.tongji.yanluapp.viewmodel.InfoViewModel;
import java.lang.reflect.Field;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import me.hgj.jetpackmvvm.base.KtxKt;

/* compiled from: InfoFragment.kt */
@Metadata(m143d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0017J\b\u0010\u0016\u001a\u00020\rH\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016J\b\u0010\u0018\u001a\u00020\rH\u0002J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u001c"}, m142d2 = {"Lcom/tongji/yanluapp/ui/fragment/InfoFragment;", "Lcom/tongji/lib_base/ui/BaseFragment1;", "Lcom/tongji/yanluapp/viewmodel/InfoViewModel;", "Lcom/tongji/yanluapp/databinding/FragmentInfoBinding;", "()V", "schoolAdapter", "Lcom/tongji/yanluapp/ui/adapter/SchoolAdapter;", "viewModel", "getViewModel", "()Lcom/tongji/yanluapp/viewmodel/InfoViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "dismissLoading", "", "initView", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onDestroy", "onResume", "setMenu", "showLoading", "message", "", "module_app_release"}, m141k = 1, m140mv = {1, 7, 1}, m138xi = 48)
/* renamed from: com.tongji.yanluapp.ui.fragment.InfoFragment */
/* loaded from: classes2.dex */
public final class InfoFragment extends BaseFragment1<InfoViewModel, FragmentInfoBinding> {
    private SchoolAdapter schoolAdapter;
    private final Lazy viewModel$delegate = LazyKt.lazy(new Functions<InfoViewModel>() { // from class: com.tongji.yanluapp.ui.fragment.InfoFragment$viewModel$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Functions
        public final InfoViewModel invoke() {
            return (InfoViewModel) new ViewModelProvider(InfoFragment.this).get(InfoViewModel.class);
        }
    });

    @Override // me.hgj.jetpackmvvm.base.fragment.BaseVmFragment
    public void dismissLoading() {
    }

    @Override // me.hgj.jetpackmvvm.base.fragment.BaseVmFragment
    public void showLoading(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    private final InfoViewModel getViewModel() {
        return (InfoViewModel) this.viewModel$delegate.getValue();
    }

    @Override // com.tongji.lib_base.p009ui.BaseFragment1, me.hgj.jetpackmvvm.base.fragment.BaseVmFragment
    public void initView(Bundle bundle) {
        getViewModel().getListData().addAll(SchoolData.INSTANCE.addList());
        RecyclerView recyclerView = ((FragmentInfoBinding) getMViewBind()).rvSchool;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "mViewBind.rvSchool");
        recyclerView.setLayoutManager(new LinearLayoutManager(KtxKt.getAppContext()));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        SchoolAdapter schoolAdapter = new SchoolAdapter(requireContext, SchoolData.INSTANCE.addList());
        this.schoolAdapter = schoolAdapter;
        recyclerView.setAdapter(schoolAdapter);
        SchoolAdapter schoolAdapter2 = this.schoolAdapter;
        if (schoolAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("schoolAdapter");
            schoolAdapter2 = null;
        }
        schoolAdapter2.setOnItemClickListener(new SchoolAdapter.OnItemClickListener() { // from class: com.tongji.yanluapp.ui.fragment.InfoFragment$initView$1
            @Override // com.tongji.yanluapp.p010ui.adapter.SchoolAdapter.OnItemClickListener
            public void onItemClick(View view, int i) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intent intent = new Intent(InfoFragment.this.getContext(), SchoolInfoActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("data", SchoolData.INSTANCE.addList().get(i).getSchoolInfo());
                intent.putExtras(bundle2);
                InfoFragment.this.startActivity(intent);
            }

            @Override // com.tongji.yanluapp.p010ui.adapter.SchoolAdapter.OnItemClickListener
            public void onItemLongClick(View view, int i) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intent intent = new Intent(InfoFragment.this.getContext(), SchoolInfoActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("data", SchoolData.INSTANCE.addList().get(i).getSchoolWeb());
                intent.putExtras(bundle2);
                InfoFragment.this.startActivity(intent);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    @Deprecated(message = "Deprecated in Java")
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        inflater.inflate(C1848R.C1853menu.menu_search, menu);
        getMActivity().setTitle("院校信息");
        MenuItem findItem = menu.findItem(C1848R.C1851id.action_search);
        View actionView = findItem != null ? findItem.getActionView() : null;
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        final SearchView searchView = (SearchView) actionView;
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("搜索院校");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.tongji.yanluapp.ui.fragment.InfoFragment$onCreateOptionsMenu$1$1
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                if (str != null) {
                    SearchView searchView2 = SearchView.this;
                    InfoFragment infoFragment = this;
                    Intent intent = new Intent(searchView2.getContext(), SearchResultActivity.class);
                    intent.putExtra("searchKey", str);
                    infoFragment.startActivity(intent);
                    return false;
                }
                return false;
            }
        });
        searchView.setSubmitButtonEnabled(true);
        Field declaredField = searchView.getClass().getDeclaredField("mGoButton");
        declaredField.setAccessible(true);
        Object obj = declaredField.get(searchView);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type android.widget.ImageView");
        ((ImageView) obj).setImageResource(C1848R.mipmap.ic_search);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override // me.hgj.jetpackmvvm.base.fragment.BaseVmFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setMenu();
    }

    private final void setMenu() {
        setHasOptionsMenu(true);
        getMActivity().setSupportActionBar(((FragmentInfoBinding) getMViewBind()).includeToolbar.toolbar);
        getMActivity().setTitle("院校信息");
    }

    @Override // me.hgj.jetpackmvvm.base.fragment.BaseVmFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        getMActivity().setSupportActionBar(null);
    }
}