package br.com.searchmove.dataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.searchmove.model.Result;

public class SearchMove {
    private static SearchMove instance;
    private Context context;
    private SQLiteDatabase db;

    private SearchMove(Context context) {
        this.context = context;
        MySqlOpenHelper mySqlOpenHelper = new MySqlOpenHelper(context);
        db = mySqlOpenHelper.getWritableDatabase();
    }
    public static synchronized SearchMove getInstance(Context context) {
        if (instance == null) {
            instance = new SearchMove(context);
        }
        return instance;
    }
    public long insertSearchMove(Result resultTmDb) {
        long result = -1;
        if (resultTmDb != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseConstants.IDDB, resultTmDb.getId());
            contentValues.put(DatabaseConstants.POSTERPATCH, resultTmDb.getPosterPath());
            contentValues.put(DatabaseConstants.ORIGINALTITLE, resultTmDb.getTitle());
            contentValues.put(DatabaseConstants.RELEASEDATE, resultTmDb.getReleaseDate());
            contentValues.put(DatabaseConstants.ORIGINALLANGUAGE, resultTmDb.getOriginalLanguage());
            contentValues.put(DatabaseConstants.OVERVIEWER, resultTmDb.getOverview());


            result = db.insert(DatabaseConstants.TBTMDB, null, contentValues);
        }
        return result;
    }
    public long deleteSearchMove(Result resultTmDb) {
        long result = -1;

        if (resultTmDb != null) {

            result = db.delete(DatabaseConstants.TBTMDB,DatabaseConstants.IDDB
                    + " = ? ",new String[]{resultTmDb.getId().toString()});
        }

        return result;
    }
    public Result getResult(Result resultTmDb){
        Result result = null;

        if (resultTmDb != null) {
            Cursor cursor = db.rawQuery("select * from " + DatabaseConstants.TBTMDB + " where " + DatabaseConstants.IDDB
                            + " = ? ", new String[]{resultTmDb.getId().toString()});
            if (cursor.moveToFirst()) {
                result = getSearchMoveFromCursor(cursor);
            }
        }
        return result;
    }
    public List<Result> getFavoriteSearchMove(){
        List<Result> resultList = new ArrayList<Result>();
        Cursor cursor = db.rawQuery("select * from "+ DatabaseConstants.TBTMDB, null);

        while (cursor.moveToNext()){
            resultList.add(getSearchMoveFromCursor(cursor));
        }
        return resultList;
    }
    public Result getSearchMoveFromCursor(Cursor cursor){
        Result result = new Result();
        if (cursor != null){
            result.setId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(DatabaseConstants.IDDB))));
            result.setBackdropPath(cursor.getString(cursor.getColumnIndex(DatabaseConstants.POSTERPATCH)));
            result.setOriginalTitle(cursor.getString(cursor.getColumnIndex(DatabaseConstants.ORIGINALTITLE)));
            result.setReleaseDate(cursor.getString(cursor.getColumnIndex(DatabaseConstants.RELEASEDATE)));
            result.setOriginalLanguage(cursor.getString(cursor.getColumnIndex(DatabaseConstants.ORIGINALLANGUAGE)));
            result.setOverview(cursor.getString(cursor.getColumnIndex(DatabaseConstants.OVERVIEWER)));
        }

        return  result;
    }
}
