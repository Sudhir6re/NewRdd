package com.mahait.gov.in.common;


import java.math.BigDecimal;

public class AmountInWords {
	String string;

	String Units[] = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
			"Eight", "Nine", };

	String b[] = { "Hundred", "Thousand", "Lakh", "Crore" };

	String Tens[] = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
			"Fifteen", "Sixteen", "Seventeen", "Eighteen", "Ninteen", };

	String d[] = { "Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy",
			"Eighty", "Ninty" };

	public String convertNumToWord(int number) {

		int c = 1;
		int rm;
		string = "";
		while (number != 0) {
			switch (c) {
			case 1:
				rm = number % 100;
				pass(rm);
				if (number > 100 && number % 100 != 0) {
					display("and ");
				}
				number /= 100;

				break;

			case 2:
				rm = number % 10;
				if (rm != 0) {
					display(" ");
					display(b[0]);
					display(" ");
					pass(rm);
				}
				number /= 10;
				break;

			case 3:
				rm = number % 100;
				if (rm != 0) {
					display(" ");
					display(b[1]);
					display(" ");
					pass(rm);
				}
				number /= 100;
				break;

			case 4:
				rm = number % 100;
				if (rm != 0) {
					display(" ");
					display(b[2]);
					display(" ");
					pass(rm);
				}
				number /= 100;
				break;

			case 5:
				rm = number % 100;
				if (rm != 0) {
					display(" ");
					display(b[3]);
					display(" ");
					pass(rm);
				}
				number /= 100;
				break;

			}
			c++;
		}

		return string;
	}

	public void pass(int number) {
		int rm, q;
		if (number < 10) {
			display(Units[number]);
		}

		if (number > 9 && number < 20) {
			display(Tens[number - 10]);
		}

		if (number > 19) {
			rm = number % 10;
			if (rm == 0) {
				q = number / 10;
				display(d[q - 2]);
			} else {
				q = number / 10;
				display(Units[rm]);
				display(" ");
				display(d[q - 2]);
			}
		}
	}

	public void display(String s) {
		String t;
		t = string;
		string = s;
		string += t;
	}

	public String getFinalWordsForAmt(BigDecimal lBDAmt) {
		
		String lstrAmt = lBDAmt.setScale(2,
				BigDecimal.ROUND_HALF_UP).toString();
		int lLength = lstrAmt.length();

		int decimal = Integer.parseInt(lstrAmt.substring(lLength - 2, lLength));
		int intgr = Integer.parseInt(lstrAmt.substring(0, lLength - 3));
		String temp = convertNumToWord(intgr);
		String lStrAmt1 = "";
		if(temp != "")
		{
			lStrAmt1 = convertNumToWord(intgr) + " Rupees ";
		}
		else
		{
			lStrAmt1 = " Zero Rupees ";
		}
		String lStrAmt2 = "";
		/*if (decimal > 0) {
			lStrAmt2 = convertNumToWord(decimal) + " Paise.";
		}*/

		return (lStrAmt1 + lStrAmt2);
	}
}