package mywishes.androidstudio.com.mywishes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import data.DatabaseHandler;
import model.Wish;

public class MainActivity extends Activity {

    private EditText title;
    private EditText content;
    private Button saveButton;
    private DatabaseHandler dba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dba = new DatabaseHandler(MainActivity.this);

        title = (EditText) findViewById(R.id.editTextWishTitleId);
        content = (EditText) findViewById(R.id.editTextWishContentId);
        saveButton = (Button) findViewById(R.id.buttonSaveId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }
        });



    }

    private void saveToDB() {

        Wish wish = new Wish();
        wish.setTitle(title.getText().toString().trim());
        wish.setContent(content.getText().toString().trim());


        dba.addWishes(wish);
        dba.close();

        //clear
        title.setText("");
        content.setText("");

        Intent i = new Intent(MainActivity.this, WishList.class);
        startActivity(i);



    }
}
