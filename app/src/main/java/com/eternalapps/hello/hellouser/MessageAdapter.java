package com.eternalapps.hello.hellouser;

/**
 * Created by prithvi on 13-06-2017.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class MessageAdapter extends ArrayAdapter<MessageObject> {

    public MessageAdapter(Context context, List<MessageObject> messages) {
        super(context,R.layout.message_row, messages);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater messageInflator = LayoutInflater.from(getContext());
        View messageView = messageInflator.inflate(R.layout.message_row,parent,false);

        MessageObject SingleMessage = getItem(position);
        TextView message = (TextView) messageView.findViewById(R.id.editText2);
        ImageView image = (ImageView) messageView.findViewById(R.id.imageView2);

        message.setText(SingleMessage.getText());
        image.setImageResource(R.drawable.profile);
        return messageView;
    }
}
