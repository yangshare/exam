<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>

<html:html>
<head>
  <html:base/>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title><bean:message key="exam.title"/></title>
          &nbsp;&nbsp;&nbsp;
          <html:link action="/admin/program/searchAction?jsp=e_list">
            <bean:message key="button.back"/>
          </html:link>
<link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />
<script type="text/javascript" src="../js/tabs.js"></script>
  <link href="../css/main.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffff">
  <html:form action="/exam/saveTestPaper" method="post">
    <bean:size id="s_size" name="preViewExamTestPaperForm" property="singleQ"/>
    <bean:size id="m_size" name="preViewExamTestPaperForm" property="multiQ"/>
    <bean:size id="f_size" name="preViewExamTestPaperForm" property="fitinQ"/>
    <bean:size id="a_size" name="preViewExamTestPaperForm" property="answerQ"/>
    <table align="center">
        <TR>
          <TD width="775" align=center class=info1>
            <div align="center" class="style2 style1"><bean:message key="exam.inqury"/></div>
          </TD>
        </TR>
        <TR>
          <TD align=center>
            <table cellSpacing=0 cellPadding=0 width=760 align=center border=0>
              <TBODY>
                <TR>
                  <TD bgColor=#333333 height=2>                  </TD>
                </TR>
              </TBODY>
            </table>
          </TD>
        </TR>
        <TR>
          <TD align=center bgcolor="FFFFFF">
            <table cellSpacing=0 bgColor=#ffffff cellPadding=1 width=760 align=center border=1>
              <TBODY>
                <TR>
                  <TD bgColor="#0099FF">
                    <table class=list1 cellSpacing=0 cellPadding=0 width="100%" align=center bgColor=#FFFFFF border=0>
                      <TBODY>
                        <TR bgcolor="#FFFFFF">
                          <td width="11%">                          </td>
                          <TD colspan="2">
                            <B><bean:message key="search.e_name"/></B>
                           <bean:write name="preViewExamTestPaperForm" property="examPaper.e_name"/>
