package ru.remotecrm.whatsAppUnDelete.concrete.view_concrete.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.remotecrm.whatsAppUnDelete.R;
import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo.MessageForView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<MessageForView> messages;
    private int countMessagesOnTheScreen;
    private static final int COUNT_MESSAGES_ON_THE_PAGE = 10;

    public MessageAdapter() {
        messages = new ArrayList<>();
        countMessagesOnTheScreen = 0;
    }

    public void setMessages(List<MessageForView> messages) {
        this.messages = messages;
        if (messages.size() < COUNT_MESSAGES_ON_THE_PAGE) {
            countMessagesOnTheScreen = messages.size();
        } else {
            messages.size();
            if (countMessagesOnTheScreen < COUNT_MESSAGES_ON_THE_PAGE) {
                countMessagesOnTheScreen = COUNT_MESSAGES_ON_THE_PAGE;
            } else if (countMessagesOnTheScreen / COUNT_MESSAGES_ON_THE_PAGE ==
                    messages.size() / countMessagesOnTheScreen) {
                countMessagesOnTheScreen = messages.size();
            }
        }
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        if (position == countMessagesOnTheScreen - 1) {
            if (messages.size() > countMessagesOnTheScreen + COUNT_MESSAGES_ON_THE_PAGE) {
                countMessagesOnTheScreen += COUNT_MESSAGES_ON_THE_PAGE;
            } else if (countMessagesOnTheScreen < messages.size()) {
                countMessagesOnTheScreen = messages.size();
            }
        }
        if (position < countMessagesOnTheScreen) {
            MessageForView message = messages.get(messages.size() - 1 - position);
            holder.textViewPostTime.setText(message.getTimePost());
            holder.textViewMessageFrom.setText(message.getFromContact());
            holder.textViewMessageText.setText(message.getMessageText());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView textViewPostTime;
        TextView textViewMessageFrom;
        TextView textViewMessageText;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPostTime = itemView.findViewById(R.id.textViewPostTime);
            textViewMessageFrom = itemView.findViewById(R.id.textViewMessageFrom);
            textViewMessageText = itemView.findViewById(R.id.textViewMessageText);

        }
    }
}
