package com.example.ai.swuplant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyFavoriteDatabaseHelper extends SQLiteOpenHelper {

    // 数据库名字
    private static final String DATABASE_NAME = "MyFavorite";
    // 表名
    private static final String TABLE_NAME = "my_favorite";
    // 列名
    private static final String PLANT_NAME = "plantName";
    // 列名
    private static final String IMAGE_ID = "imageId";

    private Context context;

    // sql语句
    private static final String MY_FAVORITE = "create table if not exists my_favorite ("+
                     "id integer primary key autoincrement,"+
                     "imageId integer,"+
                     "plantName varchar)";

    public MyFavoriteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MY_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insert(int imageId,String plantName){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(IMAGE_ID,imageId);
        cv.put(PLANT_NAME,plantName);
        database.insert(TABLE_NAME,null,cv);
    }

    public Cursor getAllData(){
        SQLiteDatabase database=getWritableDatabase();
        return database.query(TABLE_NAME,null,null,null,null,null,null);
    }


    public void deleteData(int imageId){
        SQLiteDatabase database=getWritableDatabase();
        database.delete(TABLE_NAME,"imageId = ?",new String[]{imageId+""});
    }

}
