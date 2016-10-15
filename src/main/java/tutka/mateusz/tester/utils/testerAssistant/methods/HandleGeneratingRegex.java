package tutka.mateusz.tester.utils.testerAssistant.methods;

import com.mifmif.common.regex.Generex;

import tutka.mateusz.interfaces.Method;
import tutka.mateusz.tester.utils.testerAssistant.clipboard.ClipboardHandle;

public class HandleGeneratingRegex implements Method {
	private String regex;
	private String alias;
	
	public HandleGeneratingRegex(String regex, String alias) {
		super();
		this.regex = regex;
		this.alias = alias;
	}

	@Override
	public String execute(String... args) {
	    Generex generex = new Generex(regex);
	    String randomContent = generex.random();
	    new ClipboardHandle().setClipboardContents(randomContent);
		return "'" + randomContent + "'" + " copied to the clipboard..";
	}

}
