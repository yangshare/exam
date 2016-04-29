<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>
<%@ include file="menu.jsp"%>
<%@page contentType="text/html; charset=GBK"%>
<html:html>
<head> 
  <html:base/>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title><bean:message key="exam.title"/></title>
<link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />
<script type="text/javascript" src="../js/tabs.js"></script>

<script language="JavaScript"  type="text/JavaScript">
var w_x,w_y,item,okscroll=false,godown;
function init(){
  item=eval('document.all.item.style');
  getwindowsize();
  item.visibility='visible';
  scrollpage();
}

function getwindowsize()
{
  w_x=document.body.clientWidth;
  w_y=document.body.clientHeight;
  item.width=21;
  item.height=20;
  moveitem();
}

function moveitem()
{
  item.pixelLeft=document.body.scrollLeft+w_x-34;
  item.pixelTop=w_y+document.body.scrollTop-64;
}

function scrollpage()
{
  status='';
  if (okscroll)
  {
   if (godown)
   {
    window.scrollBy(0,2);
   } else {
    window.scrollBy(0,-2) ;
   }
  }
  moveitem();
  setTimeout('scrollpage()',30);
}
//ban refresh and backspace
function onKeyDown()
{
if ( (event.altKey) || ((event.keyCode == 8) &&
	(event.srcElement.type != "text" &&
	event.srcElement.type != "textarea" &&
	event.srcElement.type != "password")) ||
	((event.ctrlKey) && ((event.keyCode == 78) || (event.keyCode == 82))) || ((event.altKey) && (event.keyCode == 115)) ||
	(event.keyCode == 116)) {
 		event.keyCode = 0;
 		event.returnValue = false;
 	}
 }

var time_minute=60;
var time_second=1;
var timeid=null;
var runmark=false;

function stoptime()
{
 if(runmark) clearTimeout(timeid);
 runmark=false;
 }

function starttime()
{

  init();
  stoptime();
  showtime();
 }

function showtime()
{
 if (time_second==0)
 {
  time_second=59;
  time_minute-=1;
  }
  else
  {time_second-=1};

 if (time_minute<0) form1.submit();
 else
 {
  document.all.label_sy.innerHTML=time_minute+": "+time_second +": ";
  timeid=setTimeout("showtime()",1000);
  runmark=true;
  }
 }
</script>
  <link href="../css/main.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffff" onload="starttime()" onKeyDown="onKeyDown()">
  <html:form action="/exam/saveTestPaper.do" method="post" name="examTestActionForm" type="org.mmxbb.exam.business.exam.ExamTestActionForm">
    <html:hidden name="examTestActionForm" property="t_id"/>

    <table width="760" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="760" height="32">&nbsp;</td>
      </tr>
      <tr>
        <td>
          <div align="center" class="examtitle"> <bean:message key="examinationPaper.name" />
            <bean:write name="examTestActionForm" property="examPaper.e_name"/>
          </div>
        </td>
      </tr>
      <tr>
        <td height="2">        </td>
        <td>
          <html:submit styleClass="button"><bean:message key="button.submit" /></html:submit>
        </td>
      </tr>
      <tr>
        <td height="2" bgcolor="#000066">        </td>
      </tr>
    </table>
    <table width="760" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="760" height="10">        </td>
      </tr>
    </table>
    <table width="760" align="center" border="0" cellpadding="12" cellspacing="1" bgcolor="#FF3300">
      <tr>
        <td width="760" bgcolor="#FFFFFF">
          <table width="100%" border="0" cellpadding="1" cellspacing="3" class="ex2">
            <tr>
              <td height="29" colspan="2" valign="top">
                <strong>1.<bean:message key="question.single" />:</strong>
              </td>
            </tr>
          <%int i = -1;          %>
            <logic:iterate id="singleQ1" name="examTestActionForm" property="singleQ" indexId="index">
              <bean:define id="singleQ" name="singleQ1" type="org.mmxbb.exam.bean.QuestionInTest">              </bean:define>
              <tr>
                <td width="23" height="30"><%= index.intValue()+1 %>                  .
