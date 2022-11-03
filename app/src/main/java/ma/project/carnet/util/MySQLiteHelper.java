package ma.project.carnet.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "dukan";

    private static final String CREATE_TABLE_CLIENT = "create table client(" +
            "id INTEGER primary key autoincrement," +
            "nom TEXT," +
            "prenom TEXT," +
            "cin TEXT," +
            "telephone INTEGER)";
    private static final String CREATE_TABLE_PRODUIT = "create table produit(" +
            "id INTEGER primary key autoincrement," +
            "libelle TEXT," +
            "prix INTEGER," +
            "categorie_id INTEGER," +
            "FOREIGN KEY (categorie_id) REFERENCES categorie(id))";
    private static final String CREATE_TABLE_CATEGORIE = "create table categorie(" +
            "id INTEGER primary key autoincrement," +
            "designation TEXT)";
    private static final String CREATE_TABLE_CREDIT = "create table credit(" +
            "id INTEGER primary key autoincrement," +
            "prix FLOAT," +
            "client_id INTEGER NOT NULL," +
            "category_id INTEGER NOT NULL," +
            "product_id INTEGER NOT NULL,"+
            "date DATE,"+
            "etat STRING," +
            "FOREIGN KEY (client_id) REFERENCES client(id)," +
            "FOREIGN KEY (category_id) REFERENCES category(id)," +
            "FOREIGN KEY (product_id) REFERENCES product(id))";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIENT);
        db.execSQL(CREATE_TABLE_CATEGORIE);
        db.execSQL(CREATE_TABLE_PRODUIT);
        db.execSQL(CREATE_TABLE_CREDIT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists client");
        db.execSQL("DROP table if exists produit");
        db.execSQL("DROP table if exists categorie");
        db.execSQL("DROP table if exists credit");
        this.onCreate(db);
    }
}
