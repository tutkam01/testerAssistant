package tutka.mateusz.tester.utils.testerAssistant.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AliasEntity implements Serializable {
	private static final long serialVersionUID = 1111L;
	@Id
	@GeneratedValue
	private long id;

	protected String alias;

	public AliasEntity(String alias) {
		super();
		this.alias = alias;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public long getId(){
		return id;
	}
}
