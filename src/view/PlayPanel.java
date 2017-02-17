package view;

import javax.swing.*;
import java.awt.*;

public class PlayPanel extends JPanel {

	private static final long serialVersionUID = 7355231247946317697L;
	private JPanel boardPanel;
    private JPanel cardPanel;
    private JPanel statusPanel;

    private JPanel comBoardPanel;
    private JPanel comCardPanel;
    private JPanel comStatusPanel;

    private JLabel chips;
    private JLabel userChips;

    private JPanel valuePanel;
    private JLabel comValue;
    private JLabel value;
    private JLabel userValue;

    private JLabel bet;
    private JLabel userBet;


    private JPanel btnPanel;
    private JButton hitBtn;
    private JButton stayBtn;
    private JButton betBtn;
    private JButton exitBtn;
    private JButton resetBtn;


    public PlayPanel() {

    }

    public void setBoardPanel() {
        boardPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        boardPanel.setBackground(new Color(143, 225, 81));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;

        cardPanel = new JPanel();
        cardPanel.setBackground(new Color(143, 225, 81));
        cardPanel.setPreferredSize(new Dimension(450, 160));
        cardPanel.setBorder(BorderFactory.createTitledBorder("User"));


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        statusPanel = new JPanel(new GridLayout(6, 1));
        statusPanel.setBackground(new Color(143, 225, 81));
        statusPanel.setPreferredSize(new Dimension(140, 160));
        statusPanel.setBorder(BorderFactory.createTitledBorder("Status"));

        boardPanel.add(statusPanel, c);
        boardPanel.add(cardPanel, c);
    }

    public void setComBoardPanel() {
        comBoardPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        comBoardPanel.setBackground(new Color(143, 225, 81));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;

        comCardPanel = new JPanel();
        comCardPanel.setBackground(new Color(143, 225, 81));
        comCardPanel.setPreferredSize(new Dimension(450, 160));
        comCardPanel.setBorder(BorderFactory.createTitledBorder("Dealer"));


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        comStatusPanel = new JPanel(new GridLayout(5, 1));
        comStatusPanel.setBackground(new Color(143, 225, 81));
        comStatusPanel.setPreferredSize(new Dimension(140, 160));
        comStatusPanel.setBorder(BorderFactory.createTitledBorder("Status"));

        comBoardPanel.add(comStatusPanel, c);
        comBoardPanel.add(comCardPanel, c);
    }

    public void setBtnPanel() {
        btnPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        btnPanel.setBackground(new Color(143, 225, 81));
        exitBtn = new JButton("Exit");
        hitBtn = new JButton("Hit");
        stayBtn = new JButton("Stay");
        betBtn = new JButton("Bet");
        resetBtn = new JButton("Reset");
        c.insets = new Insets(0, 15, 0, 0);
        c.gridx = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        btnPanel.add(exitBtn, c);

        c.insets = new Insets (0,0,0,15);
        c.gridx = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.LINE_END;
        btnPanel.add(betBtn, c);
    }

    public void setStatusPanel() {

        JPanel chipPanel = new JPanel(new GridBagLayout());
        JPanel betPanel = new JPanel(new GridBagLayout());

        chipPanel.setBackground(new Color(143, 225, 81));
        betPanel.setBackground(new Color(143, 225, 81));

        valuePanel = new JPanel(new GridBagLayout());
        valuePanel.setBackground(new Color(143, 225, 81));

        userChips = new JLabel("100");
        chips = new JLabel("Chips : ");
        bet = new JLabel("Bet : ");
        userBet = new JLabel("0");
        value = new JLabel("Value : ");
        userValue = new JLabel("");


        valuePanel.add(value);
        valuePanel.add(userValue);


        chipPanel.add(chips);
        chipPanel.add(userChips);
        betPanel.add(bet);
        betPanel.add(userBet);

        statusPanel.add(chipPanel);
        statusPanel.add(betPanel);
        statusPanel.add(valuePanel);

        valuePanel = new JPanel( new GridBagLayout());
        valuePanel.setBackground(new Color(143, 225, 81));
        value = new JLabel("Value : ");
        comValue = new JLabel("");
        valuePanel.add(value);
        valuePanel.add(comValue);


        comStatusPanel.add(valuePanel);
    }

    public void setPlayPanel() {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setBackground(new Color(143, 225, 81));

        setBoardPanel();
        setComBoardPanel();
        setBtnPanel();
        setStatusPanel();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        this.add(comBoardPanel, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 20;
        this.add(btnPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 2;
        this.add(boardPanel, c);

    }

    public JPanel getBtnPanel() {
        return btnPanel;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public JPanel getComCardPanel() {
        return comCardPanel;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JPanel getComBoardPanel() {
        return comBoardPanel;
    }

    public JLabel getComValue() {
        return comValue;
    }

    public void setComValue(String str) {
        getComValue().setText(str);
    }

    public JLabel getUserValue() {
        return userValue;
    }

    public void setUserValue(String str) {
        getUserValue().setText(str);
    }

    public JLabel getUserChips() {
        return userChips;
    }

    public JButton getHitBtn() {
        return hitBtn;
    }

    public JButton getStayBtn() {
        return stayBtn;
    }

    public JButton getBetBtn() {
        return betBtn;
    }

    public JButton getResetBtn() {
        return resetBtn;
    }
    
    public JButton getExitBtn(){
    	return exitBtn;
    }
    
    

    public void setCardPanel() {
        cardPanel = new JPanel();
        cardPanel.setBackground(new Color(143, 225, 81));
        cardPanel.setPreferredSize(new Dimension(450, 160));
        cardPanel.setBorder(BorderFactory.createTitledBorder("User"));
    }

    public void setComCardPanel() {
        comCardPanel = new JPanel();
        comCardPanel.setBackground(new Color(143, 225, 81));
        comCardPanel.setPreferredSize(new Dimension(450, 160));
        comCardPanel.setBorder(BorderFactory.createTitledBorder("Dealer"));
    }

    public JLabel getUserBet() {
        return userBet;
    }
}
