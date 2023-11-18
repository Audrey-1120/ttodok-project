const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const card = urlParams.get('card');
let checkQuestion = false;

console.log("card", card);

function redirectToTextLearningPage() {
                console.log("redirecting with card", card);
                const apiUrl = `/quiz3?card=${card}`;
                window.location.href = apiUrl;
            }

document.getElementById("arrowButton").addEventListener("click", function(event) {
            const card1 = card;
            console.log("card1", card1);

            if(checkQuestion) {
                redirectToTextLearningPage(card1);

            } else {
                alert("퀴즈를 풀어주세요!");
                location.reload();
                event.preventDefault();
            }

    });

let questionCode;

$(document).ready(function(){
                    var card1 = getCardFromPreviousPage();

                    $.ajax({
                        type: 'GET',
                        url: '/study/quizQuestion?card=' + card1,
                        success: function(data) {
                            console.log("data: ", data);
                            if (data.data.length > 0) {
                                questionCode = data.data[1].questionCode;
                                console.log("questionCode: ", questionCode);

                                var questionContent = data.data[1].questionContent;
                                var questionPassage = data.data[1].questionPassage;

                                $('.quiz1-question').text(questionContent);
                                $('.quiz1-passage').text(questionPassage);

                                // 2. 두 번째 Ajax 호출
                                $.ajax({
                                    type: 'GET',
                                    url: '/study/quizChoice?card=' + questionCode,
                                    success: function(optionData) {
                                        console.log("optionData: ", optionData);
                                        if (optionData.data.length > 0) {
                                                   var dataArray = optionData.data;

                                                   var choiceContent01 = dataArray[0].choiceContent;
                                                   var choiceContent02 = dataArray[1].choiceContent;
                                                   var choiceContent03 = dataArray[2].choiceContent;
                                                   var choiceContent04 = dataArray[3].choiceContent;

                                                   var isAnswerRight01 = dataArray[0].isAnswerRight;
                                                   var isAnswerRight02 = dataArray[1].isAnswerRight;
                                                   var isAnswerRight03 = dataArray[2].isAnswerRight;
                                                   var isAnswerRight04 = dataArray[3].isAnswerRight;

                                                   $('.quiz1-choice01')
                                                    .text(choiceContent01)
                                                    .data('isAnswerRight', isAnswerRight01);

                                                   $('.quiz1-choice02')
                                                    .text(choiceContent02)
                                                    .data('isAnswerRight', isAnswerRight02);

                                                   $('.quiz1-choice03')
                                                    .text(choiceContent03)
                                                    .data('isAnswerRight', isAnswerRight03);

                                                   $('.quiz1-choice04')
                                                    .text(choiceContent04)
                                                    .data('isAnswerRight', isAnswerRight04);


                                        } else {
                                            console.log("No option data available.");
                                        }
                                    },
                                    error: function(jqXHR, textStatus, errorThrown) {
                                        console.log(textStatus, errorThrown);
                                    }
                                });
                            } else {
                                console.log("No data available.");
                            }
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.log(textStatus, errorThrown);
                        }
                    });
                });

                function getCardFromPreviousPage() {
                    var urlParams = new URLSearchParams(window.location.search);
                    return urlParams.get('card');
                }




                $('.quiz1-choice01').on('click', function() {

                    var $clickedElement = $(this);
                    var isAnswerRight = $clickedElement.data('isAnswerRight');
                    var questionCode01 = questionCode;

                    console.log("isAnswerRight 값 가져와졌나요?", isAnswerRight);


                    if (isAnswerRight == 1) {
                        $.ajax({
                            type: 'POST',
                            url: '/submitAnswer',
                            data: { questionCode: questionCode01 },
                            success: function(response) {
                                    console.log(response);
                                    showAlertCorrect();
                                        if (response.data.length > 0) {
                                            var dataArray = response.data;
                                            var answerContent = dataArray[0].answerContent;
                                            $('.quiz1-choice01')
                                            .text(answerContent)
                                            .css('color', 'red')
                                            .css('font-size', '35px');
                                            checkQuestion = true;

                                        } else {
                                            console.log("No option data available.");
                                        }

                            },
                            error: function(jqXHR, textStatus, errorThrown) {
                                console.log(textStatus, errorThrown);
                            }
                        });
                    } else {
                        showAlertWrong();
                    }
                });




                //답안클릭 시 해당 답안 서버로 보냄. - 1,2,3,4 선택지
                $('.quiz1-choice02').on('click', function() {

                    var $clickedElement = $(this);
                    var isAnswerRight = $clickedElement.data('isAnswerRight'); // 클릭된 요소의 data-isAnswerRight 값 가져오기
                    var questionCode01 = questionCode;

                    console.log("isAnswerRight 값 가져와졌나요?", isAnswerRight);


                    if (isAnswerRight == 1) {
                        $.ajax({
                            type: 'POST',
                            url: '/submitAnswer',
                            data: { questionCode: questionCode01 },
                            success: function(response) {
                                    console.log(response);
                                    showAlertCorrect();
                                        if (response.data.length > 0) {
                                            var dataArray = response.data;
                                            var answerContent = dataArray[1].answerContent;
                                            $('.quiz1-choice02')
                                            .text(answerContent)
                                            .css('color', 'red')
                                            .css('font-size', '35px');
                                            checkQuestion = true;

                                        } else {
                                            console.log("No option data available.");
                                        }

                            },
                            error: function(jqXHR, textStatus, errorThrown) {
                                console.log(textStatus, errorThrown);
                            }
                        });
                    } else {
                        // 틀린 경우 알림 후 페이지 새로고침
                        showAlertWrong();
                    }
                });




                //답안클릭 시 해당 답안 서버로 보냄. - 1,2,3,4 선택지
                $('.quiz1-choice03').on('click', function() {

                    var $clickedElement = $(this);
                    var isAnswerRight = $clickedElement.data('isAnswerRight'); // 클릭된 요소의 data-isAnswerRight 값 가져오기
                    var questionCode01 = questionCode;

                    console.log("isAnswerRight 값 가져와졌나요?", isAnswerRight);


                    if (isAnswerRight == 1) {
                        $.ajax({
                            type: 'POST',
                            url: '/submitAnswer',
                            data: { questionCode: questionCode01 },
                            success: function(response) {
                                    console.log(response);
                                    showAlertCorrect();
                                        if (response.data.length > 0) {
                                            var dataArray = response.data;
                                            var answerContent = dataArray[2].answerContent;
                                            $('.quiz1-choice03')
                                            .text(answerContent)
                                            .css('color', 'red')
                                            .css('font-size', '35px');
                                            checkQuestion = true;

                                        } else {
                                            console.log("No option data available.");
                                        }

                            },
                            error: function(jqXHR, textStatus, errorThrown) {
                                console.log(textStatus, errorThrown);
                            }
                        });
                    } else {
                        // 틀린 경우 알림 후 페이지 새로고침
                        showAlertWrong();
                    }
                });






                //답안클릭 시 해당 답안 서버로 보냄. - 1,2,3,4 선택지
                $('.quiz1-choice04').on('click', function() {

                    var $clickedElement = $(this);
                    var isAnswerRight = $clickedElement.data('isAnswerRight'); // 클릭된 요소의 data-isAnswerRight 값 가져오기
                    var questionCode01 = questionCode;

                    console.log("isAnswerRight 값 가져와졌나요?", isAnswerRight);


                    if (isAnswerRight == 1) {
                        $.ajax({
                            type: 'POST',
                            url: '/submitAnswer',
                            data: { questionCode: questionCode01 },
                            success: function(response) {
                                    console.log(response);
                                    showAlertCorrect();
                                        if (response.data.length > 0) {
                                            var dataArray = response.data;
                                            var answerContent = dataArray[3].answerContent;
                                            $('.quiz1-choice04')
                                            .text(answerContent)
                                            .css('color', 'red')
                                            .css('font-size', '35px');
                                            checkQuestion = true;

                                        } else {
                                            console.log("No option data available.");
                                        }

                            },
                            error: function(jqXHR, textStatus, errorThrown) {
                                console.log(textStatus, errorThrown);
                            }
                        });
                    } else {
                        // 틀린 경우 알림 후 페이지 새로고침
                        showAlertWrong();
                    }
                });


                function showAlertWrong() {
                    Swal.fire({
                      title: "이런! 다시 생각해보세요!",
                      text: "다시 한번 도전!",
                      icon: "error"
                    });
                }

                function showAlertCorrect() {
                    Swal.fire({
                      title: "정답이예요!!! 해설을 확인해볼까요?",
                      text: "축하해요!",
                      icon: "success"
                    });
                }