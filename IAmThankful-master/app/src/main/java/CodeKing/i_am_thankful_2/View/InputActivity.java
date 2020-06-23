package CodeKing.i_am_thankful_2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import CodeKing.i_am_thankful_2.R;

public class InputActivity extends AppCompatActivity {
    public static final String INPUT_1 = "i_am_thankful_2.INPUT_1";
    public static final String INPUT_2 = "i_am_thankful_2.INPUT_2";
    public static final String INPUT_3 = "i_am_thankful_2.INPUT_3";
    public static final String INPUT_4 = "i_am_thankful_2.INPUT_4";
    public static final String INPUT_5 = "i_am_thankful_2.INPUT_5";
    public static final String DAYS = "i_am_thankful_2.DAYS";

    private EditText txt1;
    private EditText txt2;
    private EditText txt3;
    private EditText txt4;
    private EditText txt5;
    private SharedPreferences sp;
    private NumberPicker days;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(getClass().getName(), String.format("Received intent from Main Activity"));

        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        int theme = sp.getInt("theme", R.style.AppTheme);
        setTheme(theme);
        setContentView(R.layout.activity_input);

        txt1 = findViewById(R.id.entry1);
        txt2 = findViewById(R.id.entry2);
        txt3 = findViewById(R.id.entry3);
        txt4 = findViewById(R.id.entry4);
        txt5 = findViewById(R.id.entry5);
        days = findViewById(R.id.number_picker_days);
        days.setMaxValue(30);
        days.setMinValue(HomeActivity.daysCounter + 1);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Items");
    }

    /**
     *
     */
    private void saveNote() {
        String input1 = txt1.getText().toString();
        String input2 = txt2.getText().toString();
        String input3 = txt3.getText().toString();
        String input4 = txt4.getText().toString();
        String input5 = txt5.getText().toString();
        int inputDays = days.getValue();

        //avoid all the empty input
        if (input1.trim().isEmpty() || input2.trim().isEmpty() || input3.trim().isEmpty() || input4.trim().isEmpty() || input5.trim().isEmpty()) {
            Toast.makeText(this,"Please insert a things that you are thankful for", Toast.LENGTH_SHORT).show();
            return;
        }

        //send data back to the main activity
        Intent data = new Intent();
        data.putExtra(INPUT_1, input1);
        data.putExtra(INPUT_2, input2);
        data.putExtra(INPUT_3, input3);
        data.putExtra(INPUT_4, input4);
        data.putExtra(INPUT_5, input5);
        data.putExtra(DAYS, inputDays);

        setResult(RESULT_OK,data);
        finish();

    }

    /**
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        //true == wanna display the menue
        return true;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
