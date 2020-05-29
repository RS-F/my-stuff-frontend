package de.telekom.sea.mystuff.frontend.android.util;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.model.Item;

public class ItemListViewModel extends MyStuffViewModel{

    public LiveData<ApiResponse<List<Item>>> getItems() {
        return getMyStuffContext().getItemRepo().getAll();
    }
}
