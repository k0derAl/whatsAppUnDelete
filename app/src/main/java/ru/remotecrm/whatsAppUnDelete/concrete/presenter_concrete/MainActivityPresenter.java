package ru.remotecrm.whatsAppUnDelete.concrete.presenter_concrete;

import android.app.Application;

import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo.Message;
import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.view_model.MessageViewModel;

import java.util.List;

public class MainActivityPresenter {

    MessageViewModel messageViewModel;

    public MainActivityPresenter(Application application) {
        messageViewModel = new MessageViewModel(application);
    }


    public void addMessage(Message message) {
        List<Message> messagesByDate = messageViewModel.getMessagesByMessageText(message.getMessageText());
        if (messagesByDate != null && messagesByDate.size() > 0) {
            for (Message msg : messagesByDate) {
                if (msg.getFromContact().equals(message.getFromContact())) {
                    return;
                }
            }
        }
        messageViewModel.insertMessage(message);
    }
}
