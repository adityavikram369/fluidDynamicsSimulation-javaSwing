/**
 * 
 */



 
public abstract class RuleA implements RuleI {

	
	public RuleA() { }
     
	protected double ratio;
	protected double maxValue;
	 
	
	
	@Override
	
	 public FluidFrame createNextFrame(FluidFrame inFrame)
	{

	    NextFrameThread frameThread= new NextFrameThread(inFrame,this);
	    
	    return frameThread.runRuleByThread();
	}
    
	
	
	
	abstract public int createNextCell(int inVal) ;
	

	
	
	public double getratio()
	{
		return ratio;
	}
	
	
	public double[][][] calculateNxNaverage(FluidFrame frame, int conv_size, int vector)
	{
	     
		maxValue=Integer.MIN_VALUE;
		
		if(frame.size%conv_size==0)
		{
			
		double[][][] NxNaverage= new double[frame.size/conv_size][frame.size/conv_size][2]; 
		
		double[][][] cellAverage=calculateFrame(frame, vector);
		
		
		int a_y=0;
		for(int y=0; y<frame.size; y+=conv_size)
		{
			
			int a_x=0;
			for(int x=0; x<frame.size;x+=conv_size)
			{
				
				
				NxNaverage[a_y][a_x]= NxNAverage(y, x, conv_size, cellAverage,vector);
				
								
				
				a_x++;
			}
			
	
			a_y++;
		}
		
		
		ratio=vector/maxValue;
		
		
		return NxNaverage;
		
		
		
		
		}
		
		
		
		
		
		return null;
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	 public double[] NxNAverage(int i_y, int i_x, int conv_size, double[][][] cellAverage, int vector)
	 {
		 
		 double xValue=0;
		 double yValue=0;
		 
		 for(int y=i_y ; y<i_y+conv_size; y++)
		 {
			 
			 for(int x=i_x; x<i_x+conv_size; x++)
			 {
				 			 
				xValue-=cellAverage[y][x][1];
				yValue-=cellAverage[y][x][0];
				
			 }
			 
			 		
			 
		 }
		 
		 
		 
		 if(Math.max(xValue, yValue)>maxValue) maxValue=Math.max(xValue, yValue);
		 
		     
		 
		 return new double[] {yValue, xValue};
		 
	 }
	
	
	
		
	
	 
	 
	
	public double[][][] calculateFrame(FluidFrame frame, int vector)
	{
		double[][][] frameCellaverage= new double[frame.size][frame.size][2];
		
		for(int y=0; y<frame.size;y++)
		{
			
			for(int x=0; x<frame.size; x++)
			{
				frameCellaverage[y][x]=calculateCell(frame.getCellOutValue(x, y),vector);		
			}
			
		}
		
		return frameCellaverage;		
		
	}
	
	
	
	
	
	public double[] calculateCell( int inVal, int vector)
	{
		
		int xValue=0;
		int yValue=0;
		
		for (int dir = 0; dir < 6; dir++) 
	    {
		
		
		if (ParticleCell.hasDirectionFlag(inVal, dir))
		{
			 double deg=0;
			
			 switch(dir)
			 {
			 case 0: deg=0.0;    break;
			 case 1: deg=60.0;   break;
			 case 2: deg=120.0;  break;
			 case 3: deg=180.0;  break; 
			 case 4: deg=240.0;  break;
			 case 5: deg=300.0;  break;
			 }
			
			
			xValue-=vector*Math.cos(Math.toRadians(deg));
			yValue-=vector*Math.sin(Math.toRadians(deg));
		     
		}
		
	    
	    
	    }
		
			
		return new double[] {yValue,xValue};
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	

}
