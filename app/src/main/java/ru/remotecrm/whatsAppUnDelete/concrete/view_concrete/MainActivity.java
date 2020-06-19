package ru.remotecrm.whatsAppUnDelete.concrete.view_concrete;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ru.remotecrm.whatsAppUnDelete.R;
import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.services.NotificationListener;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS =
            "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    public static final String IS_RECORD_START_INTENT = "Is record start?";
    public static final String IS_RECORD_START_TAG_PREFERENCE = "Is record start?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!isNotificationServiceEnabled()) {
            startActivity(new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS));
        }
        Button buttonStartStopWhatsappRec = findViewById(R.id.buttonStartStopRecWhatsappMes);
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String buttonTag = sharedPreferences.getString(IS_RECORD_START_TAG_PREFERENCE,
                getString(R.string.false_answer));
        buttonStartStopWhatsappRec.setTag(buttonTag);
        if (buttonTag.equals(getString(R.string.false_answer))) {
            buttonStartStopWhatsappRec.setText(getString(R.string.start_record_whatsapp_messages));
        }
        else if (buttonTag.equals(getString(R.string.true_answer))) {
            buttonStartStopWhatsappRec.setText(getString(R.string.stop_record_whatsapp_messages));
        }
    }

    public void onClickStartRecordMessages(View view) {
        Button button = (Button) view;
        Intent intent = new Intent(this, NotificationListener.class);
        if (button.getTag().toString().equals(getString(R.string.false_answer))) {
            intent.putExtra(IS_RECORD_START_INTENT, true);
            button.setText(getString(R.string.stop_record_whatsapp_messages));
            button.setTag(getString(R.string.true_answer));
            sharedPreferences.edit().putString(IS_RECORD_START_TAG_PREFERENCE,
                    getString(R.string.true_answer)).apply();
        } else if (button.getTag().toString().equals(getString(R.string.true_answer))) {
            intent.putExtra(IS_RECORD_START_INTENT, false);
            button.setText(getString(R.string.start_record_whatsapp_messages));
            button.setTag(getString(R.string.false_answer));
            sharedPreferences.edit().putString(IS_RECORD_START_TAG_PREFERENCE,
                    getString(R.string.false_answer)).apply();
        }
        startService(intent);
    }

    private boolean isNotificationServiceEnabled() {
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(),
                ENABLED_NOTIFICATION_LISTENERS);
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void onClickGoToMessagesFromDBActivity(View view) {
        Intent intent = new Intent(this, MessagesFromDBActivity.class);
        startActivity(intent);
    }
}
