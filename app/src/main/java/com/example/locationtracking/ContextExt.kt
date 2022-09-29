package com.example.locationtracking

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import java.security.Permission


/**
 * Created by Prasannakumar Nalam, on 29/09/22.
 */

fun Context.hasLocationPermission(): Boolean{
    return ContextCompat.checkSelfPermission(this,
    Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
}

