package mywishes.androidstudio.com.mywishes;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import data.DatabaseHandler;
import model.Wish;

public class WishList extends Activity {
    private DatabaseHandler dba;
    private ArrayList<Wish> dbWishes = new ArrayList<>();
    private WishAdapter wishAdapter;
    private ListView listView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        listView = (ListView) findViewById(R.id.listViewId);

        refreshData();



    }

    private void refreshData() {
        dbWishes.clear();
        dba = new DatabaseHandler(getApplicationContext());

        ArrayList<Wish> wishesFromDB = dba.getWishes();

        for (int i = 0; i < wishesFromDB.size(); i++){

            String title = wishesFromDB.get(i).getTitle();
            String dateText = wishesFromDB.get(i).getRecordDate();
            String content = wishesFromDB.get(i).getContent();
//            int mid = wishesFromDB.get(i).getItemId();


//            Log.v("IDs: " , String.valueOf(mid));

            Wish myWish = new Wish();
            myWish.setTitle(title);
            myWish.setContent(content);
            myWish.setRecordDate(dateText);
//            myWish.setItemId(mid);



            dbWishes.add(myWish);


        }
        dba.close();

    }

    private class WishAdapter {
    }
}
