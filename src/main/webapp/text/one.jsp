<%--
  Created by IntelliJ IDEA.
  User: syl
  Date: 2017/9/22
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>测试小例子</title>
</head>
<body>
接受到的参数
${pd}


<form action="/text/one.do" method="get">
    <input type="text" name="123"/>

    <input type="text" name="dasdsa">
    <input type="submit" vlaue="提交">
</form>

pd 这里的pd是后台传到前台的map，所以我们可以用foeach去遍历它 $这个相当于jQuery，是el表达式，具体用法参照el表达式<br>
而c:catch之类的是jstl表达式，具体用法参照jstl表达式<br>
两者相互结合去读取数据<br>
<c:if test="${pd != null}">
    <table>
        <c:forEach items="${pd.list}" var="item">
            <tr>
            <td>
                    ${item} dasdsa
            </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
