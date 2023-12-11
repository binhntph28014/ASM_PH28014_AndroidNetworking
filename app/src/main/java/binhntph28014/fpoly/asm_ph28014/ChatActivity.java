package binhntph28014.fpoly.asm_ph28014;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatActivity extends AppCompatActivity {

    private Socket mSocket;
    private EditText mInputMessageView;
    private List<String> mMessages = new ArrayList<>();
    private ListView mMessagesView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mInputMessageView = findViewById(R.id.inputMessage);
        mMessagesView = findViewById(R.id.messagesView);


        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mMessages);
        mMessagesView.setAdapter(mAdapter);

        try {
            mSocket = IO.socket("http://192.168.56.1:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.on(Socket.EVENT_CONNECT, onConnect);
        mSocket.on("chat message", onNewMessage);

        mSocket.connect();
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(() -> {
                // Gửi thông điệp "user connected" khi kết nối thành công
                mSocket.emit("chat message", "User connected");
            });
        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(() -> {
                String message = (String) args[0];
                // Hiển thị tin nhắn mới trong ListView
                mAdapter.add(message);
                mMessagesView.setSelection(mAdapter.getCount() - 1);
            });
        }
    };

    public void sendMessage(View view) {
        String message = mInputMessageView.getText().toString().trim();
        if (!message.isEmpty()) {
            mSocket.emit("chat message", message);
            mInputMessageView.setText("");
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
        mSocket.off(Socket.EVENT_CONNECT, onConnect);
        mSocket.off("chat message", onNewMessage);
    }

}
