package bjfu.it.huangyichao.firesafety;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FireDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "fire.db";
    private static final int DB_VERSION = 1;

    public FireDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE FIRE (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "DESCRIPTION TEXT," +
                "IMAGE_RESOURCE_ID INTEGER,"
                +"FAVORITE NUMERIC);");
        insertFire(db,"AodaliyaFire","Fire is bad!",R.drawable.aodaliya);
        insertFire(db,"SichuanFire","Firefighter is brave!",R.drawable.sichuan);
        insertFire(db,"NeimengguFire","Hope we will be okay!",R.drawable.neimenggu);
        db.execSQL("CREATE TABLE LOGIN (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IDENTITY TEXT," +
                "PASSWORD TEXT," +
                "TYPE INTEGER);");
        insertUser(db,"root","123",1);
        insertUser(db,"hyc","123",2);
        insertUser(db,"new","123",3);
    }

    private static void insertFire(SQLiteDatabase db, String name, String description, int resourceId){
        ContentValues fireValues = new ContentValues();
        fireValues.put("NAME",name);
        fireValues.put("DESCRIPTION",description);
        fireValues.put("IMAGE_RESOURCE_ID",resourceId);
        long result = db.insert("FIRE",null,fireValues);
        Log.d("sqlite", "insert "+name+",id: "+result);
    }

    private static void insertUser(SQLiteDatabase db, String identity, String password, int type){
        ContentValues fireValues = new ContentValues();
        fireValues.put("IDENTITY",identity);
        fireValues.put("PASSWORD",password);
        fireValues.put("TYPE",type);
        long result = db.insert("LOGIN",null,fireValues);
        Log.d("sqlite", "insert "+identity+",id: "+result);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
