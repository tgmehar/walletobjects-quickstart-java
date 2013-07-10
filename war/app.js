var successHandler = function(params){
  console.log("Object added successfully" + params);
}

var failureHandler = function(params){
  console.log("Object insertion failed" + params);
}

function init(){
  document.getElementById("insert").addEventListener("click", function(){
    $.get("insert", function(data){
      console.log(data);
    })
  });

  $.get("jwt", function(data){
    saveToWallet = document.createElement("g:savetowallet");
    saveToWallet.setAttribute("jwt", data);
    saveToWallet.setAttribute("onsuccess","successHandler");
    saveToWallet.setAttribute("onfailure","failureHandler");
    document.body.appendChild(saveToWallet);
    
    script = document.createElement("script");
    script.src = "https://apis.google.com/js/plusone.js";
    document.head.appendChild(script);
  });
}


$(window).ready(function(){
  init();
});