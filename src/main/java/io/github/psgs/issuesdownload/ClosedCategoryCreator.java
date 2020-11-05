package io.github.psgs.issuesdownload;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;
import com.representqueens.lingua.en.Fathom;
import com.representqueens.lingua.en.Readability;
import com.representqueens.lingua.en.Fathom.Stats;

public class ClosedCategoryCreator {
	public static void main(String[] args) {
		try{
			//Per leggere da csv
			CSVReader reader = new CSVReader(new FileReader("FinalClosed.csv") , '\t');
			//Per scrivere un nuovo file csv
			System.out.println("Letto" );
			FileWriter writer = new FileWriter("ClosedWithCategory.csv");
			
			String nextLine[];
			
			// *********** HEADER ************
			nextLine=reader.readNext();
		    //String header =Arrays.toString(nextLine);
			String header = String.join(",", nextLine);
			String[] h = header.split(";");
			System.out.println(h[0] + "\t" + h[1] + "\t"+ h[3]);
			writer.append(h[0].substring(3)+ "\t" + h[1]+ "\t" + "Category\t"+ h[2] + "\t" + h[3]+ "\t" + h[4]+ "\t" + h[5]+ "\t" + h[6]+ "\t" + h[7]+ "\t" + h[8]+ "\t" + h[9]+ "\t" + h[10]+ "\t" + h[11]+ "\t" + h[12]+ "\t" + "Body Without URL\t Itemization\t Screenshots\t Fog Index\t Flesch Score\t Kincaid Score\t Time To Resolution" );
			writer.append("\n");
			
			// ************ DATI *************
			while((nextLine=reader.readNext()) != null){
				System.out.println("fin qui ci sono" );
				String  line=Arrays.toString(nextLine);
				// String  line = String.join(",", nextLine);
				String[] tok = line.split(";");
				
				 System.out.println(tok[0].substring(1));
				
			    writer.append(tok[0].substring(1) + "\t");
			    System.out.println(tok[0].substring(1));
			    //System.out.println(tok[1]);
			    writer.append(tok[1] + "\t");
			    System.out.println(tok[1]);
			    String category;
			    if(tok[1].toLowerCase().contains("sale") || tok[1].toLowerCase().contains("sales")){
			    	if (tok[1].toLowerCase().contains("stock")) {
			    		category= "Sale-Stock";
				    	writer.append(category + "\t");
			    	}
			    	else if( tok[1].toLowerCase().contains("account")) {
			    		category= "Sale-Account";
				    	writer.append(category + "\t");
			    	}
			    	else {
			    		category= "Sale";
			    		writer.append(category + "\t");
			    	}
			    }else if(tok[1].toLowerCase().contains("account") || tok[1].toLowerCase().contains("accounts") ){
			    	if (tok[1].toLowerCase().contains("stock")) {
			    		category= "Account-Stock";
				    	writer.append(category + "\t");
			    	}
			    	else if( tok[1].toLowerCase().contains("sale")) {
			    		category= "Sale-Account";
				    	writer.append(category + "\t");
			    	}
			    	else {
			    		category= "Account";
			    		writer.append(category + "\t");
			    	}
			    }else if(tok[1].toLowerCase().contains("stock") || tok[1].toLowerCase().contains("stocks") || tok[1].toLowerCase().contains("product") || tok[1].toLowerCase().contains("products") ){
			    	if (tok[1].toLowerCase().contains("sale")) {
			    		category= "Sale-Stock";
				    	writer.append(category + "\t");
			    	}
			    	else if( tok[1].toLowerCase().contains("account")) {
			    		category= "Account-Stock";
				    	writer.append(category + "\t");
			    	}
			    	else {
			    		category= "Stock";
			    		writer.append(category + "\t");
			    	}
			    }else if(tok[1].toLowerCase().contains("mail")){
			    	category= "Mail";
			    	writer.append(category + "\t");
			    }else if(tok[1].toLowerCase().contains("doc:")){
			    	category= "DOC";
			    	writer.append(category + "\t");
			    }else if(tok[1].toLowerCase().contains("calendar")){
			    	category = "Calendar";
			    	writer.append(category + "\t");
			    }else if(tok[1].toLowerCase().contains("base:")||tok[1].toLowerCase().contains("base ")){
			    	category = "Base";
			    	writer.append(category + "\t");
			    }else if(tok[1].toLowerCase().contains("payment") || tok[1].toLowerCase().contains("payments")|| tok[1].toLowerCase().contains("payroll")|| tok[1].toLowerCase().contains("pos") || tok[1].toLowerCase().contains("paypal")){
			    	category = "Payment";
			    	writer.append(category + "\t");
			    }else if(tok[1].toLowerCase().contains("purchase") || tok[1].toLowerCase().contains("purchases")){
			    	category= "Purchase";
			    	writer.append(category + "\t");
			    }else if (tok[1].toLowerCase().contains("sms")){
			    	category= "SMS";
			    	writer.append(category + "\t");
			    }else if(tok[1].toLowerCase().contains("portal")){
			    	category="Portal";
			    	writer.append(category + "\t");
			    }else if(tok[1].toLowerCase().contains("crm")){
			    	category="CRM";
			    	writer.append(category + "\t");
			    }else if(tok[1].toLowerCase().contains("timesheet")){
			    	category="Timesheet";
			    	writer.append(category + "\t");
			    }else if(tok[1].toLowerCase().contains("pivot")){
			    	category="Pivot";
			    	writer.append(category + "\t");
			    }else {
			    	category ="Others";
			    	writer.append(category + "\t");
			    }
			    System.out.println(category);
			    
				writer.append(tok[2] + "\t");
				System.out.println(tok[2] );
				writer.append(tok[3] + "\t");
				System.out.println(tok[3] );
				writer.append(tok[4] + "\t");
				System.out.println(tok[4] );
				writer.append(tok[5] + "\t");
				System.out.println(tok[5] );
				writer.append(tok[6] + "\t");
				System.out.println(tok[6] );
				writer.append(tok[7] + "\t");
				writer.append(tok[8] + "\t");
				writer.append(tok[9] + "\t");
				writer.append(tok[10] + "\t");
				writer.append(tok[11] + "\t");
				writer.append(tok[12] + "\t");
				System.out.println(tok[12] );
				System.out.println(tok[13] );
				writer.append(tok[13] + "\t");
				writer.append(tok[14] + "\t");
				writer.append(tok[15] + "\t");
				Stats s = Fathom.analyze(tok[13]);
				
			     float fog = Readability.calcFog(s);				
				 float flesch = Readability.calcFlesch(s);
				 float kincaid = Readability.calcKincaid(s);
	        
				 String f= String.valueOf(fog).replace(".", ",");
				 String fl= String.valueOf(flesch).replace(".", ",") ;
				 String k= String.valueOf(kincaid).replace(".", ",") ;
				 System.out.println(fog);
				 writer.append(f+ "\t");
				 writer.append(fl + "\t");
				 writer.append(k + "\t");
				
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				 //String closDate = tok[11].substring(0, 19)+ " "+ tok[11].substring(25);
				 DateFormat df2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		         Date closingDate =  df2.parse(tok[11]);
				 //System.out.println(closingDate);
		         Date creationDate = df.parse(tok[10]);
		         //System.out.println("Closing date: " + closingDate);
		         //System.out.println("Craetion date: " + creationDate);
				long millisDiff = closingDate.getTime() - creationDate.getTime();
				//int minutes = (int) (millisDiff / 60000 % 60);
				int hours = (int) (millisDiff / 3600000 % 24);
				int days = (int) (millisDiff / 86400000);
				//System.out.println("CreationDate: " + creationDate + "\tClosing Date: "+ closingDate + "\tTime To Resolutiton: "  +days + "," + hours + "," +  minutes  ); 
				writer.append( days + " days \t ");
				System.out.println(days);
				System.out.println("*******************************");
				 writer.append("\n");
				
			}
			 writer.flush();
			 writer.close();
		 }catch(Exception e ){
			 
		 }
	 }
}
