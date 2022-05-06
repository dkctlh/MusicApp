package DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.musicapp.PlaylistModel;

import java.util.ArrayList;

public class DBHelper2 extends SQLiteOpenHelper {

    public static final String DBNAME="musicapp2.db";

    public DBHelper2( Context context) {
        super(context, "musicapp2.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table playlists(playlist TEXT ,musicpath TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists playlists");
    }
    public Boolean insertData(String playlist,String musicpath){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("playlist",playlist);
        contentValues.put("musicpath",musicpath);

        long result=db.insert("playlists",null,contentValues);

        if (result==-1) return false;
        else return true;

    }
    public Boolean checkplaylist(String playlist){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from playlists where playlist = ?",new String[] {playlist});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    public Boolean checkmusicpath(String musicpath){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from playlists where musicpath = ?",new String[] {musicpath});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    public ArrayList<PlaylistModel>  readData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from playlists" ,null);

        ArrayList<PlaylistModel> playlistModelArrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                playlistModelArrayList.add(new PlaylistModel(cursor.getString(1),
                        cursor.getString(2)));

            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close();
        return playlistModelArrayList;
    }

    }



