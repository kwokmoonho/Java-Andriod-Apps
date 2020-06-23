package CodeKing.i_am_thankful_2.Controller;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        //since I create the notedatabase instance, room subclassers out abstract class in the notedatabase class
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    /**
     * database operation method
     * @param note
     */
    public void insert (Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    /**
     *
     */
    public void deleteAllNote() {
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    /**
     * @return all notes
     */
    public LiveData<List<Note>> getAllNotes () {
        return allNotes;
    }


    /**
     * thread
     */
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        /**
         * constructor for NoteDao
         */
        private NoteDao noteDao;

        /**
         * @param noteDao
         */
        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        /**
         * @param notes
         * @return
         */
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }


    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;

        /**
         * @param noteDao
         */
        private DeleteAllNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        /**
         * @param voids
         * @return
         */
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
