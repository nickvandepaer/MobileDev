package be.thomasmore.flinkspreken;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "databaseProject";

    // uitgevoerd bij instantiatie van DatabaseHelper
    // -> als database nog niet bestaat, dan creëren (call onCreate)
    // -> als de DATABASE_VERSION hoger is dan de huidige versie,
    //    dan upgraden (call onUpgrade)

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // methode wordt uitgevoerd als de database gecreëerd wordt
    // hierin de tables creëren en opvullen met gegevens
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_MINIMALEPAAR = "CREATE TABLE minimalePaar (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woord1 TEXT," +
                "woord2 TEXT, " +
                "doelKlank TEXT, " +
                "finaalInitiaal TEXT, " +
                "fonologischProces TEXT)";
        db.execSQL(CREATE_TABLE_MINIMALEPAAR);

        insertMinimalePaar(db);
    }

    private void insertMinimalePaar(SQLiteDatabase db) {

        //Fronting Finaal
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Bek', 'Bed', 'K-T', 'finaal', 'fronting');");
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Bak', 'Bad', 'K-T', 'finaal', 'fronting');");
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Nek', 'Net', 'K-T', 'finaal', 'fronting');");

        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Buig', 'Buis', 'G-S', 'finaal', 'fronting');");
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Leeg', 'Lees', 'G-S', 'finaal', 'fronting');");
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Dag', 'Das', 'G-S', 'finaal', 'fronting');");

        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Tong', 'Ton', 'NG-N', 'finaal', 'fronting');");
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Pang', 'Pan', 'NG-N', 'finaal', 'fronting');");

        //Fronting Initiaal
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Koe', 'Toe', 'K-T', 'initiaal', 'fronting');");
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Kou', 'Touw', 'K-T', 'initiaal', 'fronting');");
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Kam', 'Tam', 'K-T', 'initiaal', 'fronting');");

        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Goed', 'Voet', 'G-V/F/S', 'initiaal', 'fronting');");
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Guus', 'Suus', 'G-V/F/S', 'initiaal', 'fronting');");
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Goud', 'Fout', 'G-V/F/S', 'initiaal', 'fronting');");

        //Stopping Finaal
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Bos', 'Bot', 'S-T', 'finaal', 'stopping');");
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Boos', 'Boot', 'S-T', 'finaal', 'stopping');");

        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Pech', 'Pet', 'CH-T', 'finaal', 'stopping');");

        //Stopping Initiaal
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Gat', 'Kat', 'G-K', 'initiaal', 'stopping');");

        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Sok', 'Tok', 'S/Z-T', 'initiaal', 'stopping');");
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Zak', 'Tak', 'S/Z-T', 'initiaal', 'stopping');");

        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Fee', 'Thee', 'F-T', 'initiaal', 'stopping');");
        db.execSQL("INSERT INTO minimalePaar (woord1, woord2, doelKlank, finaalInitiaal, fonologischProces) VALUES ('Fien', 'Tien', 'F-T', 'initiaal', 'stopping');");
    }

    // methode wordt uitgevoerd als database geupgrade wordt
    // hierin de vorige tabellen wegdoen en opnieuw creëren
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS minimalePaar");
        // Create tables again
        onCreate(db);
    }

    //-------------------------------------------------------------------------------------------------
    //  CRUD Operations
    //-------------------------------------------------------------------------------------------------

    // query-methode
    public MinimalePaar getMinimalePaar(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "minimalePaar",      // tabelnaam
                new String[] { "id", "woor1", "woord2", "doelKlank", "finaalInitiaal", "fonologischProces"  }, // kolommen
                "id = ?",  // selectie
                new String[] { String.valueOf(id) }, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null);          // ??

        MinimalePaar minimalePaar = new MinimalePaar();

        if (cursor.moveToFirst()) {
            minimalePaar = new MinimalePaar(cursor.getLong(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        }
        cursor.close();
        db.close();
        return minimalePaar;
    }

    // rawQuery-methode
    public List<MinimalePaar> getMinimaleParen() {
        List<MinimalePaar> lijst = new ArrayList<MinimalePaar>();

        String selectQuery = "SELECT  * FROM minimalePaar";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                MinimalePaar minimalePaar = new MinimalePaar(cursor.getLong(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                lijst.add(minimalePaar);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    // rawQuery-methode
    public int getCountMinimaleParen() {
        String selectQuery = "SELECT  * FROM minimaleParen";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int aantal = cursor.getCount();

        cursor.close();
        db.close();
        return aantal;
    }

}

