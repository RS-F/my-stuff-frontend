package de.telekom.sea.mystuff.frontend.android.repo;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.api.ItemApi;
import de.telekom.sea.mystuff.frontend.android.model.Item;

public class ItemRepository {

    private final ItemApi itemApi;

    public ItemRepository(ItemApi itemApi) {
        this.itemApi = itemApi;
    }

    public LiveData<ApiResponse<List<Item>>> getAll() {return this.itemApi.getAll();}
}
