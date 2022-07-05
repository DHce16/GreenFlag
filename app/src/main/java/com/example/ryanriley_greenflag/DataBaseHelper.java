package com.example.ryanriley_greenflag;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String ACCOUNT_TABLE = "ACCOUNT_TABLE";
    public static final String COLUMN_ACCOUNT_EMAIL = "ACCOUNT_EMAIL";
    public static final String COLUMN_ACCOUNT_PASSWORD = "ACCOUNT_PASSWORD";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "accounts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE "+ACCOUNT_TABLE+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_ACCOUNT_EMAIL+" TEXT, "+COLUMN_ACCOUNT_PASSWORD+" TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void addOne(AccountModel accountModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ACCOUNT_EMAIL, accountModel.getEmail());
        cv.put(COLUMN_ACCOUNT_PASSWORD,accountModel.getPassword());

        db.insert(ACCOUNT_TABLE, null, cv);

    }

    public AccountModel getAccount(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { "ACCOUNT_EMAIL", "ACCOUNT_PASSWORD"};

        String[] input = { email };

        AccountModel account;

        Cursor cursor = db.query(ACCOUNT_TABLE, columns, "ACCOUNT_EMAIL = ?", input,null,null, null);
        if (cursor.getCount() >0){
            cursor.moveToFirst();
            account = new AccountModel(cursor.getString(0), cursor.getString(1));
        }else {
            account = new AccountModel(null,null);
        }
        cursor.close();
        db.close();
        return account;
    }

    public boolean matches (String email){
        AccountModel accountModel = getAccount(email);
        Log.d(TAG, "matches: " + accountModel.getEmail());
        return accountModel.getEmail() != null;
    }
}
