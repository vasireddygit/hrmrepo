$(document).ready(function(){
    var windowHeight = window.innerHeight;
    var formstartpoint = windowHeight-450;
   $("#loginImage").css("height", windowHeight+"px");
   $("#loginform").css("margin-top", formstartpoint+"px");

   $(window).resize(function() {
    $("#loginImage").css("height", windowHeight+"px");
    $("#loginform").css("margin-top", formstartpoint+"px");
   });
});