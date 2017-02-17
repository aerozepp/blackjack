package view;

import com.prettymuch.blackjack.Card;
import com.prettymuch.blackjack.Dealer;
import com.prettymuch.blackjack.Record;

import com.prettymuch.blackjack.User;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.prettymuch.blackjack.Dealer.BLACK_JACK;

public class MainView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 4728244239185625080L;
	private Dealer dealer;
	private User user;
	private ControlPanel controlPanel;
	private PlayPanel playPanel;
	private CardLabel card;
	public static Container c;

	public MainView() {

	}

	public MainView(String title) throws IOException {

		super(title);

		dealer = new Dealer();
		user = new User();

		System.out.println(title + " frame made");

		controlPanel = new ControlPanel();
		controlPanel.setControlPanel();

		playPanel = new PlayPanel();
		playPanel.setPlayPanel();

		c = getContentPane();
		c.add(controlPanel);

		controlPanel.getPlayBtn().addActionListener(this);
		controlPanel.getRecordBtn().addActionListener(this);
		playPanel.getBetBtn().addActionListener(this);
		playPanel.getHitBtn().addActionListener(this);
		playPanel.getStayBtn().addActionListener(this);
		playPanel.getResetBtn().addActionListener(this);
		playPanel.getExitBtn().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Play")) {
			System.out.println("play clicked");
			c.removeAll();
			c.add(playPanel);
			c.validate();

		} else if (e.getActionCommand().equals("Record")) {
			Record record = getRecords();
			String message = record.getName() + "\n" + record.getChipsRecord() + " chips in " + record.getRound() + " round\n " + record.getTime();
			showNotificationDialog(message);

		} else if (e.getActionCommand().equals("Exit")) {

			String name = JOptionPane.showInputDialog(this, "GAME OVER! ======== Input your name");
			user.setName(name);

			setRecord();
			dispose();

		} else if (e.getActionCommand().equals("Bet")) {

			showAndSetBet();
			showDealtCards();
			showUserAce(user.getHand());
			ifDealerHadAce();

		} else if (e.getActionCommand().equals("Hit")) {

			showHitcard();
			showUserAce(user.getHand());

			if (user.handValue() > BLACK_JACK) {

				showNotificationDialog("BUST! It is over 21.");
				showBettingResult();

				goToReset();
			}

		} else if (e.getActionCommand().equals("Stay")) {

			System.out.println("stay clicked");
			openDealerHand();

			while (dealer.handValue() < 17) {
				card = new CardLabel(dealer.getCard(dealer.getDealIndex()));
				playPanel.getComCardPanel().add(card.getCardImage());
				if (dealer.handValue() > 16) {
					break;
				}
			}

			whoIsWinner(checkWhoWins());

		} else if (e.getActionCommand().equals("Reset")) {
			reset();

		}

	}

	public void reset() {

		System.out.println("reset clicked");
		cardPanelReset();

		dealer.setRound();
		dealer.shuffleDeck();

		user.resetHand();
		dealer.resetHand();

		playPanel.getResetBtn().setVisible(false);
		playPanel.getBetBtn().setVisible(true);
		playPanel.validate();

		System.out.println("=============================");
		System.out.println("round : " + dealer.getRound());

	}

	public void showDealtCards() {

		for (int i = 0; i < 2; i++) {

			card = new CardLabel(user.getCard(dealer, dealer.getDealIndex()));
			playPanel.getCardPanel().add(card.getCardImage());

			dealer.getCard(dealer.getDealIndex());

		}

		user.showHand();
		dealer.showHand();

		card = new CardLabel(dealer.getHand().get(0));
		playPanel.getComCardPanel().add(card.getCardImage());
		card = new CardLabel();
		playPanel.getComCardPanel().add(card.getCardImage());

		playPanel.getBtnPanel().add(playPanel.getHitBtn());
		playPanel.getBtnPanel().add(playPanel.getStayBtn());
		playPanel.getHitBtn().setVisible(true);
		playPanel.getStayBtn().setVisible(true);

		playPanel.getUserValue().setText(String.valueOf(user.handValue()));
		playPanel.validate();

	}

	public void showHitcard() {

		card = new CardLabel(user.getCard(dealer, dealer.getDealIndex()));
		playPanel.getCardPanel().add(card.getCardImage());
		playPanel.getUserValue().setText(String.valueOf(user.handValue()));
		playPanel.validate();

	}

	public void showAndSetBet() {
		Object[] options = { "10", "20", "50", "100", "All" };
		int option = showOptionDialog("How many chips to bet?_________( 10 chips by default )", "Betting", options);

		System.out.println(option);
		user.bet(option);

		String bet = String.valueOf(user.getBet());
		String chips = String.valueOf(user.getMoney());

		System.out.println("remain : " + user.getMoney());
		System.out.println("you bet : " + user.getBet());

		playPanel.getUserBet().setText(bet);
		playPanel.getUserChips().setText(chips);

		playPanel.getBetBtn().setVisible(false);
		playPanel.validate();
	}

	public int showOptionDialog(String message, String title, Object[] object) {

		int option = JOptionPane.showOptionDialog(this, message, title, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, object, object[0]);

		return option;
	}

	public void showNotificationDialog(String message) {

		JOptionPane pane = new JOptionPane(message);
		JDialog dialog = pane.createDialog("Blackjack");
		dialog.setSize(250, 150);
		dialog.setLocation(120, 180);
		dialog.setVisible(true);
	}

	public void showUserAce(ArrayList<Card> hand) {

		for (Card card : hand) {
			Object[] options = { "1", "11" };
			boolean isAce = dealer.isCardAce(card);

			if (isAce == true) {

				int option = showOptionDialog(card.getSuite() + " " + card.getFace() + " is ?", "Ace to?", options);

				if (option == 0) {
					card.setValue(1);

				} else {
					card.setValue(11);

				}
				playPanel.validate();
			}

			playPanel.getUserValue().setText(String.valueOf(user.handValue()));

		}

	}

	public void showBettingResult() {

		playPanel.getUserBet().setText("0");
		playPanel.getUserChips().setText(String.valueOf(user.getMoney()));
		playPanel.validate();
	}

	public void youHaveBlackjack() {
		user.setMoney((int) (user.getBet() * 2.5) + user.getMoney());
		System.out.println(user.getMoney());
		showBettingResult();
		showNotificationDialog("You've got Blackjack!");

	}

	public void openDealerHand() {

		playPanel.getComCardPanel().removeAll();

		for (int i = 0; i < 2; i++) {
			card = new CardLabel(dealer.getHand().get(i));
			playPanel.getComCardPanel().add(card.getCardImage());
		}
		playPanel.validate();
	}

	public int takeInsurance() {

		int userBet = user.getBet();
		int half = user.getBet() / 2;
		int insurance;
		Object[] objects = { userBet, half, 0 };

		int option = showOptionDialog(
				"Dealer has Ace faced up" + "\n" + "How many chips to take an insurance?" + "\n"
						+ "If dealer had BlackJack, you will get twice of the amount of insurance",
				"Insurance", objects);

		if (option == 0)
			insurance = userBet;
		else if (option == 1)
			insurance = half;
		else
			insurance = 0;

		if (insurance > user.getMoney())
			insurance = user.getMoney();

		return insurance;
	}

	public void ifDealerHadAce() {

		if (dealer.getHand().get(0).getFace().equals("ace")) {

			int insurance = takeInsurance();
			user.setMoney(user.getMoney() - insurance);
			playPanel.getUserChips().setText(String.valueOf(user.getMoney()));

			if (dealer.isBlackJack(dealer.getHand())) {

				showNotificationDialog("Dealer has Blackjack!");
				openDealerHand();
				user.setMoney(user.getMoney() + insurance * 2);
				user.setBet(0);
				showBettingResult();
				goToReset();

			} else if (dealer.isBlackJack(dealer.getHand()) && dealer.isBlackJack(user.getHand())) {
				showNotificationDialog("You both have Blackjack!");
				openDealerHand();
				user.setMoney(user.getMoney() + insurance * 2);
				user.setMoney(user.getMoney() + user.getBet());
				user.setBet(0);
				showBettingResult();
				goToReset();

			}
		} else if (dealer.isBlackJack(user.getHand())) {
			youHaveBlackjack();
			goToReset();

		}

	}

	public String checkWhoWins() {

		int userTotalValue = user.handValue();
		int dealerTotalValue = dealer.handValue();
		playPanel.setComValue(String.valueOf(dealerTotalValue));
		playPanel.setUserValue(String.valueOf(userTotalValue));
		playPanel.validate();

		if (dealerTotalValue > BLACK_JACK) {
			return "User";
		} else if (userTotalValue > dealerTotalValue) {
			return "User";
		} else if (userTotalValue < dealerTotalValue) {
			return "Dealer";
		} else
			return "Tie";
	}

	public void whoIsWinner(String winner) {

		if (winner.equals("User")) {

			showNotificationDialog("You win!");
			user.setMoney(user.getMoney() + user.getBet() * 2);
			showBettingResult();

			goToReset();

		} else if (winner.equals("Dealer")) {

			showNotificationDialog("Dealer wins!");
			showBettingResult();
			goToReset();

		} else if (winner.equals("Tie")) {

			showNotificationDialog("Tie");
			user.setMoney(user.getMoney() + user.getBet());
			showBettingResult();
			goToReset();
		}

	}

	public void cardPanelReset() {

		playPanel.getCardPanel().setVisible(false);
		playPanel.setCardPanel();
		playPanel.getBoardPanel().add(playPanel.getCardPanel());
		playPanel.getCardPanel().setVisible(true);
		playPanel.validate();

		playPanel.getComCardPanel().setVisible(false);
		playPanel.setComCardPanel();
		playPanel.getComBoardPanel().add(playPanel.getComCardPanel());
		playPanel.getComCardPanel().setVisible(true);
		playPanel.validate();
	}

	public void goToReset() {

		if (user.getMoney() <= 0) {

			String name = JOptionPane.showInputDialog(this, "GAME OVER! ======== Input your name");
			user.setName(name);

			setRecord();
			dispose();

		}

		for (Card card : dealer.getCardSet()) {

			if (card.getFace().equals("ace")) {

				card.setValue(11);
			}
		}

		user.setMostChips();

		playPanel.setComValue("0");
		playPanel.setUserValue("0");
		playPanel.getHitBtn().setVisible(false);
		playPanel.getStayBtn().setVisible(false);
		playPanel.getBtnPanel().add(playPanel.getResetBtn());
		playPanel.getResetBtn().setVisible(true);
		playPanel.validate();
	}

	public void setRecord() {

		if (user.getName() == null) {

			dispose();
		}

		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
		Date dateobj = new Date();

		Record record = new Record();

		record.setName(user.getName());
		record.setRound(dealer.getRound());
		record.setChipsRecord(user.getMostChips());
		record.setTime(df.format(dateobj));
		Record prev_record = getRecords();

		if (record.getChipsRecord() > prev_record.getChipsRecord()) {

			showNotificationDialog("New Record!!");

			try {

				File file = new File("C:\\codelab\\workspace_java\\Blackjack2017\\src\\Record.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Record.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(record, file);
				jaxbMarshaller.marshal(record, System.out);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}

	}

	public Record getRecords() {

		Record record = null;

		try {
			File file = new File("C:\\codelab\\workspace_java\\Blackjack2017\\src\\Record.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Record.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			record = (Record) jaxbUnmarshaller.unmarshal(file);

			System.out.println(record.getName());

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return record;

	}

}
