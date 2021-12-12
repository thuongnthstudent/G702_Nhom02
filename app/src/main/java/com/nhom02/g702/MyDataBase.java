package com.nhom02.g702;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class MyDataBase extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="book.sqlite";

    public static final String TBL_NAME="Book";

    private static final String COL_W_ID= "W_Id";
    private static final String COL_W_TEN= "W_Ten";
    private static final String COL_W_NHAXB= "W_NhaXB";
    private static final String COL_W_SOLAN= "W_Solan";
    private static final String COL_W_GIA= "W_Gia";
    private static final String COL_W_ANH= "W_Anh";


    public MyDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE IF NOT EXISTS "+ TBL_NAME+"("+COL_W_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_W_TEN+" VARCHAR(100), "+ COL_W_NHAXB +" VARCHAR(200), "+ COL_W_SOLAN +" VARCHAR(100), "
                + COL_W_GIA +" VARCHAR(200), "+ COL_W_ANH+ " BLOB)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TBL_NAME);
        onCreate(sqLiteDatabase);
    }

    public void queryExec(String sql){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql);
    }

    public Cursor getData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    public boolean insertData(String name, String des, byte[] photo) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + TBL_NAME + " VALUES(null, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,id);
            statement.bindString(2, ten);
            statement.bindString(3, nhaxb);
            statement.bindString(4, solan);
            statement.bindDouble(5, gia);
            statement.bindBlob(6, anh);
            statement.executeInsert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