</TD>
                          <td width="11%">                          </td>
                        </TR>
                        <TR>
                        <TR bgcolor="#FFFFFF">
                          <td width="11%">                          </td>
                          <TD>
                            <B><bean:message key="search.e_begin"/>:</B>
                            <bean:write name="preViewExamTestPaperForm" property="examPaper.e_begin"/>
                          </TD>
                          <td width="39%">
                            <B><bean:message key="search.e_end"/>:</B>
                            <bean:write name="preViewExamTestPaperForm" property="examPaper.e_end"/>
                          </TD>
                        </TR>
                        <TR bgcolor="#FFFFFF">
                          <td width="11%">                          </td>


                          <td width="11%">                          </td>
                        </TR>
                        <TR bgcolor="#FFFFFF">
                          <td width="11%">                          </td>
                          <TD>
                            <B><bean:message key="exam.e_timer"/></B>
                            <bean:write name="preViewExamTestPaperForm" property="examPaper.e_timer"/>
                          </TD>
                          <td width="39%">
                          </TD>
                        </TR>
                        <TR bgcolor="#FFFFFF">
                          <td width="11%">                          </td>
                          <td width="39%">
                            <B><bean:message key="exam.e_total"/></B>
                            <bean:write name="preViewExamTestPaperForm" property="examPaper.e_total"/>
                          </TD>
                          <td width="39%">
                            <B><bean:message key="exam.e_passvalue"/></B>
                            <bean:write name="preViewExamTestPaperForm" property="examPaper.e_passvalue"/>
                          </TD>
                          <td width="11%">                          </td>
                        </TR>
                        <TR bgcolor="#FFFFFF">
                          <td width="11%">                          </td>



                          <td width="11%">                          </td>
                        </TR>
                        <TR bgcolor="#FFFFFF">
                          <td width="11%">                          </td>


                          <td width="11%">                          </td>
                        </TR>
                        <TR bgcolor="#FFFFFF">
                          <td width="11%">                          </td>


                          <td width="39%">                          </td>
                        </TR>
                      </TBODY>
                    </table>
                  </TD>
                </TR>
              </TBODY>
            </table>
            <table cellSpacing=0 cellPadding=0 width=680 align=center border=0>
              <TBODY>
                <TR>
                  <TD class=info2 height=20 valign="bottom">
                    <DIV align=center>
                      <strong><bean:message key="exam.details"/></strong>
                    </DIV>
                  </TD>
                </TR>
              </TBODY>
            </table>
        <TR>
          <TD align=center>
            <table cellSpacing=0 cellPadding=0 width=760 align=center border=0>
              <TBODY>
                <TR>
                  <TD bgColor=#333333 height=2>                  </TD>
                </TR>
              </TBODY>
            </table>
          </TD>
        </TR>
        <table cellSpacing=0 bgColor=#ffffff cellPadding=1 width=760 align=center border=1>
          <logic:greaterThan name="s_size" value="0">
            <tr>
              <td width="650" bgcolor="#FFFFFF">
                <table width="90%" border="0" cellpadding="1" cellspacing="3" class="ex2">
                  <tr>
                    <td height="2" colspan="2" valign="top">
                      <strong><bean:message key="question.single"/>:</strong>
                    </td>
                  </tr>
                  <logic:iterate id="singleQ" name="preViewExamTestPaperForm" property="singleQ" indexId="index">
                    <br>
                    <tr>
                      <td valign="top" width="27" height="30">
                        <strong><%= index.intValue()+1 %>.</strong>
                      </td>
                      <td valign="top" width="645">
                        <bean:write name="singleQ" property="q_content"/>
                      </td>
                    </tr>
                    <logic:notEmpty name="singleQ" property="q_picture">
                      <tr>
                        <td height="48">&nbsp;</td>
                        <td>
                          <bean:define id="picture" name="singleQ" property="q_picture"/>
                          <html:img src='<%="../../upload/images/"+picture%>' width="100" height="100"/>
                        </td>
                      </tr>
                    </logic:notEmpty>
                    <tr>
                      <td height="30">&nbsp;</td>
                      <td bgcolor="#FFFFFF">
                        A
                        <bean:write name="singleQ" property="text1"/>
                        <br>
                        B
                        <bean:write name="singleQ" property="text2"/>
                        <br>
                        C
                        <bean:write name="singleQ" property="text3"/>
                        <br>
                        D
                        <bean:write name="singleQ" property="text4"/>
                      </td>
                    </tr>
                    <br>
                    <strong>
                      <tr>
                        <td height="30">&nbsp;</td>
                        <td width="760" style="WORD-BREAK: break-all">
                          <bean:message key="exam.q_standard"/>
                          <bean:write name="singleQ" property="q_standard"/>

                          <br>
                          <bean:message key="exam.q_value"/>
                          <bean:write name="singleQ" property="q_value"/>

                          <br>
                        </td>
                      </tr>
                    </strong>
                  </logic:iterate>
                </table>
              </td>
            </tr>
          </logic:greaterThan>
          <logic:greaterThan name="m_size" value="0">
            <tr>
              <td width="680" bgcolor="#FFFFFF">
                <table width="100%" border="0" cellpadding="1" cellspacing="3" class="ex2">
                  <tr>
                    <td height="29" colspan="2" valign="top">
                      <strong><bean:message key="question.multi"/>:</strong>
                    </td>
                  </tr>
                  <logic:iterate id="multiQ" name="preViewExamTestPaperForm" property="multiQ" indexId="index">
                    <tr>
                      <td valign="top" width="27" height="30">
                        <strong><%= index.intValue()+1 %>                          .
