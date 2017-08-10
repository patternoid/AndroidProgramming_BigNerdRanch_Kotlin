package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText

/**
 * Created by joonv on 2017-08-10.
 */

class CrimeFragment : Fragment()
{
    private var mCrime          : Crime?    = null
    private var mTitleField     : EditText? = null
    private var mDateButton     : Button?   = null
    private var mSolvedCheckBox : CheckBox? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCrime = Crime()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater!!.inflate(R.layout.fragment_crime, container, false)

        mTitleField = view.findViewById<EditText>(R.id.crime_title)
        mTitleField!!.addTextChangedListener( object : TextWatcher{

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mCrime!!.mTitle = p0.toString()
            }
        })

        mDateButton = view.findViewById<Button>(R.id.crime_date)
        mDateButton!!.setText( mCrime!!.mDate.toString() )
        mDateButton!!.isEnabled = false

        mSolvedCheckBox = view.findViewById<CheckBox>(R.id.crime_solved)
        mSolvedCheckBox!!.setOnCheckedChangeListener { buttonView , isChecked ->
            mCrime!!.mSolved = isChecked
        }


        return view
    }



}