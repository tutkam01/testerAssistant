package tutka.mateusz.tester.utils.testerAssistant.methods;

import java.text.ParseException;

import tutka.mateusz.interfaces.Method;
import tutka.mateusz.tester.utils.testerAssistant.sequence.GlobalSequence;

public class SetSequence implements Method {

	@Override
	public String execute(String... args) {
		try{
			int newSequence = Integer.parseInt(args[0]);
			GlobalSequence.currentValue = newSequence;
			return "Sequence updated successfully..";
		}catch(NumberFormatException e){
			return "Provided value is not a number..";
		}
	}

}
