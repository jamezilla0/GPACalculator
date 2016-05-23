package me.jamesbernard.gpacalculator;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by james on 5/21/2016.
 */
public class DBManager extends SQLiteOpenHelper {
    private static final int dbVersion = 1;
    private static final String dbName = "bgc_temp.db";
    public static String[] tables = {"user", "courses", "course_assignments", "graded_assignments"};

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ArrayList<String[][]> columnList = new ArrayList<String[][]>();
        String[][] columns = new String[0][];

        for(String table : tables){
            switch (table){
                case "user":
                    columns = new String[][]{
                            {"id", "INTEGER PRIMARY KEY AUTOINCREMENT"},
                            {"name", "VARCHAR(150)"},
                            {"email", "VARCHAR(150)"}
                    };
                    break;
                case "courses":
                    columns = new String[][]{
                            {"id", "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE"},
                            {"year", "INTEGER DEFAULT 2016"},
                            {"semester", "STRING"},
                            {"user_id", "INTEGER"},
                            {"name", "TEXT"},
                            {"professor", "TEXT"},
                            {"credit_hours", "INTEGER"},
                            {"meet_times", "INTEGER DEFAULT 0"},
                            {"grade_number", "INTEGER DEFAULT 0"},
                            {"grade_letter", "VARCHAR(2) DEFAULT 'F'"},
                            {"grade_point_average", "FLOAT DEFAULT 0.0"}
                    };
                    break;
                case "course_assignments":
                    columns = new String[][]{
                            {"id", "INTEGER PRIMARY KEY AUTOINCREMENT"},
                            {"course_id", "INTEGER DEFAULT 0"},
                            {"name", "TEXT"},
                            {"weight", "FLOAT"}
                    };
                    break;
                case "graded_asignments":
                    columns = new String[][]{
                            {"id", "INTEGER PRIMARY KEY AUTOINCREMENT"},
                            {"course_id", "INTEGER DEFAULT 0"},
                            {"assignment_type", "TEXT"},
                            {"title", "TEXT"},
                            {"grade_number", "INTEGER DEFAULT 0"},
                            {"grade_letter", "VARCHAR(2)"}
                    };
            }

            columnList.add(columns);
            int cCount = 1;
            String query = "CREATE TABLE " + table + "(";
            String append = "";
            for(String[] column : columns){

                if(columns.length > 1 && cCount < columns.length){
                    append = ",";
                    cCount++;
                }else append = "";

                query += "" + column[0] + " " + column[1] + append;
            }
            query += ");";

            db.execSQL(query);
        }
    }

    public void logDatabase(String query, String[] params){
        SQLiteDatabase db = getDB();
        Cursor c =  db.rawQuery(query,params);
        c.moveToFirst();
        String header = "";
        Log.i("Log DB Query", query);

        while(!c.isAfterLast()){
            String[] columnNames = c.getColumnNames();
            String row = "";
            for (String column : columnNames){
                if(c.isFirst()){
                    header += column + " ";
                }
                row += c.getString(c.getColumnIndex(column)) + " ";
            }

            if(c.isFirst()) Log.i("header", header);
            Log.i("row", row);

            c.moveToNext();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String table : tables){
            db.execSQL("DROP_TABLE_IF_EXIST " + table);
        }
        onCreate(db);
    }

    public SQLiteDatabase getDB(){
        SQLiteDatabase db = getWritableDatabase();
        return db;
    }
}
