package com.JordHan.Gloggr.Services;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public abstract class AbstractService implements Serializable, Runnable {

    private ArrayList<IServiceListener> listeners;
    private boolean error;

    public AbstractService() {
        listeners = new ArrayList<IServiceListener>();
    }

    public void addListener(IServiceListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IServiceListener listener) {
        listeners.remove(listener);
    }

    public boolean hasError() {
        return error;
    }

    public void serviceComplete(boolean error) {
        this.error = error;

        Message m = _handler.obtainMessage();
        Bundle b = new Bundle();
        b.putSerializable("service", this);
        m.setData(b);
        _handler.sendMessage(m);
    }

    final Handler _handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            AbstractService service
                    = (AbstractService) msg.getData().getSerializable("service");

            for (IServiceListener listener : service.listeners) {
                listener.ServiceComplete(service);
            }
        }
    };
}
