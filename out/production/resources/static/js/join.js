
    let checkid;
    let checkpw;
    let checkpwsame;
    let checknick;

    // 아이디 유효성 검사 및 중복확인
    const idCheck = () => {
        let id = document.getElementById("memberId").value; //HTML 문서에서 memberId라는 ID를 가진 요소의 값 가져옴. 이 값은 정규식 검사에 사용됨.
        let checkResult = document.getElementById("check-result"); //check-result라는 ID를 가진 요소를 찾음. 검사 결과를 표시하는데 사용
        const idRegex = /^(?=.*[a-z])(?=.*[0-9])[a-z0-9]{8,16}$/; //정규식

        console.log("입력값: ", id); //테스트용 로그 출력

        //아래 if문의 조건식은 값이 false일때 -> 정규식 조건에 맞지 않음.
        if (!idRegex.test(id)) { //idRegex라는 정규식 사용해서 id가 정규식 조건에 맞는지 확인. test메소드는 주어진 문자열이 정규식과 일치하는지 판단. !연산자는 일치하지 않을때
            checkResult.style.color = "red"; //checkResult요소의 스타일 변경.
            checkResult.innerHTML = "아이디는 영문 소문자와 숫자로 구성된 8~16자여야 합니다."; //checkResult 요소의 내용을 변경해서 오류 메시지 설정.
            checkid = false;
            return; //함수 실행 종료
        }

        $.ajax({ //AJAX 요청을 초기화함. 서버로 HTTP POST 요청을 보내는데 사용됨. url에 지정된 엔드포인트에 데이터 보냄.
            type: "post", //HTTP 요청의 유형 지정. POST 유형 사용됨.
            url: "/member/id-check", //요청을 보낼 URL 지정. /member/id-check 엔드포인트로 요청이 보내짐. 아이디의 중복 여부 확인.
            data: {
                "memberId": id //POST 요청과 함께 보낼 데이터 정의. 아이디 값 서버로 보냄.
            },
            success: function(res) { //서버 요청이 성공했을 때 실행. 서버에서의 응답이 res 매개변수로 전달됨. 이를 통해 응답 데이터 확인 가능.

                console.log("요청성공", res); //테스트용 로그 출력

                if (res == "ok") { //서버로부터 받은 응답을 검사. 응답이 ok일때 다음 문장 실행
                    console.log("사용가능한 아이디"); //테스트용 로그 출력
                    checkResult.style.color = "green"; //checkResult 요소의 스타일을 변경.
                    checkResult.innerHTML = "사용가능한 아이디입니다."; //checkResult 요소의 내용을 변경하여 사용자에게 메시지 보여줌.
                    checkid = true;

                } else {
                    console.log("이미 사용중인 아이디"); //테스트용 로그 출력
                    checkResult.style.color = "red"; //checkResult 요소의 스타일을 변경.
                    checkResult.innerHTML = "이미 사용중인 아이디입니다."; //checkResult 요소의 내용을 변경하여 사용자에게 메시지 보여줌.
                    checkid = false;

                }
            },
            error: function(err) { //AJAX 요청이 실패했을 때 실행. err 매개변수를 통해 발생한 오류의 종류 알 수 있음.
                console.log("에러발생", err); //테스트용 로그 출력 : 콘솔에 에러발생 : 에러내용 출력
            }
        });
    }





    //비밀번호 유효성 검사 및 중복확인
    let passwordCheck = () => {
    let pw = document.getElementById("memberPw").value;
    let pwcheckResult = document.getElementById("pwcheck-result");
    const passwordRegex = /^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?:[0-9]+))$)[A-Za-z\d~!@#$%^&*()_+=]{10,16}$/;

    console.log("입력값: ", pw, "pwcheckResult: ", pwcheckResult);

    if (!passwordRegex.test(pw)) {
        pwcheckResult.style.color = "red";
        pwcheckResult.innerHTML = "비밀번호는 영문 대소문자, 숫자, 특수문자 중 2가지 이상 조합된 10~16자여야 합니다.";
        checkpw = false;
        return;
    }

    $.ajax({
        type: "post",
        url: "/member/pw-check",
        data: {
            "memberPw": pw
        },
        success: function(res1) {
            console.log("요청성공", res1);
            if (res1 == "ok") {
                console.log("사용가능한 비밀번호");
                pwcheckResult.style.color = "green";
                pwcheckResult.innerHTML = "사용가능한 비밀번호입니다.";
                checkpw = true;
            } else {
                console.log("이미 사용중인 비밀번호");
                pwcheckResult.style.color = "red";
                pwcheckResult.innerHTML = "이미 사용중인 비밀번호입니다.";
                checkpw = false;
            }
        },
        error: function(err) {
            console.log("에러발생", err);
        }
      });
   }


    //비밀번호 확인 검사
    const isPwSame = () => {

    let pwcheck01Result = document.getElementById("pwcheck01-result"); //에러 메시지 표시할 label 가져오기
    console.log(pwcheck01Result.innerText); //테스트용 로그 출력
    let realPw = document.getElementById("memberPw").value; //비밀번호 칸에 입력한 값 가져오기
    let samePw = document.getElementById("memberPw_check").value; //비밀번호 확인칸에 입력한 값 가져오기

    if(realPw != samePw) {
        pwcheck01Result.style.color = "red";
        pwcheck01Result.innerHTML = "비밀번호가 일치하지 않습니다.";
        checkpwsame = false;


    } else {
        pwcheck01Result.style.color = "green";
        pwcheck01Result.innerHTML = "비밀번호가 일치합니다.";
        checkpwsame = true;
    }
   }

    //닉네임 글자 수 검사
    const checkNicknameLength = () => {

    let nickResult = document.getElementById("nick-result");
    console.log(nickResult.innerText); //테스트용 로그 출력
    let nickName = document.getElementById("memberNick").value;

    if(nickName.length > 9) {
        nickResult.style.color = "red";
        nickResult.innerHTML = "닉네임은 9글자 이하입니다.";
        checknick =  false;
    } else {
        nickResult.style.color = "green";
        nickResult.innerHTML = "사용할 수 있는 닉네임입니다.";
        checknick = true;
    }

    }


    //입력하지 않은 값이 하나라도 있으면 회원가입 통과 X
    let submitButton = document.getElementById("joinbutton");
    let isValidation;
    let isNull;

    submitButton.addEventListener("click",function(event){
<!--        event.preventDefault();-->

        let Id = document.getElementById("memberId");
        let Pw = document.getElementById("memberPw");
        let Pw_check = document.getElementById("memberPw_check");
        let Name = document.getElementById("memberName");
        let Phone = document.getElementById("memberPhone");
        let Nick = document.getElementById("memberNick");


        //데이터 확인용 로그 출력
<!--        console.log("Id",Id.value);-->
<!--        console.log("Pw",Pw.value);-->
<!--        console.log("Pw_check",Pw_check.value);-->
<!--        console.log("Name",Name.value);-->
<!--        console.log("Phone",Phone.value);-->
<!--        console.log("Nick",Nick.value);-->

<!--        console.log("checkid",checkid);-->
<!--        console.log("checkpw",checkpw);-->
<!--        console.log("checkpwsame",checkpwsame);-->
<!--        console.log("checknick",checknick);-->


            //유효성, 중복확인 통과했는지 검사
        if(!checkid) {
            alert('아이디를 올바르게 입력하세요');
            isValidation = false;
            Id.focus();

        } else if (!checkpw) {
            alert('비밀번호를 올바르게 입력하세요');
            isValidation = false;
            Pw.focus();

        } else if (!checkpwsame) {
            alert('비밀번호가 일치하지 않습니다.');
            isValidation = false;
            Pw_check.focus();

        } else if (!checknick) {
            alert('닉네임을 9자 이하로 입력하세요.');
            isValidation = false;
            Nick.focus();
        } else
            isValidation = true;
            //console.log("isValidation", isValidation);

        console.log("isValidation", isValidation);


        //null값 있는지 검사
        if(Id.value.trim() === '') {
            alert('아이디를 다시 입력하세요');
            Id.focus();
            isNull = false;

        } else if (Pw.value.trim() === '') {
            alert('비밀번호를 다시 입력하세요');
            isNull = false;
            Pw.focus();

        } else if(Pw_check.value.trim() === '') {
            alert('비밀번호 확인을 해주세요');
            isNull = false;
            Pw_check.focus();

        } else if(Name.value.trim() === '') {
            alert('이름을 입력해주세요');
            isNull = false;
            Name.focus();

        } else if(Phone.value.trim() === '') {
            alert('전화번호를 입력해주세요');
            isNull = false;
            Phone.focus();

        } else if(Nick.value.trim() === '') {
            alert('닉네임을 입력해주세요');
            isNull = false;
            Nick.focus();
        } else
            isNull = true;
            //console.log("isNull", isNull);

        console.log("isNull", isNull);

        //최종점검
        if(isValidation && isNull) {
            return;

        } else
            event.preventDefault();



 });









