package bjfu.it.huangyichao.firesafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Type;

public class TopLevelActivity extends AppCompatActivity {
    public  static final  String EXTRA_TYPEID = "typeId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        final int typeId = getIntent().getIntExtra(EXTRA_TYPEID,0);
        //实现OnItemClickListener，点击时打开二级界面
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                        if(position == 0){
                            if(typeId == 1) {
                                Intent intent = new Intent(TopLevelActivity.this, FireNotifyActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(TopLevelActivity.this, "权限不足！", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(position == 1){
                            Intent intent = new Intent(TopLevelActivity.this,FireCategoryActivity.class);
                            startActivity(intent);
                        }
                    }
                };
        //为listView注册单击监听器
        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }
}
