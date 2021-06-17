package io.davidhancu.jclipboard;

import io.davidhancu.jclipboard.event.JClipboardEventListener;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public final class JClipboard {
    private final Clipboard clipboard;
    private final JClipboardEventHandler eventHandler = new JClipboardEventHandler(this);

    private JClipboard(String name)
    {
        this.clipboard = new Clipboard(name);
        new Thread(eventHandler).start();
    }

    private JClipboard()
    {
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        new Thread(eventHandler).start();
    }

    /**
     * Get the name of the clipboard.
     * @return The name of the clipboard.
     */
    public String getName()
    {
        return clipboard.getName();
    }

    /**
     * This method allows you to set the text currently copied in the clipboard.
     * @param text The new text that should be set in the clipboard.
     */
    public void setText(String text)
    {
        clipboard.setContents(new StringSelection(text), null);
    }

    /**
     * This method allows you to clear the content from the clipboard.
     */
    public void clear()
    {
        clipboard.setContents(new EmptyTransferable(), null);
    }

    /**
     * This method allows you to get the currently copied text to the clipboard.
     * @return The current copied String to the clipboard or null if there isn't any String copied or an exception is thrown.
     */
    public String getText(){
        String result = null;
        Transferable contents = clipboard.getContents(null);
        if (hasText()) {
            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                return null;
            }
        }
        return result;
    }

    /**
     * Checks if the clipboard has text.
     * @return Whether the clipboard has text copied or not.
     */
    public boolean hasText()
    {
        try {
            Transferable contents = clipboard.getContents(null);
            return (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        } catch (Exception e)
        {
            return false;
        }
    }

    /**
     * Creates a new JClipboard object.
     * @param name The name of the clipboard.
     * @return
     */
    public static JClipboard create(String name)
    {
        return new JClipboard(name);
    }

    /**
     * Get the system default JClipboard object.
     * @return
     */
    public static JClipboard getDefault()
    {
        return new JClipboard();
    }

    /**
     * <p>Allows you to add an event listener for clipboard events. (See the methods inside of JClipboardEventListener for the available events)</p>
     * <p><strong>Note: </strong> The event listener will only be added if it isn't registered already (on this JClipboard instance).</p>
     * @param eventListener The event listener that should be added.
     */
    public void addEventListener(JClipboardEventListener eventListener)
    {
        if(!eventHandler.eventListeners.contains(eventListener))
            eventHandler.eventListeners.add(eventListener);
    }

    /**
     * <p>Allows you to remove an event listener for clipboard events added previously.</p>
     * @param eventListener The event listener that should be removed.
     */
    public void removeEventListener(JClipboardEventListener eventListener)
    {
        eventHandler.eventListeners.remove(eventListener);
    }

    private static class EmptyTransferable implements Transferable {
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[0];
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return false;
        }

        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
