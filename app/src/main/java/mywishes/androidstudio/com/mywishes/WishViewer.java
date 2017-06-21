package mywishes.androidstudio.com.mywishes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import data.DatabaseHandler;

public class WishViewer extends AppCompatActivity {

    private TextView title,date,content;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_viewer);

        title = (TextView) findViewById(R.id.textViewTitleId);
        date = (TextView) findViewById(R.id.textViewDateId);
        content = (TextView) findViewById(R.id.textViewContentId);
        deleteButton = (Button) findViewById(R.id.buttonDeleteId);

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            title.setText(extras.getString("title"));
            date.setText("Created: " + extras.getString("date"));
            content.setText(" \" " + extras.getString("content") + " \" ");

            final int id = extras.getInt("id");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHandler dba = new DatabaseHandler(getApplicationContext());
                    dba.deleteWish(id);

                    Toast.makeText(getApplicationContext(), "Wish was deleted!", Toast.LENGTH_LONG);

                    startActivity(new Intent(WishViewer.this, WishList.class));
                }
            });

        }
    }
}
