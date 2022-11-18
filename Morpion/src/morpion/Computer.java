package morpion;

public class Computer extends Player {
	 
	public Computer(){
		super();
		this.symbol = Symbol.getForm(2);
	}
	
	@Override
	public Case play(Plateau p) {
		int highest_value = 0;
		Case highest_case = p.getCase(0, 0);
		for(Case c : p.getCasesPlateau()) {
			c.setVal();
			if ( c.getValue() > highest_value ) {
				highest_value = c.getValue();
				highest_case = c;
			}
		}
		return highest_case;
	}
	
	
}
