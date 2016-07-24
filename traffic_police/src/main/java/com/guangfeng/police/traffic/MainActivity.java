package com.guangfeng.police.traffic;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Messenger;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextId;
    private EditText mEditTextPassward;
    private Button mButtonConfirm;
    private Button mButtonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
    }

    private void setupView() {
        mEditTextId = (EditText) findViewById(R.id.login_id);
        mEditTextId.requestFocus();
        mEditTextPassward = (EditText) findViewById(R.id.login_password);
        mButtonConfirm = (Button) findViewById(R.id.login_sign_in_button);
        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mButtonCreate = (Button) findViewById(R.id.login_create);
        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptCreate();
            }
        });

    }

    private void attemptCreate() {
        Intent homeIntent = new Intent(this, ScrollingActivity.class);
        startActivity(homeIntent);
    }

    private void attemptLogin() {
        //SQLiteDatabase db = MainApplication.sTrafficModel.getDbHelper().getWritableDatabase();
        //Log.d("MainActivity","-------attemptLogin " + db);
        // Reset errors.
        mEditTextId.setError(null);
        mEditTextPassward.setError(null);

        // Store values at the time of the login attempt.
        String policeId = mEditTextId.getText().toString();
        String password = mEditTextPassward.getText().toString();

        // Check for a valid email address.
        if (TextUtils.isEmpty(policeId)) {
            mEditTextId.setError(getString(R.string.error_field_required));
        } else if (TextUtils.isEmpty(password)) {
            mEditTextPassward.setError(getString(R.string.error_field_required));
        }

        if (policeId.equals("9527") && password.equals("123456")) {
            Intent homeIntent = new Intent(this, ItemListActivity.class);
//            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }

}
