package bjfu.it.huangyichao.firesafety;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FireActivity extends AppCompatActivity {

    public  static final  String EXTRA_FIREID = "fireId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);

        //Get the drink from the intent
        int fireId = getIntent().getIntExtra(EXTRA_FIREID,0);
        SQLiteOpenHelper fireDatabaseHelper = new FireDatabaseHelper(this);
        //try with resource 自动关闭database
        try(SQLiteDatabase db = fireDatabaseHelper.getReadableDatabase()){
            //TODO 读数据库
            Cursor cursor = db.query("FIRE",
                    new String[]{"NAME","DESCRIPTION","IMAGE_RESOURCE_ID"},
                    "_id=?",
                    new String[]{Integer.toString(fireId)},
                    null,null,null
                    );
            if(cursor.moveToFirst()){//导航至游标第一条记录
                //取出1-3列
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                //显示name
                TextView name = findViewById(R.id.name);
                name.setText(nameText);
                //显示description
                TextView description = findViewById(R.id.description);
                description.setText(descriptionText);
                //显示图片
                ImageView photo = findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
             }
        } catch(SQLiteException e){
            Log.e("sqlite",e.getMessage());
            Toast toast = Toast.makeText(this,"Database unvaliable",Toast.LENGTH_SHORT);
            toast.show();
        }
/*        Fire fire = Fire.fires[fireId];
        //显示火情图片
        ImageView photo = findViewById(R.id.photo);
        photo.setImageResource(fire.getImageSourceId());
        photo.setContentDescription(fire.getName());
        //显示地点名称
        TextView name = findViewById(R.id.name);
        name.setText(fire.getName());
        //显示火情描述
        TextView description = findViewById(R.id.description);
        description.setText(fire.getDescription());*/
    }
}
