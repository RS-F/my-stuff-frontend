package de.telekom.sea.mystuff.frontend.android.ui;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.api.ApiFactory;
import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.api.ItemApi;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import de.telekom.sea.mystuff.frontend.android.util.MyStuffViewModel;

public class ItemListViewModel extends MyStuffViewModel {

    private final ItemApi itemApi = ApiFactory.getInstance().createApi(ItemApi.class);

    public LiveData<ApiResponse<List<Item>>> getItems() {
        return getMyStuffContext().getItemRepo().getAll();
    }
}
