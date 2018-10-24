



public class ParticleCell {

	
	public static final int DIR_0 = 0b000001; 
	public static final int DIR_1 = 0b000010; 
	public static final int DIR_2 = 0b000100; 
	public static final int DIR_3 = 0b001000; 
	public static final int DIR_4 = 0b010000; 
	public static final int DIR_5 = 0b100000; 

	
	public static boolean hasDirectionFlag(int cellVal, int direction) {
		
		switch (direction) {
		default:
		case 0: return ((cellVal & DIR_0) > 0); 
		case 1: return ((cellVal & DIR_1) > 0); 
		case 2: return ((cellVal & DIR_2) > 0);
		case 3: return ((cellVal & DIR_3) > 0);
		case 4: return ((cellVal & DIR_4) > 0);
		case 5: return ((cellVal & DIR_5) > 0);
		}
	}
	
	
	
	public static int setFlag(int cellVal, int direction) {
		
		switch (direction) {
		default:
		case 0: cellVal = setDirectionFlag(cellVal, DIR_0); break;
		case 1: cellVal = setDirectionFlag(cellVal, DIR_1); break;
		case 2: cellVal = setDirectionFlag(cellVal, DIR_2); break;
		case 3: cellVal = setDirectionFlag(cellVal, DIR_3); break;
		case 4: cellVal = setDirectionFlag(cellVal, DIR_4); break;
		case 5: cellVal = setDirectionFlag(cellVal, DIR_5); break;
		}
		return cellVal;
	}
	
	
	public static int setDirectionFlag(int cellVal, int directionFlag) {
		cellVal |= directionFlag; 
		return cellVal; 
	}
	
	
	public static int getOppositeDirection(int direction) {
		switch (direction) {
		default:
		case 0: return 3;
		case 1: return 4;
		case 2: return 5;
		case 3: return 0;
		case 4: return 1;
		case 5: return 2;
		}
	}
	
}
