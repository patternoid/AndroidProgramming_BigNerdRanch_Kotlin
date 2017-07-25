package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*


/**
 * Created by patternoid on 2017. 7. 25..
 */

class QuizKtActivity : AppCompatActivity()
{
    override fun onCreate( savedInstanceState : Bundle? )
    {
        super.onCreate( savedInstanceState )
        setContentView(R.layout.activity_quiz)

        true_button!!.setOnClickListener { v ->
            Toast.makeText(this@QuizKtActivity, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        }

        false_button!!.setOnClickListener { v ->
            Toast.makeText(this@QuizKtActivity, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()

        }
    }
}