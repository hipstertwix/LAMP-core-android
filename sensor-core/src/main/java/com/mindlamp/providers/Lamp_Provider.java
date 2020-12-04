
package com.mindlamp.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
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
 * LAMP framework content provider - Device information - Framework settings -
 * Plugins
 *
 * @author df
 */
public class Lamp_Provider extends ContentProvider {

    /**
     * LAMP framework content authority
     * com.aware.provider.aware
     */
    public static String AUTHORITY = "com.aware.provider.aware";

    private final int DEVICE_INFO = 1;
    private final int DEVICE_INFO_ID = 2;
    private final int SETTING = 3;
    private final int SETTING_ID = 4;
    private final int PLUGIN = 5;
    private final int PLUGIN_ID = 6;
    private final int STUDY = 7;
    private final int STUDY_ID = 8;
    private final int LOG = 9;
    private final int LOG_ID = 10;

    /**
     * Information about the device in which the framework is installed.
     *
     * @author denzil
     */
    public static final class Aware_Device implements BaseColumns {
        private Aware_Device() {
        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + Lamp_Provider.AUTHORITY + "/aware_device");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.aware.device";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.aware.device";

        public static final String _ID = "_id";
        public static final String TIMESTAMP = "timestamp";
        public static final String DEVICE_ID = "device_id";
        public static final String BOARD = "board";
        public static final String BRAND = "brand";
        public static final String DEVICE = "device";
        public static final String BUILD_ID = "build_id";
        public static final String HARDWARE = "hardware";
        public static final String MANUFACTURER = "manufacturer";
        public static final String MODEL = "model";
        public static final String PRODUCT = "product";
        public static final String SERIAL = "serial";
        public static final String RELEASE = "release";
        public static final String RELEASE_TYPE = "release_type";
        public static final String SDK = "sdk";
        public static final String LABEL = "label";
    }

    /**
     * Aware settings
     *
     * @author denzil
     */
    public static final class Aware_Settings implements BaseColumns {
        private Aware_Settings() {
        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + Lamp_Provider.AUTHORITY + "/aware_settings");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.aware.settings";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.aware.settings";

        public static final String SETTING_ID = "_id";
        public static final String SETTING_KEY = "key";
        public static final String SETTING_VALUE = "value";
        public static final String SETTING_PACKAGE_NAME = "package_name";
    }

    /**
     * Aware plugins
     *
     * @author denzil
     */
    public static final class Aware_Plugins implements BaseColumns {
        private Aware_Plugins() {
        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + Lamp_Provider.AUTHORITY + "/aware_plugins");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.aware.plugins";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.aware.plugins";

        public static final String PLUGIN_ID = "_id";
        public static final String PLUGIN_PACKAGE_NAME = "package_name";
        public static final String PLUGIN_NAME = "plugin_name";
        public static final String PLUGIN_VERSION = "plugin_version";
        public static final String PLUGIN_STATUS = "plugin_status";
        public static final String PLUGIN_AUTHOR = "plugin_author";
        public static final String PLUGIN_ICON = "plugin_icon";
        public static final String PLUGIN_DESCRIPTION = "plugin_description";
    }

    public static final class Aware_Studies implements BaseColumns {
        private Aware_Studies() {
        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + Lamp_Provider.AUTHORITY + "/aware_studies");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.aware.studies";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.aware.studies";

        public static final String STUDY_ID = "_id";
        public static final String STUDY_TIMESTAMP = "timestamp";
        public static final String STUDY_DEVICE_ID = "device_id";
        public static final String STUDY_URL = "study_url";
        public static final String STUDY_KEY = "study_key";
        public static final String STUDY_API = "study_api";
        public static final String STUDY_PI = "study_pi";
        public static final String STUDY_CONFIG = "study_config";
        public static final String STUDY_TITLE = "study_title";
        public static final String STUDY_DESCRIPTION = "study_description";
        public static final String STUDY_JOINED = "double_join";
        public static final String STUDY_EXIT = "double_exit";
        public static final String STUDY_COMPLIANCE = "study_compliance";
    }

