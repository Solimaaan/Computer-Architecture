
public class IDEX {
	IFID y = new IFID();
	Instruction x = y.getX();
	boolean ex = true;
	boolean mem = false;
	boolean wb = false;
	String op = x.getOpcode();
	int pc = y.pc;
	int R1 = x.getR1();
	int R2 = x.getR2();
	int R3 = x.getR3();
	int shift = x.getShamt();
	int addr = x.getAddress();
	int imm = x.getImm();
	int output = x.getOutput();
	
	public Instruction getX() {
		return x;
	}
	public int getOutput() {
		return output;
	}
	public void setMem(boolean mem) {
		this.mem = mem;
	}
	public void setEx(boolean ex) {
		this.ex = ex;
	}
	public void setWb(boolean wb) {
		this.wb = wb;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public void setR1(int r1) {
		R1 = r1;
	}
	public void setR2(int r2) {
		R2 = r2;
	}
	public void setR3(int r3) {
		R3 = r3;
	}
	public void setShift(int shift) {
		this.shift = shift;
	}
	public void setAddr(int addr) {
		this.addr = addr;
	}
	public void setImm(int imm) {
		this.imm = imm;
	}
	public boolean isEx() {
		return ex;
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
	public String getOp() {
		return op;
	}
	public int getR1() {
		return R1;
	}
	public int getR2() {
		return R2;
	}
	public int getR3() {
		return R3;
	}
	public int getShift() {
		return shift;
	}
	public int getAddr() {
		return addr;
	}
	public int getImm() {
		return imm;
	}

}
