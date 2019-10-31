$(document).ready (
    function carrentPage(){
        var carrentId = window.location.search;
        console.log(carrentId);
        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/getwitid'+carrentId,
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(data){
                if(data){

                            if(data.title != null){
                                var title = data.title;
                                var description = data.description;
                                $('.title_of_single_article').text(title);

                                    // var clone = $('.article:first').clone(true,true);
                                    // var cloneTitle = clone.find('#article_title').attr('href',hrefSinglePage+articleId).empty().append(title);
                                    // var cloneItems = clone.find('.smallDescription').empty().append(smallDescription);
                                    // clone.appendTo('.container');
                                    console.log(data.title);
                            }
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert(error  + textStatus +   + errorThrown);
            }
        });
    }
    );