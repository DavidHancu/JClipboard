package io.davidhancu.jclipboard;

import io.davidhancu.jclipboard.JClipboard;
import io.davidhancu.jclipboard.event.JClipboardEventTest;
import org.junit.jupiter.api.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class JClipboardTest {
    @Test
    public void main() throws IOException, UnsupportedFlavorException {
        JClipboard jClipboard = JClipboard.getDefault();
        JClipboardEventTest jClipboardEventTest = new JClipboardEventTest();
        jClipboard.addEventListener(jClipboardEventTest);
        jClipboard.removeEventListener(jClipboardEventTest);

        System.out.println("Clipboard Name: " + jClipboard.getName());

        jClipboard.clear();
        System.out.println("Text after clear: " + jClipboard.getText());

        System.out.println("Has text: " + jClipboard.hasText());

        jClipboard.setText("hello");
        System.out.println("New text: " + jClipboard.getText());

        new Thread(() -> {while(true) {}}).start();
    }
}
