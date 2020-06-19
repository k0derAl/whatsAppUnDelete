package ru.remotecrm.whatsAppUnDelete.absctract.view_abstract;

import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo.MessageForView;

import java.util.List;

public interface MessagesFromDBActivityAbstract {
    void dataChanged (List<MessageForView> messages);
}