    public static final class Aware_Log implements BaseColumns {
        private Aware_Log() {
        }

        public static final Uri CONTENT_URI = Uri.parse("content://" + Lamp_Provider.AUTHORITY + "/aware_log");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.aware.log";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.aware.log";

        public static final String LOG_ID = "_id";
        public static final String LOG_TIMESTAMP = "timestamp";
        public static final String LOG_DEVICE_ID = "device_id";
        public static final String LOG_MESSAGE = "log_message";
    }

    public static String DATABASE_NAME = "aware.db";
    public static final String[] DATABASE_TABLES = {"aware_device", "aware_settings", "aware_plugins", "aware_studies", "aware_log"};
    public static final String[] TABLES_FIELDS = {
            // Device information
            Aware_Device._ID + " integer primary key autoincrement,"
                    + Aware_Device.TIMESTAMP + " real default 0,"
                    + Aware_Device.DEVICE_ID + " text default '',"
                    + Aware_Device.BOARD + " text default '',"
                    + Aware_Device.BRAND + " text default '',"
                    + Aware_Device.DEVICE + " text default '',"
                    + Aware_Device.BUILD_ID + " text default '',"
                    + Aware_Device.HARDWARE + " text default '',"
                    + Aware_Device.MANUFACTURER + " text default '',"
                    + Aware_Device.MODEL + " text default '',"
                    + Aware_Device.PRODUCT + " text default '',"
                    + Aware_Device.SERIAL + " text default '',"
                    + Aware_Device.RELEASE + " text default '',"
                    + Aware_Device.RELEASE_TYPE + " text default '',"
                    + Aware_Device.SDK + " text default '',"
                    + Aware_Device.LABEL + " text default '',"
                    + "UNIQUE(" + Aware_Device.DEVICE_ID + ")",

            // Settings
            Aware_Settings.SETTING_ID + " integer primary key autoincrement,"
                    + Aware_Settings.SETTING_KEY + " text default '',"
                    + Aware_Settings.SETTING_VALUE + " text default '',"
                    + Aware_Settings.SETTING_PACKAGE_NAME + " text default ''",

            // Plugins
            Aware_Plugins.PLUGIN_ID + " integer primary key autoincrement,"
                    + Aware_Plugins.PLUGIN_PACKAGE_NAME + " text default '',"
                    + Aware_Plugins.PLUGIN_NAME + " text default '',"
                    + Aware_Plugins.PLUGIN_VERSION + " text default '',"
                    + Aware_Plugins.PLUGIN_STATUS + " integer default 0,"
                    + Aware_Plugins.PLUGIN_AUTHOR + " text default '',"
                    + Aware_Plugins.PLUGIN_ICON + " blob default null,"
                    + Aware_Plugins.PLUGIN_DESCRIPTION + " text default ''",

            // Studies
            Aware_Studies.STUDY_ID + " integer primary key autoincrement," +
                    Aware_Studies.STUDY_TIMESTAMP + " real default 0," +
                    Aware_Studies.STUDY_DEVICE_ID + " text default ''," +
                    Aware_Studies.STUDY_URL + " text default ''," +
                    Aware_Studies.STUDY_KEY + " integer default -1," +
                    Aware_Studies.STUDY_API + " text default ''," +
                    Aware_Studies.STUDY_PI + " text default ''," +
                    Aware_Studies.STUDY_CONFIG + " text default ''," +
                    Aware_Studies.STUDY_TITLE + " text default ''," +
                    Aware_Studies.STUDY_DESCRIPTION + " text default ''," +
                    Aware_Studies.STUDY_JOINED + " real default 0," +
                    Aware_Studies.STUDY_EXIT + " real default 0," +
                    Aware_Studies.STUDY_COMPLIANCE + " text default ''",

            Aware_Log.LOG_ID + " integer primary key autoincrement," +
                    Aware_Log.LOG_TIMESTAMP + " real default 0," +
                    Aware_Log.LOG_DEVICE_ID + " text default ''," +
                    Aware_Log.LOG_MESSAGE + " text default ''"
    };

