package tutka.mateusz.tester.utils.testerAssistant.methods;

import tutka.mateusz.interfaces.Method;
import tutka.mateusz.tester.utils.testerAssistant.sequence.GlobalSequence;

public class IncreaseSequence implements Method {

	public String execute(String... args) {
		GlobalSequence.currentValue++;
		return "Current sequence:=" + GlobalSequence.currentValue;
	}

	
	
	



}
