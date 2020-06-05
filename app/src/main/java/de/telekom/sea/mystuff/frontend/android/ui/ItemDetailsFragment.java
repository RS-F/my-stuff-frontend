package de.telekom.sea.mystuff.frontend.android.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Date;

import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.databinding.ItemDetailsFragmentBinding;
import de.telekom.sea.mystuff.frontend.android.model.Item;

public class ItemDetailsFragment extends Fragment {

    private ItemDetailsFragmentBinding binding;

    public static ItemDetailsFragment newInstance() {
        return new ItemDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_details_fragment, container, false);
//        return inflater.inflate(R.layout.item_details_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Item item = new Item();
        item.setName("Bla");
        item.setDescription("Blubb");
        item.setLocation("hier");
        item.setAmount(3);
        item.setLastUsed(Date.valueOf("2020-05-01"));
        binding.setItem(item);
    }

}
