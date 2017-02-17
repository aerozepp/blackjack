package view;


import javax.swing.*;
import java.io.IOException;

public final class Main {

	static JFrame frame = null;

    public static void main(String[] args) throws IOException {
    	
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
				try {
					frame = new MainView("Blackjack 2017");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(650, 450);
                frame.setVisible(true);
            }
        });


    }
}
