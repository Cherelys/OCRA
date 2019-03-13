package com.ocra.dwarbi.ocra.Database;

import android.bluetooth.BluetoothClass;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.ocra.dwarbi.ocra.Entities.User;
import com.ocra.dwarbi.ocra.Formulier;

import static android.os.Build.ID;
import static android.provider.Telephony.Carriers.PASSWORD;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="ocra.db";
    private static final String TABLE_USER ="registeruser";
    public static final String ID ="ID";
    public static final String USERNAME ="username";
    public static final String PASSWORD ="password";

    private static final String CREATE_USER_TABLE = "CREATE TABLE "+TABLE_USER+" (id integer primary key autoincrement not null ,"  +
            " username string not null, password string not null)";

    //schema for formulier table
    private static final String TABLE_FORMULIER = "for_table";
    private static final String PHONE = "phone";
    private static final String BIRTHDAY = "birthday";
    public static final String GENDER = "gender";
    public static final String BIRTHPLACE = "birthplace";
    public static final String EMAIL = "email";
    public static final String PREVIOUS_EDUCATION = "previouseducation";
    private static final String MAJOR = "major";

    private static final String CREATE_FORMULIER_TABLE = "CREATE TABLE "+TABLE_FORMULIER+" (id integer primary key not null ,"  +
            " phone string , birthday string , gender string "+
            ", birthplace string , email string, previouseducation text,major string, user_id integer,"+
            "FOREIGN KEY (user_id) REFERENCES "+TABLE_USER+"(id)); ";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FORMULIER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);

    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { ID };
        SQLiteDatabase db = getReadableDatabase();
        String selection = USERNAME + "=?" + " and " + PASSWORD + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_USER,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

    public User getUserByName(String username){

        User user=null;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(TABLE_USER,null,USERNAME+" = ?",new String[]{""+username},null,null,null);
        if (cursor.moveToFirst()){

            String email=cursor.getString(cursor.getColumnIndex(EMAIL));
            String password=cursor.getString(cursor.getColumnIndex(PASSWORD));
            int id = cursor.getInt(cursor.getColumnIndex(ID));

            user=new User(id,username,email,password);

        }

        cursor.close();
        db.close();

        return user;
    }


    public boolean insertPVE(ContentValues contentValues){

        SQLiteDatabase db=getWritableDatabase();
        return db.insert(TABLE_FORMULIER,null,contentValues)>0;
    }

    public Formulier getFormulierByUserID(int userID){

        Formulier formulier =null;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor = db.query(TABLE_FORMULIER, null,
                "user_id = ?", new String[]{""+userID},null,null,null);
        if (cursor.moveToFirst()){

            String phone=cursor.getString(cursor.getColumnIndex(PHONE));
            String birthday=cursor.getString(cursor.getColumnIndex(BIRTHDAY));
            String birthplace=cursor.getString(cursor.getColumnIndex(BIRTHPLACE));
            String email=cursor.getString(cursor.getColumnIndex(EMAIL));
            String previous_education=cursor.getString(cursor.getColumnIndex(PREVIOUS_EDUCATION));
            double major=cursor.getDouble(cursor.getColumnIndex(MAJOR));

            new Formulier(phone, birthday, birthplace, email, previous_education, major);

        }

        cursor.close();
        db.close();

        return formulier;
    }

}
