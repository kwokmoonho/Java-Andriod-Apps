package CodeKing.i_am_thankful_2.Controller;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 *
 */
@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    /**
     * @return the info from database
     */
    //to access the database
    public abstract NoteDao noteDao();

    /**
     * @param context
     * @return
     */
    //only one thread at a time
    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            //we cannot call new, since it's abstract, so we use the builder
            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class, "note_database").fallbackToDestructiveMigration().build();
        }
        //if we already have something in the database, then just return
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };


}
