
package com.mindlamp.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

/**
 * Scheduler Provider: keeps a record of scheduled tasks that need to be performed on triggered events
 */
public class Scheduler_Provider extends ContentProvider {

    /**
     * Authority of Scheduler content provider
     */
    public static String AUTHORITY = "com.aware.provider.scheduler";

    // ContentProvider query paths
    private final int SCHEDULER = 1;
    private final int SCHEDULER_ID = 2;

    public static final class Scheduler_Data implements BaseColumns {
        private Scheduler_Data() {
        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + Scheduler_Provider.AUTHORITY + "/scheduler");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.aware.scheduler";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.aware.scheduler";

        public static final String _ID = "_id";
        public static final String TIMESTAMP = "timestamp";
        public static final String DEVICE_ID = "device_id";
        public static final String SCHEDULE_ID = "schedule_id";
        public static final String SCHEDULE = "schedule";
        public static final String LAST_TRIGGERED = "last_triggered";
        public static final String PACKAGE_NAME = "package_name";
    }

    public static String DATABASE_NAME = "scheduler.db";
    public static final String[] DATABASE_TABLES = {"scheduler"};

    public static final String[] TABLES_FIELDS = {
            Scheduler_Data._ID + " integer primary key autoincrement,"
                    + Scheduler_Data.TIMESTAMP + " real default 0,"
                    + Scheduler_Data.DEVICE_ID + " text default '',"
                    + Scheduler_Data.SCHEDULE_ID + " text default '',"
                    + Scheduler_Data.SCHEDULE + " text default '',"
                    + Scheduler_Data.LAST_TRIGGERED + " real default 0,"
                    + Scheduler_Data.PACKAGE_NAME + " text default ''"};

    private UriMatcher sUriMatcher = null;
    private HashMap<String, String> dataMap = null;

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case SCHEDULER:
                return Scheduler_Data.CONTENT_TYPE;
            case SCHEDULER_ID:
                return Scheduler_Data.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /**
     * Returns the provider authority that is dynamic
     * @return
     */
    public static String getAuthority(Context context) {
        AUTHORITY = context.getPackageName() + ".provider.scheduler";
        return AUTHORITY;
    }

    @Override
    public boolean onCreate() {
        AUTHORITY = getContext().getPackageName() + ".provider.scheduler";

        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Scheduler_Provider.AUTHORITY, DATABASE_TABLES[0], SCHEDULER);
        sUriMatcher.addURI(Scheduler_Provider.AUTHORITY, DATABASE_TABLES[0] + "/#", SCHEDULER_ID);

        dataMap = new HashMap<>();
        dataMap.put(Scheduler_Data._ID, Scheduler_Data._ID);
        dataMap.put(Scheduler_Data.TIMESTAMP, Scheduler_Data.TIMESTAMP);
        dataMap.put(Scheduler_Data.DEVICE_ID, Scheduler_Data.DEVICE_ID);
        dataMap.put(Scheduler_Data.SCHEDULE_ID, Scheduler_Data.SCHEDULE_ID);
        dataMap.put(Scheduler_Data.SCHEDULE, Scheduler_Data.SCHEDULE);
        dataMap.put(Scheduler_Data.LAST_TRIGGERED, Scheduler_Data.LAST_TRIGGERED);
        dataMap.put(Scheduler_Data.PACKAGE_NAME, Scheduler_Data.PACKAGE_NAME);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

}