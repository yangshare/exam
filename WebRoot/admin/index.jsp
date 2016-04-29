<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html>
<head>
<title>
<bean:message key="admin.title"/>
</title>
<Script LANGUAGE="JavaScript">
if(self!=top){top.location=self.location;}
</script>
</head>

<frameset rows="*" cols="180,*"  framespacing="0" frameborder="NO" border="0">
  <frame name="menu" scrolling="NO" noresize src="/exam/admin/menu.jsp">
  <frame name="main" src="/exam/admin/data.htm">
</frameset>
<noframes><body>

</body></noframes>
</html>
