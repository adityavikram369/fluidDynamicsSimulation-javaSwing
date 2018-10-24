


public class BasicRule extends RuleA {

	
	
	
	public BasicRule() {}

	
	public FluidFrame createNextFrame(FluidFrame inFrame) {
		
	    NextFrameThread frameThread= new NextFrameThread(inFrame,this);
	    
	    return frameThread.runRuleByThread();
	    
		
}
		

	@Override
	public int createNextCell(int inVal) {
		
		int outVal=0;
		
		for (int dir = 0; dir < 6; dir++) {
			if (ParticleCell.hasDirectionFlag(inVal, dir))
			{	
				outVal= ParticleCell.setFlag(outVal, ParticleCell.getOppositeDirection(dir));
				//int direction= ParticleCell.setFlag(outVal, ParticleCell.getOppositeDirection(dir)); // Just pass through in the opposite direction from where it came
		        //outVal=ParticleCell.setFlag(outVal, direction);
		    }
		}
		
		
	
		
		return outVal;
 
 }

}
