
public class IFID {
	Instruction x = new Instruction();
	String ins = x.getInstruction();
	int pc = x.getPc();
	public String getIns() {
		return ins;
	}
	public int getPc() {
		return pc;
	}
	boolean flag = true;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	} 
	public Instruction getX() {
		return x;
	}
	
}

