package io.davidhancu.jclipboard;

import io.davidhancu.jclipboard.event.JClipboardEvent;
import io.davidhancu.jclipboard.event.JClipboardEventListener;

import java.awt.*;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

final class JClipboardEventHandler extends Thread{
    protected JClipboardEventHandler(JClipboard jClipboard)
    {
        this.jClipboard = jClipboard;
    }

    protected final Queue<JClipboardEventListener> eventListeners = new ConcurrentLinkedDeque<>();
    protected final JClipboard jClipboard;

    @Override
    public void run() {
        String recentContent = jClipboard.getText() == null ? "" : jClipboard.getText();
        // continuously perform read from clipboard
        while (true) {
            try {
                String data = jClipboard.getText();
                if (data != null && !data.equals(recentContent)) {
                    JClipboardEvent event = new JClipboardEvent(recentContent, data.isEmpty() ? null : data);
                    recentContent = data;
                    for(JClipboardEventListener listener : eventListeners)
                        listener.onClipboardTextChange(event);
                }

            } catch (HeadlessException e) {
                e.printStackTrace();
            }
        }
    }
}
