package com.maxleap.test.payment.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.maxleap.LogInCallback;
import com.maxleap.MLUser;
import com.maxleap.MLUserManager;
import com.maxleap.SignUpCallback;
import com.maxleap.exception.MLException;
import com.maxleap.test.payment.R;

/**
 * Created by mrseasons on 2015/12/15.
 */
public class TimelineActivity extends Activity {

    public static final String TAG = TimelineActivity.class.getName();

    EditText username;
    EditText password;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pasword);

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!checkUser()) {
                    return;
                }


                MLUser user = new MLUser();
                user.setUserName(username.getText().toString());
                user.setPassword(password.getText().toString());
                MLUserManager.signUpInBackground(user, new SignUpCallback() {
                    @Override
                    public void done(final MLException e) {
                        if (e == null) {
                            Log.v(TAG, "sign up success");
                            return;
                        }
                        e.printStackTrace();
                    }
                });
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!checkUser()) {
                    return;
                }


                MLUserManager.logInInBackground(username.getText().toString(),
                        password.getText().toString(), new LogInCallback<MLUser>() {
                            @Override
                            public void done(final MLUser user, final MLException e) {
                                if (e == null) {
                                    Log.v(TAG, "login success");
                                    return;
                                }
                                e.printStackTrace();
                            }
                        });
            }
        });

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                MLUser.logOut();
            }
        });
    }

    private boolean checkUser() {
        if (TextUtils.isEmpty(username.getText()) || TextUtils.isEmpty(password.getText())) {
            Log.w(TAG, "Please enter username and password first!");
            return false;
        }
        return true;
    }
}
