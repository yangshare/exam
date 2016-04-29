<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="Home"/>
    <menu:displayMenu name="GoToTest"/>
    <menu:displayMenu name="CheckTest"/>
    <menu:displayMenu name="UserInfo"/>
    <menu:displayMenu name="EditPwd"/>
    <menu:displayMenu name="LogOff"/>
</menu:useMenuDisplayer>
<div id="source">
  <table>
    <tr>
      <td nowrap="nowrap">
<bean:message key="exam.id" />
<app:checkLogon role="user" display="true"/>
<bean:message key="exam.current_time" />
<SCRIPT  language="javascript">
<!--
function  Year_Month(){
	var  now  =  new Date();
	var  yy  =  now.getYear();
	var  mm  =  now.getMonth();
	var  mmm=new  Array();
	mmm[0]="1-";
	mmm[1]="2-";
	mmm[2]="3-";
	mmm[3]="4-";
	mmm[4]="5-";
	mmm[5]="6-";
	mmm[6]="7-";
	mmm[7]="8-";
	mmm[8]="9-";
	mmm[9]="10-";
	mmm[10]="11-";
	mmm[11]="12-";
	mm=mmm[mm];
	return(mm);
}

function  thisYear(){
	var  now  =  new  Date();
	var  yy  =  now.getYear();
	return(yy + "-");
}

function  Date_of_Today(){
	var  now  =  new  Date();
	return(now.getDate());
}

function  CurentTime(){
	var  now  =  new  Date();
	var  hh  =  now.getHours();
	var  mm  =  now.getMinutes();
	var  ss  =  now.getTime()  %  60000;
	ss  =  (ss  -  (ss  %  1000))  /  1000;
	var  clock  =  hh+':';
	if  (mm  <  10)  clock  +=  '0';
	clock  +=  mm+':';
	if  (ss  <  10)  clock  +=  '0';
	clock  +=  ss;
	return(clock);
}

function  refreshCalendarClock(){
	document.all.calendarClock1.innerHTML = thisYear();
	document.all.calendarClock2.innerHTML = Year_Month();
	document.all.calendarClock3.innerHTML = Date_of_Today();
	document.all.calendarClock4.innerHTML = CurentTime();  }
	document.write('<font  id="calendarClock1"  >  </font>');
	document.write('<font  id="calendarClock2"  >  </font>');
	document.write('<font  id="calendarClock3"  >  </font>&nbsp;');
	document.write('<font  id="calendarClock4"  >  </font>');
	setInterval('refreshCalendarClock()',1000);
//-->
</SCRIPT>
</td>
</tr>
</table>
</div>
