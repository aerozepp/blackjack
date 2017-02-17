package view;

import com.prettymuch.blackjack.Card;
import javax.swing.*;
import java.awt.*;

public class CardLabel extends JLabel {

	private static final long serialVersionUID = 5739716308342064649L;
	private JLabel cardImage;
	private ImageIcon img;
	private Image img_;
	private Image resizedImg;

	public CardLabel(Card card) {

		img = new ImageIcon(card.getImg());
		img_ = img.getImage();
		resizedImg = img_.getScaledInstance(68, 100, Image.SCALE_SMOOTH);
		img = new ImageIcon(resizedImg);
		cardImage = new JLabel(img);

	}

	public CardLabel() {

		img = new ImageIcon("src/img/back.png");
		img_ = img.getImage();
		resizedImg = img_.getScaledInstance(68, 100, Image.SCALE_SMOOTH);
		img = new ImageIcon(resizedImg);
		cardImage = new JLabel(img);

	}

	public JLabel getCardImage() {
		return cardImage;
	}
}
