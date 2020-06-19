package ru.remotecrm.whatsAppUnDelete.concrete.view_concrete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import ru.remotecrm.whatsAppUnDelete.R;
import ru.remotecrm.whatsAppUnDelete.absctract.view_abstract.MessagesFromDBActivityAbstract;
import ru.remotecrm.whatsAppUnDelete.concrete.model_concrete.pojo.MessageForView;
import ru.remotecrm.whatsAppUnDelete.concrete.presenter_concrete.MessagesFromDBPresenter;
import ru.remotecrm.whatsAppUnDelete.concrete.view_concrete.adapters.MessageAdapter;

import java.util.List;

public class MessagesFromDBActivity extends AppCompatActivity implements MessagesFromDBActivityAbstract {

    private RecyclerView recyclerViewMessages;
    private MessageAdapter adapter;
    private MessagesFromDBPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_from_db);
        recyclerViewMessages = findViewById(R.id.recyclerViewMessages);
        presenter = new MessagesFromDBPresenter(this, this);
        adapter = new MessageAdapter();
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMessages.setAdapter(adapter);
            }

    @Override
    public void dataChanged(List<MessageForView> messages) {
        adapter.setMessages(messages);
    }

    public void onClickClear(View view) {
        presenter.clearDB();
    }
}
