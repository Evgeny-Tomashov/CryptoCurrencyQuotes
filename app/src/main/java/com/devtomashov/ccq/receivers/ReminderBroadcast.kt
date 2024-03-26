package com.devtomashov.ccq.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.devtomashov.ccq.data.entity.Quote
import com.devtomashov.ccq.ui.notifications.NotificationConstants
import com.devtomashov.ccq.ui.notifications.NotificationHelper

class ReminderBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val bundle = intent?.getBundleExtra(NotificationConstants.QUOTE_BUNDLE_KEY)
        val quote: Quote = bundle?.get(NotificationConstants.QUOTE_KEY) as Quote

        NotificationHelper.createNotification(context!!, quote)
    }
}