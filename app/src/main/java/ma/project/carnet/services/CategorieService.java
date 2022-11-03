package ma.project.carnet.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ma.project.carnet.classes.Categorie;
import ma.project.carnet.util.MySQLiteHelper;

public class CategorieService {
    private static final String TABLE_NAME ="categorie";
    private static final String KEY_ID = "id";
    private static final String KEY_NOM = "designation";
    private static String [] COLUMNS = {KEY_ID, KEY_NOM};
    private MySQLiteHelper helper;
    private static CategorieService categorieService;
    public static CategorieService getCategorieService(Context context){
        if(categorieService==null){
            categorieService = new CategorieService(context);
        }
        return categorieService;
    }
    private CategorieService(Context context) {
        this.helper = new MySQLiteHelper(context);

    }
    public void create(Categorie e){
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOM, e.getName());
        db.insert(TABLE_NAME,
                null,
                values);
        Log.d("insert", e.getName());
        db.close();
    }
    public void update(Categorie e){
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, e.getId());
        values.put(KEY_NOM, e.getName());
        db.update(TABLE_NAME,
                values,
                "id = ?",
                new String[]{e.getId()+""});
        db.close();
        Log.d("update", e.getName());
    }

    public Categorie findById(int id){
        Categorie e = null;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor c;
        c = db.query(TABLE_NAME,
                COLUMNS,
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(c.moveToFirst()){
            e = new Categorie();
            e.setId(c.getInt(c.getColumnIndexOrThrow("id")));
            e.setName(c.getString(c.getColumnIndexOrThrow("designation")));
        }
        db.close();
        Log.d("findById","update"+e.getName());
        return e;
    }

    public void delete(Categorie e){
        SQLiteDatabase db = this.helper.getWritableDatabase();
        db.delete(
                TABLE_NAME,
                "id = ?",
                new String[] {String.valueOf(e.getId())}
        );
        db.close();
    }

    public List<Categorie> findAll(){
        List<Categorie> eds = new ArrayList<>();
        String req ="select * from "+TABLE_NAME;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor c = db.rawQuery(req, null);
        Categorie e = null;
        if(c.moveToFirst()){
            do{
                e = new Categorie();
                e.setId(c.getInt(c.getColumnIndexOrThrow("id")));
                e.setName(c.getString(c.getColumnIndexOrThrow("designation")));
                eds.add(e);
                Log.d("id = ", e.getId()+"");
            }while(c.moveToNext());
        }
        return eds;
    }


}

