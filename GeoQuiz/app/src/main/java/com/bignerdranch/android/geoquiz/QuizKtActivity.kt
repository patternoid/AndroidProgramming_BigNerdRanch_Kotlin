package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*


/**
 * Created by patternoid on 2017. 7. 25..
 */

class QuizKtActivity : AppCompatActivity()
{
    private var mQuestionBank : Array<Question> = arrayOf(
            Question(R.string.question_oceans,true),
            Question(R.string.question_africa,false),
            Question(R.string.question_americas,false),
            Question(R.string.question_asia,false),
            Question(R.string.question_mideast,false)
    )

    private var mCurrentIndex : Int = 0;


    override fun onCreate( savedInstanceState : Bundle? )
    {
        super.onCreate( savedInstanceState )
        setContentView(R.layout.activity_quiz)

        true_button!!.setOnClickListener { v ->
            checkAnswer(true)
        }

        false_button!!.setOnClickListener { v ->
            checkAnswer(false)
        }

        next_button!!.setOnClickListener { v ->
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            updateQuestion()
        }

        updateQuestion()
    }



    private fun updateQuestion(){
        val question :Int = mQuestionBank[mCurrentIndex].textResId
        question_text_view!!.setText(question)
    }



    private fun checkAnswer( userPressedTrue : Boolean ){

        val ansewerIsTrue   = mQuestionBank[mCurrentIndex].answerTrue
        var messageResId    = 0

        if( userPressedTrue == ansewerIsTrue ){
            messageResId = R.string.correct_toast
        } else {
            messageResId = R.string.incorrect_toast
        }

        Toast.makeText(this@QuizKtActivity, messageResId, Toast.LENGTH_SHORT).show()
    }
}