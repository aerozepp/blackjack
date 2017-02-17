package com.prettymuch.blackjack;

public class User extends Player implements Play {

	int mostChips = 0;
	
	public User() {
		super(100);
		mostChips = 100;
	}

	public Card getCard(Dealer dealer, int dealIndex) {
		Card card = dealer.dealCard(dealIndex);
		getHand().add(card);

		return card;
	}
	
	

	public int getMostChips() {
		return mostChips;
	}

	public void setMostChips() {
		
		if(mostChips < getMoney()){
			mostChips = getMoney();
		}
	}

	@Override
	public void stand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void split() {
		// TODO Auto-generated method stub

	}

	@Override
	public void surrender() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bet(int option) {

		int bet = 0;
		switch (option) {
		case -1:
			bet = 10;
			break;
		case 0:
			bet = 10;
			break;
		case 1:
			bet = 20;
			break;
		case 2:
			bet = 50;
			break;
		case 3:
			bet = 100;
			break;
		case 4:
			bet = getMoney();
			break;
		}
		
		if (bet > getMoney()) {
			setBet(getMoney());
		}else{
			setBet(bet);
		}
	}

}
