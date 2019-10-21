package admin.example.demosql;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView listView;
    ArrayList<DeMo> deMoArrayList;
    DemoAdapter demoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);
        deMoArrayList=new ArrayList<>();
        demoAdapter=new DemoAdapter(this,deMoArrayList,R.layout.demo_sql);//:v ở đây phải dúng thứ tự đặt contrusstor
        listView.setAdapter(demoAdapter);

        database= new Database(this,"ghichu.sqlite",null,1);

        database.QueryData("CREATE TABLE  IF NOT EXISTS Demo(id Integer PRIMARY KEY AUTOINCREMENT, ten varchar(100))");
        //để insert test
//        database.QueryData("INSERT INTO Demo VALUES(null,'abc')");
        getDataDemo();
    }

    private void getDataDemo() {
        Cursor dataDemo=database.getData("SELECT *FROM Demo");
        deMoArrayList.clear();

        while (dataDemo.moveToNext()){
            String ten=dataDemo.getString(1);
            int id=dataDemo.getInt(0);
            deMoArrayList.add(new DeMo(id,ten));
        }
        demoAdapter.notifyDataSetChanged();

    }

    public  void deMoSua(String ten, final int id){
        final Dialog dialog= new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.demo_sua);
        final EditText edtTenSua=dialog.findViewById(R.id.edtSua);
        Button btnXacnhan=dialog.findViewById(R.id.btn_sua);
        edtTenSua.setText(ten);
        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi=edtTenSua.getText().toString().trim();
                database.QueryData("UPDATE Demo SET ten='"+tenMoi+"' WHERE id='"+id+"' ");
                Toast.makeText(MainActivity.this,"Sửa thành công",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                getDataDemo();
            }
        });
        dialog.show();

    }

    // để xử lý sự kiện trong menu bắt buộc phải dùng 2 hàm này

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add){
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }



    private void DialogThem() {
        final Dialog dialog= new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//không đặt thanh tiêu đề trên layout
        dialog.setContentView(R.layout.demo_them);
        final EditText edtten=dialog.findViewById(R.id.edt_them);
        Button btnThem=dialog.findViewById(R.id.btn_them);
        Button btnHuy=dialog.findViewById(R.id.btn_huy);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();// quay về ban đầu
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten=edtten.getText().toString();
                if(ten.equals("")){
                    Toast.makeText(MainActivity.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();

                }else{
                    //null vì ở đây là khóa tự động tăng
                    database.QueryData("INSERT INTO Demo VALUES(null,'"+ten+"')");
                    Toast.makeText(MainActivity.this,"Insert Success",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    getDataDemo();//khi insert xong phải gọi lại hàm lúc nãy
                }


            }
        });
        dialog.show();



    }
    public void deMoXoa(String ten, final int id){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa "+ ten+ " không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM Demo WHERE id='"+id+"'");
                Toast.makeText(MainActivity.this,"Xóa thành công",Toast.LENGTH_SHORT).show();
                getDataDemo();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }
}
