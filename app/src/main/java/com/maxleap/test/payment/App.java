package com.maxleap.test.payment;

import android.app.Application;
import com.maxleap.MaxLeap;
import com.maxleap.TestUtils;

/**
 * Created by mrseasons on 2015/07/22.
 */
public class App extends Application {

    public static final String TEST_APP_ID = "565bc14ce9db730001d5a168";
    public static final String TEST_API_KEY = "V0FKQlJmWmo2OXlpLWxKdTgxRGt2dw";

    @Override
    public void onCreate() {
        super.onCreate();
//        TestUtils.useUatEnv();
//        TestUtils.useDevEnv();
          TestUtils.useCnUatEnv();
//        TestUtils.useUatEnv();
        TestUtils.debug();
        MaxLeap.setLogLevel(MaxLeap.LOG_LEVEL_VERBOSE);
        MaxLeap.Options options = new MaxLeap.Options();
        options.marketingEnable = true;
        options.appId = TEST_APP_ID;
        options.clientKey = TEST_API_KEY;
        options.serverRegion = MaxLeap.REGION_CN;
        MaxLeap.initialize(this, options);
//        TestUtils.useHttp();
    }
}
