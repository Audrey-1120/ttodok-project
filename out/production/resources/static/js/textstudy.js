const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const card = urlParams.get('card');

console.log("card", card);

document.getElementById("textStudyBotton").addEventListener("click", function(event) {
            const card1 = card; // 수정된 부분
            console.log("card1", card1); // 로그 출력
            redirectToTextLearningPage(card1);
    //        event.preventDefault();
    });

function redirectToTextLearningPage() {
                console.log("redirecting with card", card);
                const apiUrl = `/quizhome?card=${card}`;
                              // 단어 학습 페이지 URL에 이미지 번호를 매개변수로 추가하여 페이지 이동
                window.location.href = apiUrl;
            }