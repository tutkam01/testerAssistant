package tutka.mateusz.tester.utils.testerAssistant.domain;

import javax.persistence.Entity;

@Entity
public class MaskEntity extends AliasEntity {
	
	private static final long serialVersionUID = -3368725021994753523L;
	private String constantPart;
    private boolean isTimestamped;
	private boolean isSequential;
	private String dateFormat;
	 
	public MaskEntity(String constantPart, String alias, boolean isTimestamped, boolean isSequential, String dateFormat) {
		super(alias);
		this.constantPart = constantPart;
		this.isTimestamped = isTimestamped;
		this.isSequential = isSequential;
		this.dateFormat = dateFormat;
	}
	
	public String getConstantPart() {
		return constantPart;
	}

	public boolean isTimestamped() {
		return isTimestamped;
	}

	public boolean isSequential() {
		return isSequential;
	}
	
	public String getDateFormat(){
		return dateFormat;
	}
	
	public String toString(){
		return alias;
	}

}
