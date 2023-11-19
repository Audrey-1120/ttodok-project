var selectProductPoint = 0;


                document.getElementById("buybtn1").addEventListener("click", function(event) {
                showAlertBuy();
                });

                document.getElementById("buybtn2").addEventListener("click", function(event) {
                showAlertBuy();
                });

                document.getElementById("buybtn3").addEventListener("click", function(event) {
                showAlertBuy();
                });

                document.getElementById("buybtn4").addEventListener("click", function(event) {
                showAlertBuy();
                });


                //.gifticon_list 클래스의 하위에 있는 직계 자식 div 요소를 모두 선택합니다.
                //.forEach(...)를 사용하여 선택된 각 요소에 대해 주어진 함수를 실행합니다.
                document.querySelectorAll('.gifticon_list > div').forEach(function(gifticonElement, index) {
                    gifticonElement.addEventListener('click', function() {
                        // 클릭한 요소의 productPoint 값을 읽어와서 처리
                        var gifticonPriceElement = gifticonElement.querySelector('.gifticon_price');
                        var selectProductPointValue = gifticonPriceElement.innerText;
                        selectProductPoint = parseInt(selectProductPointValue, 10);

                        console.log('선택한 요소의 productPoint:', selectProductPoint);
                    });
                });

                //구매하시겠습니까? 알림창
//                function showAlertBuy() {
//
//                    Swal.fire({
//                      title: "해당 상품을 구매하시겠어요?",
//                      text: "멋지네요!",
//                      icon: "question",
//                      showCancelButton: true,
//                      confirmButtonColor: "#3085d6",
//                      cancelButtonColor: "#d33",
//                      confirmButtonText: "네!"
//                    }).then((result) => {
//                        buyProduct();
//
//
//                    });
//
//                }


                function showAlertBuy() {

                Swal.fire({
                  title: "해당 상품을 구매하시겠어요?",
                  text: "멋지네요!",
                  icon: "question",
                  showCancelButton: true,
                  confirmButtonColor: "#3085d6",
                  cancelButtonColor: "#d33",
                  confirmButtonText: "네!"
                }).then((result) => {
                  if (result.isConfirmed) {
                    buyProduct();

                  } else if (result.isDenied) {

                  }
                });

                }

                //구매하기 전에 로그인 유저의 아이디와 현 포인트 값을 받아와서 계산.
                function buyProduct() {

                    $.ajax({
                        type: 'Post',
                        url: '/pointshop/buyproduct',
                        success: function(response) {
                            console.log('Received response:', response);

                            var currentUser = response.loginId; //현재 로그인한 유저의 아이디 얻어옴.
                            var currentPoint = response.memberPoint; // 현재 포인트 값 가져오기

                            var updatedProductPoint = 0;
                            if (currentPoint >= selectProductPoint) {
                                updatedProductPoint = currentPoint - selectProductPoint; // 100을 추가하여 업데이트
                                console.log("updatedProductPoint: ",updatedProductPoint);
                                updateProductPointsOnServer(currentUser, updatedProductPoint); // 서버에 업데이트된 포인트 전송

                            } else {
                                // 현재 포인트값이 선택요소의 point값보다 작으면 결과를 false로 설정
                                console.log("포인트가 부족해요!!");
                                showAlertProductFailed();
//                                window.location.href = "/pointshop";

                            }

                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.error('Error fetching points:', errorThrown);
                        }
                    });

                }

                //구매 후 차감한 포인트 값 다시 서버에 전달
                function updateProductPointsOnServer(currentUser, updatedProductPoint) {
                    $.ajax({
                        type: 'POST',
                        url: '/pointshop/productupdatepoint',
                        data: { loginId: currentUser, point: updatedProductPoint },
                        success: function (data) {
                              showAlertProductSuccess();
//                              location.reload();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.error('Error updatingProduct points:', errorThrown);
                        }
                    });
                }



                //구매 실패시
                function showAlertProductFailed() {
                    Swal.fire({
                      title: "이런! 포인트가 부족해요!",
                      text: "공부를 더 열심히 해봐요!",
                      icon: "error"
                    });
                }

                //구매 성공시
                function showAlertProductSuccess() {
                    Swal.fire({
                      title: "구매가 완료되었습니다!",
                      text: "축하해요!",
                      icon: "success"
                    });
                }