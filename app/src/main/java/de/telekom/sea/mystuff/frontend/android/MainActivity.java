package de.telekom.sea.mystuff.frontend.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import de.telekom.sea.mystuff.frontend.android.databinding.MyStuffItemBinding;
import de.telekom.sea.mystuff.frontend.android.ui.ItemListRecyclerViewAdapter;
import de.telekom.sea.mystuff.frontend.android.util.ItemListViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ItemListViewModel viewModel;
    private ItemListRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyStuffItemBinding myStuffItemBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
//        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(ItemListViewModel.class);
        viewModel.initWithApplication(getApplication());

        // bind RecyclerView
        recyclerView = findViewById(R.id.rv_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemListRecyclerViewAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        getAllItems();
    }

    private void getAllItems() {
        viewModel.getItems().observe(this, apiResponse -> {
            if (apiResponse.isSuccessful()) {
                adapter.updateItemList(apiResponse.body);
            } else {
                ((MyStuffApplication) getApplication()).getMyStuffContext().sendInfoMessage(apiResponse.errorMessage);
            }
        });
    }


}
