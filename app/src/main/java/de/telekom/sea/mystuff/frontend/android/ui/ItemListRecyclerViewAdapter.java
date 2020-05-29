package de.telekom.sea.mystuff.frontend.android.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
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

    private int createCounter;
    private int bindCounter;

    public ItemListRecyclerViewAdapter(List<Item> list) {
        this.itemList = list;
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
