package ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo.Message;

@Database(entities = {Message.class}, version = 1, exportSchema = false)
public abstract class MessageDatabase extends RoomDatabase {
    private static MessageDatabase database;
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "messages.db";

    public static MessageDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, MessageDatabase.class, DATABASE_NAME).
                        fallbackToDestructiveMigration().build();
            }
        }
        return database;
    }

    public abstract MessageDao messageDao ();
}

