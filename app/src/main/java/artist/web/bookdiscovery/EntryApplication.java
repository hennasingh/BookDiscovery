package artist.web.bookdiscovery;


import android.app.Application;

import artist.web.bookdiscovery.network.ApiManager;

public class EntryApplication extends Application {

    public static ApiManager sApiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sApiManager = ApiManager.getInstance();
    }
}
