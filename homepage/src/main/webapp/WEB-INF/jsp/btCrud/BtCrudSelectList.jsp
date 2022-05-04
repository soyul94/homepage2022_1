<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- 자바에서 받아온 값을 jsp에서 쓸 때 사용하는 양식 -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<title>여기는 BtCrudSelectList.jsp이다</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script> <!-- url주소에서 가장 최신의 제이쿼리를 불러옴 -->
</head>
<body>
게시물 총 수 : <c:out value="${paginationInfo.totalRecordCount}"/> 건
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}">
			<tr>
				<td>
					<c:out value="${result.crudId}"/>
				</td>
				<td>
					<c:url var="viewUrl" value="/btCRUD/select.do">
						<c:param name="crudId" value="${result.crudId}"/>
					</c:url>
					<a href="${viewUrl}"><c:out value="${result.crudSj}"/></a>
				</td>	
				<td>
					<c:out value="${result.crudNm}"/>
				</td>
				<td>
					<fmt:formatDate value="${result.crudPnttm}" pattern="yyyy.MM.dd"/>
				</td>
				<td>
					<c:url var="delUrl" value="/btCRUD/delete.do">
					<c:param name="crudId" value="${result.crudId}"/>
					</c:url>
					<button><a href="${delUrl}" class="btn-del">삭제</a></button>
				</td>		
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div id="paging_div">
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
