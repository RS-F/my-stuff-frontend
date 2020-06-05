package de.telekom.sea.mystuff.frontend.android.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.databinding.MyStuffItemBinding;
import de.telekom.sea.mystuff.frontend.android.databinding.MyStuffItemBindingImpl;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import lombok.Getter;
import timber.log.Timber;

public class ItemListRecyclerViewAdapter extends RecyclerView.Adapter<ItemListRecyclerViewAdapter.ViewHolder> {


    @Getter
    private final List<Item> itemList;
    private final NavController navController;


    private int createCounter;
    private int bindCounter;

    public ItemListRecyclerViewAdapter(List<Item> list, NavController navController) {
        this.itemList = list;
        this.navController = navController;
    }

    public void updateItemList(List<Item> list) {
        this.itemList.clear();
        this.itemList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyStuffItemBinding listItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.my_stuff_item, parent, false);
        Timber.d("--> onCreateViewHolder " + ++createCounter);
        return new ViewHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item item = this.itemList.get(position);
        holder.binding.setItem(item);
        Timber.d("--> onBindViewHolder   " + ++bindCounter);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("itemId", item.getId());
                navController.navigate(R.id.action_itemListFragment_to_itemDetailsFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Getter
        final MyStuffItemBinding binding;


        ViewHolder(MyStuffItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