</strong>
                      </td>
                      <td valign="top" width="660" style="WORD-BREAK: break-all">
                        <bean:message key="question.content"/>
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
                          <html:img src='<%="../../upload/images/"+picture%>' width="100" height="100"/>
                        </td>
                      </tr>
                    </logic:notEmpty>
                    <tr>
                      <td height="48">&nbsp;</td>
                      <td>                        A:
                        &nbsp;
                        <bean:write name="multiQ" property="text1"/>
                        <br>
                        B:
                        &nbsp;
                        <bean:write name="multiQ" property="text2"/>
                        <br>
                        C:
                        &nbsp;
                        <bean:write name="multiQ" property="text3"/>
                        <br>
                        D:
                        &nbsp;
                        <bean:write name="multiQ" property="text4"/>
                        <br>
                        E:
                        &nbsp;
                        <bean:write name="multiQ" property="text5"/>
                      </td>
                    </tr>
                    <br>
                    <tr>
                      <td height="30">&nbsp;</td>
                      <td width="680" style="WORD-BREAK: break-all">
                       <bean:message key="exam.q_standard"/>
                        <bean:write name="multiQ" property="q_standard"/>

                        <br>
                       <bean:message key="exam.q_value"/>
                        <bean:write name="multiQ" property="q_value"/>

                        <br>
                      </td>
                    </tr>
                  </logic:iterate>
                </table>
            </tr>
          </logic:greaterThan>
          <logic:greaterThan name="f_size" value="0">
            <tr>
              <td width="680" bgcolor="#FFFFFF">
                <table width="90%" border="0" cellpadding="1" cellspacing="3" class="ex2">
                  <tr>
                    <td height="29" colspan="2" valign="top">
                      <strong><bean:message key="question.fitin"/></strong>
                    </td>
                  </tr>
                  <logic:iterate id="fitinQ" name="preViewExamTestPaperForm" property="fitinQ" indexId="index">
                    <tr>
                      <td valign="top" width="27" height="30">
                        <strong><%= index.intValue()+1 %>                          .
</strong>
                      </td>
                      <td width="647" valign="top">
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
                          <html:img src='<%="../../upload/images/"+picture%>' width="100" height="100"/>
                        </td>
                      </tr>
                    </logic:notEmpty>
                    <tr>
                      <td height="30">&nbsp;</td>
                      <td width="680" style="WORD-BREAK: break-all">
                       <bean:message key="exam.q_standard"/>
                        <bean:write name="fitinQ" property="q_standard"/>

                        <br>
                       <bean:message key="exam.q_value"/>
                        <bean:write name="fitinQ" property="q_value"/>

                        <br>
                      </td>
                    </tr>
                  </logic:iterate>
                </table>
              </td>
            </tr>
          </logic:greaterThan>
          <logic:greaterThan name="a_size" value="0">
            <tr>
              <td width="650" bgcolor="#FFFFFF">
                <table width="90%" border="0" cellpadding="1" cellspacing="3" class="ex2">
                  <tr>
                    <td height="29" colspan="2" valign="top">
                      <strong><bean:message key="question.answer"/>:</strong>
                    </td>
                  </tr>
                  <logic:iterate id="answerQ" name="preViewExamTestPaperForm" property="answerQ" indexId="index">
                    <tr>
                      <td valign="top" width="27" height="30">
                        <strong><%= index.intValue()+1 %>                          .
</strong>
                      </td>
                      <td valign="top" width="645">
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
                          <html:img src='<%="../../upload/images/"+picture%>' width="100" height="100"/>
                        </td>
                      </tr>
                    </logic:notEmpty>
                    <tr>
                      <td height="30">&nbsp;</td>
                     <td width="680" style="WORD-BREAK: break-all">
                       <bean:message key="exam.q_standard"/>
                        <bean:write name="answerQ" property="q_standard"/>

                        <br>
                       <bean:message key="exam.q_value"/>
                        <bean:write name="answerQ" property="q_value"/>

                        <br>
                      </td>
                    </tr>

                  </logic:iterate>
                </table>
              </td>
            </tr>
</logic:greaterThan>
</table>
</table>
</html:form>

</html:html>
