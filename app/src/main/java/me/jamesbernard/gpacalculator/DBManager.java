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
                            {"id", "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE"},
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
                            {"meet_times", "INTEGER DEFAULT 1"},
                            {"grade_number", "INTEGER DEFAULT 0"},
                            {"grade_letter", "VARCHAR(2) DEFAULT 'F'"},
                            {"grade_point_average", "FLOAT DEFAULT 0.0"}
                    };
                    break;
                case "course_assignments":
                    columns = new String[][]{
                            {"id", "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE"},
                            {"course_id", "INTEGER"},
                            {"name", "TEXT"},
                            {"weight", "FLOAT"}
                    };
                    break;
                case "graded_asignments":
                    columns = new String[][]{
                            {"id", "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE"},
                            {"course_id", "INTEGER"},
                            {"assignment_type", "TEXT"},
                            {"title", "TEXT"},
                            {"grade_number", "INTEGER DEFAULT 0"},
                            {"grade_letter", "VARCHAR(2)"}
                    };
            }

            columnList.add(columns);
            // Column count, it will incriment based on columns for loop.
            int cCount = 1;
            // Start create table query which will get appended in the columns for loop
            String query = "CREATE TABLE " + table + "(";
            // This will be placed between each column
            String append = "";
            // Start columns for loop
            for(String[] column : columns){
                // If the columns length is more than one or column count is less than columns length
                if(columns.length > 1 && cCount < columns.length){
                    // Add a comma after the column and its settings
                    append = ",";
                    // Increase column count.
                    cCount++;
                }else append = ""; // Else return delimiter to null
                String columnName = column[0];
                String columnSettings = column[1];

                // Middle of query
                query += columnName  + " " + columnSettings + append;
            }
            // End of query
            query += ");";
            // Execute query.
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
