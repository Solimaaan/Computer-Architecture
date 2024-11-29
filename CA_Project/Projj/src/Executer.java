public class Executer {
Instruction x = new Instruction();
int pc = 0;

//static String y = x.getInstruction();
static int count = Instruction.count;
static int n = 7 + ((count - 1) * 2);					
static String u = "";
int startid = 0;
int startex = 0;
int startmem = 0;
int startwb = 0;
String rs = "";

public void pipeline() {
	
	for(int i = 1; i<=n; i++) {
		System.out.println("cycle " + i + ":");
		IDEX b = new IDEX();
		EXMEM m = new EXMEM();
		MEMWB mb = new MEMWB();
		pc = x.getPc();
		System.out.println("PC: " + pc);
		if(i%2==1 && pc <= count - 1) {
			u = (String) x.fetch(pc);			
		}
		if(i >= 2 && i%2==0 && startid <= count*2){
			x.decode(u);
			startid++;
		}
		if(i >= 3 && i%2==1 && startid <= count*2){
			x.decode1(u);
			startid++;
		}
		if(i >= 4 && i%2 == 0 && startex < count*2 ) {
			x.execute(b.getOp(),b.getR1(),b.getR2(),b.getR3(),b.getShift(),b.getAddr(),b.getImm(),b.getPc());
			rs = b.getOp();
			startex++;
			
		}
		if(i >= 5 && i%2 == 1 && startex < count*2 ) {
			x.execute1(rs,b.getR1(),b.getR2(),b.getR3(),b.getShift(),b.getAddr(),b.getImm());
			startex++;
		}
		if(i >= 7 && i%2 == 1 && startwb <= count) {
				x.WriteBack(mb.getOp(), mb.getAlu(), mb.getR());
				startwb++;
		}
		if( i >= 6 && i%2 == 0 && startmem <= count  ){
			x.Mem(m.getOp(),m.getR(),m.getAlu() );
			startmem++;
		}

	}
	for (int i = 0; i <= x.getRegisterFile().length - 1 ; i++) {
		int [] array = new int[32];
		array = x.getRegisterFile();
		System.out.println(array[i]);
		
	}
}

}
