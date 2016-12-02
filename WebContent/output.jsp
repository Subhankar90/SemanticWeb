<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Output Window</title>
        <script>
            function mySignup() {
                document.getElementById("table").style.display = "block";
                document.getElementById("name").style.display = "none";

            }
        </script>
        <style>
            .click {
                background-color: #4CAF50;
                /* Green */
                
                border: none;
                color: white;
                padding: 5px 8px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 15px;
                margin: 4px 2px;
                cursor: pointer;
                -webkit-transition-duration: 0.4s;
                /* Safari */
                
                transition-duration: 0.4s;
                border-radius: 12px;
                box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
            }
            body {
                font-family: Florence, cursive;
                font-weight: 300;
                background: linear-gradient(45deg, #ffffcc 45px, transparent 45px)64px 64px, linear-gradient(45deg, #ffffcc 45px, transparent 45px, transparent 91px, #CFF8FC 91px, #CFF8FC 135px, transparent 135px), linear-gradient(-45deg, #ffffcc 23px, transparent 23px, transparent 68px, #ffffcc 68px, #ffffcc 113px, transparent 113px, transparent 158px, #ffffcc 158px);
                background-color: #CFF8FC;
                background-size: 128px 128px;
            }
            h3 {
                font-family: Florence, cursive;
                font-weight: 300;
            }
            table {
                border: 1px solid black;
                border-collapse: collapse;
                width: 100%;
                border-radius: 12px;
                -moz-border-radius: 12px;
            }
            td {
                border: 1px solid black;
                text-align: center;
                padding: 3px;
            }
            th {
                border: 1px solid black;
                text-align: center;
                background-color: #4CAF50;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2
            }
            tr:nth-child(odd) {
                background-color: #ff99ff
            }
            pre {
                font-family: Florence, cursive;
                font-weight: 300;
            }
        </style>
    </head>

    <body>
        <h2><b>Here are the top three states matching your preferences...</b></h2>
        <b><div id = "state">
 <pre><b>  1. ${state1}       2. ${state2}       3. ${state3} </b>
        </pre>
        </div>
        </b>
        <span> <b>Based on your Education Level(${edulevel}) then average income in US are as follows : 
               <p>Average income for ${Gender} in US (per month) is: $${incomegender}</p>
               <p>Average income for ${race} in US (per month) is: $${incomerace}</p>
               <p>Average income for Age ${age} in US (per month) is: $${incomeage} </p></span>
        
        <br>
        <div id="name">
            <h5>For additional info: <a href="#" onclick = "mySignup()" ><button style="font-family:Florence, cursive;margin-left:30px;width: 100px;text-align: center;border-radius: 12px; background-color:#2FB04D; color:#EBF1ED ">Click Here</button> </a></h5>
        </div>
        <span id="table" hidden>
<table>
  <tr>
    <th>Num</th>
    <th>Criteria</th>
    <th>${state1} </th>
    <th>${state2} </th>
    <th>${state3}  </th>
    
  </tr>
  <tr>
    <td>1</td>
    <td>Capital</td>
    <td>${capital1}</td>
    <td>${capital2}</td>
    <td>${capital3}</td>
  </tr>
  <tr>
   <td>2</td>
    <td>Population</td>
    <td>${population1}</td>
    <td>${population2}</td>
    <td>${population3}</td>
  </tr>
  <tr>
    <td>3</td>
    <td>JobAmount</td>
    <td>${jobamount1}</td>
    <td>${jobamount2}</td>
    <td>${jobamount3}</td>
  </tr>
  <tr>
    <td>4</td>
    <td>EthnicityDistribution</td>
    <td>${enthnicitydistribution1}</td>
    <td>${enthnicitydistribution2}</td>
    <td>${enthnicitydistribution3}</td>
  </tr>
  <tr>
   <td>5</td>
    <td>CrimeRank</td>
    <td>${crimerank1}</td>
    <td>${crimerank2}</td>
    <td>${crimerank3}</td>
  </tr>
  <tr>
   <td>6</td>
    <td>ViolentCrimeRate</td>
    <td>${violentcrimerate1}</td>
    <td>${violentcrimerate2}</td>
    <td>${violentcrimerate3}</td>
  </tr>
  <tr>
   <td>7</td>
    <td>PropertyCrimeRate</td>
    <td>${PropertyCrimeRate1}</td>
    <td>${PropertyCrimeRate2}</td>
    <td>${PropertyCrimeRate3}</td>
  </tr>
  <tr>
   <td>8</td>
    <td>Numberofschools</td>
    <td>${Numberofschools1}</td>
    <td>${Numberofschools2}</td>
    <td>${Numberofschools3}</td>
  </tr>
  <tr>
   <td>9</td>
    <td>Numberofhospitals</td>
    <td>${Numberofhospitals1}</td>
    <td>${Numberofhospitals2}</td>
    <td>${Numberofhospitals3}</td>
  </tr>
  <tr>
   <td>10</td>
    <td>HumanDevelopmentIndex</td>
    <td>${HumanDevelopmentIndex1}</td>
    <td>${HumanDevelopmentIndex2}</td>
    <td>${HumanDevelopmentIndex3}</td>
  </tr>
  <tr>
   <td>11</td>
    <td>HealthIndex</td>
    <td>${HealthIndex1}</td>
    <td>${HealthIndex2}</td>
    <td>${HealthIndex3}</td>
  </tr>
  <tr>
   <td>12</td>
    <td>EducationIndex</td>
    <td>${EducationIndex1}</td>
    <td>${EducationIndex2}</td>
    <td>${EducationIndex3}</td>
  </tr>
  <tr>
   <td>13</td>
    <td>IncomeIndex</td>
    <td>${IncomeIndex1}</td>
    <td>${IncomeIndex2}</td>
    <td>${IncomeIndex3}</td>
  </tr>
  <tr>
   <td>14</td>
    <td>StateSpendingEducation</td>
    <td>${StateSpendingEducation1}</td>
    <td>${StateSpendingEducation2}</td>
    <td>${StateSpendingEducation3}</td>
  </tr>
  <tr>
   <td>15</td>
    <td>StateSpendingResearch</td>
    <td>${StateSpendingResearch1}</td>
    <td>${StateSpendingResearch2}</td>
    <td>${StateSpendingResearch3}</td>
  </tr>
  <tr>
   <td>16</td>
    <td>StateSpendingMedicine</td>
    <td>${StateSpendingMedicine1}</td>
    <td>${StateSpendingMedicine2}</td>
    <td>${StateSpendingMedicine3}</td>
  </tr>
  <tr>
   <td>17</td>
    <td>minimumExpensee</td>
    <td>${minimumExpence1}</td>
    <td>${minimumExpence2}</td>
    <td>${minimumExpence3}</td>
  </tr>
  <tr>
   <td>18</td>
    <td>maximumExpense</td>
    <td>${maximumExpence1}</td>
    <td>${maximumExpence2}</td>
    <td>${maximumExpence3}</td>
  </tr>
  
  <tr>
   <td>19</td>
    <td>AverageTemp</td>
    <td>${AverageTemp1}</td>
    <td>${AverageTemp2}</td>
    <td>${AverageTemp3}</td>
  </tr>
  <tr>
   <td>20</td>
    <td>WeatherRank</td>
    <td>${WeatherRank1}</td>
    <td>${WeatherRank2}</td>
    <td>${WeatherRank3}</td>
  </tr>
  
</table>
<span>
<h2 style = "text-align:center"><b> Happy Relocate !! :) </b> </h2>
</body>
</html>