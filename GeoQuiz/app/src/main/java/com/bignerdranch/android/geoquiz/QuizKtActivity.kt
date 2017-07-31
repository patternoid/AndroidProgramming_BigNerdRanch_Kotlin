package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*


/**
 * Created by patternoid on 2017. 7. 25..
 */

class QuizKtActivity : AppCompatActivity()
{
    companion object {
        private val TAG : String = "QuizKtActivity"
        private val KEY_INDEX : String = "index"
        private val REQUEST_CODE_CHEAT : Int = 0
    }



    private var mQuestionBank : Array<Question> = arrayOf(
            Question(R.string.question_oceans,true),
            Question(R.string.question_africa,false),
            Question(R.string.question_americas,false),
            Question(R.string.question_asia,false),
            Question(R.string.question_mideast,false)
    )

    private var mCurrentIndex : Int? = 0
    private var mIsCheater : Boolean = false



    override fun onCreate( savedInstanceState : Bundle? )
    {
        super.onCreate( savedInstanceState )
        setContentView(R.layout.activity_quiz)

        Log.d( TAG, "onCreate(Bundle) called" )

        savedInstanceState?.apply{
            mCurrentIndex = getInt(KEY_INDEX, 0)
        }


        true_button!!.setOnClickListener { v ->
            checkAnswer(true)
        }

        false_button!!.setOnClickListener { v ->
            checkAnswer(false)
        }

        cheat_button!!.setOnClickListener { v ->
            val answerIsTrue : Boolean = mQuestionBank[mCurrentIndex!!].answerTrue
            val intent : Intent = CheatActivity.newIntent(this@QuizKtActivity, answerIsTrue)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }

        next_button!!.setOnClickListener { v ->
            mCurrentIndex = (mCurrentIndex!! + 1) % mQuestionBank.size
            updateQuestion()
        }

        updateQuestion()
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.i( TAG, "onSaveInstanceState")
        outState!!.putInt( KEY_INDEX, mCurrentIndex!! )
    }


    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }


    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onSTop() called")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }



    private fun updateQuestion(){
        val question :Int = mQuestionBank[mCurrentIndex!!].textResId
        question_text_view!!.setText(question)
    }



    private fun checkAnswer( userPressedTrue : Boolean ){

        val ansewerIsTrue   = mQuestionBank[mCurrentIndex!!].answerTrue
        var messageResId    = 0

        if( mIsCheater ){
            messageResId = R.string.judgement_toast
        }

        else{
            if( userPressedTrue == ansewerIsTrue ){
                messageResId = R.string.correct_toast
            } else {
                messageResId = R.string.incorrect_toast
            }

        }

        Toast.makeText(this@QuizKtActivity, messageResId, Toast.LENGTH_SHORT).show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if( resultCode != Activity.RESULT_OK )
            return

        if( requestCode == REQUEST_CODE_CHEAT){

            data?.let{
                mIsCheater = CheatActivity.wasAnswerShown(data)
            }
        }
    }
}