package com.lappungdev.relationshipmeter.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lappungdev.relationshipmeter.model.Question;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    //DB version, table and database name
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "quizdb";
    private static final String DB_TABLE = "quiztable";

    //table column names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_PAIRQUES = "pair_question";

    private SQLiteDatabase dbase;
    private int rowCount = 0;

    public DbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sqlQuery = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)", DB_TABLE, KEY_ID, KEY_QUES, KEY_PAIRQUES);
        db.execSQL(sqlQuery);
        addQuestions();
    }

    private void addQuestions() {
        String coupleQ[][] = {
                {"Siapa nama lengkapmu?","Siapa nama lengkap pasanganmu?"},
                {"Siapa nama lengkap pasanganmu?","Siapa nama lengkapmu?"},
                {"Kapan tanggal jadian kalian?","Kapan tanggal jadian kalian?"},
                {"Di mana kalian pertama kali bertemu?","Di mana kalian pertama kali bertemu?"}
        };
        for (String q[] : coupleQ) {
            this.addQuestionToDB(new Question(q[0], q[1]));
        }
    }

    private void addQuestionToDB(Question q){
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, q.getQuestion());
        dbase.insert(DB_TABLE, null, values);
    }

    public List<Question> getAllQuestions(){
        List <Question> questionList = new ArrayList<Question>();

        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DB_TABLE;
        Cursor cursor = dbase.rawQuery(selectQuery,null);
        rowCount = cursor.getCount();

        if(cursor.moveToFirst()){
            do{
                Question q = new Question();
                q.setId(cursor.getInt(0));
                q.setQuestion(cursor.getString(1));
                q.setPairQuestion(cursor.getString(2));
                questionList.add(q);
            }while (cursor.moveToNext());
        }
        return questionList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);
        onCreate(db);
    }

    public int getRowCount(){
        return rowCount;
    }

}