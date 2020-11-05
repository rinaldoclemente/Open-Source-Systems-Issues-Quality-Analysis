package io.github.psgs.issuesdownload;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.opencsv.CSVReader;

public class Lists {
	public static void main(String[] args) throws Exception {
		try{
			 CSVReader readerO = new CSVReader(new FileReader("FinalOpen.csv") , '\t');
			 CSVReader readerC = new CSVReader(new FileReader("FinalClosed.csv") , '\t');
			 
			 FileWriter w1= new FileWriter("IssuesList.csv");  //Contiene : Id, Creator, State
			 FileWriter w2= new FileWriter("UsersList.csv");
			 
			 String nextLineO[];
			 String nextLineC[];
			 //Leggo prima le intestazioni
			 nextLineO=readerO.readNext();
			 nextLineC=readerC.readNext();
			 
			 Set<String> list = new HashSet<String>(); //per non avere duplicati
			 
			 while((nextLineO =readerO.readNext()) != null){
					String  lineO=Arrays.toString(nextLineO);
					//String  lineO = String.join(",", nextLineO);
					String[] tok = lineO.split(";");
					list.add(tok[2]); //aggiungo alla lista il creator della issue
					w1.append(tok[0].substring(1) + "\t" + tok[2] + "\t" + tok[6]);
					//System.out.println(tok[0].substring(1) + "\t" + tok[2] + "\t" + tok[6]);
					w1.append("\n");
			 }
			 while((nextLineC =readerC.readNext()) != null){
				 
					String  lineC=Arrays.toString(nextLineC);
					//String  lineC = String.join(",", nextLineO);
					String[] s = lineC.split(";");
					System.out.println(s[2]);
					list.add(s[2]); //aggiungo alla lista il creator della issue
					System.out.println("Sono qui3!");
					w1.append(s[0].substring(1) + "\t" + s[2] + "\t" + s[6]);
					System.out.println(s[0] + "\t" + s[2] + "\t" + s[6]);
					w1.append("\n");
			 }
			 w1.flush();
			 w1.close();
			 
			 for(String str : list){
				 w2.append(str + "\n");
			 }
			 w2.flush();
			 w2.close();
		 }catch(Exception e ){
			 
		 }
	
	}

}
