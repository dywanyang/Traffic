package com.guangfeng.police.traffic.mode;

import android.provider.BaseColumns;

/**
 * Created by heyongjian
 * on 16/7/15.
 */
public class TrafficContract {

    public TrafficContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_CAUSE_ACTION = "cause_action";
        public static final String COLUMN_NAME_ENTRY_ID = "entry_id";
        //public static final String COLUMN_NAME_TITLE = "title";
        //public static final String COLUMN_NAME_SUBTITLE = "subtitle";

        public static final String COLUMN_NAME_VIOLATIONS_CODES = "violations_codes";
        public static final String COLUMN_NAME_VIOLATIONS_DATE = "violations_date";
        public static final String COLUMN_NAME_CAR_NUMBER = "car_number";
        public static final String COLUMN_NAME_CAR_MODEL = "car_model";
        public static final String COLUMN_NAME_CAR_MILEAGE = "car_mileage";
        public static final String COLUMN_NAME_CAR_PHOTOS = "car_photos";
        public static final String COLUMN_NAME_CAR_STORE_LOCATION = "car_store_location";
        public static final String COLUMN_NAME_DUTY_POLICE = "duty_police";
        public static final String COLUMN_NAME_CAR_MANAGER = "car_manager";
        public static final String COLUMN_NAME_CAR_PROCESS_RESULTS = "car_process_results";
        public static final String COLUMN_NAME_CAR_RELEASE_DATE = "car_release_date";

    }
}
