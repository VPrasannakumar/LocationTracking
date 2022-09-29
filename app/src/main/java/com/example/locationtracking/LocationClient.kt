package com.example.locationtracking

import android.location.GnssNavigationMessage
import android.location.Location
import kotlinx.coroutines.flow.Flow


/**
 * Created by Prasannakumar Nalam, on 29/09/22.
 */
interface LocationClient {
    fun getLocation(interval:Long): Flow<Location>
    class LocationException(message: String) : Exception()
}