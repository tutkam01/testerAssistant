package tutka.mateusz.tester.utils.testerAssistant.domain;

import javax.persistence.Entity;

@Entity
public class RegexEntity extends AliasEntity {
	private static final long serialVersionUID = 1113L;
	private String regex;

	public RegexEntity(String alias, String regex) {
		super(alias);
		this.regex = regex;
	}
	
	public String getRegex(){
		return regex;
	}

}
