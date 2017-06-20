package mywishes.androidstudio.com.mywishes;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        for (int i = 0; i < wishesFromDB.size(); i++) {

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

        //setup adapter
        wishAdapter = new WishAdapter(WishList.this,R.layout.wish_list_template, dbWishes);
        listView.setAdapter(wishAdapter);
        wishAdapter.notifyDataSetChanged();

    }

    private class WishAdapter extends ArrayAdapter<Wish> {

        Activity activity;
        int layoutResource;
        Wish wish;
        ArrayList<Wish> mData = new ArrayList<>();

        public WishAdapter(Activity act, int resource, ArrayList<Wish> data) {

            super(act, resource, data);

            activity = act;
            layoutResource = resource;
            mData = data;
            notifyDataSetChanged();


        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Wish getItem(int position) {
            return mData.get(position);
        }

        @Override
        public int getPosition(Wish item) {
            return super.getPosition(item);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            ViewHolder holder = null;

            if (row == null || (row.getTag()) == null) {

                LayoutInflater inflater = LayoutInflater.from(activity);

                row = inflater.inflate(layoutResource, null);
                holder = new ViewHolder();

                holder.mTitle = (TextView) row.findViewById(R.id.wishListTemplateTitleId);
                holder.mDate = (TextView) row.findViewById(R.id.wishListTemplateDateId);


                row.setTag(holder);

            } else {

                holder = (ViewHolder) row.getTag();
            }

            holder.myWish = getItem(position);

            holder.mTitle.setText(holder.myWish.getTitle());
            holder.mDate.setText(holder.myWish.getRecordDate());

            return row;
        }
    }

    class ViewHolder{

        Wish myWish;
        TextView mTitle;
        TextView mDate;
//        int mId;
//        TextView mContent;


    }
}