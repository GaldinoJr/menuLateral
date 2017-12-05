package com.galdino.examplemenulateral.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galdino.examplemenulateral.BR;
import com.galdino.examplemenulateral.R;
import com.galdino.examplemenulateral.databinding.HomeSideMenuListItemBinding;
import com.galdino.examplemenulateral.domain.HomeSideMenuItem;

import java.util.List;

/**
 * Created by galdino on 05/12/17.
 */

public class HomeSideMenuAdapter extends BaseAdapter<HomeSideMenuItem>
{
//    private final PublishSubject<HomeSideMenuItem> onItemClick = PublishSubject.create();
    public void setData(List<HomeSideMenuItem> list)
    {
        this.enableItemPairClick();
        dataList = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolderBase(ViewGroup parent, int viewType)
    {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_side_menu_list_item, parent, false);
        return new HomeSideMenuViewHolder(inflate);
    }

    @Override
    public void onBindViewHolderBase(RecyclerView.ViewHolder holder, int position)
    {
        HomeSideMenuItem homeSideMenuItem = dataList.get(position);
        HomeSideMenuListItemBinding binding = ((HomeSideMenuViewHolder)holder).getBinding();
        binding.setVariable(BR.homeSideMenuItem,homeSideMenuItem);
        binding.executePendingBindings();
        binding.icon.setImageResource(dataList.get(position).getIcon());
//        binding.getRoot().setOnClickListener(view -> getClick());
    }
    // ViewHolder
    public class HomeSideMenuViewHolder extends RecyclerView.ViewHolder
    {
        HomeSideMenuListItemBinding mBinding;
        public HomeSideMenuViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        public HomeSideMenuListItemBinding getBinding()
        {
            return mBinding;
        }
    }
    // region Click
//    Observable<HomeSideMenuItem> observableClick() {
//        return onItemClick;
//    }
//
//    private PublishSubject<HomeSideMenuItem> getClick() {
//        return onItemClick;
//    }
    // endregion
}
