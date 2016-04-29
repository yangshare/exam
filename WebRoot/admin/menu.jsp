<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html locale="true">
<head>
  <html:base/>
<title>
index
</title>
<link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/menuExpandable.css" />
<script type="text/javascript" src="../js/menuExpandable.js"></script>
</head>
<body bgcolor="#000fff">
<menu:useMenuDisplayer name="ListMenu" bundle="org.apache.struts.action.MESSAGE" permissions="rolesAdapter">
    <SCRIPT  language="javascript">
<!--
function  Year_Month(){
	var  now  =  new  Date();
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
	document.all.yyyy.innerHTML = thisYear();
	document.all.mm.innerHTML = Year_Month();
	document.all.dd.innerHTML = Date_of_Today();
	document.all.tt.innerHTML = CurentTime();  }
	document.write('<font face="Arial" id="yyyy"></font>');
	document.write('<font face="Arial" id="mm"></font>');
	document.write('<font face="Arial" id="dd"></font>&nbsp;');
	document.write('<font face="Arial" id="tt"></font>');
	setInterval('refreshCalendarClock()',1000);
//-->
</SCRIPT>
  	<menu:displayMenu name="TesterMenuExaminee" />
    <menu:displayMenu name="TesterMenuQuestion" />
    <menu:displayMenu name="TesterMenuPaper" />
    <menu:displayMenu name="TesterMenuMaintain" />
    <menu:displayMenu name="TesterMenuAdmin" />
    
</menu:useMenuDisplayer>
</body>
</html:html>
