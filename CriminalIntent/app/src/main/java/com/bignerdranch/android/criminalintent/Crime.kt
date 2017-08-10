package com.bignerdranch.android.criminalintent

import java.util.*

/**
 * Created by joonv on 2017-08-10.
 */
data class Crime( var mId       : UUID      = UUID.randomUUID()
                 ,var mTitle    : String    =""
                 ,var mDate     : Date      = Date()
                 ,var mSolved   : Boolean   = false)