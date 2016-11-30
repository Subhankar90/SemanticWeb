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
import org.apache.jena.util.FileManager;

@WebServlet("/InputFormServlet")
public class InputFormServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InputClass inputObj = new InputClass();
		inputObj.setName(inputObj.getName());
		inputObj.setGender(request.getParameter("gender"));
		inputObj.setAge(Integer.parseInt(request.getParameter("age")));
		inputObj.seteducation(request.getParameter("education"));
		inputObj.setEthnicity(request.getParameter("ethnicity"));
		inputObj.setJob(request.getParameter("jobarea"));
		ArrayList<String> recommendedStates = new ArrayList<String>() ;
		recommendedStates = PoccessInputData(inputObj);
		
		PrintWriter out = response.getWriter();
		for(String state: recommendedStates)
			out.println(state);
			
		/*
		PrintWriter out = response.getWriter();
		String filename = "/WEB-INF/Project_Team8.owl";
		ServletContext context = getServletContext();
		InputStream is = context.getResourceAsStream(filename);
		if (is != null) {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			PrintWriter writer = response.getWriter();
			String text = "";
			while ((text = reader.readLine()) != null) {
				writer.println(text);
			}
		}
		else
			out.print("Fuck");
		*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	public ArrayList<String> PoccessInputData(InputClass InputObj) throws IOException {
		String defaultNameSpace = "http://www.semanticweb.org/team8/ontologies/ontology-team-8#";
		ArrayList<String> recommendedStates = new ArrayList<String>();
		String[] preference = {"Job", "CostOfLiving", "Safety"};
		InputClass InputDetails = new InputClass();
		Model state = null;
		InputDetails = InputObj;
		state = ModelFactory.createOntologyModel();
		String filename = "/WEB-INF/Project_Team8.owl";
		ServletContext context = getServletContext();
		InputStream is = context.getResourceAsStream(filename);
		//InputStream inInstance = FileManager.get().open("/WEB-INF/Project_Team8.owl");
		state.read(is,defaultNameSpace);
		is.close();
		recommendedStates = runQuery(roundOneQueryGenerator(preference[0], InputDetails), state);
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