    private UriMatcher sUriMatcher;
    private HashMap<String, String> deviceMap;
    private HashMap<String, String> settingsMap;
    private HashMap<String, String> pluginsMap;
    private HashMap<String, String> studiesMap;
    private HashMap<String, String> logMap;

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case DEVICE_INFO:
                return Aware_Device.CONTENT_TYPE;
            case DEVICE_INFO_ID:
                return Aware_Device.CONTENT_ITEM_TYPE;
            case SETTING:
                return Aware_Settings.CONTENT_TYPE;
            case SETTING_ID:
                return Aware_Settings.CONTENT_ITEM_TYPE;
            case PLUGIN:
                return Aware_Plugins.CONTENT_TYPE;
            case PLUGIN_ID:
                return Aware_Plugins.CONTENT_ITEM_TYPE;
            case STUDY:
                return Aware_Studies.CONTENT_TYPE;
            case STUDY_ID:
                return Aware_Studies.CONTENT_ITEM_TYPE;
            case LOG:
                return Aware_Log.CONTENT_TYPE;
            case LOG_ID:
                return Aware_Log.CONTENT_ITEM_TYPE;
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
        AUTHORITY = context.getPackageName() + ".provider.aware";
        return AUTHORITY;
    }

    @Override
    public boolean onCreate() {
        AUTHORITY = getContext().getPackageName() + ".provider.aware";

        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Lamp_Provider.AUTHORITY, DATABASE_TABLES[0], DEVICE_INFO);
        sUriMatcher.addURI(Lamp_Provider.AUTHORITY, DATABASE_TABLES[0] + "/#", DEVICE_INFO_ID);
        sUriMatcher.addURI(Lamp_Provider.AUTHORITY, DATABASE_TABLES[1], SETTING);
        sUriMatcher.addURI(Lamp_Provider.AUTHORITY, DATABASE_TABLES[1] + "/#", SETTING_ID);
        sUriMatcher.addURI(Lamp_Provider.AUTHORITY, DATABASE_TABLES[2], PLUGIN);
        sUriMatcher.addURI(Lamp_Provider.AUTHORITY, DATABASE_TABLES[2] + "/#", PLUGIN_ID);
        sUriMatcher.addURI(Lamp_Provider.AUTHORITY, DATABASE_TABLES[3], STUDY);
        sUriMatcher.addURI(Lamp_Provider.AUTHORITY, DATABASE_TABLES[3] + "/#", STUDY_ID);
        sUriMatcher.addURI(Lamp_Provider.AUTHORITY, DATABASE_TABLES[4], LOG);
        sUriMatcher.addURI(Lamp_Provider.AUTHORITY, DATABASE_TABLES[4] + "/#", LOG_ID);

        deviceMap = new HashMap<>();
        deviceMap.put(Aware_Device._ID, Aware_Device._ID);
        deviceMap.put(Aware_Device.TIMESTAMP, Aware_Device.TIMESTAMP);
        deviceMap.put(Aware_Device.DEVICE_ID, Aware_Device.DEVICE_ID);
        deviceMap.put(Aware_Device.BOARD, Aware_Device.BOARD);
        deviceMap.put(Aware_Device.BRAND, Aware_Device.BRAND);
        deviceMap.put(Aware_Device.DEVICE, Aware_Device.DEVICE);
        deviceMap.put(Aware_Device.BUILD_ID, Aware_Device.BUILD_ID);
        deviceMap.put(Aware_Device.HARDWARE, Aware_Device.HARDWARE);
        deviceMap.put(Aware_Device.MANUFACTURER, Aware_Device.MANUFACTURER);
        deviceMap.put(Aware_Device.MODEL, Aware_Device.MODEL);
        deviceMap.put(Aware_Device.PRODUCT, Aware_Device.PRODUCT);
        deviceMap.put(Aware_Device.SERIAL, Aware_Device.SERIAL);
        deviceMap.put(Aware_Device.RELEASE, Aware_Device.RELEASE);
        deviceMap.put(Aware_Device.RELEASE_TYPE, Aware_Device.RELEASE_TYPE);
        deviceMap.put(Aware_Device.SDK, Aware_Device.SDK);
        deviceMap.put(Aware_Device.LABEL, Aware_Device.LABEL);

        settingsMap = new HashMap<>();
        settingsMap.put(Aware_Settings.SETTING_ID, Aware_Settings.SETTING_ID);
        settingsMap.put(Aware_Settings.SETTING_KEY, Aware_Settings.SETTING_KEY);
        settingsMap.put(Aware_Settings.SETTING_VALUE, Aware_Settings.SETTING_VALUE);
        settingsMap.put(Aware_Settings.SETTING_PACKAGE_NAME, Aware_Settings.SETTING_PACKAGE_NAME);

        pluginsMap = new HashMap<>();
        pluginsMap.put(Aware_Plugins.PLUGIN_ID, Aware_Plugins.PLUGIN_ID);
        pluginsMap.put(Aware_Plugins.PLUGIN_PACKAGE_NAME, Aware_Plugins.PLUGIN_PACKAGE_NAME);
        pluginsMap.put(Aware_Plugins.PLUGIN_NAME, Aware_Plugins.PLUGIN_NAME);
        pluginsMap.put(Aware_Plugins.PLUGIN_VERSION, Aware_Plugins.PLUGIN_VERSION);
        pluginsMap.put(Aware_Plugins.PLUGIN_STATUS, Aware_Plugins.PLUGIN_STATUS);
        pluginsMap.put(Aware_Plugins.PLUGIN_AUTHOR, Aware_Plugins.PLUGIN_AUTHOR);
        pluginsMap.put(Aware_Plugins.PLUGIN_ICON, Aware_Plugins.PLUGIN_ICON);
        pluginsMap.put(Aware_Plugins.PLUGIN_DESCRIPTION, Aware_Plugins.PLUGIN_DESCRIPTION);

        studiesMap = new HashMap<>();
        studiesMap.put(Aware_Studies.STUDY_ID, Aware_Studies.STUDY_ID);
        studiesMap.put(Aware_Studies.STUDY_TIMESTAMP, Aware_Studies.STUDY_TIMESTAMP);
        studiesMap.put(Aware_Studies.STUDY_DEVICE_ID, Aware_Studies.STUDY_DEVICE_ID);
        studiesMap.put(Aware_Studies.STUDY_URL, Aware_Studies.STUDY_URL);
        studiesMap.put(Aware_Studies.STUDY_KEY, Aware_Studies.STUDY_KEY);
        studiesMap.put(Aware_Studies.STUDY_API, Aware_Studies.STUDY_API);
        studiesMap.put(Aware_Studies.STUDY_PI, Aware_Studies.STUDY_PI);
        studiesMap.put(Aware_Studies.STUDY_CONFIG, Aware_Studies.STUDY_CONFIG);
        studiesMap.put(Aware_Studies.STUDY_TITLE, Aware_Studies.STUDY_TITLE);
        studiesMap.put(Aware_Studies.STUDY_DESCRIPTION, Aware_Studies.STUDY_DESCRIPTION);
        studiesMap.put(Aware_Studies.STUDY_JOINED, Aware_Studies.STUDY_JOINED);
        studiesMap.put(Aware_Studies.STUDY_EXIT, Aware_Studies.STUDY_EXIT);
        studiesMap.put(Aware_Studies.STUDY_COMPLIANCE, Aware_Studies.STUDY_COMPLIANCE);

        logMap = new HashMap<>();
        logMap.put(Aware_Log.LOG_ID, Aware_Log.LOG_ID);
        logMap.put(Aware_Log.LOG_TIMESTAMP, Aware_Log.LOG_TIMESTAMP);
        logMap.put(Aware_Log.LOG_DEVICE_ID, Aware_Log.LOG_DEVICE_ID);
        logMap.put(Aware_Log.LOG_MESSAGE, Aware_Log.LOG_MESSAGE);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

}
