
public class Rule3 extends RuleA{

	
	
	
	
	public FluidFrame createNextFrame(FluidFrame inFrame) {
		
	    NextFrameThread frameThread= new NextFrameThread(inFrame,this);
	    
	    return frameThread.runRuleByThread();
	   	
}
	
	
@Override
public int createNextCell(int inVal) {
   if(inVal==21||inVal==42)
	   return inVal;
   else
   {
	   int outVal=0;
		for (int dir = 0; dir < 6; dir++) 
	{
			if (ParticleCell.hasDirectionFlag(inVal, dir))
			{	
				outVal= ParticleCell.setFlag(outVal, ParticleCell.getOppositeDirection(dir));
		
		    }
	}
	return outVal;
   }

}
}
