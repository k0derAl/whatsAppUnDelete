
package ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.services;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo.Message;
import ru.remotecrm.whatsAppUnDelete.concrete.presenter_concrete.MainActivityPresenter;
import ru.remotecrm.whatsAppUnDelete.concrete.view_concrete.MainActivity;

public class NotificationListener extends NotificationListenerService {

    private MainActivityPresenter mainActivityPresenter;
    private boolean isRecordStart = false;
    public static final String WHATSAPP_PACK_NAME = "com.whatsapp";
    public static final String PUSH_TITLE = "android.title";
    public static final String PUSH_MESSAGE = "android.text";


    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.hasExtra(MainActivity.IS_RECORD_START_INTENT)) {
            isRecordStart = intent.getBooleanExtra
                    (MainActivity.IS_RECORD_START_INTENT, false);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        if (sbn.getPackageName().equals(WHATSAPP_PACK_NAME) && isRecordStart) {
            long postTime = (sbn.getPostTime());
            Bundle bundle = sbn.getNotification().extras;
            String fromContact = bundle.getString(PUSH_TITLE);
            if (fromContact.equals("WhatsApp")) {
                return;
            }
            String messageText = bundle.getString(PUSH_MESSAGE);
            Message message = new Message(postTime, fromContact, messageText);
            mainActivityPresenter = new MainActivityPresenter(getApplication());
            mainActivityPresenter.addMessage(message);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }

}
