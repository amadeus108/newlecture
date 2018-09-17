//test가 로드됨.
window.addEventListener("load", function(event) {
	// template.html 을 읽어들이는데, 어디서??
	// 위치와 크기를 template.html로 바꾼다.
	// 원 위치에 있던건 hidden이 되고, editorTarget의 동생으로 template.html이 들어간다.
	var editorTarget = document.querySelector("textarea");
	var parent = editorTarget.parentElement;

	var webEditor = document.createElement("div"); // 로드 해야하고
	// webEditor.contentEditable = true; //수정가능한 element로 만들기
	// webEditor.style.width = editorTarget.clientWidth;
	// 결과가 안나온다 = css가 적용되는 시점과 app.js가 적용되는 시점이 다르기 때문

	// console.log("witdh" +editorTarget.clientWidth); //border와 margin을 제외한 실제컨텐츠의 크기
	// console.log("witdh" +editorTarget.offsetWidth); //엘리먼트의 전체크기
	// console.log("witdh" +editorTarget.scrollWidth); //

	// 실제 element가 갖고있는 style 속성을 가져오기
	var width = window.getComputedStyle(editorTarget, null).getPropertyValue("width");
	var height = window.getComputedStyle(editorTarget, null).getPropertyValue("height");
	console.log(width);

	// webEditor의 크기를 textarea와 동일하게 만들기
	webEditor.style.background = "yellow";
	webEditor.style.width = width;
	webEditor.style.height = height;

	// 편집기 부분은 새로운 페이지에서 만들기

	var request = new XMLHttpRequest();
	// template이 로드됨.
	request.onload = function(data) {

		// alert(request.responseText);
		webEditor.innerHTML = request.responseText;
		// alert(style.editorTarget.innerHTML);

		editorTarget.after(webEditor);

		var iframe = webEditor.querySelector("iframe");
		var win = iframe.contentWindow; // template의 iframe에 접근할 window를 받아온다.

		// body가 로드됨.
		win.addEventListener("load", function() {
			var doc = win.document; // template의 window의 document에 접근한다.
			// alert(doc);

			//버튼 하나하나에 적용하는 방법
			/*var boldButton = webEditor.querySelector(".bold-button");
			boldButton.onclick = function(e) {
				// alert("test");
				// window.document.execCommand("bold");

				doc.execCommand("bold");
			}*/
			
			//toobar 전체에 적용하는 방법
			var toolbar = webEditor.querySelector(".toolbar");
			
			toolbar.onclick = function(e) {
				var name = e.target.dataset.name;
				
				var select = doc.getSelection();
				var range = select.getRangeAt(0);
				switch(name){
					case "bold":
						//doc.execCommand("bold");
						alert(select.anchorNode);
						doc.execCommand("insertHTML", false, "<strong>"+range+"</strong>");
						break;
					case "italic":
						//doc.execCommand("italic");
						doc.execCommand("insertHTML", false, "</strong>"+range+"<strong>");
						break;
					case "color":
						doc.execCommand("foreColor", false, '#ff0000');
						break;
					default:
						return;
				}
				editorTarget.value = doc.body.innerHTML;
			}

			doc.body.onkeyup = function(){
				//console.log("change");
				editorTarget.value = doc.body.innerHTML; //body에 입력한 내용이 textarea에 적용
			}

		})

	}
	request.open("GET", "template.html", true);
	request.send();

})