package discworld;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 * Player class is to give each player a personality card, fifty dollars, 
 * green cards and brown cards.
 * 
 * @author Shu Liu, Zixi Quan and Jay Tanna
 * @version 2.00, 12 March 2015
 *
 */

public class Player {

	protected int id;
	protected int money;
	protected CardColor color;
	protected int minion,building;
	public int getId() {
		return id;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public CardColor getColor() {
		return color;
	}

	public void setColor(CardColor color) {
		this.color = color;
	}

	public PersonalityCard getPersonalityCard() {
		return personalityCard;
	}

	public void setPersonalityCard(PersonalityCard personalityCard) {
		this.personalityCard = personalityCard;
	}

	public ArrayList<BoardCard> getHoldingCards() {
		return holdingCards;
	}

	public void setHoldingCards(ArrayList<BoardCard> holdingCards) {
		this.holdingCards = holdingCards;
	}

	public int getMinion() {
		return minion;
	}

	public int getBuilding() {
		return building;
	}

	private PersonalityCard personalityCard;
	private ArrayList<BoardCard> holdingCards=new  ArrayList<BoardCard>();

	/**
	 * 
	 * The Player constructor shows all the information,name of personality cards,
	 * greencard and browncard 
	 * @param id
	 * @param personalitycards
	 * @param greencard
	 * @param browncard
	 */
	public Player(String s,CardColor cardcolor,Vector<PersonalityCard> personalitycards,Vector<BoardCard> greencard, Vector<BoardCard>browncard){
		int numGreenCard = 0,numBrownCard = 0;
		this.id=Integer.parseInt(s.substring(6, 7));
		money=10;
		System.out.println("Player "+this.getID()+" has "+money+" Ankh-Morpork dollars.");
		this.color=cardcolor;
		minion=12;
		building=6;
		personalityCard=this.gain_personalityCard(personalitycards);
		System.out.println("Player "+this.getID()+" get personality card:"+this.personalityCard.toString());
		String greenInfo="",brownInfo="";
		for(int i=0;i<5;i++)
		{
			BoardCard temp=this.gain_boardcard(greencard,browncard);

			if(temp.Id()<48){
				numGreenCard++;
				greenInfo+=temp.Name()+"  ";
			}
			else{
				numBrownCard++;
				brownInfo+=temp.Name()+"  ";
			}
			holdingCards.add(temp);
		}
		System.out.println("Player "+this.getID()+" has "+holdingCards.size()+" Board Cards:");
		System.out.println("\t"+numGreenCard+" Green Cards: "+ greenInfo);
		System.out.println("\t"+numBrownCard+" Brown Cards: "+ brownInfo);
	}
	/**
	 * Constructs an instance of player class with the given parameters.
	 * 
	 * This constructor will create a player with the id,money,color,
	 * minion,building,personalityCard and the holdingCards.
	 * 
	 * @param id  The id of the player
	 * @param money It shows how much amount a player has
	 * @param color  It shows the color of the player
	 * @param minion  It shows the number of minions a player have
	 * @param building It shows the number of buildings a player have
	 * @param personalityCard It shows the name of the personalityCard of a player
	 * @param holdingCards It shows the name of all the holdingCards
	 */

	public Player(int id, int money, CardColor color, int minion, int building,
			PersonalityCard personalityCard, ArrayList<BoardCard> holdingCards) {
		//super();
		this.id = id;
		this.money = money;
		this.color = color;
		this.minion = minion;
		this.building = building;
		this.personalityCard = personalityCard;
		this.holdingCards = holdingCards;
	}

	/**
	 * 
	 * The gain_boardcard method is for each player to get a random BoardCard.
	 * 
	 * @param g
	 * @param b
	 * @return card
	 */
	public BoardCard gain_boardcard(Vector<BoardCard> g, Vector<BoardCard>b){
		Random rn=new Random();
		int total;
		int randomNum;
		BoardCard card=null;
		if(!g.isEmpty())
		{
			total=g.size();
			randomNum=rn.nextInt(total);
			card=g.get(randomNum);
			g.remove(randomNum);
		} else if(!b.isEmpty())
		{
			total=b.size();
			randomNum=48+rn.nextInt(total);
			card=b.get(randomNum);
			b.remove(randomNum);
		}
		return card;
	}

	/**
	 * 
	 * The gain_personalityCard is used for each player to get a personality card.
	 * 
	 * @param personalitycards
	 * @return card
	 */
	public PersonalityCard gain_personalityCard(Vector<PersonalityCard> personalitycards){
		Random rn=new Random();
		int total;
		int randomNum;
		PersonalityCard card=null;
		if(!personalitycards.isEmpty())
		{
			total=personalitycards.size();
			randomNum=rn.nextInt(total);
			card=personalitycards.get(randomNum);
			personalitycards.remove(randomNum);
		} 
		return card;
	}

	/** The method is used for output. */
	public String toString(){
		String s="";
		s=s+"Player "+this.getID()+"\t Color: "+color+"\t Personality Card: "+this.personalityCard+"\n";
		BoardCard temp;
		String greenInfo="",brownInfo="";
		for(int i=0;i<holdingCards.size();i++)
		{
			temp=holdingCards.get(i);

			if(temp.Id()<48){
				greenInfo+=temp.Id()+"  ";
			}
			else{
				brownInfo+=temp.Id()+"  ";
			}
		}
		s=s+"Green Card: "+greenInfo+"\n";
		s=s+"Brown Card: "+brownInfo+"\n";
		s=s+"Money: "+money+" Ankh-Morpork dollars\n";
		s=s+"Number of Minions\t"+minion+"\n";
		s=s+"Number of Building\t"+building+"\n";
		return s;
	}
	public int getID(){
		return id;
	}
	public CardColor Color()
	{
		return color;
	}
	/** 
	 * This method allows the player to put a minion in a city
	 * @param city
	 * @param cityCards
	 * @return
	 */
	public boolean putMinion(CityCard city,Vector<CityCard> cityCards){
		if(minion==0 )
		{
			System.out.println("You need to remove one minion from following cities:");
			for(int i=0;i<12;i++)
				if(cityCards.get(i).getMinions().get(this.id-1)>0)
					System.out.println(cityCards.get(i).getId()+"/t"+cityCards.get(i).Name());
			Scanner scan = new Scanner(System.in);
			int input;
			do{
				input=scan.nextInt();
				scan.nextLine();
			}while(input<13 && input >0 && cityCards.get(input-1).getMinions().get(this.id-1)>0);
			scan.close();
			city.putMinion(this);
			cityCards.get(input-1).removeMinion(this);

		}
		else{
			city.putMinion(this);
		}
		return true;
	}
	/** 
	 *This method will allow player to interrupt or play his card even if it is not
	 * his turn.
	 * @author JayTanna
	 */

	public boolean interrupt()
	{	
		boolean flag=false;
		for(int i=0;i<holdingCards.size();i++)
			if(holdingCards.get(i).Interrupt())
			{
				flag=true;
				break;
			}

		if(flag){
			System.out.println("Do Player"+this.ID() +" want to interrupt?(Y/N)");
			String input;
			do{
				input=Master.scan.nextLine().toUpperCase();
			}while(!input.equals("Y") && !input.equals("N"));
			if(input.equals("Y"))
			{
				for(int i=0;i<holdingCards.size();i++)
					if(holdingCards.get(i).Interrupt())
					{
						System.out.println((i+1)+". "+holdingCards.get(i).Name());
					}
				int num;
				do{
					num=Master.scan.nextInt();
				}while(num<0 || num>=holdingCards.size()||!holdingCards.get(num).Interrupt());
				holdingCards.remove(num);
				return true;
			}
			else 
			{
				return false;
			}
		}
		return false;
	}
	/** 
	 * It will refill the player holdingCards by 5.
	 *  @author Jay Tanna
	 * @param g
	 * @param b
	 */
	public void refill(Vector<BoardCard> g,Vector<BoardCard> b) {
		if(holdingCards.size()<5)
		{
			System.out.println("Player "+this.getID()+" needs to refill");
			holdingCards.add(gain_boardcard(g,b));
		}

	}
	/** 
	 * This method will allow a player to build a building in a particular city
	 * @param city
	 * @param cities
	 */

	public boolean putBuilding(CityCard city,ArrayList<CityCard> cities){
		if(building==0 )
		{
			System.out.println("You need to remove one minion from following cities:");
			for(int i=0;i<12;i++)
				if(cities.get(i).isBuilding() && cities.get(i).getOwner()==this.id )
					System.out.println(cities.get(i).getId()+"/t"+cities.get(i).Name());
			int input;
			do{
				input=Master.scan.nextInt();
				Master.scan.nextLine();
			}while(input<13 && input >0 && cities.get(input).getOwner()==this.id);
			city.build(this);
			cities.get(input-1).destroy();
		}
		else{
			city.putMinion(this);
		}
		return true;
	}

	public boolean playCard(BoardCard b){
		int numSym=0;
		boolean playNextCard=false;
		BoardCard.Symbols s;
		boolean rondomEventFlag=b.Event();
		String con;
		int indexSym, indexCity;
		do{
			numSym=b.allSymbols().size();
			do{
				System.out.println("Choose a symbol from following(1-"+numSym+"):");
				for(int i=0;i<numSym;i++)
				{
					s=b.allSymbols().get(i);
					System.out.println((i+1)+". "+s.name());
				}
				indexSym=Master.scan.nextInt();
				Master.scan.nextLine();
			}while(indexSym<=0 || indexSym>numSym);
			s=b.allSymbols().get(indexSym-1);
			if (s== BoardCard.Symbols.Assassination)
			{
				do{
					System.out.println("Choose a city from following: ");
					for(int i=0;i<12;i++)
						if(Master.cityCards.get(i).containTroubleMaker())
							System.out.println((i+1)+". "+Master.cityCards.get(i).getName());
					indexCity=Master.scan.nextInt();
					Master.scan.nextLine();
				}while(indexCity<=0 ||indexCity>12 ||! Master.cityCards.get(indexCity-1).containTroubleMaker());
				for(int i=0;i<indexSym;i++)
				{
					b.allSymbols().remove(i);
				}
				int indexPlayer;
				do{
					System.out.println("Choose a player:");
					for(int i =0;i<Master.playerList.size();i++)
					{
						if(Master.cityCards.get(indexCity-1).minionNum(Master.playerList.get(i))>0)
							System.out.println("Player "+Master.playerList.get(i).getId());
					}
					indexPlayer=Master.scan.nextInt();
					Master.scan.nextLine();
				}while(indexPlayer<=0||indexPlayer>4);
				if(Master.cityCards.get(indexCity-1).minionNum(Master.playerList.get(indexPlayer-1))>0)
					if(!Master.playerList.get(indexPlayer-1).interrupt())
						b.Assassination(Master.cityCards.get(indexCity-1), Master.playerList.get(indexPlayer-1));
					else System.out.println("Player "+indexPlayer+" Interrupt.");
				else System.out.println("Player "+indexPlayer+ " does not have ant minions.");
			}
			else if (s==BoardCard.Symbols.Building)
			{
				do{
					for(int i=0;i<12;i++)
						System.out.println((i+1)+". "+Master.cityCards.get(i).getName());
					indexCity=Master.scan.nextInt();
					
				}while(indexCity<=0 ||indexCity>12 || !Master.cityCards.get(indexCity-1).build(this));
				for(int i=0;i<indexSym;i++)
					b.allSymbols().remove(0);
			}else if(s==BoardCard.Symbols.Dollar){
				money+=b.dollar();
				Master.bank-=b.dollar();
				System.out.println("You got "+ money + " dollars.");
				for(int i=0;i<indexSym;i++)
					b.allSymbols().remove(0);
			}else if(s==BoardCard.Symbols.RemoveTroubleMaker)
			{
				do{
					for(int i=0;i<12;i++)
						System.out.println((i+1)+". "+Master.cityCards.get(i).getName());
					indexCity=Master.scan.nextInt();
					Master.scan.nextLine();
				}while(indexCity<=0 ||indexCity>12 ||! Master.cityCards.get(indexCity-1).removeTM());
				for(int i=0;i<indexSym;i++)
					b.allSymbols().remove(0);
			}else if(s==BoardCard.Symbols.Minion){
				do{
					for(int i=0;i<12;i++)
						System.out.println((i+1)+". "+Master.cityCards.get(i).getName());
					indexCity=Master.scan.nextInt();
					Master.scan.nextLine();
				}while(indexCity<=0 ||indexCity>12 ||! this.putMinion(Master.cityCards.get(indexCity-1), Master.cityCards));
				for(int i=0;i<indexSym;i++)
					b.allSymbols().remove(0);
			}else if(s==BoardCard.Symbols.Scroll){
				System.out.println("Scroll");
			}else if (s==BoardCard.Symbols.PlayCard)
			{
				playNextCard=true;
			}
			con="J";
			while(!(con.equals("Y")||con.equals("N")))
			{
				System.out.println("Do you want to use other Symbols of the card?(Y/N)");
				con=Master.scan.next().trim().toUpperCase();
			}
		}while(!b.allSymbols().isEmpty() && con.equals("Y"));
		if(rondomEventFlag){
			System.out.println(Master.eventCards.get(0).toString()+" happens.");
			Master.eventCards.remove(0);
		}

		return playNextCard;

	}
	void setMinion(int x){
		minion+=x;

	}

	public void setBuilding(int x){
		building+=x;
	}

	public int personalityCard(){
		return personalityCard.ID();
	}

	public int ID(){
		return this.id;
	}

	public int Money() {
		return money;
	}
}