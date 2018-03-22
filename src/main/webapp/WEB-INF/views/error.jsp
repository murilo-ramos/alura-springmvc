<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Erro">

    <section id="index-section" class="container middle">
        <h2>Ocorreu um erro na aplicação. Estamos trabalhando para resolver o mais rápido possível.</h2>
    </section>
    
    <!-- 
        Mensagem: ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="stk">
            ${stk}
        </c:forEach>    
    -->

</tags:pageTemplate>