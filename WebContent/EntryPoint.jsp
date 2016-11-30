<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<p><h1> Welcome to ReLoc8</h1></p>
<h3> Please fill the below form</h3>
<form action = "InputFormServlet" method = "post">
<fieldset>
    <legend>Personal information:</legend>
  Name:
  <input type="text" id="name" name="name">
  <br>
 <br>
   Age:
  <input type="text" id="age" name="age">
  <br>
<br>
   Education: <select id="education" name="education">
    <option value="Elementary">Elementary</option>
    <option value="Middle School">Middle School</option>
    <option value="High School">High School</option>
    <option value="College">College</option>
     <option value="Vocational Certificate">Vocational Certificate</option>
      <option value="Associate Degree">Associate Degree</option>
     <option value="Bachelor Degree">Bachelor Degree</option>
      <option value="Master Degree">Master Degree</option>
    <option value="Professional Degree">Professional Degree</option>
    <option value="Doctarate Degree">Doctarate Degree</option>

  </select>
  <br>

<br>
   Ethnicity : <select id="ethnicity" name="ethnicity">
    <option value="White">White</option>
    <option value="Black">Black</option>
    <option value="Hispanic">Hispanic</option>
    <option value="Others">Others</option>

  </select>
  <br><br>
<br>
   Job Area: <select id="jobarea" name="jobarea">
    <option value="BusinessFinancialOperations ">Business & Financial Operations</option>
    <option value="ComputerMathematical">Computer & Mathematical</option>
    <option value="ArchitectureEngineering">Architecture & Engineering </option>
    <option value="LifePhysicalSocialScience">Life,Physical and Social Science </option>
     <option value="CommunitySocialService">Community & Social Service </option>
      <option value="Legal">Legal</option>
     <option value="EducationTrainingLibrary">Education,Training & Library </option>
      <option value="ArtsDesign">Art,Design,Entertainment,Sports & Media</option>
    <option value="HealthcarePractionersTechnical">Healthcare Practioners & Technical </option>
    <option value="HealthcareSupport">Healthcare Support</option>
<option value="ProtectiveService">Protective Service </option>
<option value="FoodPreparationServingRelated">Food Prepartion & Serving Related</option>
<option value="BuildingGroundCleaningMaintenance">Building & Grounds Cleaning & Maintenance</option>
<option value="PersonalCareService">Personal Care & Service</option>
<option value="SalesRelated">Sales & Related</option>
<option value="OfficeAdministrativeSupport">Office & Administrative Support </option>
<option value="FarmingFishingForestry">Farming,Fishing & Forestry</option>
<option value="ConstrcutionExtraction">Construction& Extraction</option>
<option value="InstallationMaintenanceRepair">Installation,Maintenance & Repair</option>
<option value="Prodcution">Production</option>
<option value="TransportationMaterialMoving">Transportation & Material Moving</option>



  </select>
  <br><br>


   GENDER<br>

  <input type="radio" id="male" name="gender" value="male" checked> Male
  <input type="radio" id="female" name="gender" value="female"> Female
 
 
<br>
</fieldset>
<br>
<fieldset>
    <legend>Preferences:</legend>
<!--  Job:<input id="job" type="range" min="0" max="100" value="50" /><br>-->
Job:<input  id ="myjob" type="range" min="0" max="100" value="50" onchange="showValue(this.value)"  /><br>

<!--Safety: <input type="range" min="0" max="100" value="50" name = "safety"/><br>-->
<p id = "job"></p>
<script type="text/javascript">
function myfunction()
{
	var x = document.getElementById("myjob").value;
    document.getElementById("job").innerHTML = x;
	}
</script>
<!--  
Safety: <input id="safety" type="range" min="0" max="100" value="50" name = "safety"/><br>
Weather: <input id="weather" type="range" min="0" max="100" value="50" /><br>
Cost of Living: <input id="costofliving" type="range" min="0" max="100" value="50" /><br>
Ethnicity: <input id="ethn" type="range" min="0" max="100" value="50" /><br>
Education: <input id="edu" type="range" min="0" max="100" value="50" /><br>
Health: <input  id="health" type="range" min="0" max="100" value="50" /><br>
-->
</fieldset>
<br>
<input type="submit" value="Submit">
</form>



</body>
</html>
