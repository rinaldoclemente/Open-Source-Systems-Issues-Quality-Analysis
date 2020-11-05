package io.github.psgs.issuesdownload;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;

public class Reputation {
	public static void main(String[] args) throws Exception {
		Double repA=0.0;
		int tot=0, res=0,c=0;
		int all=16,closed=8, open =8;  //all, closed  e open sono valori noti (li ho calcolati in altre classi) -->NON SERVONO
		
		try {
			//Scrivo il file Reputation.csv
			FileWriter writer = new FileWriter("Reputation.csv");
			writer.append("Users \t Submitted \t Resolved \t Reputation A ");
	        writer.append("\n");
	        List<User> users=new ArrayList<User>();
	        
	        //CSVReader readerI = new CSVReader(new FileReader("IssuesList.csv") , '\t');
			CSVReader readerU = new CSVReader(new FileReader("UsersList.csv") , '\t');
			
			String nextLineI[];
			String nextLineU[];
			
			while((nextLineU =readerU.readNext()) != null){
				//String  lineU=Arrays.toString(nextLineU);
				String lineU = String.join(",", nextLineU);
				String[] tok = lineU.split(";");
				//System.out.println(tok[0]);
				users.add(new User(tok[0]));
			}
			
			for(User u:users) {
				c++;
				tot=0;
				res=0;
				// Leggo le issue nel file
				CSVReader readerI = new CSVReader(new FileReader("IssuesList.csv") , '\t');
				while((nextLineI =readerI.readNext()) != null){
						//String  lineI=Arrays.toString(nextLineI);
						String lineI = String.join(",", nextLineI);
						String[] str = lineI.split(";");
						if(str[1].equals(u.getName())) {
							//System.out.println(str[1]+" ***** "+u.getName()); 
							//System.out.println(u.getName() + ": " + tot);
							u.setTotal(tot = tot +1);
							//System.out.println(str[2]);
							if(str[2].equals("CLOSED")) {
								u.setResolved(res = res+1);	
							}
						}
				}
				
				//calcolo delle reputazioni
				repA= (double) ((double)u.getResolved()/(double)u.getTotal());
				//System.out.println(repA);
				u.setReputationA(repA);
				System.out.println(u.getReputationA());
				//System.out.println(u.getName()+"\t"+u.getTotal()+"\t"+u.getResolved()+"\t"+repA);
				writer.append(u.getName()+"\t"+u.getTotal()+"\t"+u.getResolved()+"\t"+ u.getReputationA());
				writer.append("\n");
		}	
			    writer.flush();
			   writer.close();
	
			System.out.println( "DONE!"+"\n");
			
		 }catch (Exception ex) {
	          
         }
	}
}
