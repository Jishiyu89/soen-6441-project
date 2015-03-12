package test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import discworld.BoardCard;
import discworld.CardColor;
import discworld.Player;
import discworld.Master;
import discworld.PersonalityCard;


public class MasterTest {
	//private Master m;
	private Player player;
	private PersonalityCard p;
	ArrayList<BoardCard> holdingCards = new ArrayList<BoardCard>();
	@Before
	public void initialize(){
		//m=new Master();
		player = new Player(2,200,CardColor.blue,2,3,p,holdingCards);
	}
	
	@Test
	public void ConstructorTest()
	{
		
	}
	
	/**
	 * The method checks that whether the number we got from roll() function 
	 * of the dice is greater than Zero or Not 
	 */
	@Test
	public void roll()
	{
		int result = Master.roll();
		boolean res;
		if(result>0){
			res = true;
		} else{
			res = false;
		}
		assertTrue(res);
	}
	
	/**
	 * This method checks whether the winning condition of an player shows or not.
	 */
	@Test
	public void winCheck()
	{
		boolean res = Master.winCheck(player);
		assertFalse(res);
	}
}