</td>
                <td width="603">
                  <bean:write name="singleQ" property="q_content"/>
                  (
                  <bean:write name="singleQ" property="q_value"/>
                  <bean:message key="examinationPaper.score" />)
                </td>
              </tr>
              <logic:notEmpty name="singleQ" property="q_picture">
                <tr>
                  <td height="48">&nbsp;</td>
                  <td>
                    <bean:define id="picture" name="singleQ" property="q_picture"/>
                    <html:img src='<%="../upload/images/"+picture%>' width="500" height="300"/>
                  </td>
                </tr>
              </logic:notEmpty>
              <tr>
              <%i = i + 1;              %>
                <td height="48">                </td>
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
      <tr>
        <td width="760" bgcolor="#FFFFFF">
          <table width="100%" border="0" cellpadding="1" cellspacing="3" class="ex2">
            <tr>
              <td height="29" colspan="2" valign="top">
                <strong>2.<bean:message key="question.multi" />:</strong>
              </td>
            </tr>
            <logic:iterate id="multiQ" name="examTestActionForm" property="multiQ" indexId="index">
              <tr>
                <td width="23" height="30"><%= index.intValue()+1 %>                  .
</td>
                <td width="603">
                  <bean:write name="multiQ" property="q_content"/>
                  (
                  <bean:write name="multiQ" property="q_value"/>
                  <bean:message key="examinationPaper.score" />)
                </td>
              </tr>
              <logic:notEmpty name="multiQ" property="q_picture">
                <tr>
                  <td height="48">&nbsp;</td>
                  <td>
                    <bean:define id="picture" name="multiQ" property="q_picture"/>
                    <html:img src='<%="../upload/images/"+picture%>' width="500" height="300"/>
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
      </td>
	</tr>
	<tr>
        <td width="760" bgcolor="#FFFFFF">
          <table width="100%" border="0" cellpadding="1" cellspacing="3" class="ex2">
            <tr>
              <td height="29" colspan="2" valign="top">
                <strong>3.<bean:message key="question.fitin" />:</strong>
              </td>
            </tr>
            <logic:iterate id="fitinQ" name="examTestActionForm" property="fitinQ" indexId="index">
              <tr>
                <td width="23" height="30"><%= index.intValue()+1 %>                  .
</td>
                <td width="603">
                  <bean:write name="fitinQ" property="q_content"/>
                  (
                  <bean:write name="fitinQ" property="q_value"/>
                  <bean:message key="examinationPaper.score" />)
                </td>
              </tr>
              <logic:notEmpty name="fitinQ" property="q_picture">
                <tr>
                  <td height="48">&nbsp;</td>
                  <td>
                    <bean:define id="picture" name="fitinQ" property="q_picture"/>
                    <html:img src='<%="../upload/images/"+picture%>' width="500" height="300"/>
                  </td>
                </tr>
              </logic:notEmpty>
              <tr>
                <td height="48">&nbsp;</td>
                <td>
                <%i = i + 1;                %>
                  <html:textarea name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>' cols="60" rows="6">                  </html:textarea>
                </td>
              </tr>
            </logic:iterate>
          </table>
        </td>
      </tr>
      <tr>
        <td width="760" bgcolor="#FFFFFF">
          <table width="100%" border="0" cellpadding="1" cellspacing="3" class="ex2">
            <tr>
              <td height="29" colspan="2" valign="top">
                <strong>4.<bean:message key="question.answer" />:</strong>
              </td>
            </tr>
            <logic:iterate id="answerQ" name="examTestActionForm" property="answerQ" indexId="index">
              <tr>
                <td width="23" height="30"><%= index.intValue()+1 %>                  .
</td>
                <td width="603">
                  <bean:write name="answerQ" property="q_content"/>
                  (
                  <bean:write name="answerQ" property="q_value"/>
                  <bean:message key="examinationPaper.score" />)
                </td>
              </tr>
              <logic:notEmpty name="answerQ" property="q_picture">
                <tr>
                  <td height="48">&nbsp;</td>
                  <td>
                    <bean:define id="picture" name="answerQ" property="q_picture"/>
                    <html:img src='<%="../upload/images/"+picture%>' width="500" height="300"/>
                  </td>
                </tr>
              </logic:notEmpty>
              <tr>
                <td height="48">&nbsp;</td>
                <td>
                <%i = i + 1;                %>
                  <html:textarea name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>' cols="60" rows="6">                  </html:textarea>
                </td>
              </tr>
            </logic:iterate>
          </table>
	</table>
  </html:form>
</body>
</html:html>
