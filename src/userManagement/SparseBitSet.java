package userManagement;

import java.io.Serializable;

public class SparseBitSet implements Cloneable, Serializable{
	
	protected transient int CompactionCount;
	
	static int CompactionCountDefault = 2;
	
	protected transient long[][][] bits;
	
	protected transient int bitsLength;
	
	protected static final int LENGTH4 = Long.SIZE;
	
	protected static final int INDEX_SIZE = Integer.SIZE - 1;
	
	

	
	
}
