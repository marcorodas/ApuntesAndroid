package pe.area51.fragmentapp;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alumno on 19/01/16.
 */
public class SQLiteManager extends SQLiteOpenHelper{

    private final static String DATABASE_NAME = "notes";
    private final static int DATABASE_VERSION = 1;

    public SQLiteManager(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String creationScript = "CREATE TABLE notes(_id INTEGER PRIMARY KEY , title TEXT, content TEXT, creationTimestamp INTEGER)";
        db.execSQL(creationScript);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se usa cuando se actualiza la app y la nueva version tiene otro modelo de datos.
        //Salva la informacion de la estructura anterior hacia la nueva
    }
}
