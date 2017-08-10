package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager


class CrimeActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime)

        var fragment : Fragment? = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if( fragment == null ){
            fragment = CrimeFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }
    }
}
