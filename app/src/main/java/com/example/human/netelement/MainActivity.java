package com.example.human.netelement;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.util.VKUtil;

import java.util.Arrays;

public class MainActivity extends Activity {

    private String[] scope = new String[]{VKScope.PHOTOS,VKScope.WALL,VKScope.DIRECT};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        System.out.println("NOW IS YOU Key!!!");
       System.out.println(Arrays.asList(fingerprints));

        VKSdk.login(this,scope);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
// Пользователь успешно авторизовался
            }
            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(),"Wrong!",Toast.LENGTH_LONG).show();

// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
