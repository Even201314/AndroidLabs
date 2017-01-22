package com.even.androidlabs.recyclerview.snaphelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.even.androidlabs.R;
import com.even.androidlabs.test.interfaces.OnItemClickListener;
import com.even.androidlabs.test.model.TestModel;
import java.util.List;

/**
 * Test Adapter
 * Created by Even on 17/1/22.
 */

public class TestSnapHelperAdapter extends RecyclerView.Adapter<TestSnapHelperAdapter.ItemHolder> {
    private Context mContext;

    private List<TestModel> testModelList;
    private OnItemClickListener mOnItemClickListener;

    public TestSnapHelperAdapter(Context mContext, List<TestModel> testModelList) {
        this.mContext = mContext;
        this.testModelList = testModelList;
    }

    @Override public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_test_snap_helper, parent, false));
    }

    @Override public void onBindViewHolder(ItemHolder holder, int position) {
        Glide.with(mContext)
            .load(testModelList.get(position).getDescription())
            .asBitmap()
            .centerCrop()
            .into(holder.ivContent);
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
        @BindView(R.id.iv_content) ImageView ivContent;

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
