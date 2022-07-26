package com.example.registration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.registration.model.QuizItem;
import com.example.registration.utility.JsonUtilities;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class QuizFragment extends Fragment {
    private TextView mOptions, mQNo,mResult, mQuestion;
    private Button optA, optB, optC, optD, btnRestart;
    private static int cnt=0, i=0;
    private static QuizItem currentQues;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_quiz, container, false);

        mOptions = view.findViewById(R.id.textView_options);
        mQuestion = view.findViewById(R.id.question);
        mQNo = view.findViewById(R.id.questionNo);
        optA = view.findViewById(R.id.optA);
        optB = view.findViewById(R.id.optB);
        optC = view.findViewById(R.id.optC);
        optD = view.findViewById(R.id.optD);
        mResult = view.findViewById(R.id.textViewResult);
        btnRestart = view.findViewById(R.id.button);

        ArrayList<QuizItem> listQuestions = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(JsonUtilities.loadJSONFromAsset(getActivity().getAssets().open("question_set.json")));
            JSONArray m_jArry = obj.getJSONArray("questionnaires");


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jsonObject = m_jArry.getJSONObject(i);
                String ques= jsonObject.getString("question");
                ArrayList<String> stringList = new ArrayList<>();
                JSONArray listOfString = jsonObject.getJSONArray("answers");
                for(int j=0;j<listOfString.length();j++)
                    stringList.add(listOfString.getString(j));
                int correctAnswer = jsonObject.getInt("correct_answer");
                Log.d("COrrect kya hai ", Integer.toString(correctAnswer));

                listQuestions.add(new QuizItem(ques, stringList, correctAnswer));
            }
            Log.v("Mera app", "yaha tak thik hai ");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
            Log.v("MEra Application ", "Error aaya hai ");
        }
        int total = listQuestions.size();
        i=0;
        currentQues = listQuestions.get(i);
        mQuestion.setText(currentQues.getQuestion());
        mQNo.setText(Integer.toString(i+1));
        mOptions.setText("");
        ArrayList<String> options = currentQues.getAnswers();
        for(int j=0;j<options.size();j++){
            mOptions.append(options.get(j));
            mOptions.append(" \n");
        }
            optA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentQues.getCorrectAnswer()==1)
                       cnt++;
                    i++;
                    if(i>=total) {
                        quizCompleted(total);
                        return;
                    }
                    currentQues=listQuestions.get(i);
                    mQuestion.setText(currentQues.getQuestion());
                    mQNo.setText(Integer.toString(i+1));
                    mOptions.setText("");
                    ArrayList<String> options = currentQues.getAnswers();
                    for(int j=0;j<options.size();j++){
                        mOptions.append(options.get(j));
                        mOptions.append(" \n");
                    }
                }
            });
            optB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentQues.getCorrectAnswer()==2)
                        cnt++;
                    i++;
                    if(i>=total) {
                        quizCompleted(total);
                        return;
                    }
                    currentQues=listQuestions.get(i);
                    mQuestion.setText(currentQues.getQuestion());
                    mQNo.setText(Integer.toString(i+1));
                    mOptions.setText("");
                    ArrayList<String> options = currentQues.getAnswers();
                    for(int j=0;j<options.size();j++){
                        mOptions.append(options.get(j));
                        mOptions.append(" \n");
                    }
                }
            });
            optC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentQues.getCorrectAnswer()==3)
                        cnt++;
                    i++;
                    if(i>=total) {
                        quizCompleted(total);
                        return;
                    }
                    currentQues=listQuestions.get(i);
                    mQuestion.setText(currentQues.getQuestion());
                    mQNo.setText(Integer.toString(i+1));
                    mOptions.setText("");
                    ArrayList<String> options = currentQues.getAnswers();
                    for(int j=0;j<options.size();j++){
                        mOptions.append(options.get(j));
                        mOptions.append(" \n");
                    }
                }
            });
            optD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentQues.getCorrectAnswer()==4)
                        cnt++;
                    i++;
                    if(i>=total)
                    {
                        quizCompleted(total);
                        return;
                    }
                    currentQues=listQuestions.get(i);
                    mQuestion.setText(currentQues.getQuestion());
                    mQNo.setText(Integer.toString(i+1));
                    mOptions.setText("");
                    ArrayList<String> options = currentQues.getAnswers();
                    for(int j=0;j<options.size();j++){
                        mOptions.append(options.get(j));
                        mOptions.append(" \n");
                    }
                }
            });
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartQuiz();
                i=0;
                cnt=0;
                QuizItem currentQues = listQuestions.get(i);
                mQuestion.setText(currentQues.getQuestion());
                mQNo.setText(Integer.toString(i+1));
                mOptions.setText("");
                ArrayList<String> options = currentQues.getAnswers();
                for(int j=0;j<options.size();j++){
                    mOptions.append(options.get(j));
                    mOptions.append(" \n");
                }
            }
        });
        return view;
    }

    private void restartQuiz() {
        mQuestion.setVisibility(View.VISIBLE);
        mOptions.setVisibility(View.VISIBLE);
        mQNo.setVisibility(View.VISIBLE);
        optA.setVisibility(View.VISIBLE);
        optB.setVisibility(View.VISIBLE);
        optC.setVisibility(View.VISIBLE);
        optD.setVisibility(View.VISIBLE);
        mResult.setVisibility(View.GONE);
    }

    private void quizCompleted(int total) {
        mOptions.setVisibility(View.GONE);
        mQuestion.setVisibility(View.INVISIBLE);
        mQNo.setVisibility(View.GONE);
        optA.setVisibility(View.GONE);
        optB.setVisibility(View.GONE);
        optC.setVisibility(View.GONE);
        optD.setVisibility(View.GONE);
        mResult.setVisibility(View.VISIBLE);
        String result = "The Score is " + Integer.toString(cnt) + " out of " + String.valueOf(total) +".";
        mResult.setText(result);
        i=0;
    }

}