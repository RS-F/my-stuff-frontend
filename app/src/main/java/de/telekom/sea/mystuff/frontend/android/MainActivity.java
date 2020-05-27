package de.telekom.sea.mystuff.frontend.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.databinding.MyStuffItemBinding;
import de.telekom.sea.mystuff.frontend.android.model.Item;
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
//        setContentView(R.layout.activity_main);
        MyStuffItemBinding myStuffItemBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        viewModel = new ViewModelProvider(this).get(ItemListViewModel.class);
        viewModel.initWithApplication(getApplication());
        adapter = new ItemListRecyclerViewAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        getAllItems();
    }

    private void getAllItems() {
        viewModel.getItems().observe(this, new Observer<ApiResponse<List<Item>>>() {
            @Override
            public void onChanged(ApiResponse<List<Item>> listApiResponse) {
                adapter.getItemList();
            }
        });
    }


}
