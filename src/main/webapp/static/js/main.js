$(document).ready (
    function madeAjaxCall(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/all",
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        success: function(data){
            if(data){
                var len = data.length;
                var txt = "";
                if(len > 0){
                    for(var i=0;i<len;i++){
                        if(data[i].title != null && data[i].description != null){
                            txt += "<tr><td>"+data[i].title+"</td><td>"+data[i].description+"</td></tr>";
                        }
                    }
                    if(txt != ""){
                        $("#table1").append(txt).removeClass("hidden");
                    }
                }
            }
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert(error  + textStatus +   + errorThrown);
        }
    });
    return false;
})