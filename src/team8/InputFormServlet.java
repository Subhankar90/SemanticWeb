package team8;

import java.io.*;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;

@WebServlet("/InputFormServlet")
public class InputFormServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static InputStream ins = null;
	public static Model state = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InputClass inputObj = new InputClass();
		inputObj.setName(request.getParameter("name"));
		inputObj.setGender(request.getParameter("gender"));
		inputObj.setAge(Integer.parseInt(request.getParameter("age")));
		inputObj.seteducation(request.getParameter("education"));
		inputObj.setEthnicity(request.getParameter("ethnicity"));
		inputObj.setJob(request.getParameter("jobarea"));
		
		String[] preference = new String[3];
		preference[0] = request.getParameter("priority1");
		preference[1] = request.getParameter("priority2");
		preference[2] = request.getParameter("priority3");
		
		ArrayList<String> recommendedStates = new ArrayList<String>() ;
		recommendedStates = PoccessInputData(inputObj, preference);

		request.setAttribute("state1", recommendedStates.get(0));	
		request.setAttribute("state2", recommendedStates.get(1));	
		request.setAttribute("state3", recommendedStates.get(2));	
		request.setAttribute("name", inputObj.getName());
		request.setAttribute("gender", inputObj.getGender());
		request.setAttribute("age", inputObj.getAge());
		request.setAttribute("education", inputObj.geteducation());
		request.setAttribute("ethnicity", inputObj.getEthnicity());
		request.setAttribute("jobarea", inputObj.getJob());

		OutputFormServlet outObj = new OutputFormServlet();
		outObj.doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	public ArrayList<String> PoccessInputData(InputClass InputObj, String[] preference) throws IOException {
		String defaultNameSpace = "http://www.semanticweb.org/team8/ontologies/ontology-team-8#";
		ArrayList<String> recommendedStates = new ArrayList<String>();
		//String[] preference = {"Job", "CostOfLiving", "Safety"};
		
		InputClass InputDetails = new InputClass();		
		InputDetails = InputObj;
		state = ModelFactory.createOntologyModel();
		String filename = "/WEB-INF/Project_Team8.owl";
		ServletContext context = getServletContext();
		ins = context.getResourceAsStream(filename);
		state.read(ins,defaultNameSpace);
		recommendedStates = runQuery(roundOneQueryGenerator(preference[0], InputDetails), state);
		
		String finalStates = "";
		for(String statename : recommendedStates)
			finalStates += " state:" + statename + ",";
		finalStates = finalStates.substring(0, finalStates.length() -1);		
		recommendedStates = runQuery(roundTwoQueryGenerator(preference[1], finalStates, InputDetails), state);
		
		finalStates = "";
		for(String statename : recommendedStates)
			finalStates += " state:" + statename + ",";
		finalStates = finalStates.substring(0, finalStates.length() -1);
		recommendedStates = runQuery(roundThreeQueryGenerator(preference[2], finalStates, InputDetails), state);
		
		return recommendedStates;
	}	
	
	public String roundOneQueryGenerator(String Type, InputClass InputDetails) {
		switch(Type) {
		case "Job": return " select  ?subject where{ ?subject state:" + InputDetails.getJob() + "?object } ORDER BY DESC(?object) LIMIT 10";
		case "Safety": return " select  ?subject where{ ?subject state:stateSafetyRank ?object } ORDER BY ?object LIMIT 10";
		case "Weather": return " select  ?subject where{ ?subject state:favorabilityFactor ?object } ORDER BY ?object LIMIT 10";
		case "CostOfLiving": return " select  ?subject where{ ?subject state:hasMinimumExpenses ?object } ORDER BY ?object LIMIT 10";
		case "Ethnicity": return " select  ?subject where{ ?subject state:" + InputDetails.getEthnicity() + "?object } ORDER BY DESC(?object) LIMIT 10";
		case "Education": return " select  ?subject where{ ?subject state:educationIndex ?object } ORDER BY DESC(?object) LIMIT 10";
		case "Health": return " select  ?subject where{ ?subject state:healthIndex ?object } ORDER BY DESC(?object) LIMIT 10";
		default: return "";
		}
	}	
	
	public static String roundTwoQueryGenerator(String Type, String stateList, InputClass InputDetails) {
		switch(Type) {		
		case "Job": return " select  ?subject where{ ?subject state:" + InputDetails.getJob() + " ?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY DESC(?object) LIMIT 5";
		case "Safety": return " select  ?subject where{ ?subject state:stateSafetyRank ?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY ?object LIMIT 5";
		case "Weather": return " select  ?subject where{ ?subject state:favorabilityFactor ?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY ?object LIMIT 5";
		case "CostOfLiving": return " select  ?subject where{ ?subject state:hasMinimumExpenses ?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY ?object LIMIT 5";
		case "Ethnicity": return " select  ?subject where{ ?subject state:" + InputDetails.getEthnicity() + " ?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY DESC(?object) LIMIT 5";
		case "Education": return " select  ?subject where{ ?subject state:educationIndex ?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY DESC(?object) LIMIT 5";
		case "Health": return " select  ?subject where{ ?subject state:healthIndex ?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY DESC(?object) LIMIT 5";
		default: return "";
		}		
	}
	
	public static String roundThreeQueryGenerator(String Type, String stateList, InputClass InputDetails) {
		switch(Type) {		
		case "Job": return " select  ?subject where{ ?subject state:" + InputDetails.getJob() + "?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY DESC(?object) LIMIT 3";
		case "Safety": return " select  ?subject where{ ?subject state:stateSafetyRank ?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY ?object LIMIT 3";
		case "Weather": return " select  ?subject where{ ?subject state:favorabilityFactor ?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY ?object LIMIT 3";
		case "CostOfLiving": return " select  ?subject where{ ?subject state:hasMinimumExpenses ?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY ?object LIMIT 3";
		case "Ethnicity": return " select  ?subject where{ ?subject state:" + InputDetails.getEthnicity() + "?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY DESC(?object) LIMIT 3";
		case "Education": return " select  ?subject where{ ?subject state:educationIndex ?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY DESC(?object) LIMIT 3";
		case "Health": return " select  ?subject where{ ?subject state:healthIndex ?object  FILTER(?subject IN ("+ stateList + "))} ORDER BY DESC(?object) LIMIT 3";
		default: return "";
		}		
	}
	
	private ArrayList<String> runQuery(String queryRequest, Model model)
	{
	  String defaultNameSpace = "http://www.semanticweb.org/team8/ontologies/ontology-team-8#";
	  ArrayList<String> recommendedStates = new ArrayList<String>();
	  StringBuffer queryStr = new StringBuffer();
		
	  queryStr.append("PREFIX owl" + ": <" + "http://www.w3.org/2002/07/owl#" + "> ");
	  queryStr.append("PREFIX state" + ": <" + defaultNameSpace + "> ");
	  queryStr.append("PREFIX rdfs" + ": <" +  
	                  "http://www.w3.org/2000/01/rdf-schema#" + "> ");
	  queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");

	  queryStr.append(queryRequest);
	  Query query = QueryFactory.create(queryStr.toString());
	  QueryExecution qexec = QueryExecutionFactory.create(query, model);
			
	  try  {
			ResultSet response = qexec.execSelect();
			
			while( response.hasNext()){
				QuerySolution soln = response.nextSolution();
				RDFNode name = soln.get("?subject");
				if( name != null )
					recommendedStates.add(name.toString().replaceAll(defaultNameSpace, ""));
			}
		} 
		finally { qexec.close();}	
	  return recommendedStates;
	  }	

}
