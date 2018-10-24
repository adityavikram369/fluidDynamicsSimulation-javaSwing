


public class FluidFrame {

	
	int frame[][] = null;
	int size = 16; // the current grid size
	
	/**
	 * Constructor
	 */
	public FluidFrame(int size) {
		this.size = size;
		frame = new int[size][size]; // Dynamically build the grid based on the input size
	}
    
	
	
	
    public int getSize() {
		return size;
	}
    
    
	
	public int getCellOutValue(int x, int y) {
		if (x < 0) x = 0;
		if (x >= size) x = size-1;
		if (y < 0) y = 0;
		if (y >= size) y = size-1;
		
		return frame[x][y];
	}
	
	
	public void setCellOutValue(int x, int y, int val) {
		if (x < 0) return;
		if (x >= size) return;
		if (y < 0) return;
		if (y >= size) return;
		frame[x][y] = val;
	}
	
	
	public void addCellOutParticle(int x, int y, int direction) {
		int curVal = getCellOutValue(x,y);
		
		curVal = ParticleCell.setFlag(curVal, direction);
		setCellOutValue(x,y,curVal);
	}
	
	
	public int getCellInValue(int x, int y) {
		
		
		
		
		int inVal = 0;
		//Implement edge condition
		
		int outval=getCellOutValue(x, y);
	
		if(y==0)
		{
			
			
			if(x==0)
			{
			   if(ParticleCell.hasDirectionFlag(outval, 0))  //Direction 0
			   {
				  inVal+=1;   
			   }
			   
			   if(ParticleCell.hasDirectionFlag(outval, 1))  //Direction 1
			   {
				   inVal+=2;
			   }
			   
			   if(ParticleCell.hasDirectionFlag(outval, 2))  //Direction 2
			   {
				   inVal+=4;
			   }
			   
			   if(ParticleCell.hasDirectionFlag(outval, 5)) //Direction 5
			   {
				   inVal+=32;
			   }
			   
			   int neighborOutCell = getNeighborCellOutValue(x, y, 3);
				// Does it have a particle in the opposite direction?
				if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(3)))  //Direction 3
					inVal = ParticleCell.setFlag(inVal, 3); // If so, then add that direction to our inValue
			     
				neighborOutCell=getNeighborCellOutValue(x,y,4);
				
				if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  //Direction 4
					inVal =ParticleCell.setFlag(inVal, 4);
			}
			
