package com.example.aashishravindran.gesturerecognition

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity() {

    var mediaRecorder: MediaRecorder? = null
    var FILE_RECORDING = ""
    var mediaPlayer: MediaPlayer? = null

    val PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED
    val AUDIO_PERMISSION = Manifest.permission.RECORD_AUDIO
    val PERMISSION_REQUEST_CODE = 100

    //var resID = resources.getIdentifier("test", "raw", packageName)





    override fun onCreate(savedInstanceState: Bundle?) {

        var mp: MediaPlayer? = MediaPlayer.create(this, R.raw.test)
        var time: Timer? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //FILE_RECORDING = "${externalCacheDir.absolutePath}/recorder.aac"

        record.setOnClickListener {
            if(record.text.toString().equals("Record")){
                record.text="Stop Recording"

                        record()
            }else{
                stopRecording()
                enableDisableButtonPlayRecording()
               record.text = "Record"
            }
        }

        playrecording.setOnClickListener{
            if(playrecording.text.toString().equals("Play Record")){
                playrecording.text="Stop Playing"
                play()
            }else{
                stopplaying()
                enableDisableButtonPlayRecording()
                playrecording.text = "Play Record"


            }

        }

        playchirp.setOnClickListener{


            Log.d("Play", "Play")
            time =Timer()
            time!!.scheduleAtFixedRate(object : TimerTask() {

                override fun run() {
                    mp?.start()
                }
            }, 0, 100)
        }
        stopplaying.setOnClickListener {
                Log.d("cancel", "cancel")
            time!!.cancel()
            time!!.purge()
            time = null

        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSION_REQUEST_CODE){
            if(grantResults[0] == PERMISSION_GRANTED){
                record()
            }
        }
    }

    fun isPermissionGranted(): Boolean{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) checkSelfPermission(AUDIO_PERMISSION) == PERMISSION_GRANTED
        else return true

    }

    fun requestAudioPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(arrayOf(AUDIO_PERMISSION), PERMISSION_REQUEST_CODE)
        }
    }


     fun record(){
         if(!isPermissionGranted()){
             requestAudioPermission()
             return
         }

        mediaRecorder = MediaRecorder()
        mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
        mediaRecorder!!.setOutputFile(FILE_RECORDING)
        mediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder!!.prepare()
        mediaRecorder!!.start()
    }

    fun stopRecording(){
        if(saveaudio.text.toString().equals("Save")){
            FILE_RECORDING = "${externalCacheDir.absolutePath}/recorder.aac"
            // saveaudio.text="Please enter custom name,else next audio will be overwritten"
        }
        else {
            FILE_RECORDING = "${externalCacheDir.absolutePath}/${saveaudio.text}.aac"
        }

        mediaRecorder?.stop()
        mediaRecorder?.release()
        mediaRecorder = null


    }

    fun play(){
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setDataSource(FILE_RECORDING)
        mediaPlayer!!.prepare()
        mediaPlayer!!.start()
        mediaPlayer!!.setOnCompletionListener {
            playrecording.text = "Stop Playing"
        }
    }

    fun stopplaying(){
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
     fun enableDisableButtonPlayRecording(){
        playrecording.isEnabled = doesFileExist()

    }

    fun doesFileExist(): Boolean{
        val file = File(FILE_RECORDING)
        return file.exists()
    }
}
