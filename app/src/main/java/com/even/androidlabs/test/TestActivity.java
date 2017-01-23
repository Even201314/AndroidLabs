package com.even.androidlabs.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.even.androidlabs.R;
import com.even.androidlabs.recyclerview.snaphelper.TestSnapHelperActivity;
import com.even.androidlabs.rxjava.RxBus;
import com.even.androidlabs.test.interfaces.OnItemClickListener;
import com.even.androidlabs.test.model.ModelType;
import com.even.androidlabs.test.model.TestModel;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

/**
 * Test Activity
 * Created by Even on 17/1/22.
 */

public class TestActivity extends AppCompatActivity {
    private static final String TAG = TestActivity.class.getSimpleName();

    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private TestAdapter adapter;

    private List<TestModel> testModelList;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        initBehavior();
    }

    private void initBehavior() {
        ivBack.setVisibility(View.GONE);
        tvTitle.setText(TAG);

        initTestModelList();
        initRecyclerView();

        RxBus.register("testRxBus", new Consumer<Object>() {
            @Override public void accept(Object object) throws Exception {
                Toast.makeText(TestActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTestModelList() {
        testModelList = new ArrayList<>();

        testModelList.add(new TestModel(ModelType.SnapHelper, "Snap Helper"));
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new TestAdapter(this, testModelList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override public void onItemClick(View itemView, int position) {
                handleRecyclerClickEvent(position);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void handleRecyclerClickEvent(int position) {
        TestModel testModel = testModelList.get(position);
        switch (testModel.getModelType()) {
            case SnapHelper:
                startActivity(new Intent(TestActivity.this, TestSnapHelperActivity.class));
                break;
        }
    }
}
