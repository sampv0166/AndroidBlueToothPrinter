package com.demo.ess;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public static Adapter adapter;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Upcoming Events");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper(this);
      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
          //  public void onClick(View view) {
            //    Intent intent = new Intent(MainActivity.this,AddData.class);
              //  startActivity(intent);
                //finish();
            //}
        //});
        adapter = new Adapter(this,databaseHelper.getAllDatas(),databaseHelper);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    public static void notifyAdapter(){
        adapter.notifyDataSetChanged();
    }
}