window.onload = function () {


    /* 화면에 랜더리 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서
    if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */

    if (document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.onclick = function () {
            location.href = "/member/login";
        }
    }
    /* 1. if~ : HTML 문서에서 id가 login인 요소를 찾는다. 해당 요소가 존재할 때 코드 블록 안의 내용을
    * 실행하는 조건문
    * 2. const~ : id가 login인 HTML 요소를 찾아서 $login 이라는 이름의 JavaScript 변수에 할당한다.
    * 3. $login~ : $login 변수에 저장된 HTML 요소에 클릭 이벤트 핸들러를 추가한다.
    * 4. location.href~ : 클릭 이베트가 발생하면 현재 페이지의 url을 /member/login으로 변경한다. */


    if (document.getElementById("logout")) {
        const $logout = document.getElementById("logout");
        $logout.onclick = function () {
            location.href = "/member/logout";
        }
    }

    if (document.getElementById("regist")) {
        const $regist = document.getElementById("regist");
        $regist.onclick = function () {
            location.href = "/member/regist";
        }
    }

    if (document.getElementById("duplicationCheck")) {

        const $duplication = document.getElementById("duplicationCheck");

        $duplication.onclick = function () {
            let memberId = document.getElementById("memberId").value.trim();

            fetch("/member/idDupCheck", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({memberId: memberId})
            })
                .then(result => result.text())
                .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));

        }
    }

    if (document.getElementById("updateMember")) {
        const $update = document.getElementById("updateMember");
        $update.onclick = function () {
            location.href = "/member/update";
        }
    }

    if (document.getElementById("deleteMember")) {
        const $update = document.getElementById("deleteMember");
        $update.onclick = function () {
            location.href = "/member/delete";
        }
    }

    /* ------------------------------------------------------------- */

    if (document.getElementById("writeBoard")) {
        const $writeBoard = document.getElementById("writeBoard");
        $writeBoard.onclick = function () {
            location.href = "/board/regist";
        }
    }

    if (document.getElementById("writeThumbnail")) {
        const $writeThumbnail = document.getElementById("writeThumbnail");
        $writeThumbnail.onclick = function () {
            location.href = "/thumbnail/regist";
        }
    }
}