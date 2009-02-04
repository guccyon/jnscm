<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="net.nanoflops.jnscm.util.ArrayUtil"%>
<%@page import="net.nanoflops.jnscm.ContextLoader"%>
<%@page import="net.nanoflops.jnscm.Context"%>
<% Context global = ContextLoader.createGlobal(); %>
<dl>
	<dt>特別式</dt>
	<dd><%= ArrayUtil.join(global.listSPForm(),"、")%></dd>
	<dt>関数式</dt>
	<dd><%= ArrayUtil.join(global.listClosure(),"、")%></dd>
</dl>