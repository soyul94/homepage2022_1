<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- 자바에서 받아온 값을 jsp에서 쓸 때 사용하는 양식 -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<title>여기는 BtCrudSelectList.jsp이다</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script> <!-- url주소에서 가장 최신의 제이쿼리를 불러옴 -->
	<style>
		table{
			border: 2px solid darkgray;
			border-collapse: collapse;
			margin: 10px;
			padding: 10px;
			width : 700px;
			text-align: center;		
		}
		th,td{
			border: 1px solid darkgray;
			height: 25px;
		}
		th{
			font-size: 1em;
			color: white;
			background: gray;
			padding: 5px 10px; 
		}
		td{
			padding: 2px 10px; 
		}
		.paging_align{
			list-style-type: none;	
		}
		.paging_align li{
			display: inline-flex;
			margin: 0px 10px;
		}
		.ahref a:link, .ahref a:visited{
			color: #000;
			text-decoration: none;
		}
		.ahref a:hover{
			cursor: default;
		}
	</style>
</head>
<body>
게시물 총 수 : <c:out value="${paginationInfo.totalRecordCount}"/> 건
	<table>
		<thead>
			<tr>
				<th style="width : 70px;">ID</th>
				<th style="width : 330px;" >제목</th>
				<th style="width : 110px;">작성자</th>
				<th style="width : 110px;">작성일</th>
				<th style="width : 50px;">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}">
			<tr>
				<td>
					<c:out value="${result.crudId}"/>
				</td>
				<td style="text-align: left;">
					<c:url var="viewUrl" value="/btCRUD/select.do">
						<c:param name="crudId" value="${result.crudId}"/>
					</c:url>
					<a href="${viewUrl}"><c:out value="${result.crudSj}"/></a>
				</td>	
				<td>
					<c:out value="${result.crudNm}"/>
				</td>
				<td>
					<fmt:formatDate value="${result.crudPnttm}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<c:url var="delUrl" value="/btCRUD/delete.do">
					<c:param name="crudId" value="${result.crudId}"/>
					</c:url>
					<button class="ahref"><a href="${delUrl}" class="btn-del">삭제</a></button>
				</td>		
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div id="paging_div"> <%--이거는 어디에 있는 클래스... ? --%>
		<ul class="paging_align">
			<c:url var="pageUrl" value="/btCRUD/selectList.do?"/>
			<c:set var="pagingParam"><c:out value="${pageUrl}"/></c:set>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="${pagingParam}"/>
		</ul>
	</div>
	
	<h4><a href="/btCRUD/btCrudRegist.do">글쓰기</a></h4>
	
	<script>
		$(document).ready(function(){
			$(".btn-del").click(function(){
				if(!confirm("삭제하시겠습니까 ?")){
					return false;
				} //confirm : 상단에 경고창 뜸
			});
		});
	</script>
</body>
</html>
