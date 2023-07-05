package sg.edu.rp.c346.id22014114.songdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button insert, display;
    EditText title, singers, year;
    RadioGroup stars;
    ArrayList<String> songs;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.btnInsert);
        display = findViewById(R.id.btnDisplay);
        title = findViewById(R.id.title);
        singers = findViewById(R.id.singers);
        year = findViewById(R.id.year);
        stars = findViewById(R.id.stars);
        lv = findViewById(R.id.lv);

        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                String stTitle = String.valueOf(title.getText());
                String stSingers = String.valueOf(singers.getText());
                int iYear = Integer.valueOf(String.valueOf(year.getText()));
                int iStar = 0;

                int checkedRadioId = stars.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.star1){
                    iStar += 1;
                }
                else if (checkedRadioId == R.id.star2){
                    iStar += 2;
                }
                else if (checkedRadioId == R.id.star3){
                    iStar += 3;
                }
                else if (checkedRadioId == R.id.star4){
                    iStar += 4;
                }
                else if (checkedRadioId == R.id.star5){
                    iStar += 5;
                }
                // Insert a task
                db.insertSong(stTitle, stSingers, iYear,iStar);

                Toast toast = Toast.makeText(insert.getContext(), "Song added successfully", Toast.LENGTH_LONG);
                toast.show();
            }

        });

        display.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> dTitle = db.getSongTitle();
                ArrayList<String> dSingers = db.getSongSingers();
                ArrayList<String> dyear = db.getSongYear();
                ArrayList<String> dstars = db.getSongStars();
                db.close();

                String txt = "";
                songs = new ArrayList<>();
                for (int i = 0; i < dTitle.size(); i++) {
                    Log.d("Database Content", i +"\nSong Title: " + dTitle.get(i) + "\nSingers: " + dSingers.get(i) + "\nYear of Release: " + dyear.get(i)
                            + "\nStars: " + dstars.get(i));
                    txt = "\nSong Title: " + dTitle.get(i) + "\nSingers: " + dSingers.get(i) + "\nYear of Release: " + dyear.get(i)
                    + "\nStars: " + dstars.get(i);
                    songs.add(i,txt + "\n");
                }


                ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, songs);
                lv.setAdapter(adapter);
            }


        });
    }

}