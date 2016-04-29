<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>
<html:html>
<head>
<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title><bean:message key="exam.title"/></title>
<link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />

<script type="text/javascript" src="../js/tabs.js"></script>
	<script language="JavaScript" src="../js/util.js" type="text/JavaScript"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css">

<script language="Javascript" type="text/Javascript">
</script>

<script language="Javascript" type="text/Javascript">
function KeyDown(){
	if ((window.event.altKey)&&((window.event.keyCode==37)||(window.event.keyCode==39))){
		event.returnValue=false;
	}//Alt+ left ||Alt+ right
	if (event.keyCode==116){//backspace||f5
		event.keyCode=0;
		event.returnValue=false;
	}
	if ((event.ctrlKey)&&(event.keyCode==78)){//ctrl + n
		event.returnValue=false;
	}
	if ((event.shiftKey)&&(event.keyCode==121)){//shift + f10
		event.returnValue=false;
	}
}
 </script>
 <script language="javascript" type="text/Javascript">
	window.document.attachEvent("onselectstart",f_CancelEvent);
	window.document.attachEvent("ondragstart",f_CancelEvent);
	window.document.attachEvent("oncontextmenu",f_CancelEvent);

	function f_CancelEvent()
	{
		event.returnValue = false;
	}
</script>
<script language=javascript type="text/javascript">
var timer=<%=request.getAttribute("e_timer")%>;
</script>

</head>
<body bgcolor="#ffffff" onkeydown="KeyDown()" onload="sub()">
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
	return(now.getDate()+" ");
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

  <html:form action="/exam/saveTestPaper.do" method="post" name="frm" type="org.mmxbb.exam.business.exam.ExamTestActionForm">
   <html:hidden name="examTestActionForm" property="t_id"/>
  <bean:size id="s_size" name="examTestActionForm" property="singleQ"/>
  <bean:size id="m_size" name="examTestActionForm" property="multiQ"/>
  <bean:size id="f_size" name="examTestActionForm" property="fitinQ"/>
  <bean:size id="a_size" name="examTestActionForm" property="answerQ"/>
    <table width="760" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="760" height="32">&nbsp;</td>
      </tr>
      <tr>
        <td>
          <div align="center" class="examtitle">
            <bean:message key="exam.examName"/>
            <bean:write name="examTestActionForm" property="examPaper.e_name"/>
            &nbsp;&nbsp;
            <bean:message key="examineeActionForm.examinee_id"/>
            <bean:write name="examTestActionForm" property="examinee_id"/>
            &nbsp;&nbsp;
            <bean:message key="examineeActionForm.name"/>
            <bean:write name="examTestActionForm" property="examinee_name"/>
          </div>
        </td>
      </tr>
      <tr>
        <td height="2">        </td>
        <td>
          <html:submit styleClass="button"><bean:message key="exam.submit"/></html:submit>
        </td>
      </tr>
      <tr>
        <td height="2" bgcolor="#000066">        </td>
      </tr>
    </table>
    <table width="760" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="760" height="10">      </td>
      </tr>
    </table>
     <%int i = -1;          %>
    <table width="760" align="center" border="0" cellpadding="12" cellspacing="1" bgcolor="#FF3300">
    <logic:greaterThan name="s_size" value="0">
      <tr>
        <td width="760" bgcolor="#FFFFFF">
          <table width="100%" border="0" cellpadding="1" cellspacing="3" class="ex2">
            <tr>
              <td height="29" colspan="2" valign="top">
                <strong> <bean:message key="question.single"/></strong>
              </td>
            </tr>

            <logic:iterate id="singleQ" name="examTestActionForm" property="singleQ" indexId="index">
              <tr>
                <td  valign="top" width="23" height="30"><strong><%= index.intValue()+1 %>    .</strong>
</td>
                <td valign="top" width="603" style="WORD-BREAK: break-all">
                  <bean:write name="singleQ" property="q_content"/>
                  <bean:message key="exam.leftscore"/>
                  <bean:write name="singleQ" property="q_value"/>
                 <bean:message key="exam.rightscore"/>
                </td>
              </tr>
              <logic:notEmpty name="singleQ" property="q_picture">
                <tr>
                  <td height="48">&nbsp;</td>
                  <td>
                    <bean:define id="picture" name="singleQ" property="q_picture"/>
                    <html:img src='<%="../upload/images/"+picture%>' width="100" height="100"/>
                  </td>
                </tr>
              </logic:notEmpty>
              <tr>
              <%i = i + 1;%>
                <td height="30">&nbsp;</td>
                <td>
                  <html:radio value="A" name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>'/>
                  <bean:write name="singleQ" property="text1"/>
                  <br>
                  <html:radio value="B" name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>'/>
                  <bean:write name="singleQ" property="text2"/>
                  <br>
                  <html:radio value="C" name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>'/>
                  <bean:write name="singleQ" property="text3"/>
                  <br>
                  <html:radio value="D" name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>'/>
                  <bean:write name="singleQ" property="text4"/>
                </td>
              </tr>
            </logic:iterate>
          </table>
        </td>
      </tr>
       </logic:greaterThan>
       <logic:greaterThan name="m_size" value="0">

      <tr>
        <td width="760" bgcolor="#FFFFFF">
          <table width="100%" border="0" cellpadding="1" cellspacing="3" class="ex2">
            <tr>
              <td height="29" colspan="2" valign="top">
                <strong> <bean:message key="question.multi"/></strong>
              </td>
            </tr>
            <logic:iterate id="multiQ" name="examTestActionForm" property="multiQ" indexId="index">
              <tr>
                <td valign="top" width="23" height="30"><strong><%= index.intValue()+1 %>                  .</strong>
