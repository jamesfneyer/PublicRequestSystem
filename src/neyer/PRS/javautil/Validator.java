package neyer.PRS.javautil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Validator {
	
	public static String getChoice(Scanner sc, String prompt) {
		String s = "";
		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			s = sc.next(); // read user entry
			sc.nextLine();
			if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")||s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("no"))
				isValid = true;
			else
				System.out.println("Error! Please enter a y or n.");
		}
		return s;
	}
	
	public static String getString(Scanner sc, String prompt, String s1, String s2) {
		String s = "";
		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			s = sc.next(); // read user entry
			sc.nextLine();
			if (s.equalsIgnoreCase(s1) || s.equalsIgnoreCase(s2))
				isValid = true;
			else
				System.out.println("Error! Please enter a "+s1+" or "+s2+".");
		}
		return s;
	}

	public static String getLine(Scanner sc, String prompt) {
		System.out.print(prompt);
		String s = sc.nextLine(); // read the whole line
		return s;
	}

	public static String getString(Scanner sc, String prompt) {
		System.out.print(prompt);
		String s = sc.nextLine(); // read the first string on the line
		return s;
	}

	public static String getString(Scanner sc, String prompt, int length) {
		String s = "";
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			s = sc.next(); // read the first string on the line
			if (s.length() == length) {
				isValid = true;
			} else
				System.out.println("Error! length. Should be " + length + " characters.  Try again.");
			sc.nextLine(); // discard the rest of the line
		}
		return s;
	}

	public static String getStringWithinLength(Scanner sc, String prompt, int length) {
		String s = "";
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			s = sc.next(); // read the first string on the line
			if (s.length() <= length) {
				isValid = true;
			} else
				System.out.println("Error! length. Should be " + length + " characters.  Try again.");
			sc.nextLine(); // discard the rest of the line
		}
		return s;
	}
	
	public static Date getDate(Scanner sc){
		Calendar todaysDate = Calendar.getInstance();
		int year = getInt(sc, "Enter year needed by: ");
		int month = getMonth(sc, "Enter month needed by: ", todaysDate.get(Calendar.MONTH), year, todaysDate.get(Calendar.YEAR));
		int day = getDay(sc, "Enter date needed by: ", month, todaysDate.get(Calendar.MONTH), year, todaysDate.get(Calendar.YEAR), todaysDate.getMaximum(Calendar.DAY_OF_MONTH));
		GregorianCalendar d = new GregorianCalendar(year, month, day);
		Date date = d.getTime();
		return date;
	}
	
	private static int getDay(Scanner sc, String prompt, int month, int todaysMonth, int year, int todaysYear, int todaysDate) {
		// TODO Auto-generated method stub
		int d = 0;
		int maxDay = 0;
		boolean isValid = false;
		while (!isValid) {
			switch (month){
			case 0:
			case 2:
			case 4:
			case 6:
			case 7:
			case 9:
			case 11:
				maxDay = 32;
				break;
			case 3:
			case 5:
			case 8:
			case 10:
				maxDay = 31;
				break;
			case 1:
				maxDay = 30;
				break;
			}
			d = getInt(sc, prompt, 0, maxDay);
			if((year==todaysYear)&&(month==todaysMonth)){
				if(d < todaysDate){
					System.out.println("Error! Invalid date.");
				}
				else{
					isValid = true;
				}
			}
			else{
				isValid = true;
			}
		}
		return d;
	}

	public static String getStringNumeric(Scanner sc, String prompt, int length) {
		String s = "";
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			if (sc.hasNextInt()) { // even though this is a String, the values
									// should be numeric
				s = sc.next(); // read the first string on the line
				int negativeCheck = Integer.parseInt(s);

				if (s.length() == length) {
					if (negativeCheck >= 0)
						isValid = true;
					else
						System.out.println("Error! Should be a positive integer.");
				}

				else
					System.out.println("Error! length. Should be " + length + " characters.  Try again.");
			} else
				System.out.println("Error! Should be a numeric value.  Try again.");
			sc.nextLine(); // discard the rest of the line
		}
		return s;
	}

	public static int getMonth(Scanner sc, String prompt, int month, int yearNeeded, int yearMin){
		int year = 12;
		String s = "";
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			s = sc.next(); // read the first string on the line
			if (s.equalsIgnoreCase("january"))
				year = 0;
			else if (s.equalsIgnoreCase("febuary"))
				year = 1;
			else if (s.equalsIgnoreCase("march"))
				year = 2;
			else if (s.equalsIgnoreCase("april"))
				year = 3;
			else if(s.equalsIgnoreCase("may"))
				year = 4;
			else if(s.equalsIgnoreCase("june"))
				year = 5;
			else if(s.equalsIgnoreCase("july"))
				year = 6;
			else if(s.equalsIgnoreCase("august"))
				year = 7;
			else if(s.equalsIgnoreCase("september"))
				year = 8;
			else if(s.equalsIgnoreCase("october"))
				year = 9;
			else if(s.equalsIgnoreCase("november"))
				year = 10;
			else if(s.equalsIgnoreCase("december"))
				year = 11;
			else
				System.out.println("Error! Not a month.");
			sc.nextLine(); // discard the rest of the line
			
			if(yearNeeded == yearMin && year<12){
				if(year>month)
					isValid = true;
				else
					System.out.println("Error! invalid month.");

			}
			else if(year<12){
				isValid = true;
			}
		}
		return year;
	}
	
	public static int getInt(Scanner sc, String prompt) {
		boolean isValid = false;
		int i = 0;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("Error! Invalid integer value. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return i;
	}

	public static int getInt(Scanner sc, String prompt, int min, int max) {
		int i = 0;
		boolean isValid = false;
		while (isValid == false) {
			i = getInt(sc, prompt);
			if (i <= min)
				System.out.println("Error! Number must be greater than " + min);
			else if (i >= max)
				System.out.println("Error! Number must be less than " + max);
			else
				isValid = true;
		}
		return i;
	}

	public static double getDouble(Scanner sc, String prompt) {
		boolean isValid = false;
		double d = 0;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextDouble()) {
				d = sc.nextDouble();
				isValid = true;
			} else {
				System.out.println("Error! Invalid decimal value. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return d;
	}

	public static double getDouble(Scanner sc, String prompt, double min, double max) {
		double d = 0;
		boolean isValid = false;
		while (isValid == false) {
			d = getDouble(sc, prompt);
			if (d <= min)
				System.out.println("Error! Number must be greater than " + min);
			else if (d >= max)
				System.out.println("Error! Number must be less than " + max);
			else
				isValid = true;
		}
		return d;
	}

}