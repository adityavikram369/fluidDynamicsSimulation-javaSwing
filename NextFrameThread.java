
public class NextFrameThread {

	private FluidFrame inFrame;
	private FluidFrame outFrame;
	RuleA rule= null;
	
	public NextFrameThread(FluidFrame frame, RuleA rule) {
		
		this.inFrame=frame;
		this.rule=rule;
		outFrame= new FluidFrame(frame.size);
		
	}
	
	
	
	
	
	
	class CreateNextFrame extends Thread
	{
		int x;
		int y;
		
		CreateNextFrame(int x, int y)
		{
			this.x=x;
			this.y=y;			
		}
		
		@Override
		public void run() {
		 
		 
		 int inboundValue=inFrame.getCellInValue(x, y);
		 int outboundValue=rule.createNextCell(inboundValue);
		 outFrame.setCellOutValue(x, y, outboundValue); 
		}	
		
	}
	
		
	
	public FluidFrame runRuleByThread()
	{
		
		
	   for(int y=0; y<inFrame.size;y++)
	   {
		   for(int x=0; x<inFrame.size;x++)
		   {
			   CreateNextFrame f=new CreateNextFrame(x,y);
			   f.start();
			   try {f.join();}catch(InterruptedException e){e.printStackTrace();}
			       
		   }
		   
	   }
	   
	
	   
	    return outFrame;
		
	}
	
	
	
	
}
