package tutka.mateusz.tester.utils.testerAssistant.methods;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import tutka.mateusz.interfaces.Method;
import tutka.mateusz.tester.utils.testerAssistant.clipboard.ClipboardHandle;
import tutka.mateusz.tester.utils.testerAssistant.sequence.GlobalSequence;

public class HandleGeneratingSequentialTimestampedString implements Method {
	private String constantPart;
	private String alias;
	private boolean isTimestamped;
	private boolean isSequential;
	private String dateFormat;
	
	public HandleGeneratingSequentialTimestampedString(String constantPart,	String alias, boolean isTimestamped, boolean isSequential, String dateFormat) {
		super();
		this.constantPart = constantPart;
		this.alias = alias;
		this.isTimestamped = isTimestamped;
		this.isSequential = isSequential;
		this.dateFormat = dateFormat;
	}

	public String execute(String... args){
		StringBuilder sb = new StringBuilder(constantPart);
		if(isTimestamped){
			sb.append(getCurrentDate());
		}
		
		if(isSequential){
			sb.append("_" + GlobalSequence.currentValue);
		}
		new ClipboardHandle().setClipboardContents(sb.toString());
		return " '" + sb.toString() + "' copied to the clipboard..";
	}
	
	private String getCurrentDate(){
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(now);
	}

}
