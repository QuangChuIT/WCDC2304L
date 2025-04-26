<%@ page import="vn.aptech.session2.FileInfo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Servlet</title>
</head>
<body>
<h1>Files list</h1>
<table border="1">
    <tr>
        <th>Filename</th>
        <th>Size</th>
        <th></th>
    </tr>
    <%
        List<FileInfo> fileInfos = (List<FileInfo>) request.getAttribute("infoList");
        for (FileInfo info : fileInfos) {


    %>
    <tr>
        <td><%=info.getName()%></td>
        <td><%=info.getSize()%> (KB)</td>
        <td><a href="/file-servlet?action=download&filename=<%=info.getName()%>">Download</a></td>
    </tr>
    <%
        }
    %>
</table>
<div style="margin-top: 10px">
    <form action="/file-servlet?action=upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" value="Upload">
    </form>
</div>

</body>
</html>
