package Assignment04;

import java.util.*;

public class Prob3 {

	public static void main(String[] args) {
		
		Code[] five_symbol = new Code[5];
		five_symbol[0] = new Code('A', 0.35);
		five_symbol[1] = new Code('B', 0.1);
		five_symbol[2] = new Code('C', 0.2);
		five_symbol[3] = new Code('D', 0.2);
		five_symbol[4] = new Code('_', 0.15);
		
		makeHuffman(five_symbol);
	}
	
	public static void makeHuffman(Code[] symbols) {
		
		TreeSet<HuffmanTree> con = new TreeSet<HuffmanTree>();
		
		for(int i = 0; i < symbols.length; i++) {
			
			con.add(new HuffmanTree(symbols[i]));
		}
		
		while(con.size() > 1) {
			
			HuffmanTree l = con.first();
			con.remove(l);
			
			HuffmanTree r = con.first();
			con.remove(r);
			
			HuffmanTree root = new HuffmanTree(l, r);
			con.add(root);
		}
		
		if(con.size() > 0) {
			
			HuffmanTree hct = con.first();
			con.remove(hct);
			
			hct.print();
		}
	}
}

class Code implements Comparable<Code>{
	
	char symbol;
	double frequency;
	
	public Code(char symbol, double frequency) {
		
		this.symbol = symbol;
		this.frequency = frequency;
	}
	
	public Code(Code c) {
		
		this.symbol = c.symbol;
		this.frequency = c.frequency;
	}

	public int compareTo(Code other) {
		
		if(this.frequency == other.frequency) {
			
			return (int) (this.symbol - other.symbol);
		}
		else if(this.frequency > other.frequency) {
			
			return 1;
		}
		else {
			
			return -1;
		}
	}
}

class HuffmanTree extends Code{
	
	HuffmanTree l, r;
	
	public HuffmanTree(HuffmanTree l, HuffmanTree r) {
		
		super((l.symbol < r.symbol) ? l.symbol : r.symbol, l.frequency + r.frequency);
		
		this.l = l;
		this.r = r;
	}
	
	public HuffmanTree(Code c) {

		super(c);
	}

	public int compareTo(HuffmanTree other) {
		
		return super.compareTo(new Code(other.symbol, other.frequency));
	}
	
	void print(String codeword) {
		
		if(l == null && r == null) {
			
			System.out.println(symbol + " " + frequency + " " + codeword);
		}
		
		if(l != null) {
			
			l.print(codeword + 0);
		}
		
		if(r != null) {
			
			r.print(codeword + 1);
		}
	}
	
	void print() {
		
		print("");
	}
}
