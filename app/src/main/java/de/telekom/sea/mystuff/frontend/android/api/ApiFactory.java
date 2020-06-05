package de.telekom.sea.mystuff.frontend.android.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.telekom.sea.mystuff.frontend.android.BuildConfig;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Creates API instances for performing REST calls.
 */
public class ApiFactory {

    private static ApiFactory instance;

    @Getter
    private final Retrofit retrofit;
    @Getter
    private final String baseRestUrl;
    private final String hostName;
    private final OkHttpClient okHttpClient;

    public static synchronized ApiFactory getInstance() {
        if (instance == null) {
            instance = new ApiFactory();
        }
        return instance;
    }

    /**
     * place real IP address of backend machine here
     * needs a
     * <b>android:usesCleartextTraffic="true"</b>
     * in AndroidManifest.xml in section Application
     * if not choose https protocol
     */

    public ApiFactory() {

        this.hostName = BuildConfig.apifactory_hostname;
        String protocol = BuildConfig.apifactory_protocol;
        String port = BuildConfig.apifactory_port;

        this.baseRestUrl = protocol + "://" + hostName + ":" + port;

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);



            // create OkHttp client
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(this.baseRestUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(okHttpClient)
                .build();
    }

    /**
     * @param retrofitApiInterface defines the REST interface, must not be null
     * @param <S>
     * @return API instance for performing REST calls, never null
     */
    public <S> S createApi(Class<S> retrofitApiInterface) {
        return retrofit.create(retrofitApiInterface);
    }

    public String getBackendBaseUrl() {
        return this.baseRestUrl;
    }


}
