package com.rubenpla.develop.techtestapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataStore extends SQLiteOpenHelper {

    public final String TAG = DataStore.class.getSimpleName();

    public static final String APP_DATABASE_NAME = "app_DataStore.db";
    public static final String TEST_DATABASE_NAME = "test_DataStore.db";

    private static DataStore instance = null;
    private DataStoreTables dataStoreTables = null;

    private static final int DATABASE_VERSION = 1;

    public static DataStore getInstance(Context context) {
        synchronized (DataStore.class) {
            if (instance == null) {
                instance = new DataStore(context, APP_DATABASE_NAME);
            }
            return instance;
        }
    }

    /**
     * Constructor
     *
     * Note: This is public only so that it can be called by unit tests.
     * Application code should always use the static getInstance() method
     * rather than constructing an instance.
     *
     * @param context Interface to the application environment
     */
    public DataStore(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
        dataStoreTables = new DataStoreTables();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAllTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);

        // TODO: If you need to retain existing data, rather than just deleting
        // and recreating all the tables, then put that code here.
        resetAllTables(db);
    }

    public void resetAllTables(SQLiteDatabase db) {
        dropAllTables(db);
        createAllTables(db);
    }

    public void createAllTables(SQLiteDatabase db) {
        String[] createTableStatements = {
               dataStoreTables.CREATE_FAMILY_TABLE_QUERY
        };

        for (String createTableStatement: createTableStatements) {
            Log.d(TAG, "creating table: " + createTableStatement);
            db.execSQL(createTableStatement);
        }
    }

    private void dropAllTables(SQLiteDatabase db) {
        String[] tableNames = {
                dataStoreTables.TABLE_FAMILY_MAIN
        };

        for (String tableName: tableNames) {
            Log.d(TAG, "dropping table " + tableName);
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
        }
    }

    /**
     * Generic interface for an operation that acts on an open database and returns a result.
     *
     * @param <Result>
     *            Type of result returned by method (may be Void if there is no usable result)
     */
    public interface DatabaseReadableQuery<Result> {
        Result execute(SQLiteDatabase db, final String tableName) throws Exception;
    }

    public interface DatabaseWritableQuery<Result> {
        Result execute(SQLiteDatabase db, final String tableName, Object object) throws Exception;
    }

    /**
     * Execute database operation within the context of a readable SQLiteDatabase.
     *
     * This method is synchronized so that multiple threads cannot be accessing the database
     * concurrently.
     *
     * @param query
     */
    public synchronized <Result> Result executeWithReadableDB(final DatabaseReadableQuery<Result> query,
                                                              final String tableName)
            throws Exception {
        final SQLiteDatabase db = getReadableDatabase();
        try {
            return query.execute(db, tableName);
        }
        finally {
            db.close();
        }
    }

    /**
     * Execute database operation within the context of a writable SQLiteDatabase.
     *
     * This method is synchronized so that multiple threads cannot be accessing the database
     * concurrently.
     *
     * @param query
     */
    public synchronized <Result> Result executeWithWritableDB(final DatabaseWritableQuery<Result> query,
                                                              final String tableName, final Object object)
            throws Exception {

        final SQLiteDatabase db = getWritableDatabase();
        try {
            return query.execute(db, tableName, object);
        } catch (Exception e) {
            Log.e(TAG, "Exception e: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }

        return null;
    }

    /**
     * Execute database operation within the context of a writable SQLiteDatabase with an open
     * transaction.
     *
     * The query must call SQLiteDatabase.setTransactionSuccessful() to commit the transaction.
     * Otherwise, any changes will be rolled back.
     *
     * This method is synchronized so that multiple threads cannot be accessing the database
     * concurrently.
     *
     * @param query
     */
    public synchronized <Result> Result executeWithWritableDBTransaction(final DatabaseWritableQuery<Result> query,
                                                                         final String tableName, final Object object)
            throws Exception {

        final SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();

            try {
                return query.execute(db, tableName, object);
            }
            finally {
                db.endTransaction();
            }
        }
        finally {
            db.close();
        }
    }

    public DataStoreTables getDataStoreTables() {
        return dataStoreTables;
    }
}

