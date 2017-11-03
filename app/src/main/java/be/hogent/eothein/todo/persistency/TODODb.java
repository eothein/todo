package be.hogent.eothein.todo.persistency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by eothein on 03.11.17.
 */

public class TODODb {

    private static final String TAG = "MyDB.class";
    private SQLiteDatabase db;
    private final Context context;
    private final TODODBHelper dbHelper;





    private static final String DROP_TABLE_TODO = "DROP TABLE IF EXISTS " + TODOContract.Todo
            .TABLE_NAME;

    /**
     * Initialises this DB. Make sure the dbHelper object is also initialised.
     * @param context
     */
    public TODODb(Context context) {
        this.context = context;
        dbHelper = new TODODBHelper(context,TODOContract.DATABASE_NAME,null,TODOContract.DATABASE_VERSION);
    }

    /**
     * Closes the database
     */
    public void close(){
        db.close();
    }

    /**
     * Tries to open a writable database. If this is not possible, open a readable database.
     * Log the necessary messages for debugging purposes.
     */
    public void open(){
        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            Log.e(TAG,"Could not create a writeable database. Readable database has been opened");
            Log.e(TAG,e.getMessage());
            db = dbHelper.getReadableDatabase();
        }
    }

    /**
     * Returns one TODO
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    public Cursor getTodo(String[] projection, String selection, String[] selectionArgs, String sortOrder){
        return null;
    }

    /**
     * Returns a set of TODO's
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    public Cursor getTodos(String[] projection, String selection, String[] selectionArgs, String sortOrder){
        return null;
    }

    public int insert(ContentValues values){
        return 1;
    }

    public int delete(String selection, String[] selectionArgs){
        return 10;
    }

    public int update(ContentValues values, String selection, String[]
            selectionArgs){
        return 10;
    }

}
