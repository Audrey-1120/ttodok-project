    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const card = urlParams.get('card');

    console.log("card", card);

    document.getElementById("textStudyButton").addEventListener("click", function(event) {
            const card1 = card; // 수정된 부분
            console.log("card1", card1); // 로그 출력
            redirectToTextLearningPage(card1);
    //        event.preventDefault();
    });

    //기능 처리 함수
    $(document).ready(function () {
        //DB에서 데이터 불러오기
        $.ajax({
            type: 'GET',
            url: '/study/textstudy?card=' + card, // 서버에서 TextEntity 데이터를 제공하는 엔드포인트
            success: function (data) {
                console.log("data: ", data); //가져온 데이터 확인

                for (const textEntity of data.data) {
                    // text-container에 데이터 추가
                    var htmlContent = '<div class="text-content">' +
                                        '<p class="text-content">' + $('<div/>').text(textEntity.textContent).html() + '</p>' +
                                      '</div>';
                    $('#text-container .text-content').append(htmlContent);

                    // textstudy-title에 데이터 추가
                    var titleContent = '<h3 class="textstudy-title">' + $('<div/>').text(textEntity.textTitle).html() + '</h3>';
                    $('.textstudy-title').append(titleContent);

                    /*console.log('wordTitle:', textEntity.wordTitle);
                    // wordstudy-title에 textstudy-title 불러오기
                    var wordTitleContent = '<h3 class="wordstudy-title">' + $('<div/>').text(textEntity.textTitle).html() + '</h3>';
                    $('.wordstudy-title').append(wordTitleContent);*/

                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });

        //음성출력 on/off
        var isSpeaking = false; // 음성 출력 중인지 여부를 나타내는 변수
        var speechUtterance; // SpeechSynthesisUtterance 객체를 저장할 변수
        //tts
        $('#audioButton').on('click', function () {
            var contentText = $('.text-content').text();

            // 음성 출력 중이라면 중지, 아니면 음성 합성 함수 호출
            if (isSpeaking) {
                stopSpeech();
            } else {
                speakText(contentText);
            }
        });

        // 음성 합성 함수
        function speakText(text) {
            var speechSynthesis = window.speechSynthesis;
            speechUtterance = new SpeechSynthesisUtterance(text);

            // 음성 합성 시작 이벤트
            speechUtterance.onstart = function () {
                isSpeaking = true;
                $('#audioButton').text('Stop'); // 버튼 텍스트 업데이트
            };

            // 음성 합성 종료 이벤트
            speechUtterance.onend = function () {
                isSpeaking = false;
                $('#audioButton').text('Play'); // 버튼 텍스트 업데이트
            };

            speechSynthesis.speak(speechUtterance);
        }

        // 음성 출력 중지 함수
        function stopSpeech() {
            window.speechSynthesis.cancel(); // 음성 출력 중지
            isSpeaking = false;
            $('#audioButton').text('Play'); // 버튼 텍스트 업데이트
        }

        // 페이지 언로드 시 음성 출력 중지
        $(window).on('unload', function () {
            stopSpeech();
        });

    }); //$(document).ready(function ()

    console.log(typeof $); //테스트용로그

    function redirectToTextLearningPage() {
        console.log("redirecting with card", card);
        const apiUrl = `/quizhome?card=${card}`;
                      // 단어 학습 페이지 URL에 이미지 번호를 매개변수로 추가하여 페이지 이동
        window.location.href = apiUrl;
    }