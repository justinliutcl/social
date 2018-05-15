package com.justin.social.activity;

import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ArrayAdapter;

import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.databinding.ActivityFindFriendBinding;

public class FindFriendActivity extends BackActivity {
    ActivityFindFriendBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_find_friend);
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_CONTACTS},
                    1);
        }
        bind.btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(FindFriendActivity.this,android.Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FindFriendActivity.this,
                            new String[]{android.Manifest.permission.READ_CONTACTS},
                            1);
                }else{
                    UserDataObtain.getInstance(FindFriendActivity.this).getFriend(new IDataObtain.IDBResCallback<String[]>() {
                        @Override
                        public void complete(String[] strings) {
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(FindFriendActivity.this, android.R.layout.simple_list_item_1, strings);
                            //将姓名及电话号码显示到ListView上
                            bind.lvContacts.setAdapter(adapter);
                        }
                    });
                }
            }
        });
    }


}
