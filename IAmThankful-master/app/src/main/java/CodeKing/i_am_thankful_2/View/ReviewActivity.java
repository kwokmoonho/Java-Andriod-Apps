package CodeKing.i_am_thankful_2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import CodeKing.i_am_thankful_2.R;
import CodeKing.i_am_thankful_2.View.DisplayActivity;

/**
 * implements the ReviewFragments listener and ReviewFragmentInterface
 */
public class ReviewActivity extends AppCompatActivity {

    /**
     * @param the input slot
     */
    public static final String INPUT_1 = "i_am_thankful_2.INPUT_1";
    public static final String INPUT_2 = "i_am_thankful_2.INPUT_2";
    public static final String INPUT_3 = "i_am_thankful_2.INPUT_3";
    public static final String INPUT_4 = "i_am_thankful_2.INPUT_4";
    public static final String INPUT_5 = "i_am_thankful_2.INPUT_5";
    public static final String DAYS = "i_am_thankful_2.DAYS";
    public static final String ID = "ITEM_ID";
    public static final String INPUTPART = "INPUT";

    /**
     * calls the id
     */
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private TextView txt5;
    private TextView days;
    private SharedPreferences sp;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        int theme = sp.getInt("theme", R.style.AppTheme);
        setTheme(theme);
        setContentView(R.layout.activity_review_);
        days = findViewById(R.id.textView);
        txt1 = findViewById(R.id.textView2);
        txt2 = findViewById(R.id.textView3);
        txt3 = findViewById(R.id.textView4);
        txt4 = findViewById(R.id.textView5);
        txt5 = findViewById(R.id.textView6);


        Intent intent = getIntent();
        if (intent.hasExtra(ID)){
            setTitle("Review List");
            days.setText(intent.getStringExtra(DAYS));
            txt1.setText(intent.getStringExtra(INPUT_1));
            txt2.setText(intent.getStringExtra(INPUT_2));
            txt3.setText(intent.getStringExtra(INPUT_3));
            txt4.setText(intent.getStringExtra(INPUT_4));
            txt5.setText(intent.getStringExtra(INPUT_5));
        }

    }

    /**
     * @param theButton
     */
    public void onSearch (View theButton) {
        EditText input = (EditText) findViewById(R.id.input);

        String data = input.getText().toString();

        Intent searchIntent = new Intent(this, DisplayActivity.class);
        searchIntent.putExtra(INPUTPART, data);

        startActivity(searchIntent);

    }


}
