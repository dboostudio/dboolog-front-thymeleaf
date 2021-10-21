var base_url = "http://localhost:6571"
var account_url = base_url + "/api/account"

/**
 * 로그인 요청을 보낸다.
 * @param userInfO <JSON> "userId" : "유저이메일아이디", "password" : "패스워드"
 */
function post_login(userInfo){
    let login_url = account_url + "/login";
    post(login_url, userInfo,
        function(){ location.href = "/"; }
        ,function(response) {
            hide_input_validation_error();
            if (check_object_type(response) == "Array") {
                show_input_validation_error(response);
            } else {
                console.log(response);
            }
        }
    )
}

