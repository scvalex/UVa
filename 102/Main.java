// Solution to 102 - Ecological Bin Packing; STATUS: SOLVED

import java.util.*;

public class Main{

	static int bins[] = new int[9];

	static ArrayList<ArrayList<Bin>> perms = makePermutations();

	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		List<Bin>  sol = null;
		int i, totalMoves, minMoves;
		while (sc.hasNext()){
			for (i=0;i<9;i++)
				bins[i] = sc.nextInt();
			minMoves = Integer.MAX_VALUE;
			for (ArrayList<Bin> bs : perms) {
				i = 0;
				for (Bin b : bs) {
					b.setBottles(Color.B, bins[i++]);
					b.setBottles(Color.G, bins[i++]);
					b.setBottles(Color.C, bins[i++]);
				}

				totalMoves = 0;
				for (Bin b : bs) {
					totalMoves += b.moves(bs);
				}

				if (minMoves > totalMoves) {
					minMoves = totalMoves;
					sol = bs;
				}
			}			

			System.out.println(printSolution(sol) + " " + minMoves);
		}
	}

	static String printSolution(List<Bin> bs) {
		StringBuilder sb = new StringBuilder();
		for (Bin b : bs) {
			sb.append(b.getColor().toString());
		}
		return sb.toString();
	}

	static ArrayList<ArrayList<Bin>> makePermutations(){
		ArrayList<ArrayList<Bin>> perms = new ArrayList<ArrayList<Bin>>();
		perms.add(makeBins(Color.B, Color.C, Color.G));
		perms.add(makeBins(Color.B, Color.G, Color.C));
		perms.add(makeBins(Color.C, Color.B, Color.G));
		perms.add(makeBins(Color.C, Color.G, Color.B));
		perms.add(makeBins(Color.G, Color.B, Color.C));
		perms.add(makeBins(Color.G, Color.C, Color.B));
		return perms;
	}

	static ArrayList<Bin> makeBins(Color c1, Color c2, Color c3) {
		ArrayList<Bin> bns = new ArrayList<Bin>();
		bns.add(new Bin(c1));
		bns.add(new Bin(c2));
		bns.add(new Bin(c3));
		return bns;
	}

}

class Bin{
	Map<Color, Integer> bottleMap = new HashMap<Color, Integer>();
	Color color;
	
	Bin(Color color) {
		this.color = color;
	}

	Color getColor() {
		return color;
	}

	int getBottles(Color c) {
		return bottleMap.get(c);
	}

	void setBottles(Color c, int n) {
		bottleMap.put(c, n);
	}

	int moves(List<Bin> bins) {
		int moves = 0;
		for (Bin b : bins) {
			if (!b.getColor().equals(color)) {
				moves += b.getBottles(color);
			}
		}
		return moves;
	}
}

enum Color{
	B, G, C;
}

