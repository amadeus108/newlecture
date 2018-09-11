window.addEventListener("load", function(event){
    //template.html 을 읽어들이는데, 어디서?? 
    //위치와 크기를 template.html로 바꾼다.
    //원 위치에 있던건 hidden이 되고, editorTarget의 동생으로 template.html이 들어간다.
    var editorTarget = document.querySelector("textarea");
    var parent = editorTarget.parentElement;

    var webEditor = document.createElement("div"); //로드 해야하고
    webEditor.style.background = "yellow";
    
    var request = new XMLHttpRequest();
    request.onload = function(data){
//        alert(request.responseText);
        webEditor.innerHTML = request.responseText;
//        alert(style.editorTarget.innerHTML);
        

        editorTarget.before(webEditor);
        //parent.innerHTML = webEditor.innerHTML;
        //webEditor = ?;
        //webEditor를 editorTarget 바로 밑 동생 으로 추가해야한다.
        
        //같은 크기, 위치, 클래스 이름을 webEditor가 가져와야한다.
        




    }
    request.open("GET", "template.html", true);
    request.send();
    
})