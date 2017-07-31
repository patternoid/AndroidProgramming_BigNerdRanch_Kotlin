package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cheat.*

class CheatActivity : AppCompatActivity() {

    companion object {
        private val EXTRA_ANSWER_IS_TRUE : String = "com.bignerdranch.android.geoquiz.answer_is_true"
        private val EXTRA_ANSWER_SHOWN : String = "com.bignerdranch.android.geoquiz.answer_shown"

        fun newIntent(packageContext : Context, answerIsTrue : Boolean) : Intent {
            val intent : Intent = Intent( packageContext, CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue )

            return intent
        }

        fun wasAnswerShown( result : Intent ) : Boolean{
            return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
        }
    }

    private var mAnswerIsTure : Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        mAnswerIsTure = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false)

        show_answer_button.setOnClickListener { v ->
            if( mAnswerIsTure ){
                answer_text_view.setText(R.string.true_button)
            }else{
                answer_text_view.setText(R.string.false_button)
            }

            setAnswerShownResult(true)
        }
    }



    private fun setAnswerShownResult( isAnswerShown : Boolean ){
        val data : Intent = Intent()
        data.putExtra( EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(Activity.RESULT_OK, data)
    }
}
