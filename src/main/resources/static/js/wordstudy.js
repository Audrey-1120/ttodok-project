
//메인페이지에서 사진1 클릭 시 t001이란 숫자 잘 전달되는지 확인.
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const card = urlParams.get('card');

    console.log(card); //테스트용 로그 출력
    console.log("card", card);


            $(document).ready(function(){
                $.ajax({
                    type: 'GET',
                    url: '/study/wordstudy?card=' + card,
                    success: function(data) {
                        console.log("data: ", data);
                        count = data.count;
                        console.log("count: ", count);

                        if (count > 0) { //데이터 배열의 길이가 0보다 큰지 확인한다. (서버에서 받아온 데이터가 비어있는지 확인)
                            data.data.forEach(function(wordEntity) { //데이터가 비어있지 않으면 data.forEach 메소드 사용해서 배열의 각 요소에 대해 함수 실행
                                var wordContent = '<div class="word-content"><p class="word-name">' + wordEntity.word + '</p><br>' + //각 요소에 대해 wordContent라는 HTML문자열 생성함.
                                                  '<p class="word-mean">' + wordEntity.wordMean + '</p><br>' +
                                                  '<p class="word-plusMean">' + wordEntity.wordEx + '</p></div>';
                                var itemContent = '<div class="item">' + wordContent + '</div>';
                                $('.items').append(itemContent);
                                //$('.items') : jQuery를 사용해서 문서에서 클래스가 items인 모든 요소를 선택한다.
                                //.append() : 선택한 요소의 내부에 새로운 콘텐츠 추가
                                //'<div class="item">' + wordContent + '</div>' : 문자열과 변수 결합해서 새로운 HTML 요소 생성
                            });

                            var emptyItemContent = '<div class="item"></div>';
                            $('.items').append(emptyItemContent);

                                        const silder = document.querySelector('#slider'); //id가 slider인 태그를 찾음
                                        const wrapper = document.querySelector('.wrapper'); //클래스가 wrapper인 클래스 찾음
                                        const items = document.querySelector('.items'); //const는 상수선언. 한번 선언하면 못바꿈. let은 변수 선언
                                        const item = document.querySelectorAll('.item');
//                                        const item = data.count;

                                        console.log("item 개수: ", item) //테스트용 로그 출력

                                        const next = document.querySelector('.next');
                                        const prev = document.querySelector('.prev');
                                        const itemCount = item.length - 2;
//                                        const itemCount = count;

                                        console.log("itemCount 개수: ", itemCount) //테스트용 로그 출력

                                        let startX = 0; //mousedown시 위치
                                        let moveX = 0; //움직인 정도
                                        let currentIdx = 0; //현재 위치(index)
                                        let positions = [];

                                        function initializeData() { //function은 함수. initializeData함수
                                          console.log("initializeData가 실행되었습니다..");
                                          const isActive = items.classList.contains('active'); //태그를 갖는 클래스 리스트 중에서 active 클래스를 가진 태그가 있냐? -> true / false
                                          if (isActive) items.classList.remove('active'); //true면 옆의 문장 실행. 있으면 해당 클래스 삭제
                                          const width = wrapper.clientWidth; //wrapper의 너비를 픽셀 단위로 얻어옴.
                                          const interval = item[0].clientWidth; //item 배열의 첫번째 요소의 너비를 픽셀 단위로 가져옴. - 여기가 너무 넓음...
                                          const margin = (width - interval) / 2; //화면 너비와 요소의 너비의 차이를 2로 나누어 마진을 계산함.
                                          const initX = Math.floor((interval - margin) * -1); //interval - margin을 계산하고 그 결과를 음수로 만들어서 소수점 이하를 버림.

                                          console.log("width 길이: ", width);
                                          console.log("interval 길이: ", interval);
                                          console.log("margin 길이: ", margin);
                                          console.log("initX 길이: ", initX);//테스트용 출력 - 처음 슬라이드가 반쯤 넘어가져서 보임...


                                          let pos = []; //빈 배열을 만듬.
                                          for (let i = 0; i < itemCount; i++) { //itemCount만큼의 반복문을 수행하면서 'pos'배열에 값을 추가함.
                                            pos.push(initX - interval * i);
                                          }
                                          positions = pos; //'pos'배열의 값을 'positions'에 할당함.
                                          items.style.width = (itemCount + 1) * 100 + '%'; //'items' 요소의 너비를 'itemCount + 1'배로 설정함.
                                          items.style.left = positions[currentIdx] + 'px'; //'items' 요소의 왼쪽 위치를 'positions[cutrrentIdx] 픽셀만큼 이동시킴
                                          silder.style.visibility = 'visible'; //'items'요소의 가시성을 visible로 설정함.
                                        }

                                            initializeData();
                                        // btn click event
                                        next.addEventListener('click', (e) => { //'next'요소에 클릭 이벤트를 추가함. 클릭 이벤트가 발생하면 내부의 콜백 함수가 실행됨.
                                          console.log("next.click 이벤트가 실행되었어요..");
                                          if (currentIdx === itemCount - 1) return; //현재 인덱스가 항목 수(itemCount)의 마지막 인덱스와 같다면 함수 종료.
                                          const isActive = items.classList.contains('active'); //'items'요소에 'active'클래스가 포함되어 있는지 확인함.
                                          if (!isActive) items.classList.add('active'); //'active' 클래스가 포함되어 있지 않으면 'items' 요소에 'active' 클래스를 추가함.
                                          currentIdx = currentIdx + 1; //현재 인덱스 1 증가
                                          items.style.left = positions[currentIdx] + 'px'; //'items' 요소의 왼쪽 위치를 positions 배열에서 현재 인덱스에 해당하는 값으로 설정.
                                        });

                                        prev.addEventListener('click', (e) => { //'prev' 요소에 클릭 이벤트를 추가함. 클릭 이벤트가 발생하면 내부의 콜백 함수가 실행됨.
                                          if (currentIdx === 0) return; //현재 인덱스가 0이면 함수 종료.
                                          const isActive = items.classList.contains('active'); //'items' 요소에 'active' 클래스가 포함되어 있는지 확인함.
                                          if (!isActive) items.classList.add('active'); //현재 인덱스 1 감소
                                          currentIdx = currentIdx - 1; //'items' 요소의 왼쪽 위치를 positions 배열에서 현재 인덱스에 해당하는 값으로 설정함.
                                          items.style.left = positions[currentIdx] + 'px';
                                        });

                                        //슬라이드 코드

                        } else {
                            console.log("데이터가 없습니다.");

                        }
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.log(textStatus, errorThrown);

                    }
                });
            });

