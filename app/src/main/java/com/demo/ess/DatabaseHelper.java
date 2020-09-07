package com.demo.ess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static int database_version = 1;
    public static final String DATABASE_NAME ="register.db";
    public static final String TABLE_NAME ="EventsRegister";
    public static final String COL_id ="ID";
    public static final String COL_phone ="PhoneNumber";
    public static final String COL_3 ="CustomerName";
    public static final String COL_4 ="EventDate";
    public static final String COL_5 ="EventLocation";
    public static final String COL_6 ="NumberOfPax";
    public static final String COL_7 ="Amount";
    public static final String COL_8 ="Remarks";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, database_version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_phone + " TEXT,"
                + COL_3 + " TEXT,"
                + COL_4 + " TEXT,"
                + COL_5 + " TEXT,"
                + COL_6 + " TEXT,"
                + COL_7 + " TEXT,"
                + COL_8 + " TEXT"
                + ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertData(String PhoneNumber,String CustomerName,String EventDate,String EventLocation,String NumberOfPax,
                           String Amount,String Remarks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_phone,PhoneNumber);
        cv.put(COL_3,CustomerName);
        cv.put(COL_4,EventDate);
        cv.put(COL_5,EventLocation);
        cv.put(COL_6,NumberOfPax);
        cv.put(COL_7,Amount);
        cv.put(COL_8,Remarks);

        long id = db.insert(TABLE_NAME,null, cv);
        db.close();
        return id;
    }

    public int updateData(Data data ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COL_phone,data.getPhoneNumber());
        cv.put(COL_3,data.getCustomerName());
        cv.put(COL_4,data.getEventDate());
        cv.put(COL_5,data.getEventLocation());
        cv.put(COL_6,data.getNumberOfPax());
        cv.put(COL_7,data.getAmount());
        cv.put(COL_8,data.getRemarks());

        // updating row
        return db.update(TABLE_NAME, cv, "id" + " = ?",
                new String[]{String.valueOf(data.getId())});
    }

    public Data getData(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COL_id, COL_phone, COL_3,COL_4,COL_5,COL_6,COL_7,COL_8},
                COL_id + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Data data = new Data(
                cursor.getInt(cursor.getColumnIndex(COL_id)),
                cursor.getString(cursor.getColumnIndex(COL_phone)),
                cursor.getString(cursor.getColumnIndex(COL_3)),
                cursor.getString(cursor.getColumnIndex(COL_4)),
                cursor.getString(cursor.getColumnIndex(COL_5)),
                cursor.getString(cursor.getColumnIndex(COL_6)),
                cursor.getString(cursor.getColumnIndex(COL_7)),
                cursor.getString(cursor.getColumnIndex(COL_8))
        );

        // close the db connection
        cursor.close();

        return data;
    }

    public Data getDetailsByPhoneNumber(long Number) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COL_id, COL_phone, COL_3,COL_4,COL_5,COL_6,COL_7,COL_8},
                COL_phone + "=?",
                new String[]{String.valueOf(Number)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        // prepare note object
        Data data = new Data(
                cursor.getInt(cursor.getColumnIndex(COL_id)),
                cursor.getString(cursor.getColumnIndex(COL_phone)),
                cursor.getString(cursor.getColumnIndex(COL_3)),
                cursor.getString(cursor.getColumnIndex(COL_4)),
                cursor.getString(cursor.getColumnIndex(COL_5)),
                cursor.getString(cursor.getColumnIndex(COL_6)),
                cursor.getString(cursor.getColumnIndex(COL_7)),
                cursor.getString(cursor.getColumnIndex(COL_8))
        );
        // close the db connection
        cursor.close();
        return data;
    }








    public List<Data> getAllDatas() {
        List<Data> Datas = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentdate = sdf.format(new Date());

        Calendar cldr = Calendar.getInstance();
        cldr.add(Calendar.DAY_OF_YEAR, +7);
        Date dte = cldr.getTime();
        String daysbefore = sdf.format(dte);
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " where Date(EventDate) between '"+ currentdate +"' AND '"+ daysbefore +"' ORDER BY   Date(EventDate) asc ";

     //   String sql = "select * from " + TABLE_NAME +" where Date(EventDate) between '"+ currentdate +"' AND '"+ daysbefore +"'  " +
           //     "       order by Date(EventDate) ASC ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Data data = new Data();
                data.setId(cursor.getInt(cursor.getColumnIndex(COL_id)));
                data.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COL_phone)));
                data.setCustomerName(cursor.getString(cursor.getColumnIndex(COL_3)));
                data.setEventDate(cursor.getString(cursor.getColumnIndex(COL_4)));
                data.setEventLocation(cursor.getString(cursor.getColumnIndex(COL_5)));
                data.setNumberOfPax(cursor.getString(cursor.getColumnIndex(COL_6)));
                data.setAmount(cursor.getString(cursor.getColumnIndex(COL_7)));
                data.setRemarks(cursor.getString(cursor.getColumnIndex(COL_8)));
                Datas.add(data);

                Log.d("my query", String.valueOf(Datas));
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        // return notes list
        return Datas;
    }

    public int getDataCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public String[] getLocation(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = getReadableDatabase().rawQuery("SELECT DISTINCT EventLocation FROM EventsRegister", null);
        cursor.moveToFirst();


        ArrayList<String> text = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            text.add(cursor.getString(cursor.getColumnIndex("EventLocation")));
            cursor.moveToNext();
        }
        cursor.close();
        return text.toArray(new String[text.size()]);
    }
    public String[]  getPhoneNumber(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = getReadableDatabase().rawQuery("SELECT DISTINCT PhoneNumber FROM EventsRegister", null);
        cursor.moveToFirst();


        ArrayList<String> text = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            text.add(cursor.getString(cursor.getColumnIndex("PhoneNumber")));
            cursor.moveToNext();
        }
        cursor.close();
        return text.toArray(new String[text.size()]);

    }




    public void deleteData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_id + " = ?",
                new String[]{String.valueOf(data.getId())});
        db.close();
    }
}