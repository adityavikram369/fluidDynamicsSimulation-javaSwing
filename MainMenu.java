


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class MainMenu implements ActionListener {

	private String id = "";
	private String text = "";
	static int counter=0;
	private JMenuBar menuBar = null;
	private AMyApp application = null;

	private HashMap<String, AbstractButton> buttonMap = new HashMap<String, AbstractButton>();

	
	public MainMenu(AMyApp application) {
		this.application = application;
		this.id = "menu-" + Integer.toString(counter++);
		init();
	}

	
	public MainMenu(AMyApp application, String text) {
		this.application = application;
		this.text=text;
		this.id = "menu-" + Integer.toString(counter++);
		init();
	}

	
	public MainMenu(AMyApp application, String text, String id) {
		this.application = application;
		this.text = text;
		this.id = id;
		init();
	}

	
	public String getId() {
		return id;
	}

	
	public String getText() {
		return text;
	}

	
	private void init() {
		getMenuBar();
	}

	
	
	public void createDefaultActions() {

		JMenu fileMenu = addMenu(menuBar,"core.file", "File", 'F', "File Menu Actions");
		fileMenu.addSeparator();
		createMenuItem(fileMenu, "Exit", 'x', "Exit the application", new ExitAction(application));

		JMenu editMenu = addMenu(menuBar, "core.edit", "Edit", 'E', "Edit Menu Actions");
		createMenuItem(editMenu, "Undo", 'U', "Undo", this);
		createMenuItem(editMenu, "Redo", 'U', "Undo", this);
		editMenu.addSeparator();
		createMenuItem("core.edit", "Cut", KeyEvent.VK_X, ActionEvent.CTRL_MASK, "Cut", this);
		createMenuItem("core.edit", "Copy", KeyEvent.VK_C, ActionEvent.CTRL_MASK, "Copy", this);
		createMenuItem("core.edit", "Paste", KeyEvent.VK_V, ActionEvent.CTRL_MASK, "Paste", this);
		editMenu.addSeparator();
		createMenuItem(editMenu, "Delete", 'D', "Delete", this);

		JMenu windowMenu = addMenu(menuBar, "core.window", "Window", 'W', "PanelWindow Menu Actions");

		createMenuItem(windowMenu, "Close window", '*', "Close the active window", this);
		createMenuItem(windowMenu, "Maximize window", '*', "Expand the active window", this);
		createMenuItem("core.window", "Undock window", 
				KeyEvent.VK_D, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK,
				"Move the active window pane into a detached window", this);
		windowMenu.addSeparator();

		JMenu helpMenu = addMenu(menuBar, "core.help", "Help", 'H', "Help Menu Actions");
		createMenuItem(helpMenu, "About...", '*', "About...", new AboutAction(application));
	}

	
	public JMenuBar getMenuBar() {
		if (menuBar == null)
			menuBar = new JMenuBar();

		return menuBar;
	}

	
	public JMenu getMenu(String menuPath) {
		return (JMenu) buttonMap.get(menuPath);
	}

	
	public JMenu addMenu(String menuPath, String menuName, char menuMnemonic, String desc) {
		return (createMenu(menuBar,menuPath,menuName,menuMnemonic,desc, -1));
	}

	
	public JMenu createMenu(String menuPath, String menuName, char menuMnemonic, String desc, int pos) {
		return (createMenu(menuBar,menuPath,menuName,menuMnemonic,desc, pos));
	}

	
	private JMenu addMenu(JMenuBar mBar, String menuPath, String menuName, char menuMnemonic, String desc) {
		return( createMenu(mBar, menuPath, menuName, menuMnemonic, desc, -1));
	}

	
	public JMenu createMenu(JMenuBar mBar, String menuPath, String menuName, char menuMnemonic, String desc) {
		return( createMenu(mBar, menuPath, menuName, menuMnemonic, desc, 2));
	}


	public JMenu createMenu(JMenuBar mBar, String menuPath, String menuName, char menuMnemonic, String desc, int pos) {
	

		String fullMenuPath = menuPath;
		if (menuPath.length() == 0)
			fullMenuPath = "core." + menuName.toLowerCase();

		AbstractButton bmenu = buttonMap.get(fullMenuPath);
		if (bmenu != null)
			return ((JMenu) bmenu);

		JMenu menu = new JMenu(menuName);

		if (menuMnemonic != ' ')
			menu.setMnemonic(menuMnemonic);

		menu.getAccessibleContext().setAccessibleDescription(desc);

		
		buttonMap.put(fullMenuPath, menu);

		if (pos < 0)
			menu = menuBar.add(menu);
		else
			menu = (JMenu) menuBar.add(menu,pos);

		return menu;
	}

	
	public void addSeparator(String menuPath) {
		JMenu menu = (JMenu) buttonMap.get(menuPath);
		if (menu == null) return;
		menu.addSeparator();
	}

	
	public void insertSeparator(String menuPath, int index) {
		JMenu menu = (JMenu) buttonMap.get(menuPath);
		if (menu == null) return;
		menu.insertSeparator(index);
	}

	public JMenuItem createMenuItem(String menuPath, String label, int mnemonic, 
			String accessibleDescription, ActionListener action) {
		//System.out.println("MenuManager:: Adding menu item - path: " + menuPath + " label: " + label);
		JMenu menu = (JMenu) buttonMap.get(menuPath);
		if (menu == null){
			System.err.println("MenuManager:: Unable to locate a menu with path: " + menuPath);
			return null;
		}
		return( createMenuItem(menu, label, mnemonic, accessibleDescription, action));
	}

	private JMenuItem mi = null;

	
	public JMenuItem createMenuItem(final JMenu menu, final String label, final int mnemonic,
			final String accessibleDescription,final ActionListener action) {
		

		String buttonPath = menu.getName() + "." + label;
		buttonMap.put(buttonPath, menu);

		mi = (JMenuItem) menu.add(new JMenuItem(label));

		if (mnemonic != ' ') {
			mi.setMnemonic(mnemonic);
		}

		mi.getAccessibleContext().setAccessibleDescription(
				accessibleDescription);
		mi.setToolTipText(accessibleDescription);
		mi.addActionListener(action);
		if (action == null) {
			mi.setEnabled(false);
		}

		return mi;
	}

	
	public JMenuItem createMenuItem(String menuPath, String label, int keyevent, int modifier, 
			String accessibleDescription, ActionListener action) {
		return createMenuItem(menuPath, label, keyevent, modifier, accessibleDescription, action, -1);
	}

	
	public JMenuItem createMenuItem(String menuPath, String label, int keyevent, int modifier, 
			String accessibleDescription, ActionListener action, int pos) {

		JMenu menu = (JMenu) buttonMap.get(menuPath);
		if (menu == null) {
			System.err.println("MenuManager:: Unable to locate a menu with path: " + menuPath);
			return null;
		}
		return( createMenuItem(menu, label, keyevent, modifier, accessibleDescription, action, pos));
	}


	
	public JMenuItem createMenuItem(JMenu menu, String label, int keyevent, int modifier,
			String accessibleDescription, ActionListener action) {
		return(createMenuItem(menu,label,keyevent,modifier,accessibleDescription,action,-1));
	}


	
	public JMenuItem createMenuItem(JMenu menu, String label, int keyevent, int modifier,
			String accessibleDescription, ActionListener action, int pos) {
		

		JMenuItem mi;
		if (pos < 0) 
			mi = (JMenuItem) menu.add(new JMenuItem(label));
		else
			mi = (JMenuItem) menu.insert(new JMenuItem(label),pos);

		if (keyevent != 0) {
			mi.setAccelerator(KeyStroke.getKeyStroke(keyevent, modifier));
			
		}
		mi.getAccessibleContext().setAccessibleDescription(
				accessibleDescription);
		mi.setToolTipText(accessibleDescription);
		mi.addActionListener(action);
		if (action == null) {
			mi.setEnabled(false);
		}
		String buttonPath = menu.getName() + "." + label;
		buttonMap.put(buttonPath, menu);
		return mi;
	}


	
	public JCheckBoxMenuItem createCheckBoxMenuItem(String menuPath, String label, int mnemonic, 
			String accessibleDescription, ActionListener action) {

		JMenu menu = (JMenu) buttonMap.get(menuPath);
		if (menu == null) return null;
		return( createCheckBoxMenuItem(menu, label, mnemonic, accessibleDescription, action));
	}

	/**
	 * Creates a generic CheckBox menu item
	 */
	public JCheckBoxMenuItem createCheckBoxMenuItem(JMenu menu, String label, int mnemonic,
			String accessibleDescription, ActionListener action) {
		JCheckBoxMenuItem mi = (JCheckBoxMenuItem) menu.add(new JCheckBoxMenuItem(label));
		mi.setMnemonic(mnemonic);
		mi.getAccessibleContext().setAccessibleDescription(accessibleDescription);
		mi.addActionListener(action);
		if (action == null) {
			mi.setEnabled(false);
		}
		return (mi);
	}

	private int menuNum = 0;

	
	
	public AbstractButton getMenuButton(String menuPath) {
		return(buttonMap.get(menuPath));
	}

	
	public void addActionListener(String buttonId, ActionListener listener) {
		AbstractButton button = getMenuButton(buttonId);
		if (button == null) return;
		button.addActionListener(listener);
	}


	public void update() {
		menuBar.repaint();
	}

	public void updateMenuEnableStatus() {

	}

	public void actionPerformed(ActionEvent arg0) {

		System.out.println("MenuManager:: Received an Action: " + arg0.getActionCommand() +
				" param " + arg0.paramString());
		application.actionPerformed(arg0);
	}

	
	
	
	class ExitAction extends AbstractAction {

		private static final long serialVersionUID = -9197382694558803756L;
		private AMyApp application;

		protected ExitAction(AMyApp application) {
			super("ExitAction");
			this.application = application;
		}

		public void actionPerformed(ActionEvent e) {
			application.exit();
		}
	}	

	
	class AboutAction extends AbstractAction {

		private static final long serialVersionUID = -9197382694558803756L;
		private AMyApp application;

		protected AboutAction(AMyApp application) {
			super("AboutAction");
			this.application = application;
		}

		public void actionPerformed(ActionEvent e) {
			application.showHelp();
		}
	}		

}
