<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <style>
        input[type=text],
        select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        h1 {
            font-style: italic;
            font-family: Florence, cursive;
            font-weight: 300;
            text-align: center;
            color: #0000ff;
            text-shadow: 0 0 5px #A5F1FF, 0 0 10px #A5F1FF, 0 0 20px #A5F1FF, 0 0 30px #A5F1FF, 0 0 40px #A5F1FF;
        }
        h3 {
            margin: 1em 0 0.5em 0;
            font-size: 22px;
            line-height: 40px;
            font-weight: normal;
            font-family: Florence, cursive;
            letter-spacing: 1px;
            font-style: italic;
        }
        text-shadow: 2px 2px 2px #CCCCCC;
    }
    input[type=submit] {
        width: 100%;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 12px;
        cursor: pointer;
    }
    input[type=submit]:hover {
        background-color: #45a049;
    }
    div {
        border-radius: 5px;
        background-color: #f18e99;
        padding: 20px;
    }
    span {
        font-family: Comic Sans, Comic Sans MS, cursive;
        font-style
    }
    </style>
</head>

<body>
    <div>
        <h1> Welcome to ReLoc8</h1>
        <h3> <u>Please fill the below form</u></h3>

        <form action="InputFormServlet" method="post">

            <span>Name:</span>
            <input style="font-family:Florence, cursive;margin-left:30px; width: 300px;border-radius: 12px;" type="text" id="name" name="name" required>
            <br>
            <br>
            <span> Age:</span>
            <input style="font-family:Florence, cursive;margin-left:40px;width: 100px;border-radius: 12px;" type="text" id="age" required name="age">
            <br>
            <br>
            <span>Education: </span>
            <select style="font-family:Florence, cursive;margin-left:30px;width: 300px;border-radius: 12px;" id="education" name="education">
                <option value="Elementary">Elementary</option>
                <option value="MiddleSchool">Middle School</option>
                <option value="HighSchool">High School</option>
                <option value="College">College</option>
                <option value="VocationalCertificate">Vocational Certificate</option>
                <option value="AssociateDegree">Associate Degree</option>
                <option value="BachelorDegree">Bachelor Degree</option>
                <option value="MasterDegree">Master Degree</option>
                <option value="ProfessionalDegree">Professional Degree</option>
                <option value="DoctorateDegree">Doctarate Degree</option>

            </select>
            <br>

            <br>
            <span>Ethnicity :</span>
            <select style="font-family:Florence, cursive;margin-left:30px;width: 300px;border-radius: 12px;" id="ethnicity" name="ethnicity">
                <option value="White">White</option>
                <option value="Black">Black</option>
                <option value="Hispanic">Hispanic</option>
                <option value="Others">Others</option>

            </select>
            <br>
            <br>
            <br>
            <span>Job Area: </span>
            <select style="font-family:Florence, cursive;margin-left:30px;width: 300px;border-radius: 12px;" id="jobarea" name="jobarea">
                <option value="BusinessFinancialOperations">Business & Financial Operations</option>
                <option value="ComputerMathematical">Computer & Mathematical</option>
                <option value="ArchitectureEngineering">Architecture & Engineering </option>
                <option value="LifePhysicalSocialScience">Life,Physical and Social Science </option>
                <option value="CommunitySocialService">Community & Social Service </option>
                <option value="Legal">Legal</option>
                <option value="EducationTrainingLibrary">Education,Training & Library </option>
                <option value="ArtsDesignEntertainmentSportsMedia">Art,Design,Entertainment,Sports & Media</option>
                <option value="HealthcarePractitionersTechnical">Healthcare Practioners & Technical </option>
                <option value="HealthcareSupport">Healthcare Support</option>
                <option value="ProtectiveService">Protective Service </option>
                <option value="FoodPreparationServingRelated">Food Prepartion & Serving Related</option>
                <option value="BuildingGroundsCleaningMaintenance">Building & Grounds Cleaning & Maintenance</option>
                <option value="PersonalCareService">Personal Care & Service</option>
                <option value="SalesRelated">Sales & Related</option>
                <option value="OfficeAdministrativeSupport">Office & Administrative Support </option>
                <option value="FarmingFishingForestry">Farming,Fishing & Forestry</option>
                <option value="ConstructionExtraction">Construction& Extraction</option>
                <option value="InstallationMaintenanceRepair">Installation,Maintenance & Repair</option>
                <option value="Production">Production</option>
                <option value="TransportationMaterialMoving">Transportation & Material Moving</option>
            </select>
            <br>
            <br>


            <span>Gender</span>
            <br>

            <br>
            <input type="radio" id="Male" name="gender" value="Male" checked> Male
            <input type="radio" id="Female" name="gender" value="Female"> Female
            <br>
            <br><span>Please select your three priorities.</span>
            <br><span>Priority 1 :</span>
            <select style="font-family:Florence, cursive;margin-left:30px;width: 300px;border-radius: 12px;" id="priority1" name="priority1">
                <option value="Job">Job</option>
                <option value="Safety">Safety</option>
                <option value="Weather">Weather</option>
                <option value="CostOfLiving">Cost of Living</option>
                <option value="Ethnicity">Ethnicity</option>
                <option value="Education">Education</option>
                <option value="Health">Health</option>

            </select>
            <br><span>Priority 2 :</span>
            <select style="font-family:Florence, cursive;margin-left:30px;width: 300px;border-radius: 12px;" id="priority2" name="priority2">
                <option value="Job">Job</option>
                <option value="Safety">Safety</option>
                <option value="Weather">Weather</option>
                <option value="CostOfLiving">Cost of Living</option>
                <option value="Ethnicity">Ethnicity</option>
                <option value="Education">Education</option>
                <option value="Health">Health</option>

            </select>
            <br><span>Priority 3 :</span>
            <select style="font-family:Florence, cursive;margin-left:30px;width: 300px;border-radius: 12px;" id="priority3" name="priority3">
                <option value="Job">Job</option>
                <option value="Safety">Safety</option>
                <option value="Weather">Weather</option>
                <option value="CostOfLiving">Cost of Living</option>
                <option value="Ethnicity">Ethnicity</option>
                <option value="Education">Education</option>
                <option value="Health">Health</option>

            </select>

            <!--  Job:<input id="job" type="range" min="0" max="100" value="50" /><br>-->
            <!--Job:<input  id ="myjob" type="range" min="0" max="100" value="50" onchange="showValue(this.value)"  /><br>-->

            <!--Safety: <input type="range" min="0" max="100" value="50" name = "safety"/><br>-->
            <p id="job"></p>
            <script type="text/javascript">
                function myfunction() {
                    var x = document.getElementById("myjob").value;
                    document.getElementById("job").innerHTML = x;
                }
            </script>

            <br>

            <input style="font-family:Florence, cursive;margin-left:30px;width: 150px;text-align: center;border-radius: 12px;" type="submit" value="Let's Relocate">
        </form>
    </div>


</body>

</html>