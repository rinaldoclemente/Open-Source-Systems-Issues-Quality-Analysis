package io.github.psgs.issuesdownload;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.FileWriter;
import java.io.IOException;

import com.representqueens.lingua.en.Fathom;
import com.representqueens.lingua.en.Fathom.Stats;
import com.representqueens.lingua.en.Readability;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IssuesDownload {

    //connessione a github, collezione dei campi necessari e scrittura su csv
	public static void main(String[] args) throws Exception {
		int it=0,sc=0,tot,c=0, numClosed=0;
		
		String repoDetails="odoo/odoo";
		GHIssueState issueState=GHIssueState.CLOSED;
		String[] repoInfo = repoDetails.split("/");
		try {
			//Connessione a github
			System.out.println("Trying to connect...");
			GitHub github=GitHub.connectUsingOAuth(Config.githubtoken);
			GHRepository repository = github.getUser(repoInfo[0]).getRepository(repoInfo[1]);
			System.out.println("Connected");
			
			//Inizializzazione CSV
			FileWriter writer = new FileWriter("Closed.csv");
			System.out.println("File creato.....");
			writer.append("Id\t Title\t Creator\t Assignee\t Milestone\t Labels\t State\t Comments\t Followers\t Following\t Creation Date\t Closing Date\t Body Text\t ");
	        writer.append("\n");
			
			//tot=repository.getIssues(issueState).size();
			//System.out.println("N.ro issues open: " + repository.getOpenIssueCount());
			System.out.println("Sono qui");
			//per ogni issue si ottengono i vari campi 
			for (GHIssue issue : repository.getIssues(issueState)) {
				//AGGIUNGO AL FILE L'ID DELL'ISSUE
				if(issue.getNumber() <= 16641){
                writer.append(String.valueOf(issue.getNumber()) + "\t");
				System.out.println(issue.getNumber());
                //AGGIUNGO AL FILE IL TITOLO DELL'ISSUE
                writer.append(issue.getTitle() + "\t");
                //AGGIUNGO AL FILE L'USERNAME DEL CREATORE DELL'ISSUE
                writer.append(issue.getUser().getLogin() + "\t");
                //SE DIVERSO DA NULL, AGGIUNGO AL FILE LA LISTA DI ASSIGNEE 
                if (issue.getAssignee() != null) {
                    writer.append(issue.getAssignee().getName() + "\t");
                } else {
                    writer.append("\t");
                }
                //SE DIVERSO DA NULL, AGGIUNGO I MILESTONES
                if (issue.getMilestone() != null) {
                    writer.append(issue.getMilestone().getTitle() + "\t");
                } else {
                    writer.append("\t");
                }
                //SE DIVERSO DA NULL, AGGIUNGO AL FILE LE LABEL DELL'ISSUE
                if (issue.getLabels() != null) {
                    writer.append(issue.getLabels() + "\t");
                } else {
                    writer.append("\t");
                }
                //AGGIUNGO AL FILE LO STATO (OPEN || CLOSED)
                writer.append(issue.getState() + "\t");
                //AGGIUNGO AL FILE IL NUMERO DI COMMENTI ASSOICATI A UNA ISSUE
				writer.append(String.valueOf(issue.getCommentsCount()) + "\t");
				//AGGIUNGO AL FILE IL NUMERO DI FOLLOWERS
				writer.append(String.valueOf(issue.getUser().getFollowersCount()) + "\t");
				//AGGIUNGO AL FILE IL NUMERO DI FOLLOWING
				writer.append(String.valueOf(issue.getUser().getFollowingCount()) + "\t");
				//AGGIUNGO AL FILE LA DATA DI CREAZIONE DEL FILE
				writer.append(String.valueOf(issue.getUser().getCreatedAt()) + "\t");
				
				//SE DIVERSO DA NULL(QUANDO STATE == CLOSED), AGGIUNGO AL FILE LA DATA DI CHIUSURA
				if (issue.getClosedAt() != null) {
					writer.append(String.valueOf(issue.getClosedAt()) + "\t");
	                } else {
	                    writer.append("\t");
	                }
					
				if (issue.getBody() != null) {
					writer.append(issue.getBody() + "\t");
	                } else {
	                    writer.append("\t");
	                }
				
				//numClosed++;
				writer.append("\n");
				}
				numClosed++;
			}
			
			writer.flush();
			writer.close();
			System.out.println("Numero delle issues closed: " + numClosed);
			System.out.println( "Download Complete!"+"\n");   
			
		} catch (IOException ex) {
	            System.out.println("An IOException has occurred!");
	            ex.printStackTrace();
	            if (ex.getMessage().equalsIgnoreCase("api.github.com")) {
	                System.out.println("An error has occurred reaching " + ex.getMessage() + "! Please check your network connection.");
	            }
	     }
	}
}
