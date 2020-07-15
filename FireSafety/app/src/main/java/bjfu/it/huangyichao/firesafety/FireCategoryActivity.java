package bjfu.it.huangyichao.firesafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class FireCategoryActivity extends AppCompatActivity {

    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_category);

        ListView listFires = findViewById(R.id.list_fires);
        SQLiteOpenHelper fireDatabaseHelper = new FireDatabaseHelper(this);
        //try with resource 自动关闭database
        try(SQLiteDatabase db = fireDatabaseHelper.getReadableDatabase()){
            cursor = db.query("FIRE",
                    new String[]{"_id","NAME"},
                    null,null,null,
                    null,null
                    );
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    //Android内置布局：每行显示一个文本框
                    android.R.layout.simple_list_item_1,
                    //数据源
                    cursor,
                    //在ListView的文本框中显示NAME
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0
            );
            //指定适配器
            listFires.setAdapter(listAdapter);

        }catch(SQLiteException e){
            Log.e("sqlite",e.getMessage());
            Toast toast = Toast.makeText(this,"Databse unvaliable",Toast.LENGTH_SHORT);
            toast.show();
        }
        //Create listener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> listFires, View itemView, int position, long id) {
                        //Pass the fire the user clicks on to FireActivity
                        Intent intent = new Intent(FireCategoryActivity.this,FireActivity.class);
                        intent.putExtra(FireActivity.EXTRA_FIREID,(int)id);
                        startActivity(intent);
                    }
                };
        //Assign the listener to the list view
        listFires.setOnItemClickListener(itemClickListener);
    }
}
