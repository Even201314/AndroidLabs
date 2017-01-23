package com.even.androidlabs.recyclerview.snaphelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.even.androidlabs.R;
import com.even.androidlabs.rxjava.RxBus;
import com.even.androidlabs.test.model.ModelType;
import com.even.androidlabs.test.model.TestModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Test SnapHelper Activity
 * Created by Even on 17/1/22.
 */

public class TestSnapHelperActivity extends AppCompatActivity {
    private static final String TAG = TestSnapHelperActivity.class.getSimpleName();

    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private TestSnapHelperAdapter adapter;
    private List<TestModel> testModelList;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_snap_helper);
        ButterKnife.bind(this);

        initBehavior();
    }

    private void initBehavior() {
        tvTitle.setText(TAG);

        initTestModelList();
        initRecyclerView();

        RxBus.publish("publish by TestSnapHelperActivity");
    }

    private void initTestModelList() {
        testModelList = new ArrayList<>();

        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://7xi8d6.com1.z0.glb.clouddn.com/16229501_482786908558452_6858241750058663936_n.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://7xi8d6.com1.z0.glb.clouddn.com/16124351_1863111260639981_4361246625721483264_n.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww3.sinaimg.cn/large/610dc034gw1fbsfgssfrwj20u011h48y.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww3.sinaimg.cn/large/610dc034gw1fbou2xsqpaj20u00u0q4h.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww2.sinaimg.cn/large/0060lm7Tgw1fbnmsjogt9j30u00u0jvv.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww4.sinaimg.cn/large/0060lm7Tgw1fbmfo9is9hj30u00u0ai3.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww3.sinaimg.cn/large/610dc034gw1fbldexdog4j20u011h41b.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww1.sinaimg.cn/large/610dc034gw1fbk6h23k3ij20u00jymyw.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww4.sinaimg.cn/large/610dc034gw1fbfwwsjh3zj20u00u00w1.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww4.sinaimg.cn/large/610dc034jw1fbffqo6jjoj20u011hgpx.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww4.sinaimg.cn/large/610dc034jw1fbeerrs7aqj20u011htec.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww3.sinaimg.cn/large/610dc034jw1fbd818kkwjj20u011hjup.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww4.sinaimg.cn/large/610dc034jw1fb8iv9u08ij20u00u0tc7.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww4.sinaimg.cn/large/610dc034gw1fb7d9am05gj20u011hq64.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww1.sinaimg.cn/large/610dc034gw1fb6aqccs3nj20u00u0wk4.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww3.sinaimg.cn/large/610dc034gw1fb558z2peqj20u00u00v9.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww1.sinaimg.cn/large/610dc034gw1fb0kieivhgj20u00k2gmr.jpg"));
        testModelList.add(new TestModel(ModelType.SnapHelper,
            "http://ww2.sinaimg.cn/large/610dc034jw1faza3ghd2lj20f00k1gof.jpg"));
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapter = new TestSnapHelperAdapter(this, testModelList);

        recyclerView.setAdapter(adapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    @OnClick(R.id.iv_back) void onClickBack() {
        finish();
    }
}