</td>
                <td valign="top" width="603" style="WORD-BREAK: break-all">
                  <bean:write name="multiQ" property="q_content"/>
                  <bean:message key="exam.leftscore"/>
                  <bean:write name="multiQ" property="q_value"/>
                  <bean:message key="exam.rightscore"/>
                </td>
              </tr>
              <logic:notEmpty name="multiQ" property="q_picture">
                <tr>
                  <td height="48">&nbsp;</td>
                  <td>
                    <bean:define id="picture" name="multiQ" property="q_picture"/>
                    <html:img src='<%="../upload/images/"+picture%>' width="100" height="100"/>
                  </td>
                </tr>
              </logic:notEmpty>
              <tr>
                <td height="48">&nbsp;</td>
                <td>
                  <html:multibox value="A" name="examTestActionForm" property='<%= "multi_answer[" + index.intValue() + "]" %>'/>
                  <bean:write name="multiQ" property="text1"/>
                  <br>
                  <html:multibox value="B" name="examTestActionForm" property='<%= "multi_answer[" + index.intValue() + "]" %>'/>
                  <bean:write name="multiQ" property="text2"/>
                  <br>
                  <html:multibox value="C" name="examTestActionForm" property='<%= "multi_answer[" + index.intValue() + "]" %>'/>
                  <bean:write name="multiQ" property="text3"/>
                  <br>
                  <html:multibox value="D" name="examTestActionForm" property='<%= "multi_answer[" + index.intValue() + "]" %>'/>
                  <bean:write name="multiQ" property="text4"/>
                  <br>
                  <html:multibox value="E" name="examTestActionForm" property='<%= "multi_answer[" + index.intValue() + "]" %>'/>
                  <bean:write name="multiQ" property="text5"/>
                </td>
              </tr>
            </logic:iterate>
          </table>

      </tr>
</tr>
</logic:greaterThan>
<logic:greaterThan  name="f_size" value="0">

<tr>
        <td width="760" bgcolor="#FFFFFF">
          <table width="100%" border="0" cellpadding="1" cellspacing="3" class="ex2">
            <tr>
              <td height="29" colspan="2" valign="top">
                <strong> <bean:message key="question.fitin"/></strong>
              </td>
            </tr>
            <logic:iterate id="fitinQ" name="examTestActionForm" property="fitinQ" indexId="index">
              <tr>
                <td valign="top" width="23" height="30"><strong><%= index.intValue()+1 %>                  .</strong>
</td>
                <td width="603" valign="top" style="WORD-BREAK: break-all">
                  <bean:write name="fitinQ" property="q_content"/>
                  <bean:message key="exam.leftscore"/>
                  <bean:write name="fitinQ" property="q_value"/>
               <bean:message key="exam.rightscore"/>
                </td>
              </tr>
              <logic:notEmpty name="fitinQ" property="q_picture">
                <tr>
                  <td height="48">&nbsp;</td>
                  <td>
                    <bean:define id="picture" name="fitinQ" property="q_picture"/>
                    <html:img src='<%="../upload/images/"+picture%>' width="100" height="100"/>
                  </td>
                </tr>
              </logic:notEmpty>
              <tr>
                <td height="48">&nbsp;</td>
                <td>
                <%i = i + 1;%>
                  <html:textarea name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>' cols="60" rows="2"/>
                </td>
              </tr>
            </logic:iterate>
          </table>
        </td>
      </tr>
      </logic:greaterThan>
      <logic:greaterThan  name="a_size" value="0">

      <tr>
        <td width="760" bgcolor="#FFFFFF">
          <table width="100%" border="0" cellpadding="1" cellspacing="3" class="ex2">
            <tr>
              <td height="29" colspan="2" valign="top">
                <strong> <bean:message key="question.answer"/></strong>
              </td>
            </tr>
            <logic:iterate id="answerQ" name="examTestActionForm" property="answerQ" indexId="index">
              <tr>
                <td valign="top" width="23" height="30"><strong><%= index.intValue()+1 %>                  .</strong>
</td>
                <td valign="top" width="603" style="WORD-BREAK: break-all">
                  <bean:write name="answerQ" property="q_content"/>
                  <bean:message key="exam.leftscore"/>
                  <bean:write name="answerQ" property="q_value"/>
                 <bean:message key="exam.rightscore"/>
                </td>
              </tr>
              <logic:notEmpty name="answerQ" property="q_picture">
                <tr>
                  <td height="48">&nbsp;</td>
                  <td>
                    <bean:define id="picture" name="answerQ" property="q_picture"/>
                    <html:img src='<%="../upload/images/"+picture%>' width="100" height="100"/>
                  </td>
                </tr>
              </logic:notEmpty>
              <tr>
                <td height="48">&nbsp;</td>
                <td>
                <%i = i + 1;                %>
                  <html:textarea name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>' cols="60" rows="6"/>
                </td>
              </tr>
            </logic:iterate>
          </table>
  </logic:greaterThan>
</table>
  </html:form>
</body>
</html:html>
