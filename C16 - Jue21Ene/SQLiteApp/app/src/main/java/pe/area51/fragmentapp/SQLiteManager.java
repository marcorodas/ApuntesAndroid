package pe.area51.fragmentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteManager extends SQLiteOpenHelper {

    //Patrón Singleton: Objeto único guardado
    private static SQLiteManager instance;

    private final static String DATABASE_NAME = "notes";
    private final static int DATABASE_VERSION = 1;

    public final static String TABLE_NOTES = "notes";
    public final static String TABLE_COLUMN_ID = "_id";
    public final static String TABLE_COLUMN_TITTLE = "title";
    public final static String TABLE_COLUMN_CONTENT = "content";
    public final static String TABLE_COLUMN_CREATION_TIMESTAMP = "creationTimestamp";

    //Patrón Singleton: Obtener Instancia
    public static SQLiteManager getInstance(final Context context){
        if (instance == null) {
            //Se pasa un context global, asociado a Application y no a un activity
            instance = new SQLiteManager(context.getApplicationContext());
        }
        return instance;
    }

    //Patrón Singleton: No instanciable
    private SQLiteManager(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String creationScript = "CREATE TABLE notes (_id INTEGER PRIMARY KEY, title TEXT, content TEXT, creationTimestamp INTEGER)";
        db.execSQL(creationScript);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        Este método no debería ejecutarse ya que nuestra base de datos
        está en su primera versión.
        */
    }

    public ArrayList<Note> getNotes() {
        ArrayList<Note> list = new ArrayList<>();
        final SQLiteDatabase database = getReadableDatabase();
        final String query = "SELECT * FROM notes";
        final Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex(TABLE_COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_TITTLE));
            String content = cursor.getString(cursor.getColumnIndex(TABLE_COLUMN_CONTENT));
            long creationTimestamp = cursor.getLong(cursor.getColumnIndex(TABLE_COLUMN_CREATION_TIMESTAMP));
            Note note = new Note(id,title,content,creationTimestamp);
            list.add(note);
        }
        cursor.close();
        return list;
    }

    public long insertNote(Note note){
        final SQLiteDatabase database = getWritableDatabase();
        final ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_COLUMN_TITTLE,note.getTitle());
        contentValues.put(TABLE_COLUMN_CONTENT,note.getContent());
        contentValues.put(TABLE_COLUMN_CREATION_TIMESTAMP,note.getCreationTimestamp());
        return database.insert(TABLE_NOTES,null,contentValues);
    }
}
