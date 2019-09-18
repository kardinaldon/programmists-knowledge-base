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
                    // var articleId = "";
                    // // var title = "";
                    // // var smallDescription = "";
                    // var description = "";
                    // var categoryName = "";
                    // var dateOfCreation = "";
                    // var userName = "";

                    if(len > 0){
                        for(var i=0;i<len;i++){
                            if(data[i].articleId != null && data[i].title != null){
                                var articleId = data[i].articleId;
                                var title = data[i].title;
                                var smallDescription = data[i].smallDescription;
                                var description = data[i].description;
                                var categoryName = data[i].categoryName;
                                var dateOfCreation = data[i].dateOfCreation;
                                var userName = data[i].userName;
                                hrefGet = 'http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/getwitid?id=';
                                hrefSinglePage = 'single_article.html?id=';
                                if (articleId !="") {
                                    var clone = $('.article:first').clone(true,true);
                                    var cloneTitle = clone.find('#article_title').attr('href',hrefSinglePage+articleId).empty().append(title);
                                    var cloneItems = clone.find('.smallDescription').empty().append(smallDescription);
                                    clone.appendTo('.container');
                                    //console.log(data[i].title);
                                }

                            }
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