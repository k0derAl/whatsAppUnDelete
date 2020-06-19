package ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.utils;

import android.content.Context;
import android.os.Build;

import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo.Message;
import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo.MessageForView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PojoConverter {

    public static MessageForView messageToMessageForViewConvert(Message message, Context context) {
        long timePostLong = message.getTimePost();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yy",
                getCurrentLocale(context));
        String timePostString = simpleDateFormat.format(new Date(timePostLong));
        return new MessageForView(timePostString, message.getFromContact(), message.getMessageText());
    }

    public static Locale getCurrentLocale(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }

}
