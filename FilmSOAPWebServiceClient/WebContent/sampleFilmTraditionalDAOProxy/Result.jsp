<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleFilmTraditionalDAOProxyid" scope="session" class="dao.FilmTraditionalDAOProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleFilmTraditionalDAOProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleFilmTraditionalDAOProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleFilmTraditionalDAOProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        dao.FilmTraditionalDAO getFilmTraditionalDAO10mtemp = sampleFilmTraditionalDAOProxyid.getFilmTraditionalDAO();
if(getFilmTraditionalDAO10mtemp == null){
%>
<%=getFilmTraditionalDAO10mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
</TABLE>
<%
}
break;
case 15:
        gotMethod = true;
        String stars_2id=  request.getParameter("stars20");
            java.lang.String stars_2idTemp = null;
        if(!stars_2id.equals("")){
         stars_2idTemp  = stars_2id;
        }
        String review_3id=  request.getParameter("review22");
            java.lang.String review_3idTemp = null;
        if(!review_3id.equals("")){
         review_3idTemp  = review_3id;
        }
        String director_4id=  request.getParameter("director24");
            java.lang.String director_4idTemp = null;
        if(!director_4id.equals("")){
         director_4idTemp  = director_4id;
        }
        String year_5id=  request.getParameter("year26");
        int year_5idTemp  = Integer.parseInt(year_5id);
        String title_6id=  request.getParameter("title28");
            java.lang.String title_6idTemp = null;
        if(!title_6id.equals("")){
         title_6idTemp  = title_6id;
        }
        String id_7id=  request.getParameter("id30");
        int id_7idTemp  = Integer.parseInt(id_7id);
        %>
        <jsp:useBean id="model1Film_1id" scope="session" class="model.Film" />
        <%
        model1Film_1id.setStars(stars_2idTemp);
        model1Film_1id.setReview(review_3idTemp);
        model1Film_1id.setDirector(director_4idTemp);
        model1Film_1id.setYear(year_5idTemp);
        model1Film_1id.setTitle(title_6idTemp);
        model1Film_1id.setId(id_7idTemp);
        int insertFilm15mtemp = sampleFilmTraditionalDAOProxyid.insertFilm(model1Film_1id);
        String tempResultreturnp16 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(insertFilm15mtemp));
        %>
        <%= tempResultreturnp16 %>
        <%
break;
case 32:
        gotMethod = true;
        String stars_9id=  request.getParameter("stars37");
            java.lang.String stars_9idTemp = null;
        if(!stars_9id.equals("")){
         stars_9idTemp  = stars_9id;
        }
        String review_10id=  request.getParameter("review39");
            java.lang.String review_10idTemp = null;
        if(!review_10id.equals("")){
         review_10idTemp  = review_10id;
        }
        String director_11id=  request.getParameter("director41");
            java.lang.String director_11idTemp = null;
        if(!director_11id.equals("")){
         director_11idTemp  = director_11id;
        }
        String year_12id=  request.getParameter("year43");
        int year_12idTemp  = Integer.parseInt(year_12id);
        String title_13id=  request.getParameter("title45");
            java.lang.String title_13idTemp = null;
        if(!title_13id.equals("")){
         title_13idTemp  = title_13id;
        }
        String id_14id=  request.getParameter("id47");
        int id_14idTemp  = Integer.parseInt(id_14id);
        %>
        <jsp:useBean id="model1Film_8id" scope="session" class="model.Film" />
        <%
        model1Film_8id.setStars(stars_9idTemp);
        model1Film_8id.setReview(review_10idTemp);
        model1Film_8id.setDirector(director_11idTemp);
        model1Film_8id.setYear(year_12idTemp);
        model1Film_8id.setTitle(title_13idTemp);
        model1Film_8id.setId(id_14idTemp);
        int updateFilm32mtemp = sampleFilmTraditionalDAOProxyid.updateFilm(model1Film_8id);
        String tempResultreturnp33 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(updateFilm32mtemp));
        %>
        <%= tempResultreturnp33 %>
        <%
break;
case 49:
        gotMethod = true;
        String searchName_15id=  request.getParameter("searchName52");
            java.lang.String searchName_15idTemp = null;
        if(!searchName_15id.equals("")){
         searchName_15idTemp  = searchName_15id;
        }
        model.Film[] getAllFilmsByTitle49mtemp = sampleFilmTraditionalDAOProxyid.getAllFilmsByTitle(searchName_15idTemp);
if(getAllFilmsByTitle49mtemp == null){
%>
<%=getAllFilmsByTitle49mtemp %>
<%
}else{
        String tempreturnp50 = null;
        if(getAllFilmsByTitle49mtemp != null){
        java.util.List listreturnp50= java.util.Arrays.asList(getAllFilmsByTitle49mtemp);
        tempreturnp50 = listreturnp50.toString();
        }
        %>
        <%=tempreturnp50%>
        <%
}
break;
case 54:
        gotMethod = true;
        String id_16id=  request.getParameter("id69");
        int id_16idTemp  = Integer.parseInt(id_16id);
        model.Film getFilmByID54mtemp = sampleFilmTraditionalDAOProxyid.getFilmByID(id_16idTemp);
if(getFilmByID54mtemp == null){
%>
<%=getFilmByID54mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">stars:</TD>
<TD>
<%
if(getFilmByID54mtemp != null){
java.lang.String typestars57 = getFilmByID54mtemp.getStars();
        String tempResultstars57 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typestars57));
        %>
        <%= tempResultstars57 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">review:</TD>
<TD>
<%
if(getFilmByID54mtemp != null){
java.lang.String typereview59 = getFilmByID54mtemp.getReview();
        String tempResultreview59 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typereview59));
        %>
        <%= tempResultreview59 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">director:</TD>
<TD>
<%
if(getFilmByID54mtemp != null){
java.lang.String typedirector61 = getFilmByID54mtemp.getDirector();
        String tempResultdirector61 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typedirector61));
        %>
        <%= tempResultdirector61 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">year:</TD>
<TD>
<%
if(getFilmByID54mtemp != null){
%>
<%=getFilmByID54mtemp.getYear()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">title:</TD>
<TD>
<%
if(getFilmByID54mtemp != null){
java.lang.String typetitle65 = getFilmByID54mtemp.getTitle();
        String tempResulttitle65 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typetitle65));
        %>
        <%= tempResulttitle65 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">id:</TD>
<TD>
<%
if(getFilmByID54mtemp != null){
%>
<%=getFilmByID54mtemp.getId()
%><%}%>
</TD>
</TABLE>
<%
}
break;
case 71:
        gotMethod = true;
        model.Film[] getAllFilms71mtemp = sampleFilmTraditionalDAOProxyid.getAllFilms();
if(getAllFilms71mtemp == null){
%>
<%=getAllFilms71mtemp %>
<%
}else{
        String tempreturnp72 = null;
        if(getAllFilms71mtemp != null){
        java.util.List listreturnp72= java.util.Arrays.asList(getAllFilms71mtemp);
        tempreturnp72 = listreturnp72.toString();
        }
        %>
        <%=tempreturnp72%>
        <%
}
break;
case 74:
        gotMethod = true;
        String filmID_17id=  request.getParameter("filmID77");
        int filmID_17idTemp  = Integer.parseInt(filmID_17id);
        int deleteFilm74mtemp = sampleFilmTraditionalDAOProxyid.deleteFilm(filmID_17idTemp);
        String tempResultreturnp75 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(deleteFilm74mtemp));
        %>
        <%= tempResultreturnp75 %>
        <%
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>