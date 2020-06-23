package CodeKing.i_am_thankful_2.Controller;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao //one dao per entity, it's Data access object
public interface NoteDao {
    //we just define the return type and the method name

    /**
     * @param note
     */
    @Insert
    void insert(Note note);

    /**
     * Delete the note from the table
     */
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    /**
     * @return the notes saved.
     */
    //order the recyclerview display in descending order
    @Query("SELECT * FROM note_table ORDER BY days DESC")
    //as soon as any change in the table, the object will be updated and the activity will be notified
    LiveData<List<Note>> getAllNotes();

}
