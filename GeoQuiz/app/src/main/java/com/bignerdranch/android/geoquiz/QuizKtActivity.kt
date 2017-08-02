package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*



/*Challenge
  1. 사용자는 정답을 컨닝한 후에 커닝 결과를 지우기 위해 CheatActivity 화면에서
  장치를 회전 시킬 수 있다

  2. 커닝을 하고 돌아온 즉시 사용자는 QuizActivity 화면에서 장치를 회전시켜 mIsCheater
  값을 지울 수 있다

  3. 사용자는 자신이 커닝했던 질문이 다시 나타날 때까지 다음 버튼을 누를 수 있다
*/

/**
 * Created by patternoid on 2017. 7. 25..
 */

class QuizKtActivity : AppCompatActivity()
{
    companion object {
        private val TAG : String = "QuizKtActivity"
        private val KEY_INDEX : String = "index"
        private val KEY_ISCHEATER : String = "is_cheater"
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
            mIsCheater    = getBoolean(KEY_ISCHEATER, false)
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
        outState!!.putBoolean( KEY_ISCHEATER, mIsCheater )
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