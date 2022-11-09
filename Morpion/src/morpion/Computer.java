package morpion;

public class Computer extends Player {
	
	private int NUMBER = 2;
	private Symbol SYMBOL = Symbol.getForm(NUMBER); 
	private int score; 
	
	
	public Computer(int number){
		super(number);	
	}
	
	@Override
	public Case play(Plateau p) {
		int highest_value = 0;
		Case highest_case = new Case(0,0,0,Symbol.getForm(0));
		for(Case c : p.getCasesPlateau()) {
			if ( c.getValue() > highest_value ) {
				highest_value = c.getValue();
				highest_case = c;
			}
		}
		return highest_case;
	}
	
	
}
