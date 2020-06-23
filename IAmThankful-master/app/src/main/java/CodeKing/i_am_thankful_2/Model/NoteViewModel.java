package CodeKing.i_am_thankful_2.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import CodeKing.i_am_thankful_2.Controller.Note;
import CodeKing.i_am_thankful_2.Controller.NoteRepository;

//AndriodViewModel is a subclass of ViewModel
public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    //never store content of activity or a view that reference a activity in the view model
    //**memory leak**
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();

    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void deleteAllNotes(){
        repository.deleteAllNote();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
