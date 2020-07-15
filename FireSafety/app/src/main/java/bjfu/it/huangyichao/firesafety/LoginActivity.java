package bjfu.it.huangyichao.firesafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SQLiteDatabase db;
    String identity;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onClick(View v){
        final EditText et1 = (EditText) findViewById(R.id.et1);
        final EditText et2 = (EditText) findViewById(R.id.et2);
/*        View bt = findViewById(R.id.bt1);
        View iv = findViewById(R.id.bt2);
        EditTextUtils.clearButtonListener(et1, bt);
        EditTextUtils.clearButtonListener(et2, iv);*/
        identity = et1.getText().toString();
        password = et2.getText().toString();
        //调用DBOpenHelper （qianbao.db是创建的数据库的名称）
        FireDatabaseHelper helper = new FireDatabaseHelper(LoginActivity.this);
        db = helper.getWritableDatabase();
        //根据画面上输入的账号/密码去数据库中进行查询（user_tb是表名）
        Cursor c = db.query("LOGIN",null,"IDENTITY=? and PASSWORD=?",new String[]{identity,password},null,null,null);
        //如果有查询到数据
        if(c!=null && c.getCount() >= 1){
            int type=3;
            if(c.moveToFirst()){
                type = c.getInt(3);
                //Toast.makeText(LoginActivity.this,"type = "+type, Toast.LENGTH_SHORT).show();
            }
            c.close();
            db.close();
            Intent i = new Intent(LoginActivity.this , TopLevelActivity.class);
            //启动
            i.putExtra(TopLevelActivity.EXTRA_TYPEID,type);
            startActivity(i);
            Toast.makeText(LoginActivity.this,"登录成功！", Toast.LENGTH_SHORT).show();
        }
        //如果没有查询到数据
        else{
            Toast.makeText(LoginActivity.this,"手机号或密码输入错误！", Toast.LENGTH_SHORT).show();
        }
/*                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件，在存放资源代码的文件夹下下，
                Intent i = new Intent(LoginActivity.this , Main2ActivityAdapterDemo.class);
                //启动
                startActivity(i);*/
    }

}
