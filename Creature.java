//Creature
//SheInnovates Spring 2018 - team edgelord
// Danielle Moonah
// Aife
// CeCe
// Abby
import java.util.*;
import java.lang.Character;

class Creature {

	static Eye eye = new Eye(new Genome("RrbbwwCC"));
	//static Fur fur = new Fur(new Genome(""));
	static String name = "Jerry";
	public static void main( String[] args ){
		Scanner in = new Scanner(System.in);
		//what body part do you want to change ?
		//list parts:
		
	//static Genome genonme = new Genome();

		System.err.println("Name: "+ name);
		System.err.println(eye.toString());

	}

	public String toString(){
		return String.format("Name: %s\n--Eyes--\n%s\n", name ,eye);
	}
//Genetic Code <-- toString();
//Breeding
	
}


class GeneSelector extends GeneHalf{
	//String gene;
	char left;
	char right;
	char display;
	GeneSelector( String gene ){
		super(gene);
		left = gene.charAt(0);
		right = gene.charAt(1);
		if( right > left ){
			display = right;
		}else if ( right <= left ){
			display = left;
		}	
	}

}


class GeneCoDom extends GeneHalf{
	char display;
	boolean dominance = false;
	char dom = 'Z';
	GeneCoDom(String gene, char midway){
		
		super(gene);
		left = gene.charAt(0);
		right = gene.charAt(1);
		hetero = true;
		if(left == right){
			hetero = false;
		}else if ( left == dom || right == dom ){ //finding 'Z' dominating allele 
			display = left;
			//break;

		}else if((left == right ^ right == dom)){
			hetero =true;
		}
			
	}
		

}


class GeneHalf{ //"RR"
	char left;
	char right;
	boolean dominance = true;
	boolean hetero = false;
	GeneHalf(String gene){
		left = gene.charAt(0);
		right = gene.charAt(1);
		if(Character.isUpperCase(left) || Character.isUpperCase(right))
			dominance = true;
		else
			dominance = false;
		if(Character.isUpperCase(left) ^ Character.isUpperCase(right))
			hetero = true;
		else
			hetero =false;
	}

}

class Gene { // "RRBB" left = RR right= BB
	protected GeneHalf left;
	protected GeneHalf right;
	protected String gene;
	Gene(String gene){
		this.gene = gene;
		left = new GeneHalf(gene.substring(0,2));
		right = new GeneHalf(gene.substring(2,4));
	}

	String gene(){
		return this.gene;
	}
	//input would be something 
	public static void stringToGene( String gene ){}

	public static void wordToGene(String bodyPart, String trait){}
	//Gene representation = 
}

class Genome{
	String genome = "RRBBwwCC"; //Red light cutesy eye type

	Genome(String genome){
		this.genome = genome;
	}

}

//Eyes: color, shade, type
class Eye{

	EyeColor eyeColor;
	EyeShade eyeShade;
	EyeType  eyeType;
	String   genome;

	Eye(Genome genome){
		this.genome = genome.genome;
		setEyeColor(new Gene(this.genome.substring(0,4)));
		setEyeShade(new GeneHalf(this.genome.substring(4,6)));
		setEyeType(new GeneSelector(this.genome.substring(6,8)));
	}

	boolean setEyeShade(GeneHalf gene){ //BB Bw ww 

		if(!gene.dominance ){
			this.eyeShade = EyeShade.WHITE;
			return true;
		}else if(gene.hetero){
			this.eyeShade = EyeShade.NORMAL;
			return true;
		}else if(gene.dominance){
			this.eyeShade = EyeShade.BLACK;
			return true;
		}else{
			System.err.println("Error: Ugh, I can't believe you've done this.");
			return false;
		}

	}
	//true if successfully change, otherwise false
	boolean setEyeColor(Gene gene){ 
		if( gene.left.dominance &&  gene.right.dominance){ //RB
			this.eyeColor = EyeColor.RED;
			return true;
		} else if( gene.left.dominance ^  gene.right.dominance ){//hetero --> yellow
			this.eyeColor = EyeColor.YELLOW; //needs to account for the two different kinds of Yellow
			return true;
		} else if( !gene.left.dominance && !gene.right.dominance){ //rb --> blue
			this.eyeColor = EyeColor.BLUE;
			return true;
		} else{
			System.err.println("Error: Ugh, I can't believe you've done this.");
			return false;
		}
		
	}

	boolean setEyeType( GeneSelector gene ){
			// type: Round(C), sad(B), cat(A)
		//input: AA, AB, BB, BC, CC, AC
		// et1 > et2 > et3
		switch(gene.display){
			case 'C':
				this.eyeType = EyeType.ROUND;
				return true;
			case 'B':
				this.eyeType = EyeType.SAD;
				return true;
			case 'A':
				this.eyeType = EyeType.CAT;
				return true;
			default:
				System.err.println("Error: Ugh, I can't believe you've done this.");
				return false;
		}

	}


	enum EyeColor{
		//color: R  B
		//input : "RRBB"
		// RB = red, rb = blue, Rb/rB =Yellow 
		RED, YELLOW, BLUE;

	}

	enum EyeShade{
		// black, white
		// BB = dark, BW = normal, WW = lighter
		BLACK, NORMAL, WHITE

	}

	enum EyeType{
		// type: Round(C), sad(B), cat(A)
		// et1 > et2 > et3
		ROUND,  SAD, CAT, 
	}

	public String toString(){
		return String.format("\tEye Color: %s\n\tEye Shade: %s\n \tEye Type: %s\n",eyeColor.name(), eyeShade.name(), eyeType.name());
	}
}

/*
//Fur: color, type
class Fur{

		FurType furType;
		FurColor furColor;

	Fur(Genome genome){
		this.genome = genome.genome;
		setFurType(new Gene(this.genome.substring(0,2)));
		//setFurColor(new GeneHalf(this.genome.substring(4,8)));
	}
	//
	boolean setFurType(){
		//incomplete dominance

	}

	boolean setFurColor(){
		//codominance

	}

	enum FurType{
		//incomplete dominance
		//type: short(), medium = SL, long(), none(Z)?
		SHORT, MEDIUM, LONG, NONE
	}
	enum FurColor{
		//color: R Y B 
		RED, YELLOW, BLUE;
	}

	//toString
}

*/

/*
//mouth: size, type
class Mouth{
	MouthType mouthType;
	MouthSize mouthSize;


}

enum MouthType{
	SHARP, BLUNT, ROUNDED
}

enum MouthSize{
	SMALL, BIG, MEDIUM
	
}

/*
//antenna: size,type
enum AntennaType{

}

enum AntennaSize{
	
}

enum Wing{

}*/
//wings: size,type
	//color: R Y B*/