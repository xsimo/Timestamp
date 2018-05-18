package ca.xsimo;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import ca.udem.duranlef.Keyboard;

/**
 * This class Ts (for TimeStamp) calculates the current age (in miliseconds)
 * and prints out the first two thousand billion milionseconds anniversaries
 * The introduction text is aimed to present the unix timestamp system.
 * Big digit flips since 1990 can be added in unused printBigDigitsFlips method
 * and are left there for curious folks
 * @author Simon Arame Xsimo.CA
 */
public class Ts {
	
	final static int K = 1000;
	static int CURRENT_YEAR;
	static NumberFormat nf;
	
	public static void main(String [] args){
		
		/*initrd*/
		long a = System.currentTimeMillis();
		GregorianCalendar g0 = new GregorianCalendar();
		g0.setTimeInMillis(a);
		CURRENT_YEAR = g0.get(Calendar.YEAR);
		nf = NumberFormat.getIntegerInstance(Locale.CANADA);
		
		/*WELCOME*/
		System.out.print(System.getProperty("line.separator"));
		System.out.println("Welcome to the computers timestamp system presentation");
		System.out.println();
		System.out.println("Your current execution of this program was launched at timestamp : "+nf.format(a/K));
		System.out.println();
		System.out.println("Here are the 9 figures flips since the internet generalization:");
		
		/*STD COFFEE LIKE RFC */
		printBigDigitsFlips();
		/*EDGE*/
		System.out.println("And this once in a lifetime event: ");
		System.out.println(nf.format(1234567890)+":  13/Feb/2009");
		System.out.println();
		System.out.println("With your help, this program will now calculate your age in seconds");
		System.out.println();
		System.out.println("(The program does not overcomes the limitation for persons born before 1970.");
		System.out.println("Moreover, it relies on user to avoid invalid dates like September 31st)");
		System.out.println();	
		/*GATHERING*/
		System.out.println("What is your date of birth ?");
		System.out.println();
		String yearInput = validateBoundedIntInput("Year [ 1970-"+CURRENT_YEAR+" ]? : ",1970,g0.get(Calendar.YEAR));
		String monthInput = validateBoundedIntInput("Month [ 1  12 ]? : ",1,12);
		String dayInput = validateBoundedIntInput("Day [ 1 31 ]? : ",1,31);
		String hourInput = validateBoundedIntInput("Hour [ 0 23 ]? : ",0,23);
		String timezoneInput = validateTimezoneInput("timezone [ -12 12 ]? : ");
		System.out.println();
		String YOURBIRTHDAY = dayInput+"/"+monthInput+"/"+yearInput+" ";
		YOURBIRTHDAY += hourInput+":00:00 "+timezoneInput+"00";
		
		/*PARSING GATHERED*/
		Date d = null;
		try {
			d = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss Z").parse(YOURBIRTHDAY);
		} catch (ParseException e) {
			System.out.println("There was a problem parsing the values you entered."+
			System.getProperty("line.separator")+"The program will now close.");
			System.exit(711);
		}
		
		GregorianCalendar g = new GregorianCalendar();
		GregorianCalendar g1 = new GregorianCalendar();
		GregorianCalendar g2 = new GregorianCalendar();
		
		g.setTime(d);
		g1.setTimeInMillis(1*(long)Math.pow(10,12)+g.getTimeInMillis());
		g2.setTimeInMillis(2*(long)Math.pow(10,12)+g.getTimeInMillis());
		
		Date d1 = g1.getTime();
		Date d2 = g2.getTime();			
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		/*OUPUT THE RESULT*/
		System.out.println();
		System.out.println("You are "+nf.format((System.currentTimeMillis()-g.getTimeInMillis())/K)+" seconds old");
		System.out.println("Your 1st billion seconds is on "+sdf.format(d1));
		System.out.println("Your 2nd billion seconds is on "+sdf.format(d2));
		
		System.out.println("Have a nice day !");
	}
	
	public static String validateTimezoneInput(String invite){
		
		String result = validateBoundedIntInput(invite,-12,12);
		int input = Integer.parseInt(result);
		
		if(input<0 && input>-10){
			result = "-0"+Math.abs(input);
			
		}else if(input>=0 && input<10){
			result = "+0"+input;
			
		}else if(input <0){
			result = "-"+input;
			
		}else{
			result = "+"+input;
		}
		
		return result;
	}
	
	public static String validateBoundedIntInput(String invite, int lowerBound, int upperBound){
		
		System.out.print(invite);
		
		int input = Keyboard.readInt();
		
		while(input>upperBound || input<lowerBound){
		
			System.out.println("The number has to be between "+lowerBound+" and "+upperBound);
			System.out.print(invite);
			input = Keyboard.readInt();
		}
		
		String result = ""+input;
		/*padding*/
		if(lowerBound<10 && input<10 && input>=0){
			result = "0"+input;
		}
		
		return result;
	}
	
	public static void printBigDigitsFlips(){
		
		final int COMMUNICATION_ERA = 1990;
		final int FIRST_FLIP = 7;
		
		long BIG = (long) Math.pow(10,8);
		
		GregorianCalendar g = new GregorianCalendar();
		g.setTimeInMillis((BIG*FIRST_FLIP));
		
		double NUMBER_OF_SECONDS_PER_YEAR = (60*60*24*365.25);
		
		double NUMBER_OF_FLIPS_PER_YEAR = NUMBER_OF_SECONDS_PER_YEAR / BIG;
		
		int NUMBER_OF_FLIPS_SINCE_ERA = (int) Math.floor( (CURRENT_YEAR-COMMUNICATION_ERA) * (NUMBER_OF_FLIPS_PER_YEAR) );
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		
		for(int i=0;i<=NUMBER_OF_FLIPS_SINCE_ERA;i++){
			
			long NEXT_EVENT = (i+FIRST_FLIP)*BIG*K;
			
			while(g.getTimeInMillis()<NEXT_EVENT){
				g.add(Calendar.MILLISECOND,(int)Math.pow(10,9));
			}
			Date d = g.getTime();
			
			System.out.println(nf.format(NEXT_EVENT/K)+": "+sdf.format(d));
		}
		
	}
}















