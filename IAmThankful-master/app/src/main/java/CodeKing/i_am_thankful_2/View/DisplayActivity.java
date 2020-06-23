package CodeKing.i_am_thankful_2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import CodeKing.i_am_thankful_2.R;


public class DisplayActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PictureAdapter mPictureAdapter;
    private ArrayList<PictureItem> mPictureList;
    private RequestQueue mRequestQueue;
    private ReviewActivity act = new ReviewActivity();
    private String userInput;
    private final String key = "12840542-c75f85ae81a87f8aa262c7746";
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
        setContentView(R.layout.activity_input);
        setContentView(R.layout.activity_display);

        Intent intent2 = getIntent();
        userInput = intent2.getStringExtra(act.INPUTPART);

        mRecyclerView = findViewById(R.id.recycler_view_2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPictureList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    /**
     * display the pictures from the API
     */
    private void parseJSON(){
        String url = "https://pixabay.com/api/?key=" +
                key + "&q=" + userInput + "&safesearch=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            //here we get back the json object, then we can use the obj to get our data
            public void onResponse(JSONObject response) {
                //add new item to the list for every object in the json array
                //Passing Json data into the mPictureList
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i ++){
                        JSONObject hits = jsonArray.getJSONObject(i);
                        String imageUrl = hits.getString("webformatURL");

                        mPictureList.add(new PictureItem(imageUrl));
                    }
                    //pass the Json result into the adapter and set in the recycler view.
                    mPictureAdapter = new PictureAdapter(DisplayActivity.this, mPictureList);
                    mRecyclerView.setAdapter(mPictureAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }


}
