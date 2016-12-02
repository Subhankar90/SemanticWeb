package team8;

import java.io.*;
import java.util.*;

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
import org.apache.jena.rdf.model.RDFNode;

@WebServlet("/OutputFormServlet")
public class OutputFormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private InputClass InputObj = new InputClass();
	private ArrayList<String> recommendedStates = new ArrayList<String>() ;
	private Model state = null;
	private OutputStateClass[] outObj = new OutputStateClass[3];
	private OutputEducationClass outEducationObj = new OutputEducationClass();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getInputValues(request);
		generateOutputDate();
		PrintWriter out = response.getWriter();
		
		
		for(int i = 1; i<=3;i++){ 
			request.setAttribute("state"+i, outObj[i-1].getState());	
			request.setAttribute("capital"+i, outObj[i-1].getCapital());
			request.setAttribute("population"+i, outObj[i-1].getPopulation());
			request.setAttribute("jobamount"+i, outObj[i-1].getJobAmount());
			request.setAttribute("enthnicitydistribution"+i, outObj[i-1].getEthnicityDistribution());
			request.setAttribute("crimerank"+i, outObj[i-1].getCrimeRank());
			request.setAttribute("violentcrimerate"+i, outObj[i-1].getViolentCrimeRate());
			request.setAttribute("PropertyCrimeRate"+i, outObj[i-1].getPropertyCrimeRate());
			request.setAttribute("Numberofschools"+i, outObj[i-1].getNumberofschools());
			request.setAttribute("Numberofhospitals"+i, outObj[i-1].getNumberofhospitals());
			request.setAttribute("HumanDevelopmentIndex"+i, outObj[i-1].getHumanDevelopmentIndex());
			request.setAttribute("HealthIndex"+i, outObj[i-1].getHealthIndex());
			request.setAttribute("EducationIndex"+i, outObj[i-1].getEducationIndex());
			request.setAttribute("IncomeIndex"+i, outObj[i-1].getIncomeIndex());
			request.setAttribute("StateSpendingEducation"+i, outObj[i-1].getStateSpendingEducation());
			request.setAttribute("StateSpendingResearch"+i, outObj[i-1].getStateSpendingResearch());
			request.setAttribute("StateSpendingMedicine"+i, outObj[i-1].getStateSpendingMedicine());
			request.setAttribute("minimumExpence"+i, outObj[i-1].getMinimumExpence());
			request.setAttribute("maximumExpence"+i, outObj[i-1].getMaximumExpence());
			request.setAttribute("AverageTemp"+i, outObj[i-1].getAverageTemp());
			request.setAttribute("WeatherRank"+i, outObj[i-1].getWeatherRank());
			
			
		}
		
		request.setAttribute("edulevel", outEducationObj.getEducationLevel());
		request.setAttribute("incomegender", outEducationObj.getIncomeGender());
		request.setAttribute("incomerace", outEducationObj.getIncomeEthnicity());
		request.setAttribute("incomeage", outEducationObj.getIncomeAgeGroup());
		request.setAttribute("Gender",InputObj.getGender());
		request.setAttribute("race",InputObj.getEthnicity());
		request.setAttribute("age",InputObj.getAge());
		
		request.getRequestDispatcher("output.jsp").forward(request, response);
		
		
	}
	
	private void generateOutputDate() throws IOException {
		state = InputFormServlet.state;		
		int objectConter = 0;
		for(String stateName : recommendedStates)
			outObj[objectConter++] = generateStateData("state:"+stateName);		
		
		generateEducationIncomeData(InputObj);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void getInputValues(HttpServletRequest request) {		
		InputObj.setName((String)request.getAttribute("name"));
		InputObj.setGender((String)request.getAttribute("gender"));
		InputObj.setAge((Integer)request.getAttribute("age"));
		InputObj.seteducation((String)request.getAttribute("education"));
		InputObj.setEthnicity((String)request.getAttribute("ethnicity"));
		InputObj.setJob((String)request.getAttribute("jobarea"));
		
		recommendedStates.add((String)request.getAttribute("state1"));
		recommendedStates.add((String)request.getAttribute("state2"));
		recommendedStates.add((String)request.getAttribute("state3"));		
	}
	
	public void generateEducationIncomeData(InputClass InputObj) {
		outEducationObj = new OutputEducationClass();	
		outEducationObj.setEducationLevel(InputObj.geteducation());
		outEducationObj.setIncomeGender((Integer.parseInt(runQuery("select  ?object where{ state:"+ InputObj.geteducation() +" state:averageIncome"+ InputObj.getGender() + " ?object }", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", ""))));
		outEducationObj.setIncomeEthnicity((Integer.parseInt(runQuery("select  ?object where{ state:"+ InputObj.geteducation() +" state:averageIncome"+ InputObj.getEthnicity() + " ?object }", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", ""))));
		
		String incomeGroup = "";
		if(InputObj.getAge() <= 29)
			incomeGroup = "state:averageIncome18-29AgeGroup";
		else if (InputObj.getAge() <= 49)
			incomeGroup = "state:averageIncome30-49AgeGroup";
		else
			incomeGroup = "state:averageIncome50PlusAgeGroup";

		outEducationObj.setIncomeAgeGroup((Integer.parseInt(runQuery("select  ?object where{ state:"+ InputObj.geteducation() +" "+ incomeGroup +" ?object }", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", ""))));
	}
	
	
	public OutputStateClass generateStateData(String stateList) {
		OutputStateClass outStateObj = new OutputStateClass();		
		outStateObj.setState(stateList.replace("state:", ""));
		outStateObj.setCapital(runQuery("select  ?object where{ ?subject state:hasCapital ?object FILTER(?subject IN ("+ stateList + "))}", state));
		outStateObj.setJobAmount(Integer.parseInt(runQuery("select  ?object where{ ?subject state:" + InputObj.getJob() + " ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", "")));
		outStateObj.setPopulation((Integer.parseInt(runQuery("select  ?object where{ ?subject state:hasPopulation ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", ""))));
		outStateObj.setEthnicityDistribution(Float.parseFloat(runQuery("select  ?object where{ ?subject state:" + InputObj.getEthnicity() + " ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#float", "")));
		outStateObj.setCrimeRank((Integer.parseInt(runQuery("select  ?object where{ ?subject state:stateSafetyRank ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", ""))));
		outStateObj.setViolentCrimeRate(Float.parseFloat(runQuery("select  ?object where{ ?subject state:violentCrimeRate ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#float", "")));
		outStateObj.setPropertyCrimeRate(Float.parseFloat(runQuery("select  ?object where{ ?subject state:propertyCrimeRate ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#float", "")));
		outStateObj.setNumberofhospitals((Integer.parseInt(runQuery("select  ?object where{ ?subject state:numberOfHospitals ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", ""))));
		outStateObj.setNumberofschools((Integer.parseInt(runQuery("select  ?object where{ ?subject state:numberOfSchools ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", ""))));
		outStateObj.setHumanDevelopmentIndex(Float.parseFloat(runQuery("select  ?object where{ ?subject state:humanDevelopmentIndex ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#float", "")));
		outStateObj.setHealthIndex(Float.parseFloat(runQuery("select  ?object where{ ?subject state:healthIndex ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#float", "")));
		outStateObj.setEducationIndex(Float.parseFloat(runQuery("select  ?object where{ ?subject state:educationIndex ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#float", "")));
		outStateObj.setIncomeIndex(Float.parseFloat(runQuery("select  ?object where{ ?subject state:incomeIndex ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#float", "")));
		outStateObj.setStateSpendingEducation((Integer.parseInt(runQuery("select  ?object where{ ?subject state:onHigherEducation ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", ""))));
		outStateObj.setStateSpendingResearch((Integer.parseInt(runQuery("select  ?object where{ ?subject state:onAcedemicResearchAndDevelopment ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", ""))));
		outStateObj.setStateSpendingMedicine((Integer.parseInt(runQuery("select  ?object where{ ?subject state:onMedicalAide ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", ""))));
		outStateObj.setMinimumExpence((Integer.parseInt(runQuery("select  ?object where{ ?subject state:hasMinimumExpenses ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", ""))));
		outStateObj.setMaximumExpence((Integer.parseInt(runQuery("select  ?object where{ ?subject state:hasMaximumExpenses ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#integer", ""))));
		outStateObj.setWeatherRank((Float.parseFloat(runQuery("select  ?object where{ ?subject state:favorabilityFactor ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#float", ""))));
		outStateObj.setAverageTemp(Float.parseFloat(runQuery("select  ?object where{ ?subject state:averageTemperature ?object FILTER(?subject IN ("+ stateList + "))}", state).toString().replaceAll("[\\^][\\^]http://www.w3.org/2001/XMLSchema#float", "")));	
		return outStateObj;		
	}
	
	
	private String runQuery(String queryRequest, Model model)
	{
	  StringBuffer queryStr = new StringBuffer();
	  String defaultNameSpace = "http://www.semanticweb.org/team8/ontologies/ontology-team-8#";
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
				RDFNode name = soln.get("?object");
				if( name != null )
					return name.toString().replaceAll(defaultNameSpace, "");
			}
		} 
		finally { qexec.close();}
	  	return "";				
	  }	

}
