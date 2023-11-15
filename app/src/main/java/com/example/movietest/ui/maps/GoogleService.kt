package com.example.movietest.ui.maps

import android.Manifest
import android.R
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.example.movietest.MainActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.util.*


class GoogleService(): Service(), LocationListener {
    private lateinit var mTimer: Timer
    private var notify_interval: Long = (10000 * 6) *5
    private var str_receiver = "servicetutorial.service.receiver"
    var intent: Intent? = null
    var locationManager: LocationManager? = null
    var location: Location? = null
    val ANDROID_CHANNEL_ID = "com.sai.ANDROID"
    val IOS_CHANNEL_ID = "com.sai.IOS"
    val ANDROID_CHANNEL_NAME = "ANDROID CHANNEL"
    val IOS_CHANNEL_NAME = "IOS CHANNEL"
    private var mManager: NotificationManager? = null
    val db = Firebase.firestore


    override fun onCreate() {
        super.onCreate()
        mTimer = Timer()
        mTimer.schedule(TimerTaskToGetLocation(context = applicationContext), 5, notify_interval)
        intent = Intent(str_receiver)

    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return START_STICKY
    }

    override fun onLocationChanged(p0: Location) {

    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    fun createChannels() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val androidChannel =  NotificationChannel(ANDROID_CHANNEL_ID, ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE)
            getManager().createNotificationChannel(androidChannel)
        }

    }


    private fun getManager(): NotificationManager {
        if (mManager == null) {
            mManager =  getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        }
        return mManager as NotificationManager
    }

    private fun addNotification() {
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.arrow_up_float)
            .setContentTitle("Notifications Example")
            .setContentText("This is a test notification")
        val notificationIntent = Intent(this, MainActivity::class.java)
        var pendingIntent: PendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

                PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_MUTABLE)
        } else {

                PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        builder.setContentIntent(pendingIntent)

        // Add as notification
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, builder.build())
    }

    fun fetchLocation(context: Context) {
        locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return
            }
            locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 5f,this)

        }
        if (locationManager!=null){
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return
            }
            location = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location!=null){
                Toast.makeText(context,"${location!!.getLatitude()} ${location!!.getLongitude()}" ,Toast.LENGTH_SHORT).show()
                val location = hashMapOf(
                    "latitud" to location!!.getLatitude().toString(),
                    "longitud" to location!!.getLongitude().toString(),
                )

                db.collection("locations")
                    .add(location)
                    .addOnSuccessListener { documentReference ->
                        println("DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        println("Error adding document")
                    }
            }
        }

    }

    inner class TimerTaskToGetLocation(val context: Context) : TimerTask() {
        private val mHandler: Handler = Handler()

        override fun run() {
            mHandler.post(Runnable {
                fetchLocation(context)
            })
        }

    }

}