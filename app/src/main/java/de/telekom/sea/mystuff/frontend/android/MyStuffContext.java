package de.telekom.sea.mystuff.frontend.android;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import de.telekom.sea.mystuff.frontend.android.api.ApiFactory;
import de.telekom.sea.mystuff.frontend.android.api.ItemApi;
import de.telekom.sea.mystuff.frontend.android.repo.ItemRepository;
import lombok.Getter;
import timber.log.Timber;

/**
 * Provides all component-like objects (e.g. singletons) without exposing the Application
 * directly.
 * This makes it easier to test the viewmodels, because less objects have to be mocked.
 */
public class MyStuffContext {

    @Getter
    private ApiFactory apiFactory;

    @Getter
    private ItemRepository itemRepository;

    private MyStuffApplication app;

    public MyStuffContext initWithApplication(MyStuffApplication app) {
        this.app = app;
        this.apiFactory = new ApiFactory();
        this.itemRepository = new ItemRepository(this.apiFactory.createApi(ItemApi.class));
        return this;
    }

    public String getString(int resId) {
        return app.getString(resId);
    }

    public void sendInfoMessage(int resId) {
        Toast.makeText(this.app.getApplicationContext(), getString(resId), Toast.LENGTH_LONG).show();
    }

    public void sendInfoMessage(String msg) {
        Toast.makeText(this.app.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

}
