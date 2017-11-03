package be.hogent.eothein.todo.persistency;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by eothein on 03.11.17.
 */

public class TODOContract {

    /**
     * A provider usually has a single authority, which serves as its Android-internal name.
     * To avoid conflicts with other providers, you should use Internet domain ownership
     * (in reverse) as the basis of your provider authority. Because this recommendation is
     * also true for Android package names, you can define your provider authority as an
     * extension of the name of the package containing the provider.
     */
    public static final String CONTENT_AUTHORITY = "be.hogent.eothein.todoapp.provider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String DATABASE_NAME = "be.hogent.eothein.todoapp.database";

    public static final int DATABASE_VERSION = 1;


    public static class Todo {
        // URI for the table
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath
                ("todo").build();


        // Table name
        public static final String TABLE_NAME = "todo";

        /**
         * Bsecolumns is a simple interface which adds two fields, String _ID and String _COUNT.
         * Using common names enables the Android platform (and developers as well) to address
         * any data item, regardless of its overall structure (i.e. other, non-ID columns)
         * in a unified way. Defining constants for commonly used strings in an interface/class
         * avoids repetition and typos all over the code.
         */

        public interface Columns extends BaseColumns {
            String TITLE = "title";
            String DESCRIPTION = "description";
            String DATE = "date";
            String PRIORITY = "priority";
        }


        /**
         * Builds a Uri for the row in the table
         *
         * @param id An id for the row
         * @return
         */
        public static Uri buildRowUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}
