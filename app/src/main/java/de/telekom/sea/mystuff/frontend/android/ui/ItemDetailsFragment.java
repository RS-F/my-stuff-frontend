package de.telekom.sea.mystuff.frontend.android.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.sql.Date;

import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.api.ApiFactory;
import de.telekom.sea.mystuff.frontend.android.api.ItemApi;
import de.telekom.sea.mystuff.frontend.android.databinding.ItemDetailsFragmentBinding;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import timber.log.Timber;

public class ItemDetailsFragment extends Fragment {

    private ItemDetailsFragmentBinding binding;

    private final ItemApi itemApi = ApiFactory.getInstance().createApi(ItemApi.class);

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
        long itemId = getArguments().getLong("itemId");
        Timber.d("--> Clicked row: " +  itemId);

//        Item item = new Item();
//        item.setName("Bla");
//        item.setDescription("Blubb");
//        item.setLocation("hier");
//        item.setAmount(3);
//        item.setLastUsed(Date.valueOf("2020-05-01"));

        itemApi.getById(itemId).observe(this.getViewLifecycleOwner(), apiResponse -> {
//            if (apiResponse.isSuccessful()) {
                binding.setItem(apiResponse.body);
//            } else {
//                Toast.makeText(getContext(), "could not load item", Toast.LENGTH_LONG).show();
//            }
        });

    }

}
