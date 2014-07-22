package com.wang.tqqweibo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.weibo.sdk.android.component.Authorize;
import com.tencent.weibo.sdk.android.component.sso.AuthHelper;
import com.tencent.weibo.sdk.android.component.sso.OnAuthListener;
import com.tencent.weibo.sdk.android.component.sso.WeiboToken;


public class main extends ActionBarActivity {

    private long appId=801526668;
    private String appSecret="7c5ee49d35caf62acfc785067532d682";
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    void login(){

        AuthHelper.register(this,appId,appSecret,new OnAuthListener() {
            @Override
            public void onWeiBoNotInstalled() {
                Toast.makeText(main.this,"weibo app no installed",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(main.this, Authorize.class);
                startActivity(intent);
            }

            @Override
            public void onWeiboVersionMisMatch() {
                Toast.makeText(main.this,"weibo version no match",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(main.this,Authorize.class);
                startActivity(intent);

            }

            @Override
            public void onAuthFail(int i, String s) {

                Toast.makeText(main.this,"auth filed "+s,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAuthPassed(String s, WeiboToken weiboToken) {
                Toast.makeText(main.this,"successed",Toast.LENGTH_LONG).show();

            }
        });
        AuthHelper.auth(this,"");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
