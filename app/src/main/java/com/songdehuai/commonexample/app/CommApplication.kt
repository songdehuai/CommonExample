package com.songdehuai.commonexample.app

import android.app.Application
import android.content.Intent
import com.didichuxing.doraemonkit.DoraemonKit
import com.songdehuai.commonexample.ui.dialog.DialogActivity
import com.songdehuai.commonlib.utils.FreeSync

class CommApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
        DoraemonKit.install(this)
    }

    private fun init() {
        FreeSync.defaultFreeSync().addCall("test") { _, _ ->
            val intent = Intent(this, DialogActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

}