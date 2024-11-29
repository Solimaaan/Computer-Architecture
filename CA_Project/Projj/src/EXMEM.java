
public class EXMEM {
	IDEX e = new IDEX();
	Instruction x = e.getX();
	
	boolean mem = e.isMem();
	boolean wb = e.isWb();
	int pc = e.getPc();
	int Alu = e.getOutput();
	int r = x.getRtmp();
	String op = e.getOp();
	public IDEX getE() {
		return e;
	}
	public Instruction getX() {
		return x;
	}
	public boolean isMem() {
		return mem;
	}
	public boolean isWb() {
		return wb;
	}
	public int getPc() {
		return pc;
	}
	public int getAlu() {
		return Alu;
	}
	public int getR() {
		return r;
	}
	public String getOp() {
		return op;
	}
}
