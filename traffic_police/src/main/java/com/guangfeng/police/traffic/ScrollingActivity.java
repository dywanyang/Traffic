package com.guangfeng.police.traffic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guangfeng.police.traffic.mode.TrafficTicket;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

public class ScrollingActivity extends AppCompatActivity {

    EditText entry_id;
    EditText cause_action;
    EditText violations_code1;
    EditText violations_code2;
    EditText violations_code3;
    EditText violations_code4;
    EditText violations_code5;
    EditText violations_date;
    EditText car_number;
    EditText car_model;
    EditText car_mileage;
    EditText car_store_location;
    EditText duty_police;
    EditText car_manager;
    EditText car_process_result1;
    EditText car_process_result2;
    EditText car_release_date;

    private Button mPhotoPick;
    private Button mSave;
    private RecyclerView mRecyclerView;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectPhotos;

    private int mItemID;

    public static final String ARG_ITEM_ID = "item_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mItemID = getIntent().getIntExtra(ARG_ITEM_ID, -1);

        setUpView();

        if (mItemID != -1){
            setTitle(R.string.app_list_modify);
            loadData();
        }
    }

    private void setUpView() {
        cause_action = (EditText) findViewById(R.id.cause_action);
        entry_id = (EditText) findViewById(R.id.entry_id);
        violations_code1 = (EditText) findViewById(R.id.violations_code1);
        violations_code2 = (EditText) findViewById(R.id.violations_code2);
        violations_code3 = (EditText) findViewById(R.id.violations_code3);
        violations_code4 = (EditText) findViewById(R.id.violations_code4);
        violations_code5 = (EditText) findViewById(R.id.violations_code5);
        violations_date = (EditText) findViewById(R.id.violations_date);
        car_number = (EditText) findViewById(R.id.car_number);
        car_model = (EditText) findViewById(R.id.car_model);
        car_mileage = (EditText) findViewById(R.id.car_mileage);
        car_store_location = (EditText) findViewById(R.id.car_store_location);
        duty_police = (EditText) findViewById(R.id.duty_police);
        car_manager = (EditText) findViewById(R.id.car_manager);
        car_process_result1 = (EditText) findViewById(R.id.car_process_result1);
        car_process_result2 = (EditText) findViewById(R.id.car_process_result2);
        car_release_date = (EditText) findViewById(R.id.car_release_date);


        mPhotoPick = (Button) findViewById(R.id.button_pick_photo);
        mSave = (Button) findViewById(R.id.button_save);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        selectPhotos = new ArrayList<>();
        photoAdapter = new PhotoAdapter(this, selectPhotos);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        mRecyclerView.setAdapter(photoAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                PhotoPreview.builder()
                        .setPhotos(selectPhotos)
                        .setCurrentItem(position)
                        .start(ScrollingActivity.this);
            }
        }));

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSaveToDB();
            }
        });

        mPhotoPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Scrolling", "------setUpView() ");
                attemptPickPhoto();
            }
        });
    }

    private void loadData() {
        Log.d("ScrollingAcitivity", "------loadData mItemID: " + mItemID);
        TrafficTicket ticket = MainApplication.sTrafficModel.listTickets(false).get(mItemID);

        if (ticket != null) {
            cause_action.setText(ticket.getCauseAction());
            entry_id.setText(ticket.getEntryId());
            int photoLength = ticket.getCarPhotos().size();
            Log.d(this.getLocalClassName(),"-----loadData " + photoLength);
            if (ticket.getCarPhotos().size() > 0){
                loadPhotos(ticket.getCarPhotos());
            }
            try {
                violations_code1.setText(ticket.getViolationsCodes().get(0));
                violations_code2.setText(ticket.getViolationsCodes().get(1));
                violations_code3.setText(ticket.getViolationsCodes().get(2));
                violations_code4.setText(ticket.getViolationsCodes().get(3));
                violations_code5.setText(ticket.getViolationsCodes().get(4));
            }catch (Exception e){
                e.printStackTrace();
            }
            violations_date.setText(ticket.getViolationsDate());
            car_number.setText(ticket.getCarNumber());
            car_model.setText(ticket.getCarModel());
            car_mileage.setText(ticket.getCarMileage());
            car_store_location.setText(ticket.getCarStoreLocation());
            duty_police.setText(ticket.getDutyPolice());
            car_manager.setText(ticket.getCarManager());
            try {
                car_process_result1.setText(ticket.getCarProcessResults().get(0));
                car_process_result2.setText(ticket.getCarProcessResults().get(1));
            }catch (Exception e){
                e.printStackTrace();
            }
            car_release_date.setText(ticket.getCarReleaseDate());
        }

        Log.d("ScrollingAcitivity", "------loadData mItemID: " + ticket);
    }

    private void attemptSaveToDB() {
        if (TextUtils.isEmpty(entry_id.getText().toString()) || entry_id.getText().toString().length() != 16){
            entry_id.setError("编号标准为16位！");
            Log.d(this.getLocalClassName(),"------" + entry_id.getText() + " lenth " + entry_id.getText().toString().length());
            return;
        }
        TrafficTicket ticket = new TrafficTicket();
        ticket.setCauseAction(cause_action.getText().toString());
        ticket.setEntryId(entry_id.getText().toString());
        ticket.addViolationsCode(violations_code1.getText().toString());
        ticket.addViolationsCode(violations_code2.getText().toString());
        ticket.addViolationsCode(violations_code3.getText().toString());
        ticket.addViolationsCode(violations_code4.getText().toString());
        ticket.addViolationsCode(violations_code5.getText().toString());
        ticket.setViolationsDate(violations_date.getText().toString());
        ticket.setCarNumber(car_number.getText().toString());
        ticket.setCarModel(car_model.getText().toString());
        ticket.setCarMileage(car_mileage.getText().toString());
        ticket.setCarPhotos(selectPhotos);
        ticket.setCarStoreLocation(car_store_location.getText().toString());
        ticket.setDutyPolice(duty_police.getText().toString());
        ticket.setCarManager(car_manager.getText().toString());
        ticket.addCarProcessResults(car_process_result1.getText().toString());
        ticket.addCarProcessResults(car_process_result2.getText().toString());
        ticket.setCarReleaseDate(car_release_date.getText().toString());

        if (mItemID != -1) {
            if (MainApplication.sTrafficModel.updateTicket(mItemID, ticket)){
                Toast.makeText(this,"已修改涉案记录！",Toast.LENGTH_SHORT).show();
                finish();
            }
        }else {
            if (MainApplication.sTrafficModel.addTicket(ticket)){
                Toast.makeText(this,"已添加涉案记录！",Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    private void attemptPickPhoto() {
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }

    private void loadPhotos(List<String> list) {
        selectPhotos.clear();
        selectPhotos.addAll(list);
        photoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                if (photos != null) {
                    loadPhotos(photos);
                }
                for (String s : photos)
                    Log.d("Scrolling", "-----onActivityResult: " + s);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            attemptSaveToDB();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