			else if(x==size-1)
			{
				  if(ParticleCell.hasDirectionFlag(outval, 1))  //Direction 1
				   {
					   inVal+=2;
				   }
				   
				   if(ParticleCell.hasDirectionFlag(outval, 2))  //Direction 2
				   {
					   inVal+=4;
				   }
				   
				   if(ParticleCell.hasDirectionFlag(outval, 3))
				   {
					   inVal+=8;
				   }
				   
				    int neighborOutCell = getNeighborCellOutValue(x, y, 0);
					// Does it have a particle in the opposite direction?
					if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(0)))  //Direction 3
						inVal = ParticleCell.setFlag(inVal, 0); // If so, then add that direction to our inValue
				     
					neighborOutCell=getNeighborCellOutValue(x,y,4);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 4);
				   
                    neighborOutCell=getNeighborCellOutValue(x,y,5);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 4);
				   
			}
			else
			{
				  if(ParticleCell.hasDirectionFlag(outval, 1))
				  {
					  inVal+=2;
				  }
				  
				  if(ParticleCell.hasDirectionFlag(outval, 2))  
				   {
					   inVal+=4;
				   }
				  
				  int neighborOutCell = getNeighborCellOutValue(x, y, 0);
					// Does it have a particle in the opposite direction?
					if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(0)))  //Direction 3
						inVal = ParticleCell.setFlag(inVal, 0); // If so, then add that direction to our inValue
				     
					neighborOutCell=getNeighborCellOutValue(x,y,3);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(3)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 3);
				   
                   neighborOutCell=getNeighborCellOutValue(x,y,4);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 4);
                   
					neighborOutCell=getNeighborCellOutValue(x,y,5);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(5)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 5);			  
				  
			}
						
		}
		
		
		
		
		
		
		else if(y==size-1)
		{
			 if(x==0)
			 {
				 if(ParticleCell.hasDirectionFlag(outval, 0))  //Direction 0
				   {
					  inVal+=1;   
				   }
				 
				 if(ParticleCell.hasDirectionFlag(outval, 4))
				 {
					 inVal+=16;
				 }
				 if(ParticleCell.hasDirectionFlag(outval, 5)) //Direction 5
				   {
					   inVal+=32;
				   }
				   
				 
				 if((size-1)%2==0&&ParticleCell.hasDirectionFlag(outval, 1)) 
					 { inVal+=2;
					
					 }
				 else
				 {
					    int neighborOutCell = getNeighborCellOutValue(x, y, 1);
						// Does it have a particle in the opposite direction?
						if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  //Direction 3
							inVal = ParticleCell.setFlag(inVal, 1); // If so, then add that direction to our inValue				     
				 }
				 
				 
				 
				 
				   int neighborOutCell = getNeighborCellOutValue(x, y, 2);
					// Does it have a particle in the opposite direction?
					if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(2)))  //Direction 3
						inVal = ParticleCell.setFlag(inVal, 0); // If so, then add that direction to our inValue
				     
					neighborOutCell=getNeighborCellOutValue(x,y,3);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(3)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 3);				 
			 }
			 else if(x==size-1)
			 {
				 
					 if(ParticleCell.hasDirectionFlag(outval, 3))  //Direction 0
					   {
						  inVal+=8;   
					   }
					 
					 if(ParticleCell.hasDirectionFlag(outval, 4))
					 {
						 inVal+=16;
					 }
					 
					 if(ParticleCell.hasDirectionFlag(outval, 5)) //Direction 5
					   {
						   inVal+=32;
					   }
					   
					 
					 if((size-1)%2==0&&ParticleCell.hasDirectionFlag(outval, 2)) 
						 { inVal+=4;
						
						 }
					 else
					 {
						    int neighborOutCell = getNeighborCellOutValue(x, y, 2);
							// Does it have a particle in the opposite direction?
							if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(2)))  //Direction 3
								inVal = ParticleCell.setFlag(inVal, 2); // If so, then add that direction to our inValue				     
					 }
					 
					 
					 
					 
					   int neighborOutCell = getNeighborCellOutValue(x, y, 0);
						// Does it have a particle in the opposite direction?
						if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(0)))  //Direction 3
							inVal= ParticleCell.setFlag(inVal, 0); // If so, then add that direction to our inValue
					     
						neighborOutCell=getNeighborCellOutValue(x,y,1);
						
						if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  //Direction 4
							inVal=ParticleCell.setFlag(inVal, 1);	
									 
				 
			 }
			 else
			 {
				 if(ParticleCell.hasDirectionFlag(outval, 5))  //Direction 0
				   {
					  inVal+=32;   
				   }
				 
				 if(ParticleCell.hasDirectionFlag(outval, 4))
				 {
					 inVal+=16;
				 }
				 
				 
				    int neighborOutCell = getNeighborCellOutValue(x, y, 0);
					// Does it have a particle in the opposite direction?
					if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(0)))  //Direction 3
						inVal = ParticleCell.setFlag(inVal, 0); // If so, then add that direction to our inValue
				     
					neighborOutCell=getNeighborCellOutValue(x,y,1);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 1);
					
                    neighborOutCell=getNeighborCellOutValue(x,y,2);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 2);
					
                   neighborOutCell=getNeighborCellOutValue(x,y,3);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(3)))  //Direction 4
						inVal=ParticleCell.setFlag(inVal, 3);			 
				 
			 }
			 
			 			   								
		}
			 
		
		
		
		
			 
			 
		else if(x==0 && y!=0 && y!=size-1)
		{
			
           if(y%2==0)// y==even
           {
        	   if(ParticleCell.hasDirectionFlag(outval, 0))  //Direction 0
			   {
				  inVal+=1;   
			   }
			 
			  if(ParticleCell.hasDirectionFlag(outval, 1))
			  {
				 inVal+=2;
			  }
			 
			 if(ParticleCell.hasDirectionFlag(outval, 5)) //Direction 5
			   {
				   inVal+=32;
			   }
			   
			 
			    int neighborOutCell = getNeighborCellOutValue(x, y, 2);
				// Does it have a particle in the opposite direction?
				if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(2)))  
					inVal = ParticleCell.setFlag(inVal,2); // If so, then add that direction to our inValue
			     
				neighborOutCell=getNeighborCellOutValue(x,y,3);
				
				if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(3)))  
					inVal=ParticleCell.setFlag(inVal, 3);
				
                neighborOutCell=getNeighborCellOutValue(x,y,4);
				
				if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  
					inVal=ParticleCell.setFlag(inVal, 4);
				 	 
           }
           else  //y==odd
           {
        	   if(ParticleCell.hasDirectionFlag(outval, 0))  //Direction 0
			   {
				  inVal+=1;   
			   }
        	   
        	   for(int i=1; i<6;i++)
        	   {
        		   int neighborOutCell = getNeighborCellOutValue(x, y, i);
   				// Does it have a particle in the opposite direction?
   				if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(i)))  
   					inVal = ParticleCell.setFlag(inVal,i); // If so, then add that direction to our inValue
        	   }
        	   
        	   
           }
			
			
		}
		
		
		
			 
			 
			 
		else if(x==size-1 && y!=0 && y!=size-1)
		{
			
			
			if(y%2==0) //y even 
			{
				if(ParticleCell.hasDirectionFlag(outval, 3))  //Direction 0
				   {
					  inVal+=8;   
				   }
				   
				   int neighborOutCell = getNeighborCellOutValue(x, y, 0);
					// Does it have a particle in the opposite direction?
					if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(0)))  
						inVal = ParticleCell.setFlag(inVal,0); // If so, then add that direction to our inValue
				     
					neighborOutCell=getNeighborCellOutValue(x,y,1);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  
						inVal=ParticleCell.setFlag(inVal, 1);
					
	                neighborOutCell=getNeighborCellOutValue(x,y,2);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(2)))  
						inVal=ParticleCell.setFlag(inVal, 2);
					
                   neighborOutCell=getNeighborCellOutValue(x,y,4);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(4)))  
						inVal=ParticleCell.setFlag(inVal, 4);
					
                     neighborOutCell=getNeighborCellOutValue(x,y,5);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(5)))  
						inVal+=ParticleCell.setFlag(inVal,5);
									 	 
			}
			else   //y odd
			{
				 if(ParticleCell.hasDirectionFlag(outval, 2))  //Direction 0
				   {
					  inVal+=1;   
				   }
				 
				  if(ParticleCell.hasDirectionFlag(outval, 3))
				  {
					 inVal+=8;
				  }
				 
				 if(ParticleCell.hasDirectionFlag(outval, 4)) //Direction 5
				   {
					   inVal+=16;
				   }
				 
				 int neighborOutCell = getNeighborCellOutValue(x, y, 0);
					// Does it have a particle in the opposite direction?
					if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(0)))  
						inVal = ParticleCell.setFlag(inVal,0); // If so, then add that direction to our inValue
				     
					neighborOutCell=getNeighborCellOutValue(x,y,1);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(1)))  
						inVal=ParticleCell.setFlag(inVal, 1);
					
	                neighborOutCell=getNeighborCellOutValue(x,y,5);
					
					if(ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(5)))  
						inVal=ParticleCell.setFlag(inVal, 5);
				 
				 
			}
			
		}
			 
			 
			 
		else
		{
			for (int dir = 0; dir < 6; dir++) 
			    {
				// Get the cell in a given direction from our current cell
				int neighborOutCell = getNeighborCellOutValue(x, y, dir);
				// Does it have a particle in the opposite direction?
				if (ParticleCell.hasDirectionFlag(neighborOutCell, ParticleCell.getOppositeDirection(dir)))
					inVal = ParticleCell.setFlag(inVal, dir); // If so, then add that direction to our inValue
				}
						
		}
	
		
		
		
		
		return inVal;
	}   
	
	
	private int getNeighborCellOutValue(int x, int y, int direction) {
		if ((y & 1) == 0) { // y is even
			switch (direction) {
			  default:
			  case 0: return (getCellOutValue(x-1, y));   // Left
			  case 1: return (getCellOutValue(x-1, y-1)); // UL
			  case 2: return (getCellOutValue(x  , y-1)); // UR
			  case 3: return (getCellOutValue(x+1, y));   // Right
			  case 4: return (getCellOutValue(x  , y+1)); // LR
			  case 5: return (getCellOutValue(x-1, y+1)); // LL	
			}
		}
		else { // y is odd
			switch (direction) {
			  default:
			  case 0: return (getCellOutValue(x-1, y));   // Left  
			  case 1: return (getCellOutValue(x, y-1)); // UL     (x+1, y-1)  
			  case 2: return (getCellOutValue(x+1, y-1)); // UR    (x+2,y-1) 
			  case 3: return (getCellOutValue(x+1, y));   // Right 
			  case 4: return (getCellOutValue(x+1, y+1)); // LR     (x+2,y+1)
			  case 5: return (getCellOutValue(x, y+1));   // LL	    (x+1,y+1)
			}
		}
	}
	
	/**
	 * Fill up the current frame up a a specified percentage (i.e. 100% yields an average of one particle direction per cell)
	 * @param percent
	 */
	public void addRandomParticles(double percent) {
		if (percent > 1.0) percent = 1.0; // Don't allow us to fill all cells
		
		int total = size * size; // This is the maximum number of cells
		
		total *= percent;
		
		for (int i = 0; i < total; i++)
			addRandomParticle();
	}
	
	/**
	 * Add a single random particle - give it a direction value
	 */
	private void addRandomParticle() {
		double maxSize = size + .99; // Well want a range from 0.00 to size.99. When integerized we'll get 0, 1, 2, ... size
		// Create a random X
		int x = (int) (Math.random() * maxSize);
		int y = (int) (Math.random() * maxSize);
		// Create a random y
		// Create a random direction
		//int direction = (int) (Math.random() * 6.99);
		//direction = 3; // Test - force all particle to move right
		
		setCellOutValue(x, y, (int)(Math.random()*10000)%64);
		
		// Add the CellOutParticle
	    //addCellOutParticle(x,y,direction); // add it, or if the particle already exists, just overlay it
		
	}
	
	/**
	 * Draw the current frame to the console
	 */
	public void drawFrameToConsole() {
		//char dispChar = ' ';
		for (int y = 0; y <getSize(); y++) {

			if ((y & 1) > 0) // y is odd if true
				System.out.print(" ");
			for (int x = 0; x < getSize(); x++) {
				
		       int cel = getCellOutValue(x, y);	       
		       
		       if(cel==0) System.out.print("-"+" ");
		    	  
		       else 
		       {
		    	   System.out.print("[");
		    	   for(int i=0; i<6;i++)
		    	   {
		    		   if(ParticleCell.hasDirectionFlag(cel,i))
		    			   System.out.print(i);
		    	   }
		    	   System.out.print("]");
		    	   
		       }
		       
			}
			System.out.println(""); 
			
		}
	}


	
}
