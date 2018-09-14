<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<main>
		<section>
			<h1>검색분류</h1>
			<ul>
				<li>
					<label>교과목</label>
					<select>
						<c:forEach var="subject" items="${subjects}">
							<option value="${subject.id}">${subject.title}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<label>난이도</label>
					<select>
						<c:forEach var="level" items="${levels}">
							<option value="${level.id}">${level.name}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<label>검색어</label>
					<input type="text" />
				</li>
			</ul>
		</section>
		<section>
			<h1>문제 목록 페이지</h1>
			<div>
				<input type="checkbox" />
				내것으로만 한정
				최신순
				난이도순
			</div>
			<div>
				<table>
					<tr>
						<th>문제</th>
						<th>작성자</th>
						<th>유형</th>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
			<div>
				페이저
				1 2 3 4 5 
			</div>
		</section>
	</main>