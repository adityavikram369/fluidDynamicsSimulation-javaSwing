import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class MyApp extends AMyApp implements ActionListener  {

	private Logger log = Logger.getLogger(MyApp.class.getName());
	private JPanel mainPanel = null; 
	
	
	private JButton startBtn = new JButton("Start");
	
	private JButton pauseBtn = new JButton("Pause");
	private JButton exitBtn= new JButton("Exit");
	private JButton resumeBtn= new JButton("Resume");
	private JComboBox<String> ruleList= new JComboBox<>(new String[]{"BasicRule","Rule1", "Rule2","Rule3","Rule4"});
	private JComboBox<String> displayList= new JComboBox<>(new String[] {"CellAverage30 x 30","2 x 2 Average","3 x 3 Average",
			                                                             "5 x 5 Average","6 x 6 Average"});	
	private JTextField maxNumberFrame =new JTextField("15");
	
	
	private Queue<double[][][]> framequeue= null;
	private double ratio;
	
	
	
	private FluidSimData fluidSim=null;
	private FluidSimUI fluidSimResultPanel=null;
	
	
	private boolean pause;
	private boolean running;
	
	
	
	public  MyApp(int maxFrame, int frameSize)
	{
		
	
	log.info("App Started");
	
    //  frameList= new ArrayList<>();
    framequeue= new ArrayDeque<>();
    fluidSim= new FluidSimData(maxFrame, frameSize);
    fluidSimResultPanel= new FluidSimUI();
    pause=false;
    running=false;
    menuMgr.createDefaultActions();
    
    
	}
	
	
	
	
	 public void runUI()
	 {
		 frame.setSize(1250,700);
		 frame.setResizable(true);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setLayout(new BorderLayout());
		 frame.add(getMainPanel(),BorderLayout.NORTH);
		 frame.setVisible(true);
		 		  
	 }
	
	 
	 
	 
	
	 
	 
	 
	 class FluidSimData implements Runnable , ActionListener
	 {
		 private int maxNum;
		 private RuleA rule=null;
         private int frameSize;
         private int NxN=1;
         
		 
		 
		public FluidSimData(int maxNum, int frameSize) {
		 this.maxNum=maxNum;
		 this.frameSize=frameSize;
		 rule= new BasicRule();
		 
		}
		
		
		@Override
		public void run() {
			
			runFluidSim(maxNum);
		    startBtn.setEnabled(true);
		    pauseBtn.setEnabled(false);
		    resumeBtn.setEnabled(false);		
			
		}
		
	
		
		
		private  void fluidSim(FluidFrame f, int num)
		{
			if(num==0)  return;
			
			while(pause){try { this.wait(); }catch(Exception x) {};}
			
			System.out.println("FluidFrame "+(maxNum-num)+" :");
			f.drawFrameToConsole();
			
		    
			framequeue.add(rule.calculateNxNaverage(f, NxN, 20));
			
			ratio=rule.getratio();
			
			try {Thread.sleep(400);}catch(Exception e){}
			
			
			System.out.println();
			num--;
			
			if(num>0)
			{
			f=rule.createNextFrame(f);
			fluidSim(f,num);
			}
			
			
		}
		
		
		
		public void runFluidSim(int numFrame)
		{
			FluidFrame f= new FluidFrame(frameSize); 
			f.addRandomParticles(0.5);
			fluidSim(f,numFrame);
			
		}
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			
			if(e.getSource().equals(ruleList))
			{
				
				if(ruleList.getSelectedItem().equals("rule1")) rule= new Rule1();
				if(ruleList.getSelectedItem().equals("rule2")) rule= new Rule2();
				if(ruleList.getSelectedItem().equals("rule3")) rule= new Rule3();
				if(ruleList.getSelectedItem().equals("rule4")) rule= new Rule4();
				if(ruleList.getSelectedItem().equals("BasicRule")) rule= new BasicRule();
			}
			
			
			if(e.getSource().equals(displayList))
			{
				if(displayList.getSelectedItem().equals("2 x 2 Average")) NxN=2;
				if(displayList.getSelectedItem().equals("3 x 3 Average")) NxN=3;
				if(displayList.getSelectedItem().equals("5 x 5 Average")) NxN=5;
				if(displayList.getSelectedItem().equals("6 x 6 Average")) NxN=6;
				if(displayList.getSelectedItem().equals("CellAverage30 x 30"))  NxN=1;
				
			}
			
			if(e.getSource().equals(maxNumberFrame))
			{
				int input;
				try { input=Integer.parseInt(maxNumberFrame.getText()); maxNum=input;}catch(Exception x){};
				
			}
		    
		
		}
		 
		 
	 }
	 
	 
	 
	 
	

	 
	class FluidSimUI extends JPanel  implements  ActionListener, Runnable 
	{
		
		double[][][] frameAverage=null;
		
		
		public FluidSimUI() 
		{	  
		}
		
		
		@Override
		public void run() {
			
		
		   try {Thread.sleep(100);} catch(Exception e) {};
		       
		   paint(getGraphics());
		       
		     	}
		
		
		
		
		@Override
		public void paint(Graphics g) {
			
		  
		  drawFluidSimResult(g);
		 
		  
		}
     
     
		
		public void drawFluidSimResult(Graphics g)
		{   
			
			frame.getContentPane().revalidate();
			frame.getContentPane().repaint();
			
			Graphics2D g2d=(Graphics2D) g;
			
			Dimension size=getSize();
			
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, size.width, size.height);
			
			g2d.setColor(Color.GREEN);
			
		
		
			int cellsize=40;   
			
			
			
			if(!framequeue.isEmpty())
			{
               frameAverage=framequeue.poll();
			}
			    
			    	
				for(int y=0; y<frameAverage.length;y++)
				{
					for(int x=0; x<frameAverage[y].length;x++)
					{
						 
						double[] cellAverage=frameAverage[y][x];
						
						int xstart=20+x*cellsize;
						int ystart=20+y*cellsize;
						int xend=0;
						int yend=0;
						
						if(cellAverage[0]==-1) 
						{
						   xend=xstart;
						   yend=ystart;
						}
						else
						{	
							xend=(int)(xstart+cellAverage[1]*ratio);
							yend=(int)(ystart+cellAverage[0]*ratio);						   	
						}
								
						g2d.setColor(Color.GREEN);
		                g2d.drawLine(xstart, ystart, xend, yend);
						//g2d.drawString(Double.toString(frameAverage[y][x]),x_axis+=30,y_axis);	
					    
					}
				     
				   
				}
				
			   
							
		}
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
		
		
	}
	 
	 
	 	 
	 

	 
	
	public JPanel getMainPanel()
	{   
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
			
		startBtn.addActionListener(this);
		pauseBtn.addActionListener(this);
		resumeBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		pauseBtn.addActionListener(fluidSim);
		pauseBtn.addActionListener(fluidSimResultPanel);
		resumeBtn.addActionListener(fluidSim);
		resumeBtn.addActionListener(fluidSimResultPanel);
		ruleList.addActionListener(fluidSim);
		maxNumberFrame.addActionListener(fluidSim);
		displayList.addActionListener(fluidSim);
		
		resumeBtn.setEnabled(false);
		pauseBtn.setEnabled(false);
		
		mainPanel.add(exitBtn);
		mainPanel.add(startBtn);
		mainPanel.add(pauseBtn);
		mainPanel.add(resumeBtn);
		mainPanel.add(ruleList);
		mainPanel.add(maxNumberFrame);
		mainPanel.add(displayList);
		mainPanel.setBackground(Color.black);
		
		return mainPanel;
	}

	
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("ActionEvent: " + e);
		
		Thread Tfluidsim= new Thread(fluidSim);
		Thread TfluidsimUI= new Thread(fluidSimResultPanel);
		
		if (e.getActionCommand().equalsIgnoreCase("Start"))
			{ 
			      
			   	  if(!running)
			   	  {
				  Tfluidsim.start();		   
				  TfluidsimUI.start();
				  frame.add(fluidSimResultPanel);
				  startBtn.setEnabled(false);
				  pauseBtn.setEnabled(true);
			      resumeBtn.setEnabled(true);
			   	  }
			   	  else
			   	  {
			   		  System.out.println("Simulation is running!!");
			   	  }
			   	  
			   	  
			   	  			   
			}
		
		
		if(e.getActionCommand().equalsIgnoreCase("Pause")) pause=true;
	
		
		if(e.getActionCommand().equalsIgnoreCase("Resume")) pause=false;
		
		
		if(e.getActionCommand().equalsIgnoreCase("Exit"))
		{
			frame.dispose();
			System.exit(0);
		}
		   
		
	}
	

	
	public static void main(String[] args) {

	    MyApp app= new MyApp(15,30);  //MaxFluidFrameNumber FluidFrameSize	    
	    app.runUI();		

	    
}
	
}