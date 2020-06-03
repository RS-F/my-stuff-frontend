package de.telekom.sea.mystuff.frontend.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import de.telekom.sea.mystuff.frontend.android.R;

public class ItemListFragment extends Fragment {

    private ItemListRecyclerViewAdapter adapter;
    private ItemListViewModel viewModel;
    private SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ItemListViewModel.class);
        adapter = new ItemListRecyclerViewAdapter(new ArrayList<>());
        return inflater.inflate(R.layout.item_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.initWithApplication(getActivity().getApplication());

        // bind RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rv_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        refreshLayout = view.findViewById(R.id.refreshItemListLayout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllItems();
                refreshLayout.setRefreshing(false);
            }
        });

        getAllItems();
    }
    private void getAllItems() {
        viewModel.getItems().observe(this.getViewLifecycleOwner(), apiResponse -> {
            if (apiResponse.isSuccessful()) {
                adapter.updateItemList(apiResponse.body);
            } else {
                Toast.makeText(getContext(), "could not load list", Toast.LENGTH_LONG).show();
            }
        });
    }

}
