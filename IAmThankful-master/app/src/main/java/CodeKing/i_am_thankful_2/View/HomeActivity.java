package CodeKing.i_am_thankful_2.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import CodeKing.i_am_thankful_2.Model.NoteViewModel;
import CodeKing.i_am_thankful_2.Controller.Note;
import CodeKing.i_am_thankful_2.R;

/**
 * <h1>I Am Thankful</h1>
 * <p>The is the starting class of the Apps and handle the user ui</p>
 *
 * @author kwokmoonho , Grace Udensi, Chase A. Switzer, Michael Wilkins
 * @version 1.0
 * @since 2019-06-27
 */

public class HomeActivity extends AppCompatActivity {

    /*member variables*/
    public static final int ADD_ITEMS_REQUEST = 1;
    public static final int REVIEW_REQUEST = 2;
    private NoteAdapter adapter;
    private NoteViewModel noteViewModel;
    private RecyclerView recyclerView;
    public static int daysCounter;
    private SharedPreferences sp;


    /**
     * This method is the onCreate activity for the main activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        int theme = sp.getInt("theme", R.style.AppTheme);
        setTheme(theme);
        setContentView(R.layout.activity_home);

        setTitle("I AM THANKFUL");

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        FloatingActionButton buttonAddItems = findViewById(R.id.button_add_items);
        buttonAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (HomeActivity.this, InputActivity.class);
                startActivityForResult(intent, ADD_ITEMS_REQUEST);
            }
        });

        buildRecyclerView();

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(HomeActivity.this, ReviewActivity.class);
                intent.putExtra(ReviewActivity.DAYS, note.getDays());
                intent.putExtra(ReviewActivity.ID, note.getId());
                intent.putExtra(ReviewActivity.INPUT_1, note.getTxt1());
                intent.putExtra(ReviewActivity.INPUT_2, note.getTxt2());
                intent.putExtra(ReviewActivity.INPUT_3, note.getTxt3());
                intent.putExtra(ReviewActivity.INPUT_4, note.getTxt4());
                intent.putExtra(ReviewActivity.INPUT_5, note.getTxt5());
                startActivityForResult(intent, REVIEW_REQUEST);
            }
        });


    }

    /**
     * Ths is override onActivityResult on the FragmentActivity class
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //check the request is correct
        if (requestCode == ADD_ITEMS_REQUEST && resultCode == RESULT_OK) {
            String input1 = data.getStringExtra(InputActivity.INPUT_1);
            String input2 = data.getStringExtra(InputActivity.INPUT_2);
            String input3 = data.getStringExtra(InputActivity.INPUT_3);
            String input4 = data.getStringExtra(InputActivity.INPUT_4);
            String input5 = data.getStringExtra(InputActivity.INPUT_5);
            int days = data.getIntExtra(InputActivity.DAYS, 0);
            Note note = new Note(days, input1, input2, input3, input4, input5);
            //add to the database
            noteViewModel.insert(note);
            Toast.makeText(this, "Items Saved", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all:
                noteViewModel.deleteAllNotes();
                Toast.makeText(this,"All items deleted", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.dark:
                applyTheme(R.style.AppTheme_DarkMode);
                Toast.makeText(this,"Dark Mode Activate", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.light:
                applyTheme(R.style.AppTheme_LightMode);
                Toast.makeText(this,"Light Mode Activate", Toast.LENGTH_SHORT).show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    /**
     *
     */
    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new NoteAdapter();
        //set the days counter
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        //observe is a live data method, when activity destroy, it will auto clean up the reference
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            //change when live obj data changed
            @Override
            public void onChanged(List<Note> notes) {
                //update RecyclerView
                adapter.setNotes(notes);
                setDaysCounter();
            }
        });
    }

    /**
     *
     */
    public void setDaysCounter(){
        this.daysCounter = adapter.getItemCount();
    }

    /**
     *
     * @param view
     */
    //onClick to the image
    public void removeItem(View view){
        Toast.makeText(this,"Items Removed", Toast.LENGTH_SHORT).show();
    }

    /**
     * ApplyTheme
     * @param theme
     */
    private void applyTheme(int theme) {
        // Write theme to share preferenes
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("theme", theme);
        editor.commit();
        // Restart the activity
        recreate();
    }

}
