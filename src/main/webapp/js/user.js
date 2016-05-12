var loadoffset = 5;
var nextjsonresponse;
var loadcount;

$(document).ready(function(){
    $.ajax({
        url: '${pageContext.request.contextPath}/getallusers',
        dataType : "json",
        success: function (response) {
            var tab=$('#users tbody');
            $.each(response, function (i, item) {
                tab.append('<tr id="user_'+
                    item.name
                    +'"> <td>' +
                    item.name
                    + ' </td><td> ' +
                    '<button id="delete"  class="buttonhead" user_id="' + item.name + '">'  + decodeURIComponent(escape('Удалить')) + '</a> '
                    + ' </td> </tr>');
                loadcount = i+1;
            });
            nextjsonresponse = '${pageContext.request.contextPath}/getusers?start=' + loadcount + '&offset=' + loadoffset;
        }
    });

    function getLastUsers(){
        $.ajax({
            url: nextjsonresponse,
            dataType : "json",
            success: function (response) {
                var tab=$('#users tbody');
                $.each(response, function (i, item) {

                    tab.append('<tr id="user_'+
                        item.name
                        +'"> <td>' +
                        item.name
                        + ' </td><td> ' +
                        '<button id="delete"  class="buttonhead" user_id="' + item.name + '">'  + decodeURIComponent(escape('Удалить')) + '</a> '
                        + ' </td> </tr>');
                    loadcount = loadcount + 1;
                });
                nextjsonresponse = '${pageContext.request.contextPath}/getusers?start=' + loadcount + '&offset=' + loadoffset;
            }
        });}

    $('#loadmore').bind("click", function(){
        $.ajax({
            url: nextjsonresponse,
            dataType : "json",
            success: function (response) {

                nextjsonresponse = '${pageContext.request.contextPath}/getusers?start=' + loadcount + '&offset=' + loadoffset;
            }
        });
    });

    $('#users').delegate('#delete', 'click', function(e) {
        if (confirm(decodeURIComponent(escape('Вы точно хотите удалить пользователя?'))))
        {
            var id = $(this).attr('user_id');
            $.ajax({
                url: '${pageContext.request.contextPath}/deleteuser?name=' + id,
                dataType : "json",
                success: function (response) {
                    loadcount = loadcount - 1;
                }
            });
            $('#user_'+id).remove();
            e.preventDefault();
        }else{

        }
    });


    $('#adduser').click( function(event){
        event.preventDefault();
        $('#overlay').fadeIn(400,
            function(){
                $('#modal_form_add')
                    .css('display', 'block')
                    .animate({opacity: 1, top: '50%'}, 200);
            });
    });

    $('#modal_close, #overlay').click( function(){
        $('#modal_form_add')
            .animate({opacity: 0, top: '45%'}, 200,
                function(){
                    $(this).css('display', 'none');
                    $('#overlay').fadeOut(400);
                }
            );
    });

    $('#modal_add_user').click( function(){
        var nameAddVal = $('#nameAddVal').val();
        var passwordAddVal = $('#passwordAddVal').val();
        if (nameAddVal != '' && passwordAddVal != '')
        {
            $.ajax({
                url: '${pageContext.request.contextPath}/adduser?name=' + nameAddVal + '&password=' + passwordAddVal,
                dataType: "json",
                error: function (jqXHR, textStatus, errorThrown){
                    if (jqXHR.status == '200')
                    {
                        alert(decodeURIComponent(escape('Пользователь с таким именем уже существует!')));
                    }
                    if (jqXHR.status == '500')
                    {
                        alert(decodeURIComponent(escape('Введены неверные значения!')));
                    }
                },
                success: function(response)
                {
                    $('#modal_form_add')
                        .animate({opacity: 0, top: '45%'}, 200,
                            function(){
                                $(this).css('display', 'none');
                                $('#overlay').fadeOut(400);
                            }
                        );

                    getLastUsers();
                }

            });
        }else
        {
            alert(decodeURIComponent(escape('Есть незаполненные поля!')))
        }

    });

});