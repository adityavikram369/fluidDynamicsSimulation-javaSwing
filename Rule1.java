import java.util.HashMap;
import java.util.Map;

public class Rule1 extends RuleA {

	
	Map<Integer,Integer> rule1= new HashMap<>();
	
	Rule1()
	{
		rule1.put(9, 36);
		rule1.put(36, 18);
		rule1.put(18, 9);
	}
	
	
	public FluidFrame createNextFrame(FluidFrame inFrame) {
		
	    NextFrameThread frameThread= new NextFrameThread(inFrame,this);
	    
	    return frameThread.runRuleByThread();	    
		
}
	
	
	
		
	@Override
	public int createNextCell(int inVal) {
		
		if(rule1.containsKey(inVal)) 
		{
			return rule1.get(inVal);
		}
		else
		{   int outVal=0;
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
	

	

