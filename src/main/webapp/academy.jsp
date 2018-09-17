<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아카데미</title>
<style type="text/css">
body {
   margin: 0px;
   padding: 0px;
}

.hidden {
   display: none;
}

#main {
   
}

#edu-list {
   position: relative;
}
  
#edu-list .edu-list-container {
   margin-left: auto;
   margin-right: auto;
} 

#edu-list .edu-list-container>h2 {
   text-decoration: underline;
   text-align: center;
}

#edu-list .edu-wrapper {  
   display: flex;
   align-items: center;  
   justify-content: center;
}
  
#edu-list .filter {
   position: absolute;
   display: flex;
   right: 0px;
   top: 0px;
}

#edu-list .edu {
   width: 200px;
   margin-right: 20px;
}

#edu-list .edu-header {
   position: relative;
   box-sizing: border-box;
   padding: 10px;
   background-color: rgba(0, 0, 0, 0.8);
   background: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)),
      url("/resources/images/mac.png") no-repeat center;
   width: 100%;
   height: 70px;
}

#edu-list .edu-header .edu-date {
   color: #ffffff;
   font-size: 15px;
}

#edu-list .edu-header .edu-subject {
   color: #ffffff;
   font-size: 10px;
}

#edu-list .edu-header .edu-location {
   color: #ffffff;
   font-size: 10px;
   background: red;
   position: absolute;
   bottom: 0px;
   right: 0px;
}

#edu-list .edu-content {
   background: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)),
      url("/resources/images/computer.jpg") no-repeat center;
   width: 100%;
   height: 150px;
   color: #ffffff;
   font-weight: bold;
   font-size: 20px;
   text-align: center;
   line-height: 150px;
   box-sizing : border-box;
   border: 1px solid #2e497a;
}
</style>
</head>
<body>
   <!-- 헤더 -->
   <!-- 캐러셀 -->
   <!-- 리스트 -->
   <main id="main">
   <section id="edu-list">
      <h1 class="hidden">교육 리스트</h1>

      <div class="edu-list-container">
         <h2>국가지원 무료 취업교육</h2>

         <div class="filter">
            <div>강북센터</div>
            <div>강남센터</div>
         </div>
         <div class="edu-wrapper">
            <div class="edu">
               <div class="edu-header">
                  <div>
                     <span class="edu-date">2018.08.03~2019.02.09</span>
                  </div>
                  <div>
                     <span class="edu-subject">에자일 기법에 기반한 클라우드...</span>
                  </div>
                  <div class="hidden">
                     <div>온라인문의</div>
                     <div>수강신청</div>
                  </div>
                  <div>
                     <span class="edu-location">강북센터</span>
                  </div>
               </div>
               <div class="edu-content-container">
                  <div class="edu-content-detail"><!-- 회색 -->
                     <div><!-- 내용 -->
                        <div class="">
                           <ul>
                              <li>대상자 : 전공 무관</li>
                              <li>훈련비 : 6,311,040(<span>전액 정부지원</span>)</li>
                           </ul>
                        </div>
                        <div><span>후기 : 이 과정은 적극 추천합니다. 저는 비전공자라서 처음에는 걱정을 많이 했...</span></div>
                        <div><span>과정 만족도 : 별별별별별(4.3)</span></div>
                     </div>         
                  </div>
                  <div class="edu-content">과정 마감</div>
               </div>
            </div>
            <div class="edu">
               <div class="edu-header">
                  <div>
                     <span class="edu-date">2018.08.03~2019.02.09</span>
                  </div>
                  <div>
                     <span class="edu-subject">에자일 기법에 기반한 클라우드...</span>
                  </div>
                  <div class="hidden">
                     <div>온라인문의</div>
                     <div>수강신청</div>
                  </div>
                  <div>
                     <span class="edu-location">강북센터</span>
                  </div>
               </div>
               <div class="edu-content">과정 마감</div>
            </div>

            <div class="edu">
               <div class="edu-header">
                  <div>
                     <span class="edu-date">2018.08.03~2019.02.09</span>
                  </div>
                  <div>
                     <span class="edu-subject">에자일 기법에 기반한 클라우드...</span>
                  </div>
                  <div class="hidden">
                     <div>온라인문의</div>
                     <div>수강신청</div>
                  </div>
                  <div>
                     <span class="edu-location">강북센터</span>
                  </div>
               </div>
               <div class="edu-content">과정 마감</div>
            </div>

            <div class="edu">
               <div class="edu-header">
                  <div>
                     <span class="edu-date">2018.08.03~2019.02.09</span>
                  </div>
                  <div>
                     <span class="edu-subject">에자일 기법에 기반한 클라우드...</span>
                  </div>
                  <div class="hidden">
                     <div>온라인문의</div>
                     <div>수강신청</div>
                  </div>
                  <div>
                     <span class="edu-location">강북센터</span>
                  </div>
               </div>
               <div class="edu-content">과정 마감</div>
            </div>

         </div>
      </div>
   </section>
   </main>
</body>
</html>