package discworld;
import java.util.ArrayList;


/**
 * 
 * This class generates board cards.
 * 
 * @author Shu Liu and Zixi Quan
 * @version 2.00, 10 March 2015
 *
 */
public class BoardCard {
	protected String name;
	protected int id;
	static enum Symbols{Minion,Building,Assassination,RemoveTM,Money,Scroll,Event,PlayCard,Interrupt};
	protected ArrayList<Symbols> symbol=new ArrayList<Symbols>();
	protected String des;
	protected int money;
	/**
	 * Constructs an instance of boardCard class with the given parameters.
	 * 
	 * This constructor will create a particular board card with the id,
	 * the name, the ability and the description.
	 * 
	 * @param id
	 * @param name
	 * @param num_ablity
	 * @param des
	 */
	public BoardCard(int id, String name, String s_Symbols,int money,String des){
		this.id=id;
		this.name=name;
		
		for(int i=0;i<s_Symbols.length();i++)
		{
			char s=s_Symbols.toUpperCase().charAt(i);
			switch(s){
			case 'M':	symbol.add(Symbols.Minion);break;
			case 'B':	symbol.add(Symbols.Building);break;
			case 'A':	symbol.add(Symbols.Assassination);break;
			case 'R':	symbol.add(Symbols.RemoveTM);break;
			case 'E':	symbol.add(Symbols.Event);break;
			case 'S':	symbol.add(Symbols.Scroll);break;
			case 'P':	symbol.add(Symbols.PlayCard);break;
			case 'I':	symbol.add(Symbols.Interrupt);break;
			case 'D':	symbol.add(Symbols.Money);break;		
			}		
			
		}
		this.money=money;
		this.des=des;
	}
	
	public BoardCard(String name, int id, Vector<Boolean> ability, String des,
			int money) {
		super();
		this.name = name;
		this.id = id;
		this.ability = ability;
		this.des = des;
		this.money = money;
	}

	/*******************************************************************
	 * Getters 
	 *******************************************************************/
	
	public boolean Assassination(CityCard cityCard, Player p){
		if(cityCard.isTroubleMaker())
		{
			int numPlayer = p.getID();
			if(cityCard.getMinions().get(numPlayer-1)>0)
				cityCard.removeMinion(p);
		}
		return symbol.contains(Symbols.Assassination);
	}
	public boolean Remove_Trouble_Marker(){
		return symbol.contains(Symbols.RemoveTM);
	}
	public int Money(){
		if(symbol.contains(Symbols.Money))
		return money;
		else return 0;
	}
	public boolean Scroll(){
		return symbol.contains(Symbols.Scroll);
	}
	public boolean Event(){
		return symbol.contains(Symbols.Event);
	}
	public boolean Play_Another_Card(){
		
		return symbol.contains(Symbols.PlayCard);
	}
	public boolean Interrupt(){
		return symbol.contains(Symbols.Interrupt);
	}
	/**
	 * Gets the description in the board card.
	 * 
	 * @return description
	 */
	public String Description(){
		return des;
	}
	/**
	 * Gets the name in the board card.
	 * 
	 * @return name
	 */
	public String Name(){
		return name;
	}
	/**
	 * Gets the id in the board card.
	 * 
	 * @return id
	 */
	public int Id(){
		return id;
	}
	public ArrayList<Symbols> allSymbols(){
		return symbol;
	}
	
	
}
