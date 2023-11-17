    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const card = urlParams.get('card');

    console.log("card", card);


/*    $(document).ready(function(){
                    $.ajax({
                        type: 'GET',
                        url: '/study/textstudy?card=' + card,
                        success: function(data) {
                            console.log("data: ", data);
                            count = data.count;
                            console.log("count: ", count);

                            if(count>0) {
                            data.data.forEach(function(textEntity) {

                            var textContent = '<div class="text"><p class="text-content">' + textEntity.text + '</p></div>';

                            })
                            } else {
                                console.log("데이터가 없습니다.");

                            }
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.log(textStatus, errorThrown);

                        }
                    });
                });
    2번째수정
      $(document).ready(function () {
          $.ajax({
              type: 'GET',
              url: '/study/textstudy?card=' + card,
              success: function (data) {
                  console.log("data: ", data);
                  count = data.count;
                  console.log("count: ", count);

                  if (count > 0) {
                      // 변수 선언을 블록 외부로 이동
                      var textContent;

                      data.data.forEach(function (textEntity) {
                          // textEntity.text가 존재하는지 확인하고 사용
                          if (textEntity.text) {
                              textContent = '<div class="text"><p class="text-content">' + textEntity.text + '</p></div>';
                              // 여기에 가져온 데이터를 사용하는 로직을 추가할 수 있습니다.
                          }
                      });
                  } else {
                      console.log("데이터가 없습니다.");
                  }
              },
              error: function (jqXHR, textStatus, errorThrown) {
                  console.log(textStatus, errorThrown);
              }
          });
      });


    3번째수정
    $(document).ready(function () {
        var count;
        var textContent;

        $.ajax({
            type: 'GET',
            url: '/study/textstudy?card=' + card,
            success: function (data) {
                console.log("data: ", data);
                count = data.count;
                console.log("count: ", count);

                if (count > 0) {
                    data.data.forEach(function (textEntity) {
                        // textEntity.text가 존재하는지 확인하고 사용
                        if (textEntity.text) {
                            // textEntity.text 값을 text() 함수를 사용하여 안전하게 처리
                            textContent = $('<div class="text"><p class="text-content"></p></div>').text(textEntity.text);
                            // 여기에 가져온 데이터를 사용하는 로직을 추가할 수 있습니다.
                        }
                    });
                } else {
                    console.log("데이터가 없습니다.");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    });



//4번째
    $(document).ready(function () {
            $.ajax({
                type: 'GET',
                url: '/api/text-entities', // 서버에서 TextEntity 데이터를 제공하는 엔드포인트
                success: function (data) {
                    console.log("data: ", data);

                    // 데이터를 HTML로 동적으로 렌더링
                    data.forEach(function (textEntity) {
                        var htmlContent = '<div class="text-content">' +
                                            '<h2>' + $('<div/>').text(textEntity.textTitle).html() + '</h2>' +
                                            '<p class="text-content">' + $('<div/>').text(textEntity.textContent).html() + '</p>' +
                                          '</div>';

                        $('#text-container').append(htmlContent);
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        });*/ //코드 수정 내역

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



    console.log(typeof $);
    console.log('/study/textstudy?card=' + card);

    function redirectToTextLearningPage() {
        console.log("redirecting with card", card);
        const apiUrl = `/textstudy?card=${card}`;
                      // 단어 학습 페이지 URL에 이미지 번호를 매개변수로 추가하여 페이지 이동
        window.location.href = apiUrl;
    }
