<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
  <html:base/>
<head>
<title>
  <bean:message key="question.title.fitin"/>
</title>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
    <html:javascript formName="questionActionForm" dynamicJavascript="true" staticJavascript="true"/>
<script language="javascript" type="text/javascript">
<!--
function load_img(p_src,write_id){
        var t_html;
	if(p_src!=''){
		t_html="<img src=\'"+p_src+"\' onLoad=\'javascript:if(this.width>240){this.width=240;}if(this.height>240){this.height=240;}\'>";
	} else {
		t_html="";
	}
	write_id.innerHTML=t_html;
}
//-->
</script>
</head>
<body>
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
    <tr>
      <td class="tabletitletext">
        <div align="center" class="tabletitletext">
          <bean:message key="question.title.fitin"/>
        </div>
      </td>
    </tr>
  </table>
  <!--DWLayoutTable-->
  <html:form action="/admin/question/AUD_QuestionAction.do?action=fitin&method=addQuestion" method="post" enctype="multipart/form-data" onsubmit="return validateQuestionActionForm(this);">
    <table width="600" border="0" align="center" cellpadding="7" cellspacing="0" class="InputFrameMain">
      <bean:message key="question.baseinfo"/>
      <tr>
        <td width="3%" nowrap>&nbsp;</td>
        <td width="11%" nowrap>
          <strong>
            <b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="question.class"/>
          </strong>
        </td>
        <td width="22%" nowrap>
          <html:select styleClass="input" name="questionActionForm" property="question.q_class" style="width=110">
            <html:optionsCollection name="q_classOpts"/>
          </html:select>
        </td>
        <td width="11%" nowrap>
          <div align="center">
            <strong>
              <b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="question.knowledge"/>
            </strong>
          </div>
        </td>
        <td width="22%" nowrap>
          <html:select styleClass="input" name="questionActionForm" property="question.q_knowledge" style="width=110">
            <html:optionsCollection name="q_knowledgeOpts"/>
          </html:select>
        </td>
        <td width="10%" nowrap>
          <div align="center">
            <strong>
              <b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="question.value"/>
            </strong>
          </div>
        </td>
        <td width="18%" nowrap>
          <span class="InputAreaCell">
            <html:text styleClass="input" value="" name="questionActionForm" maxlength="10" property="question.q_value" size="10" style="width=110"/>
          </span>
        </td>
        <td width="3%" nowrap>&nbsp;</td>
      </tr>
      <tr>
        <td nowrap>&nbsp;</td>
        <td nowrap>
          <strong>
            <bean:message key="question.type"/>
          </strong>
        </td>
       <td nowrap>
           <bean:message key="question.fitin"/>
        </td>
        <td nowrap>
          <div align="center">
            <strong>
              <b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="question.difficulty"/>
            </strong>
          </div>
        </td>
        <td nowrap>
          <html:select styleClass="input" name="questionActionForm" property="question.q_difficulty" style="width=110">
            <html:optionsCollection name="q_difficultyOpts"/>
          </html:select>
        </td>
        <td nowrap>
          <div align="center">          </div>
        </td>
        <td nowrap>
          <span class="InputAreaCell">          </span>
        </td>
        <td nowrap>&nbsp;</td>
        <td height="30" colspan="8" nowrap>
          <div align="center">          </div>
          <div align="center">


      <tr>      </tr>
</div></td></tr>    </table>
    <br>

<table width="593" border="0" align="center" cellpadding="7" cellspacing="0" class="InputFrameMain">
  <!--DWLayoutTable-->
  <b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="question.content"/>
  <tr>
    <td width="120" height="89" nowrap> <bean:message key="question.content"/> <span class="InputAreaCell"> <strong> <br>
      </strong> </span> </td>
    <td width="445"> <html:textarea cols="60" rows="5" name="questionActionForm" property="question.q_content"> </html:textarea> </td>
  </tr>
</table>
    <br>
    <table width="595" border="0" align="center" cellpadding="7" cellspacing="0" class="InputFrameMain">
      <b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="question.standard"/>
      <tr>
        <td width="50%" nowrap>          &nbsp;
          <span class="InputAreaCell">
            <strong>
              <bean:message key="question.standard"/>
              <br />
              <html:textarea name="questionActionForm" property="question.q_standard" cols="40" rows="6">              </html:textarea>
            </strong>
          </span>
        </td>
        <td width="50%">          &nbsp;
          <span class="InputAreaCell">
            <strong>
              <bean:message key="question.picture"/>
              <br>
              <html:file name="questionActionForm" property="file" onchange="load_img(this.value,img);"/>
              <br>
              <br>
              <bean:message key="question.preview"/>
              <div id="img"></div>
              <br>
            </strong>
          </span>
        </td>
      </tr>
      <tr>
        <td nowrap>          &nbsp;
          <strong>&nbsp;</strong>
        </td>
        <td nowrap>&nbsp;</td>
      </tr>
      <tr>
        <td height="30" colspan="8" nowrap>
          <div align="center">          </div>
          <div align="center">
            <html:submit styleClass="button">
              <bean:message key="question.submit"/>
            </html:submit>
            &nbsp;&nbsp;
            <html:reset styleClass="button">
              <bean:message key="question.reset"/>
            </html:reset>
          </div>
        </td>
      </tr>
    </table>
  </html:form>
</body>
</html:html>
