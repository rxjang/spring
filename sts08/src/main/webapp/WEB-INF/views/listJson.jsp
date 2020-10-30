{"root":[
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:forEach items="${list }" var="bean" varStatus="status">
	<c:if test="${status.index ne 0}">,</c:if>
	{"deptno":${bean.deptno },"dname":"${bean.dname }","loc":"${bean.loc }"}
	</c:forEach>
]}

<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>