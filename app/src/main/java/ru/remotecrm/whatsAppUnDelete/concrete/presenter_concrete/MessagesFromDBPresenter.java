package ru.remotecrm.whatsAppUnDelete.concrete.presenter_concrete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import ru.remotecrm.whatsAppUnDelete.absctract.view_abstract.MessagesFromDBActivityAbstract;
import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo.Message;
import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo.MessageForView;
import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.utils.PojoConverter;
import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.view_model.MessageViewModel;

import java.util.ArrayList;
import java.util.List;

public class MessagesFromDBPresenter {
    private LiveData<List<Message>> messagesLiveData;
    private MessageViewModel viewModel;
    private List<MessageForView> messagesForView;

    public MessagesFromDBPresenter(final MessagesFromDBActivityAbstract messagesFromDBActivityAbstract,
                                   final AppCompatActivity activity) {
        viewModel = new MessageViewModel(activity.getApplication());
        messagesLiveData = viewModel.getMessages();
        messagesForView = new ArrayList<>();
        messagesLiveData.observe(activity, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                messagesForView.clear();
                for(Message message : messages) {
                    messagesForView.add
                            (PojoConverter.messageToMessageForViewConvert(message, activity));
                }
                messagesFromDBActivityAbstract.dataChanged(messagesForView);
            }
        });

    }

    public void clearDB() {
        viewModel.deleteAllMessages();
    }
}
