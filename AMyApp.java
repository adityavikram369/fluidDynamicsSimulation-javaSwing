


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public abstract class AMyApp implements ActionListener {
	protected JFrame frame = null;
	protected MainMenu menuMgr = null;

	
	public AMyApp() {
		
      initGUI();
	}
	
	
    public void initGUI() {
    	frame = new JFrame();
	

		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		
		
		menuMgr = new MainMenu(this);
		
		frame.setJMenuBar(menuMgr.getMenuBar()); 
		
		
    }
    
    
    public abstract JPanel getMainPanel() ;

    
    

    
    public void exit() {
    	frame.dispose();
    	System.exit(0);
    }

   
    public void showHelp() {
    }
	
}
