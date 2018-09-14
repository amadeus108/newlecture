<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main>
	<section>
		<h1>로그인</h1>
		<!-- action은 시큐리티 설정에서 해줌 -->
		<form method="post">
			<table>
				<tr>
					<td>
						<label>아이디 : </label>
						<!-- name="username" 약속 -->
						<input type="text" name="username" />
					</td>
				</tr>
				<tr>
					<td>
						<label>비밀번호 : </label>
						<!-- name="password" 약속 -->
						<input type="password" name="password" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="로그인">
					</td>
				</tr>  
				<tr>
					<td>
						<a href="find-id">아이디 찾기</a>
						<a href="reset-pwd">비밀번호 재설정</a>
					</td>
				</tr>  
			</table>
		</form>
	</section>
</main>