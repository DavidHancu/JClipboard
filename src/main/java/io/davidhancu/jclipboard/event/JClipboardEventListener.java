package io.davidhancu.jclipboard.event;

public abstract class JClipboardEventListener {
    /**
     * The event fired when the text in the clipboard changes.
     * @param event The event object representing the values changed.
     */
    public void onClipboardTextChange(JClipboardEvent event) {}
}
