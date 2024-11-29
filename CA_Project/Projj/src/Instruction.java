import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Instruction {
	static ArrayList<String> data = new ArrayList<String>();
	static final int R0 = 0;
	static int registerFile [] = {R0,1,4,5,7,16,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//yenfa3 array for registers?
	static String Memory[] = new String [2048];
	static int pc = 0;
	static String instruction = "add";
	String temp = "";
	static int count = 0;
	static int  singlemem = 0;
	
	static int output = 0;
	static int rtmp = 0;

	static String opcode = "0000";  
	static int r1 = 1;      
	static int r2 = 2;      
	static int r3 = 3;     
	static int shamt = 0;   
	static int imm = 0;     
	static int address = 0; 
	String henn = "";
	static int num = 0;
	
	public String fetch(int p) {
	instruction = Memory[p];
	pc++;
	System.out.println("fetching: " + instruction);
	return instruction;
	}

	public void decode(String instruction1) {
		System.out.println("decoding: " + instruction1);

		opcode = instruction1.substring(0,4);
		System.out.println("Opcode: " + opcode);
		if (opcode == "0011" || opcode == "0100" || opcode == "0110" || opcode == "1010" || opcode == "1011" ) {
			temp = instruction1.substring(4,9); 
			r1 = Integer.parseInt(temp,2);
			System.out.println("r1: " + r1);
			
			temp = instruction1.substring(9,15);
			r2 = Integer.parseInt(temp,2);
			System.out.println("r2: " + r2);
			
			temp = instruction1.substring(13,32);
			imm = Integer.parseInt(temp,2);
			System.out.println("imm: " + imm);
			
		}else if(opcode == "0111") {
			temp = instruction1.substring(3,32);
			address = Integer.parseInt(temp,2);
			System.out.println("address: " + address);
		}else {
			
			temp = instruction1.substring(4,9); 
			r1 = Integer.parseInt(temp,2);
			System.out.println("r1: " + r1);
			
			temp = instruction1.substring(9,14);
			r2 = Integer.parseInt(temp,2);
			System.out.println("r2: " + r2);
			
			temp = instruction1.substring(14,19);
			r3 = Integer.parseInt(temp,2);
			System.out.println("r3 " + r3);
			
			temp = instruction1.substring(19,32);
			shamt = Integer.parseInt(temp,2);
			System.out.println("shamt: " + shamt);
		}

	}
	public void decode1(String instruction1) {
		System.out.println("Second decode cycle for " + instruction1);

	}

	public void execute(String o, int R1, int R2 , int R3 , int Shamt, int Address, int Imm, int pcc) {
		System.out.println("execute start for " + o);
		switch(o) {
		case ("0000"):
			output = registerFile[R2] + registerFile[R3];
			rtmp = R1;
		System.out.println("Executing Add on registers " + registerFile[R2] + " and " + registerFile[R3] );
			break;
		case ("0001"):
			output = registerFile[R2] - registerFile[R3];
			rtmp = R1;
		System.out.println("Executing subtract on registers " + registerFile[R2] + " and " + registerFile[R3]);
			break;
		case ("0010"):
			output = registerFile[R2] * registerFile[R3];
			rtmp = R1;
		System.out.println("Executing multiply on registers " + registerFile[R2] + " and " + registerFile[R3] + "answer = " + output );
			break;
		case ("0011"):
			output = Imm; 
			rtmp = R1;
		System.out.println("Executing move immediate on register " + registerFile[R1] );
			break;
		case ("0100"):
			if(registerFile[R1]==registerFile[R2]) {
				pc = pc+1+Imm;
				 System.out.println("executing bas");
			}
		System.out.println("Executing jump if equal " );
			break;
		case ("0101"):
			output = registerFile[R2] & registerFile[R3];
			rtmp = R1;
		System.out.println("Executing and operation on registers " + registerFile[R2]  + " and " + registerFile[R3] );
			break;
		case ("0110"):
			output = registerFile[R2] ^ Imm; 
			rtmp = R1;
		System.out.println("Executing Exclusive or immediate on registers " + registerFile[R2]  + " and " + Imm );
			break;
		case ("0111"):
			String trr = Integer.toBinaryString(pcc);
			int temppp = Integer.parseInt(trr);
			int bi = (temppp - 1) & 0b11110000000000000000000000000000;
			output = concat(bi,Address);
		System.out.println("Executing jump to " + Address);
			break;
		case ("1000"):
			output = registerFile[R2] << Shamt;
			rtmp = R1;
		System.out.println("Executing shift left on register " + registerFile[R2] );
			break;
		case ("1001"):
			output = registerFile[R2] >>> Shamt;
			rtmp = R1;
		System.out.println("Executing shift right on register " + registerFile[R2] );
			break;
		case ("1010"):
			output = R2+Imm;
			rtmp = R1;
		System.out.println("Executing move to register on index " + registerFile[R2] + "+" + Imm);
			break;
		case ("1011"):
			output = R1;
			rtmp = R2 + Imm;
		System.out.println("Executing move from register to memory on index " + registerFile[R2] + "+" + Imm);
			break;
	}
	}
		public int concat(int bi, int address2) {
		String g = toString(bi);
		String b = toString(address2);
		String c = g + b;
		int d = Integer.parseInt(c);
		return d;
	}

		public void execute1(String o, int R1, int R2 , int R3 , int Shamt, int Address, int Imm) {
			System.out.println("Second execute cycle for " + o);
			switch(o) {
			case ("0000"):
			System.out.println("Executing Add on registers " + registerFile[R2] + " and " + registerFile[R3] );
				break;
			case ("0001"):
			System.out.println("Executing subtract on registers " + registerFile[R2] + " and " + registerFile[R3]);
				break;
			case ("0010"):
			System.out.println("Executing multiply on registers " + registerFile[R2] + " and " + registerFile[R3]);
				break;
			case ("0011"):
			System.out.println("Executing move immediate on register " + registerFile[R1]);
				break;
			case ("0100"):
			System.out.println("Executing jump if equal " );
				break;
			case ("0101"):
			System.out.println("Executing and operation on registers " + registerFile[R2]  + " and " + registerFile[R3] );
				break;
			case ("0110"):
			System.out.println("Executing Exclusive or immediate on registers " + registerFile[R2]  + " and " + Imm );
				break;
			case ("0111"):
			System.out.println("Executing jump to " + Address);
				break;
			case ("1000"):
			System.out.println("Executing shift left on register " + registerFile[R2] );
				break;
			case ("1001"):
			System.out.println("Executing shift right on register " + registerFile[R2] );
				break;
			case ("1010"):
			System.out.println("Executing move to register on index " + registerFile[R2] + "+" + Imm);
				break;
			case ("1011"):
			System.out.println("Executing move from register to memory on index " + registerFile[R2] + "+" + Imm);
				break;
		}
		
	}
	public void WriteBack(String o, int out, int rtm ) {
		System.out.println("write back cycle");
		switch(o){
		case ("0000"):
			registerFile[rtm] = out;
		System.out.println("Writing back on " + rtm + " value: " + out );
			break;
		case ("0001"):
			registerFile[rtm] = out;
		System.out.println("Writing back on " + rtm + " value: " + out );
			break;
		case ("0010"):
			registerFile[rtm] = out;
		System.out.println("Writing back on " + rtm + " value: " + out );
			break;
		case ("0011"):
			registerFile[rtm] = out;
		System.out.println("Writing back on " + rtm + " value: " + out );
			break;
		case ("0101"):
			registerFile[rtm] = out;
		System.out.println("Writing back on " + rtm + " value: " + out );
			break;
		case ("0110"):
			registerFile[rtm] = out;
		System.out.println("Writing back on " + rtm + " value: " + out );
			break;
		case ("0111"):
			pc = out;
		System.out.println("Writing back on " + rtm + " value: " + out );
			break;
		case ("1000"):
			registerFile[rtm] = out;
		System.out.println("Writing back on " + rtm + " value: " + out );
			break;
		case ("1001"):
			registerFile[rtm] = out;
		System.out.println("Writing back on " + rtm + " value: " + out );
			break;
		case ("1010"):
			registerFile[rtm] = Integer.parseInt(Memory[out]);
		System.out.println("moving from memory to register " + rtm + "the value at index " + out );
			break;
	}
	}
	
	public void Mem(String opc , int rtm, int out) { 
		System.out.println("memory cycle");
		if(opc.equals("1011")) {
			Memory[rtm] = toString(out);
			System.out.println("storing into memory value: " + out + " into index " + rtm );
		}else {
			System.out.println("Doesn't need memory");
		}
	}
	
	public static void parser(String path) throws IOException {
		FileReader filereader = new FileReader(path);
		BufferedReader br = new BufferedReader(filereader);
		String currentline = br.readLine();
		while(currentline!=null) {
			data.add(currentline);
			currentline=br.readLine();
			count++;
		}
		br.close();
		for(int i = 0; i<data.size();i++) {
			String oneInst = data.get(i); //one instruction from array list
			String [] allElements = oneInst.split(" "); // elements of instruction separated
			String element = ""; // final code will be in it
			String e = allElements[0].toLowerCase();
			switch(e) {
			case "add":
				element += "0000";
				break;

			case "sub":
				element += "0001";
				break;

			case "mul":
				element += "0010";
				break;

			case "movi":
				element += "0011";
				break;
			case "jeq":
				element += "0100";
				break;
			case "and":
				element += "0101";
				break;
			case "xori":
				element += "0110";
				break;
			case "jmp":
				element += "0111";
				break;
			case "lsl":
				element += "1000";
				break;
			case "lsr":
				element += "1001";
				break;
			case "movr":
				element += "1010";
				break;                
			case "movm":
				element += "1011";
				break;
			default:
				break;
			}
			if(e.equals("jmp")) { // only jump has 2 parts and one is 28 bit address 
				int z = Integer.parseInt(allElements[1]);
				String f = Integer.toBinaryString(z);
				while(f.length()<28) { //add zeros till address is complete to 28 bits
					f = "0" + f;
				}
				element = element + f;
			}
			else {
				element = element + binaryChange(allElements[1]);// change first register to binary
				if(e.equals("movi")){
					element = element + "00000";
					int z = Integer.parseInt(allElements[2]);//only those have immediate as last part (element 3)
					String a = Integer.toBinaryString(z);
					while(a.length()<18) { //immediate part consists of 18 bits
						a = "0" + a;
					}
					element = element + a;
				}
				else {
				element = element + binaryChange(allElements[2]);// change second register to binary
			}}
			if (e.equals("jeq") ||e.equals("xori") ||e.equals("movr") ||e.equals("movm")) {
				int z = Integer.parseInt(allElements[3]);//only those have immediate as last part (element 3)
				String a = Integer.toBinaryString(z);
				while(a.length()<18) { //immediate part consists of 18 bits
					a = "0" + a;
				}
				element = element + a;}
			
			
			else if (e.equals("lsl") || e.equals("lsr")) { //instructions that have shamt
				element = element + "00000";
				int z = Integer.parseInt(allElements[3]);//last register
				String a = Integer.toBinaryString(z);
				while(a.length()<13) {
					a = "0" + a;
				}
				element = element + a;
		}
			else if (e.equals("add")|| e.equals("sub") || e.equals("mul") || e.equals("and")){
				element = element + binaryChange(allElements[3]);
				element = element + "0000000000000"; //shamt zeros
		}
			Memory[i] = element;
			System.out.println(Memory[i]);
		}
	}
	public static String binaryChange(String a) { //method to change registers to binary
		String registerString = a.substring(1); 
		int b = Integer.parseInt(registerString);
		String c = Integer.toBinaryString(b);
		while (c.length() < 5) {
			c = "0" + c;
		}
		return c;
	}
	
	private String toString(int instruction2) {
		return "" + instruction2;
	}



	public int getPc() {
		return pc;
	}

	public String getInstruction() {
		return instruction;
	}
	

	public String getOpcode() {
		return opcode;
	}


	public ArrayList<String> getData() {
		return data;
	}


	public static int getR0() {
		return R0;
	}


	public int[] getRegisterFile() {
		return registerFile;
	}


	public static String[] getMemory() {
		return Memory;
	}


	public String getTemp() {
		return temp;
	}


	public static int getCount() {
		return count;
	}

	public static int getSinglemem() {
		return singlemem;
	}

	public int getOutput() {
		return output;
	}

	public int getRtmp() {
		return rtmp;
	}

	public int getR1() {
		return r1;
	}

	public int getR2() {
		return r2;
	}

	public int getR3() {
		return r3;
	}

	public int getShamt() {
		return shamt;
	}

	public int getImm() {
		return imm;
	}

	public int getAddress() {
		return address;
	}

	public String getHenn() {
		return henn;
	}

	public static int getNum() {
		return num;
	}

	public static void main(String[] args) {
		
		try {
			parser("/Users/ahmedsoliman/eclipse-workspace/Projj.zip_expanded.zip_expanded/Projj.zip_expanded/Projj/src/Instructions");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println(data);
			System.out.println(count);
			
		
	Executer executer = new Executer();
	
	executer.pipeline();
			
}}
