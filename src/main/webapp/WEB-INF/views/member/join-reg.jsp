<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main>
	<section id="form-section">
		<h1>회원 가입 페이지</h1>
			<form method="POST" enctype="multipart/form-data"> 
			<!-- method가 빠지면 get요청이 기본 / multipart data로 인코딩하지 않으면 전송이 안됨 -->
				<table>
					<tr>
						<td>
							<label>사진 :</label>
							<img class="photo" src="" /> <!-- 서버에 있는 이미지를 요청하기 위한 주소 -->
							<!-- 안보임 -->
							<input type="file" hidden="true" name="photo-file" value="사진선택" />
							<span class="photo-button">사진선택</span>
						</td>
					</tr>
					<tr>	
						<td>
							<label>아이디 :</label>
							<!-- id 4자이상 입력받는 문구를 뿌려주고, 4자 이하는 입력하지 못하게 만들기 -->
							<input type="text" name="id" value="${uid }" size="20" maxlength="12"/>
							<input type="button" class="id-check-button" value="중복조회" required="required"/>
							<!-- 현재 입력값이 DB에 있는지 확인하고, Ajax를 이용해서 확인해봐야한다. -->
						</td>
					</tr>
					<tr>
						<td>
							<label>비밀번호 :</label>
							<input type="password" name="pwd" required="required"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>이름 :</label>
							<input type="text" name="name" required="required"/>
						</td>
					</tr>
					<!-- 이메일은 인증 이메일 수정 불가능 readonly 쓰기만 안됨, 전송가능 -->
					<tr>
						<td>
							<label>이메일 :</label>
							<input type="text" name="email" value="${email}" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>
							<label>다음 계산 결과는? <img src="moonjae.jpg" /></label>
							<input name="moonjae" />
						</td>
					</tr>
					<tr>	
						<td>
							<input type="submit" value="회원가입" />
						</td>
					</tr>
				</table>
			</form>
		<!-- 아이디, 이름, 이메일, 비밀번호  -->
		<!-- 생년월일, 전화번호, 닉네임, 성별 -->
		<!-- 필수와 옵션은 분리한다. -->
	</section>
</main>

<script type="text/javascript">
window.addEventListener("load", function(event){
	var formSection = document.querySelector("#form-section");
	var idCheckButton = formSection.querySelector(".id-check-button");
	var idInput = formSection.querySelector("input[name='id']");
	var submitButton = formSection.querySelector("input[type='submit']");
	var photo = formSection.querySelector(".photo");
	var fileButton = formSection.querySelector("input[type='file']");
	var photoButton = formSection.querySelector(".photo-button");
	var idOk = false;

	fileButton.onchange = function(e){
		
		//file reader //사용자가 선택한 이미지를 읽어오기
		//HTML5에서부터 사용가능
		var file = fileButton.files[0]; //파일이 하나면 0번째 배열에 있는것만 읽어오면 된다.
		
		//이미지 파일만 넣을 수 있도록 제한
		//alert(file.type);
 		//alert(file.type.indexOf('image'));
		if(file.type.indexOf('image/') == -1){
			alert("이미지 파일을 선택해주세요");
			return false;
		}
		if(file.size > 1024*1024*10){ //파일 사이즈는 byte 단위
			alert("파일 사이즈가 10MB를 초과할 수 없습니다.");
			return false;
		}
		
		var reader = new FileReader();
		reader.onload = function(evt){
			photo.src = evt.target.result; //백그라운드에서 이미지를 다 읽어온 다음에 실행되야한다.	
		}
		reader.readAsDataURL(file); //선택한 파일의 리소스 위치를 읽어준다.
		
	}
	
	
	photoButton.addEventListener("click", function(){
		var event = new MouseEvent("click", {
			'view': window,
			'bubbles': true,
			'cancelable': true
		});
		fileButton.dispatchEvent(event);
	});

	submitButton.onclick = function(e){
		if(!idOk){
			//얼럿띄우고 전송되지않도록 막기
			alert("아이디 중복 검사를 하지 않았거나 유효한 아이디가 아닙니다.");
			e.preventDefault();
		}
	};

	idCheckButton.onclick = function(e){
// 		alert("test");
		/* id중복검사
		   ajax -> server 협력자 백엔드에게 연락해서 알아봐야함
		    /member/is-id-duplicated
		 */
		var request = new XMLHttpRequest();
		
		var id = idInput.value; 
		
		//XmlHttpRequest request = new XmlHttpRequest();
		request.onload = function(e){
			//완성해야돼
			if(request.status === 200){ //정상이면 
				var duplicated = JSON.parse(request.responseText);
// 				alert(request.responseText);
				if(duplicated){
					alert("중복된 아이디가 있습니다.");
					return;
				}
				alert("가입을 진행하세요~!");
				idOk = true;
			}else{
				alert("request에 문제가 있습니다.");
			}
		};
		request.open("GET", "is-id-duplicated?id=" + id, true);
		request.send();
	};
	
});
</script>