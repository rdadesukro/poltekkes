//package com.example.poltekkes.quizapp;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toolbar;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.poltekkes.R;
//
//import java.util.Collections;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    //put question id into list
//    List<Question> questionList;
//    int quid = 0;
//    Question currentQuestion;
//    TextView txtQuestion;
//    RadioButton rda,rdb,rdc;
//    Button butNext;
//    public static String result="highscore";
//    static  int score = 0;
//    TextView scoreno;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        //setSupportActionBar(toolbar);
//
//        scoreno=(TextView)findViewById(R.id.score);
//
//
//        //get all question from db
//        DbHelper dbHelper = new DbHelper(this);
//        questionList = dbHelper.getAllQuestions();
//
//        //random question
//        Collections.shuffle(questionList);
//        currentQuestion = questionList.get(quid);
//
//        txtQuestion = (TextView)findViewById(R.id.question);
//        rda = (RadioButton)findViewById(R.id.radio0);
//        rdb = (RadioButton)findViewById(R.id.radio1);
//        rdc = (RadioButton)findViewById(R.id.radio2);
//        butNext = (Button)findViewById(R.id.button1);
//        setQuestionView();
//
//    }
//
//    private void setQuestionView(){
//        txtQuestion.setText(currentQuestion.getQuestion());
//        rda.setText(currentQuestion.getOptA());
//        rdb.setText(currentQuestion.getOptB());
//        rdc.setText(currentQuestion.getOptC());
//        quid++;
//    }
//
//    public void btClick(View view){
//        RadioGroup grp = (RadioGroup)findViewById(R.id.radioGroup1);
//        RadioButton answer = (RadioButton)findViewById(grp.getCheckedRadioButtonId());
//        if(currentQuestion.getAnswer().equals(answer.getText())){
//            score++;
//            Log.d("Score", "Your score: "+score);
//            scoreno.setText(" "+score);
//        }
//
//        if(quid<5){
//            currentQuestion = questionList.get(quid);
//            setQuestionView();
//        }else{
//
//
//            final SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
//            final SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt(result,score);
//            editor.commit();
//
//            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//            startActivity(intent);
//            finish();
//        }
//
//
//    }
//
//
//    public void highscore(View v){
//        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//        startActivity(intent);
//        finish();
//    }
////
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.menu_main, menu);
////        return true;
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        // Handle action bar item clicks here. The action bar will
////        // automatically handle clicks on the Home/Up button, so long
////        // as you specify a parent activity in AndroidManifest.xml.
////        int id = item.getItemId();
////
////        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
////
////        return super.onOptionsItemSelected(item);
////    }
//
//
//
//}
