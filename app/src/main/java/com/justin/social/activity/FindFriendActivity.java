package com.justin.social.activity;

import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.ArrayAdapter;

import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.adapter.NormalPhoneContentAdapter;
import com.justin.social.databinding.ActivityFindFriendBinding;
import com.justin.social.model.five.PhoneModel;

import java.util.List;

public class FindFriendActivity extends BackActivity {
    ActivityFindFriendBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_find_friend);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_CONTACTS},
                    1);
        }
        bind.list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        if (ContextCompat.checkSelfPermission(FindFriendActivity.this, android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(FindFriendActivity.this,
                    new String[]{android.Manifest.permission.READ_CONTACTS},
                    1);
        } else {
            UserDataObtain.getInstance(FindFriendActivity.this).getFriend(new IDataObtain.IDBResCallback<List<PhoneModel>>() {
                @Override
                public void complete(List<PhoneModel> strings) {
                    //将姓名及电话号码显示到ListView上
                    NormalPhoneContentAdapter adapter = new NormalPhoneContentAdapter(strings,FindFriendActivity.this);
                    bind.list.setAdapter(adapter);
                }
            });
        }
    }


}
