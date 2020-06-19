package ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM messages")
    LiveData<List<Message>> getAllMessages ();

    @Query("DELETE FROM messages")
    void deleteAllMessages ();

    @Insert
    void insertMessage (Message message);

    @Query("SELECT * FROM messages WHERE messageText = :messageText")
    List<Message> getMessagesByMessageText(String messageText);
}
