package io.davidhancu.jclipboard.event;

public final class JClipboardEvent {
    final String newValue;
    final String oldValue;

    public JClipboardEvent(String oldValue, String newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    /**
     * Allows you to retrieve the new text.
     * @return The new value that exists in the clipboard.
     */
    public String getNewValue()
    {
        return newValue;
    }

    /**
     * <p>Allows you to retrieve the old text.</p>
     * <p><strong>Note: </strong>The old text can be null if the clipboard was empty.</p>
     * @return The old value that existed in the clipboard before the change.
     */
    public String getOldValue()
    {
        return oldValue;
    }
}
