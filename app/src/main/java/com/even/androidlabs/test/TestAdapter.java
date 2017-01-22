package com.even.androidlabs.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.even.androidlabs.R;
import com.even.androidlabs.test.interfaces.OnItemClickListener;
import com.even.androidlabs.test.model.TestModel;
import java.util.List;

/**
 * Test Adapter
 * Created by Even on 17/1/22.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ItemHolder> {
    private Context mContext;

    private List<TestModel> testModelList;
    private OnItemClickListener mOnItemClickListener;

    public TestAdapter(Context mContext, List<TestModel> testModelList) {
        this.mContext = mContext;
        this.testModelList = testModelList;
    }

    @Override public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_test, parent, false));
    }

    @Override public void onBindViewHolder(ItemHolder holder, int position) {
        holder.tvContent.setText(testModelList.get(position).getDescription());
    }

    @Override public int getItemCount() {
        return testModelList == null ? 0 : testModelList.size();
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content) TextView tvContent;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }
}
