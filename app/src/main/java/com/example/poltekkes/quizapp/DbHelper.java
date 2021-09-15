//package com.example.poltekkes.quizapp;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by JoseThomas on 3/31/2016.
// */
//
//public class DbHelper extends SQLiteOpenHelper {
//
//    //DB version, table and database name
//    private static final int DB_VERSION = 2;
//    private static final String DB_NAME = "quizdb";
//    private static final String DB_TABLE = "quiztable";
//
//    //table column names
//    private static final String KEY_ID = "id";
//    private static final String KEY_QUES = "question";
//    private static final String KEY_ANSWER = "answer";
//    private static final String KEY_OPTA = "optA";
//    private static final String KEY_OPTB = "optB";
//    private static final String KEY_OPTC = "optC";
//    private static final String KEY_EXP = "exp";
//
//    private SQLiteDatabase dbase;
//    private int rowCount = 0;
//
//    public DbHelper(Context context){
//        super(context,DB_NAME,null,DB_VERSION);
//        //  //3rd argument to be passed is CursorFactory instance
//    }
//
//    //create table
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        dbase = db;
//        String sqlQuery = "CREATE TABLE"+
//        DB_TABLE+"("+KEY_ID  + " INTEGER PRIMARY KEY," + KEY_QUES +" TEXT," + KEY_ANSWER+" TEXT," + KEY_OPTA +" TEXT," + KEY_OPTB+" TEXT," + KEY_OPTC +" TEXT," +KEY_EXP+"TEXT"+")";
//        Log.d("TaskDBHelper", "Query to form table" + sqlQuery);
//        db.execSQL(sqlQuery);
//        addQuestions();
//    }
//
//    private void addQuestions() {
//        Question q1 = new Question("Which company is the largest manufacturer of network equipment ?", "HP", "IBM", "CICSO", "CISCO","a");
//        addQuestionToDB(q1);
//        Question q2 = new Question("Which of the following is NOT an operating system ?", "Linux", "BIOS", "DOS", "BIOS","a");
//        addQuestionToDB(q2);
//        Question q3 = new Question("Who is the founder of Apple Inc. ?", "Jose Thomas", "Bill Gates", "Steve Jobs", "Steve Jobs","a");
//        addQuestionToDB(q3);
//        Question q4 = new Question("Who is the first cricketer to score an international double century in 50-over match ?", "Ricky Ponting", "Sachin Tendulkar", "Brian Lara", "Sachin Tendulkar","a");
//        addQuestionToDB(q4);
//        Question q5 = new Question("Which is the biggest largest city in the world ?", "Vienna", "Reno", "Delhi", "Reno","a");
//        addQuestionToDB(q5);
//        Question q6 = new Question("Which is the biggest desert in in the world ?", "Thar", "Sahara", "Mohave", "Sahara","a");
//        addQuestionToDB(q6);
//        Question q7 = new Question("Which is the the largest coffee growing country in the world ?", "Brazil", "India", "Myanmar", "Brazil","a");
//        addQuestionToDB(q7);
//        Question q8 = new Question("Which is the longest river in the world ?", "Ganga", "Amazon", "Nile", "Nile","a");
//        addQuestionToDB(q8);
//        Question q9 = new Question("Which country is known as the country of copper ?", "Somalia", "Cameroon", "Zambia", "Zambia","a");
//        addQuestionToDB(q9);
//        Question q10 = new Question("Which is the world's oldest known city ?", "Rome", "Damascus", "Jerusalem", "Damascus","a");
//        addQuestionToDB(q10);
//        Question q11 = new Question("Who is the first Prime minister of India ?", "Jawaharlal Nehru", "Dr.Rajendra Prasad", "Lal Bahadur Shasthri", "Jawaharlal Nehru","a");
//        addQuestionToDB(q11);
//        Question q12 = new Question("Which city is known as the city of canals ?", "Paris", "Venice", "London", "Venice","a");
//        addQuestionToDB(q12);
//        Question q13 = new Question("Australia was discovered by ?", "James Cook", "Columbus", "Magallan", "James Cook","a");
//        addQuestionToDB(q13);
//        Question q14 = new Question("The national flower of Britain is ?", "Lily", "Rose", "Lotus", "Rose","a");
//        addQuestionToDB(q14);
//        Question q15 = new Question("The red cross was founded by ?", "Gullivar Crossby", "Alexandra Maria Lara", "Jean Henri Durant", "Jean Henri Durant","a");
//        addQuestionToDB(q15);
//        Question q16 = new Question("Which place is known as the roof of the world ?", "Alphs", "Tibet", "Nepal", "Tibet","a");
//        addQuestionToDB(q16);
//        Question q17 = new Question("Who invented washing machine ?", "James King", "Alfred Vincor", "Christopher Marcossi", "James King","a");
//        addQuestionToDB(q17);
//        Question q18 = new Question("Who won the Football world Cup in 2014 ?", "Italy", "Argentina", "Germany", "Germany","a");
//        addQuestionToDB(q18);
//        Question q19 = new Question("Who won the Cricket World cup in 2011 ?", "Australia", "India", "England", "India","a");
//        addQuestionToDB(q19);
//        Question q20 = new Question("The number regarded as the lucky number in Italy is ?", "13", "7", "9", "13","a");
//        addQuestionToDB(q20);
//
//    }
//
//    //code to add new question
//    public void addQuestionToDB(Question q){
//        ContentValues values = new ContentValues();
//        values.put(KEY_QUES, q.getQuestion());
//        values.put(KEY_ANSWER,q.getAnswer());
//        values.put(KEY_OPTA,q.getOptA());
//        values.put(KEY_OPTB,q.getOptB());
//        values.put(KEY_OPTC,q.getOptC());
//        values.put(KEY_EXP,q.getExplanation());
//        //insert row
//        dbase.insert(DB_TABLE, null, values);
//    }
////get all question in listview
//    public List <Question> getAllQuestions(){
//        List <Question> questionList = new ArrayList<Question>();
//
//        dbase = getReadableDatabase();
//        String selectQuery = "SELECT * FROM "+DB_TABLE;
//        Cursor cursor = dbase.rawQuery(selectQuery,null);
//        rowCount = cursor.getCount();
//
//        if(cursor.moveToFirst()){
//            do{
//                Question q = new Question();
//                q.setId(cursor.getInt(0));
//                q.setQuestion(cursor.getString(1));
//                q.setAnswer(cursor.getString(2));
//                q.setOptA(cursor.getString(3));
//                q.setOptB(cursor.getString(4));
//                q.setOptC(cursor.getString(5));
//                q.setExplanation(cursor.getString(5));
//
//                //add question in list
//                questionList.add(q);
//
//                //loop all rows
//            }while (cursor.moveToNext());
//        }
//        return questionList;
//    }
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
//        onCreate(db);
//    }
//
//    public int getRowCount(){
//        return rowCount;
//    }
//}
