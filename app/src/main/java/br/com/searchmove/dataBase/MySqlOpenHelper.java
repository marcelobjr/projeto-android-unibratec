package br.com.searchmove.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marcelo.gomes.junior on 11/19/2017.
 */

public class MySqlOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME  = "filme.db";
    public static final int DB_VERSION = 1;

    public MySqlOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DatabaseConstants.TBTMDB+" ( "+
                DatabaseConstants._ID + " integer primary key autoincrement, " +
                DatabaseConstants.IDDB       + " integer, " +
                DatabaseConstants.POSTERPATCH       + " text, " +
                DatabaseConstants.ORIGINALTITLE     + " text, " +
                DatabaseConstants.RELEASEDATE       + " text, " +
                DatabaseConstants.ORIGINALLANGUAGE  + " text, " +
                DatabaseConstants.OVERVIEWER        + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
