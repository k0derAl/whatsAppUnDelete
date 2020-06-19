package ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.view_model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.data.MessageDatabase;
import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo.Message;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MessageViewModel extends ViewModel {

    private static MessageDatabase database;
    private LiveData<List<Message>> messages;

    public MessageViewModel(Application application) {
        database = MessageDatabase.getInstance(application.getApplicationContext());
        messages = database.messageDao().getAllMessages();
    }

    public LiveData<List<Message>> getMessages() {
        return messages;
    }

    public void deleteAllMessages() {
        new DeleteAllMessagesTask().execute();
    }

    public void insertMessage(Message message) {
        new InsertMessageTask().execute(message);
    }

    public List<Message> getMessagesByMessageText(String messageText) {
        List<Message> messages = null;
        try {
            messages = new GetMessagesByMessageTextTask().execute(messageText).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return messages;
    }

    private static class DeleteAllMessagesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            database.messageDao().deleteAllMessages();
            return null;
        }
    }

    private static class InsertMessageTask extends AsyncTask<Message, Void, Void> {
        @Override
        protected Void doInBackground(Message... messages) {
            if (messages != null && messages.length > 0) {
                database.messageDao().insertMessage(messages[0]);
            }
            return null;
        }
    }

    private static class GetMessagesByMessageTextTask extends AsyncTask<String, Void, List<Message>> {
        @Override
        protected List<Message> doInBackground(String... strings) {
            List<Message> messages = null;
            if (strings != null && strings.length > 0) {
                messages = database.messageDao().getMessagesByMessageText(strings[0]);
            }
            return messages;
        }
    }
}
