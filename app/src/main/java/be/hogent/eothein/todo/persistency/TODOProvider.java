package be.hogent.eothein.todo.persistency;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by eothein on 03.11.17.
 */

public class TODOProvider extends ContentProvider {

    private static final int TODO_TABLE_ID = 1;
    private static final int TODO_ROW_ID = 2;
    private TODODBHelper mDbHelper;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private TODODb dba;

    static
    {
        sUriMatcher.addURI(TODOContract.CONTENT_AUTHORITY, TODOContract.Todo.TABLE_NAME,
                TODO_TABLE_ID);
        sUriMatcher.addURI(TODOContract.CONTENT_AUTHORITY, TODOContract.Todo.TABLE_NAME + "/#",
                TODO_ROW_ID);
    }

    @Override
    public boolean onCreate() {
        dba = new TODODb(this.getContext());
        dba.open();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c = null;
        switch(sUriMatcher.match(uri)){
            case TODO_ROW_ID:
                c = dba.getTodo(projection,  selection,  selectionArgs, sortOrder);
                break;
            case TODO_TABLE_ID:
                c = dba.getTodos( projection,  selection,  selectionArgs, sortOrder);
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues)
    {

        long _id;
        Uri returnUri;

        if (sUriMatcher.match(uri) == TODO_TABLE_ID)
        {
            _id = dba.insert(contentValues);
            if (_id > 0)
            {
                returnUri = TODOContract.Todo.buildRowUri(_id);
            }
            else
            {
                throw new UnsupportedOperationException("Unable to insert rows into: " + uri);
            }
        }
        else
        {
            throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }


    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs)
    {

        int rows; // Number of rows effected
        if (sUriMatcher.match(uri) == TODO_TABLE_ID)
        {
            rows = dba.delete( selection, selectionArgs);
        }
        else
        {
            throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Because null could delete all rows:
        if (selection == null || rows != 0)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rows;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[]
            selectionArgs)
    {

        int rows = 0;
        if (sUriMatcher.match(uri) == TODO_TABLE_ID)
        {
            rows = dba.update(values, selection, selectionArgs);
        }
        else
        {
            throwUnsupportedUriException(uri);
        }
        if (rows != 0)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rows;
    }

    private void throwUnsupportedUriException(Uri uri)
    {
        throw new UnsupportedOperationException("Unknown uri: " + uri);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }


}
