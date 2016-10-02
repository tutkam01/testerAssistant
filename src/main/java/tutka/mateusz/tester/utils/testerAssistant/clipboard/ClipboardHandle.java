package tutka.mateusz.tester.utils.testerAssistant.clipboard;

import java.awt.IllegalComponentStateException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardHandle {
	
	public void setClipboardContents(String clipboardText) {
		StringSelection stringSelection = new StringSelection(clipboardText);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
	}

	public String getClipboardContents() {
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		if (hasTransferableText) {
			try {
				result = (String) contents.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException | IOException ex) {
				throw new IllegalComponentStateException("Clipboard text retrieval failed " + ex.getMessage());
			}
		}
		return result;
	}
}
