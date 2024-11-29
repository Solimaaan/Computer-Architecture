public class MEMWB {
	EXMEM e = new EXMEM();
	boolean mem = e.isMem();
	int Alu = e.getAlu();
	String op = e.getOp();
	int r = e.getR();
	int p = e.getPc();
	public int getP() {
		return p;
	}
	public EXMEM getE() {
		return e;
	}
	public boolean isMem() {
		return mem;
	}
	public int getAlu() {
		return Alu;
	}
	public String getOp() {
		return op;
	}
	public int getR() {
		return r;
	}
}
