const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const card = urlParams.get('card');
let checkQuestion = false;

console.log("card", card);


let questionCode;

$(document).ready(function(){

                    var card1 = getCardFromPreviousPage();

                    $.ajax({
                        type: 'GET',
                        url: '/study/quizQuestion?card=' + card1,
                        success: function(data) {
                            console.log("data: ", data);
                            if (data.data.length > 0) {
                                questionCode = data.data[3].questionCode;
                                console.log("questionCode: ", questionCode);

                                var questionContent = data.data[3].questionContent;
                                var questionPassage = data.data[3].questionPassage;

                                $('.quiz1-question').text(questionContent);
                                $('.quiz1-passage').text(questionPassage);

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
                                            addPoints();

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





                $('.quiz1-choice02').on('click', function() {

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
                                            var answerContent = dataArray[1].answerContent;
                                            $('.quiz1-choice02')
                                            .text(answerContent)
                                            .css('color', 'red')
                                            .css('font-size', '35px');
                                            addPoints();

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




                $('.quiz1-choice03').on('click', function() {

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
                                            var answerContent = dataArray[2].answerContent;
                                            $('.quiz1-choice03')
                                            .text(answerContent)
                                            .css('color', 'red')
                                            .css('font-size', '35px');
                                            addPoints();

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




                $('.quiz1-choice04').on('click', function() {

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
                                            var answerContent = dataArray[3].answerContent;
                                            $('.quiz1-choice04')
                                            .text(answerContent)
                                            .css('color', 'red')
                                            .css('font-size', '35px');
                                            addPoints();

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



                //퀴즈 - 마지막 퀴즈 다 풀면 해당 유저의 포인트 값 받아와서 100 더함.
                function addPoints() {

                    $.ajax({
                        type: 'Post',
                        url: '/quiz4/pointadd',
                        success: function(response) {
                            console.log('Received response:', response);

                            var currentUser = response.loginId; //현재 로그인한 유저의 아이디 얻어옴.
                            var currentPoint = response.memberPoint; // 현재 포인트 값 가져오기
                            var updatedPoint = currentPoint + 100; // 100을 추가하여 업데이트

//                            // 서버에 업데이트된 포인트 전송
                            updatePointsOnServer(currentUser, updatedPoint);
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.error('Error fetching points:', errorThrown);
                        }
                    });

                }




                // 퀴즈 - 서버에 업데이트된 포인트값 전송
                function updatePointsOnServer(currentUser, updatedPoint) {
                    $.ajax({
                        type: 'POST',
                        url: '/quiz4/updatepoint',
                        data: { loginId: currentUser, point: updatedPoint },
                        success: function (data) {
                              showAlertPoint();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.error('Error updating points:', errorThrown);
                        }
                    });
                }


                //마지막 퀴즈 풀면 나오는 알림창 - 포인트 얻었을 때
                function showAlertPoint() {
                    Swal.fire({
                        icon: "success",
                        title: "포인트 100점이 적립되었어요!",
                        text: "멋져요!!",
                        showCancelButton: true,
                        confirmButtonText: '메인으로 갈래요!',
                        cancelButtonText: '해설 다시 볼래요!',
                        confirmButtonColor: '#295F1C',
                        cancelButtonColor: '#295F1C',
                    }).then((result) => {
                        if (result.isConfirmed) {
                                // 확인 버튼이 클릭되면 메인 페이지로 이동
                            window.location.href = "/main_ver2";
                        }
                    });
                }

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