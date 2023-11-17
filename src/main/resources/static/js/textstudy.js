    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const card = urlParams.get('card');

    console.log("card", card);


    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: '/study/textstudy?card=' + card, // 서버에서 TextEntity 데이터를 제공하는 엔드포인트
            success: function (data) {
                console.log("data: ", data);

                for (const textEntity of data.data) {
                    // text-container에 데이터 추가
                    var htmlContent = '<div class="text-content">' +
                                        '<p class="text-content">' + $('<div/>').text(textEntity.textContent).html() + '</p>' +
                                      '</div>';
                    $('#text-container .text-content').append(htmlContent);

                    // textstudy-title에 데이터 추가
                    var titleContent = '<h3 class="textstudy-title">' + $('<div/>').text(textEntity.textTitle).html() + '</h3>';
                    $('.textstudy-title').append(titleContent);
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    });

    console.log(typeof $); //테스트용로그

    function redirectToTextLearningPage() {
        console.log("redirecting with card", card);
        const apiUrl = `/textstudy?card=${card}`;
                      // 단어 학습 페이지 URL에 이미지 번호를 매개변수로 추가하여 페이지 이동
        window.location.href = apiUrl;
    }
