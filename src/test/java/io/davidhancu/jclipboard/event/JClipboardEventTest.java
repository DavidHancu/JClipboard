package io.davidhancu.jclipboard.event;

import io.davidhancu.jclipboard.event.JClipboardEvent;
import io.davidhancu.jclipboard.event.JClipboardEventListener;

public class JClipboardEventTest extends JClipboardEventListener {
    @Override
    public void onClipboardTextChange(JClipboardEvent event) {
        System.out.println("Was: " + event.getOldValue());
        System.out.println("Is: " + event.getNewValue());
    }
}
