package be.hogent.eothein.todo.persistency;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static be.hogent.eothein.todo.persistency.TODOContract.DATABASE_NAME;
import static be.hogent.eothein.todo.persistency.TODOContract.DATABASE_VERSION;

/**
 * Created by eothein on 03.11.17.
 */

class TODODBHelper extends SQLiteOpenHelper {

    private static final String COMMA_SEP = ",";
    private static final String SPACE = " ";

    /**
     * SQL Code to genereate the database and the table
     */
    private static final String CREATE_TABLE_TODO = "CREATE TABLE " + TODOContract.Todo.TABLE_NAME +
            "(" + TODOContract.Todo.Columns._ID + SPACE + "INTEGER PRIMARY KEY" + COMMA_SEP + TODOContract.Todo.Columns.TITLE + SPACE +
            "TEXT NOT NULL" + COMMA_SEP + TODOContract.Todo.Columns.DESCRIPTION + SPACE + "TEXT NOT NULL" +
            COMMA_SEP + TODOContract.Todo.Columns.DATE + SPACE + "TEXT NOT NULL" + COMMA_SEP + TODOContract.Todo.Columns.PRIORITY +
            SPACE + "INTEGER" + ")";

    /**
     * Drops the table
     */
    private static final String DROP_TABLE_TODO = "DROP TABLE IF EXISTS " + TODOContract.Todo
            .TABLE_NAME;
    private static final String TAG = "TODODBHelper";

    /**
     * Generates the database helper
     * @param context
     */
    public TODODBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, null, version);
    }

    /**
     * Creates the database
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CREATE_TABLE_TODO);
    }

    /**
     * Executed whenever the database is upgraded.
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        Log.v(TAG," Upgrading from version "+oldVersion + " to "+newVersion +" and will destroy all old data");
        // on upgrade drop older tables
        sqLiteDatabase.execSQL(DROP_TABLE_TODO);
        // create new tables
        onCreate(sqLiteDatabase);
    }

}
