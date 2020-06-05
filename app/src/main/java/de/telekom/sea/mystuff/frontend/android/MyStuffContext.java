package de.telekom.sea.mystuff.frontend.android;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import de.telekom.sea.mystuff.frontend.android.api.ApiFactory;
import de.telekom.sea.mystuff.frontend.android.api.ItemApi;
import de.telekom.sea.mystuff.frontend.android.repo.ItemRepo;
import lombok.Getter;
import timber.log.Timber;

/**
 * Provides all component-like objects (e.g. singletons) without exposing the Application
 * directly.
 * This makes it easier to test the viewmodels, because less objects have to be mocked.
 */
public class MyStuffContext {

    private MyStuffApplication app;

    @Getter
    private ApiFactory apiFactory;

    @Getter
    private ItemRepo itemRepo;

    void initWithApplication(MyStuffApplication app){
        this.app = app;
        this.apiFactory = new ApiFactory();
        this.itemRepo = new ItemRepo(apiFactory.createApi(ItemApi.class));
    }

    public String getString(int resId) {
        return app.getString(resId);
    }

    public void runOnUiThread(Runnable r) {
        Handler h = new Handler(Looper.getMainLooper());
        h.post(r);
    }

    public void sendInfoMessage(String message) {
        Runnable r = () -> Toast.makeText(MyStuffContext.this.app, message, Toast.LENGTH_LONG).show();
        runOnUiThread(r);
    }

    public void sendInfoMessage(int resId) {
        sendInfoMessage(getString(resId));
    }

    public void sendErrorMessage(int resId, String technicalErrorMessage) {
        sendErrorMessage(getString(resId), technicalErrorMessage);
    }

    public void sendErrorMessage(String userMessage, String technicalErrorMessage) {

        // identify calling line of code from stacktrace
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        // find log relevant stacktrace elements
        StackTraceElement thirdLastStackTraceElement = stackTraceElements[2];
        StackTraceElement fourthLastStackTraceElement = stackTraceElements[3];
        StackTraceElement logRelevantStackTraceElement;

        // if MayoContext.sendErrorMessage(int resId, String technicalErrorMessage) was called before
        if ((thirdLastStackTraceElement.getClassName().equalsIgnoreCase(fourthLastStackTraceElement.getClassName())) &&
                (thirdLastStackTraceElement.getMethodName().equalsIgnoreCase(thirdLastStackTraceElement.getMethodName()))) {
            logRelevantStackTraceElement = stackTraceElements[4];
        } else {
            logRelevantStackTraceElement = thirdLastStackTraceElement;
        }

        String callingClassName = logRelevantStackTraceElement.getClassName();
        String callingMethodName = logRelevantStackTraceElement.getMethodName();
        int callingLineNumber = logRelevantStackTraceElement.getLineNumber();

        sendInfoMessage(userMessage);
        Timber.e("Error in class %s, method %s(), line %d: %s", callingClassName, callingMethodName, callingLineNumber, technicalErrorMessage);
    }

}
