package view;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel titleLabel;
    private JLabel creditLabel;

    private JPanel btnPanel;
    private JButton playBtn;
    private JButton recordBtn;

    public ControlPanel() {

    }

    public void setTitleLabel() {

        titleLabel = new JLabel("Black Jack 2017", JLabel.CENTER);
        titleLabel.setFont(new Font("serif", Font.BOLD, 35));
        titleLabel.setBackground(new Color(143, 225, 81));
        titleLabel.setOpaque(true);
    }

    public void setCreditLabel() {

        creditLabel = new JLabel("@pretty_much games", JLabel.CENTER);
        creditLabel.setFont(new Font("serif", Font.PLAIN, 15));
        creditLabel.setBackground(new Color(143, 225, 81));
        creditLabel.setOpaque(true);
    }

    public void setBtnPanel() {

        playBtn = new JButton("Play");
        recordBtn = new JButton("Record");

        btnPanel = new JPanel(new GridBagLayout());
        btnPanel.setBackground(new Color(143, 225, 81));
        btnPanel.add(playBtn);
        btnPanel.add(recordBtn);
    }

    public JButton getPlayBtn() {
        return playBtn;
    }

    public JButton getRecordBtn() {
        return recordBtn;
    }

    public void setControlPanel() {

        setLayout(new GridLayout(3, 1));

        setTitleLabel();
        setCreditLabel();
        setBtnPanel();

        this.add(titleLabel);
        this.add(btnPanel);
        this.add(creditLabel);

    }
    }
