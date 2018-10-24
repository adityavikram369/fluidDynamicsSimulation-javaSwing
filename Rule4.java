import java.util.HashMap;
import java.util.Map;

public class Rule4 extends RuleA{
	
	 Map<Integer,Integer> rule5= new HashMap<>();
	
	 
	 Rule4()
	 {
		    rule5.put(54, 27);
			rule5.put(27, 45);
			rule5.put(45, 54);
	 }
	 

	 public FluidFrame createNextFrame(FluidFrame inFrame) {
			
		    NextFrameThread frameThread= new NextFrameThread(inFrame,this);
		    
		    return frameThread.runRuleByThread();		    
			
	}	 
	 
	 
@Override
public int createNextCell(int inVal) {
	
	
	if(rule5.containsKey(inVal))return rule5.get(inVal);
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
