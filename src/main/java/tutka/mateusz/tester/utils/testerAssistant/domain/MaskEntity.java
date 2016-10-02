package tutka.mateusz.tester.utils.testerAssistant.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MaskEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
	 @Id @GeneratedValue
	 private long id;
	 
	 private String constantPart;
	 private String alias;
	 private boolean isTimestamped;
	 private boolean isSequential;
	 
	public MaskEntity(String constantPart, String alias, boolean isTimestamped, boolean isSequential) {
		super();
		this.constantPart = constantPart;
		this.alias = alias;
		this.isTimestamped = isTimestamped;
		this.isSequential = isSequential;
	}
	
	public long getId() {
		return id;
	}

	public String getConstantPart() {
		return constantPart;
	}

	public String getAlias() {
		return alias;
	}

	public boolean isTimestamped() {
		return isTimestamped;
	}

	public boolean isSequential() {
		return isSequential;
	}

}
