$("#login-button").on("click", function(){
    let param={};
    param['userId'] = $("#userId").val();
    param['password'] = $("#password").val();
    post_login(param);
})

$(document).on('keydown', '#password', function(event){
    if(event.which == 13){
        $("#login-button").click();
    }
})

$(document).on('keydown', '#userId', function(event){
    if(event.which == 13){
        $("#login-button").click();
    }
})