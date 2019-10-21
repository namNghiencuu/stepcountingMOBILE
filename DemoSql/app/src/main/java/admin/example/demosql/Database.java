package admin.example.demosql;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //ở đây là để thực hiện câu lệnh thêm sửa xóa;
    public  void QueryData(String Sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(Sql);
    }
    //cái này đê thực hiện select
    public Cursor getData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return  database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
