package vn.linh.androidgesturedetector

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.GestureDetector
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val DEBUG_TAG = "Gestures"
    private lateinit var detector: GestureDetectorCompat

    @SuppressLint("ClickableViewAccessibility")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        detector = GestureDetectorCompat(this, MyGestureListener())

        findViewById<TextView>(R.id.textview).setOnTouchListener { v, event ->
            Log.i(DEBUG_TAG, "onTouch "+getDisplayAction(event.action))
            detector.onTouchEvent(event)
        }

        findViewById<RecyclerView>(R.id.recycler_view).setOnTouchListener { v, event ->
            Log.i(DEBUG_TAG, "onTouch "+getDisplayAction(event.action))
            detector.onTouchEvent(event)
        }
    }

    private fun getDisplayAction(action: Int): String {
        return when (action) {
            MotionEvent.ACTION_DOWN -> "DOWN"
            MotionEvent.ACTION_MOVE -> "MOVE"
            MotionEvent.ACTION_UP -> "UP"
            MotionEvent.ACTION_CANCEL -> "CANCEL"
            MotionEvent.ACTION_OUTSIDE -> "OUTSIDE"
            else -> "UNKNOWN"
        }
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(event: MotionEvent): Boolean {
            Log.d(DEBUG_TAG, "onDown: " + event.toString())
            return true
        }

        override fun onFling(event1: MotionEvent, event2: MotionEvent,
                             velocityX: Float, velocityY: Float): Boolean {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString())
            return true
        }

        override fun onSingleTapUp(event: MotionEvent?): Boolean {
            Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString())
            return true
        }
    }
}
