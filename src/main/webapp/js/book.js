var loadoffset = 5;
var nextjsonresponse;
var loadcount = 0;

$(document).ready(function(){
    $.ajax({
        url: '${pageContext.request.contextPath}/getbooks',
        dataType : "json",
        success: function (response) {
            listContent(response);
            nextjsonresponse = '${pageContext.request.contextPath}/getbooks?start=' + loadcount + '&offset=' + loadoffset;
        }
    });

    var currentUserName = $('#username').text();

    function listContent(response)
    {
        var currentUserName = $('#username').text();

        var tab=$('#books tbody');
        $.each(response, function (i, item) {
            var buttonTake = item.user_name;
            if (item.user_name == 'null')
            {
                buttonTake  = '<button class = "buttonhead" id="takeButton" book_id_of="'+ item.isn +'" user_name = "' + item.user_name + '">' + decodeURIComponent(escape('Взять'))+ '</button>';
            } else if (item.user_name == currentUserName)
            {
                buttonTake  = '<button class = "buttonhead" id="returnButton" book_id_of="'+ item.isn +'" user_name = "' + item.user_name + '">' + decodeURIComponent(escape('Вернуть'))+ '</button>';
            }

            tab.append('<tr id="book_'+
                item.isn
                +'"> <td>' +
                item.isn
                + ' </td><td> ' +
                item.name
                + ' </td><td> ' +
                item.author
                + ' </td><td> ' +
                buttonTake
                + ' </td><td> ' +
                '<button id="delete"  class="buttonhead" book_id="' + item.isn + '">'  + decodeURIComponent(escape('Удалить')) + '</a> '
                + ' </td> </tr>');
            loadcount = loadcount+1;
        });
    }

    function getLastBooks(){
        $.ajax({
            url: nextjsonresponse,
            dataType : "json",
            success: function (response) {
               listContent(response);
                nextjsonresponse = '${pageContext.request.contextPath}/getbooks?start=' + loadcount + '&offset=' + loadoffset;
            }
        });}

    $('#loadmore').bind("click", function(){
        $.ajax({
            url: nextjsonresponse,
            dataType : "json",
            success: function (response) {
                listContent(response);
                nextjsonresponse = '${pageContext.request.contextPath}/getbooks?start=' + loadcount + '&offset=' + loadoffset;
            }
        });
    });

    $('#books').delegate('#delete', 'click', function(e) {
        if (confirm(decodeURIComponent(escape('Вы точно хотите удалить книгу?'))))
        {
            var id = $(this).attr('book_id');
            $.ajax({
                url: 'http://localhost:8080/deletebook?isn=' + id,
                dataType : "json",
                success: function (response) {
                    loadcount = loadcount - 1;
                }
            });
            $('#book_'+id).remove();
            e.preventDefault();
        }else{

        }
    });

    $('#books').delegate('#takeButton', 'click', function(e) {
        var isn = $(this).attr('book_id_of');
        $.ajax({
            url: '${pageContext.request.contextPath}/updateownerofbook?isn=' + isn + '&user_name=' + currentUserName,
            dataType : "json",
            success: function (response) {
                $('[book_id_of=' + isn +']').html(decodeURIComponent(escape('Вернуть')));
                $('#takeButton').attr('id', 'returnButton');
            }
        });
    });

    $('#books').delegate('#returnButton', 'click', function(e) {
        var isn = $(this).attr('book_id_of');
        $.ajax({
            url: '${pageContext.request.contextPath}/updateownerofbook?isn=' + isn ,
            dataType : "json",
            success: function (response) {
                $('[book_id_of=' + isn +']').html(decodeURIComponent(escape('Взять')));
                $('#returnButton').attr('id', 'takeButton');
            }
        });
    });

    $('#addbook').click( function(event){
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

    $('#modal_add_book').click( function(){
        var isnAddVal = $('#isnAddVal').val();
        var authorAddVal = $('#authorAddVal').val();
        var nameAddVal = $('#nameAddVal').val();
        $.ajax({
            url: '${pageContext.request.contextPath}/addbook?isn=' + isnAddVal + '&author=' + authorAddVal + '&name=' + nameAddVal,
            dataType: "json",
            error: function (jqXHR, textStatus, errorThrown){
                if (jqXHR.status == '200')
                {
                    alert(decodeURIComponent(escape('Книга с таким ключом уже существует!')));
                }
                if (jqXHR.status == '500')
                {
                    alert(decodeURIComponent(escape('Введены неверные значения!')));
                }
            },
            success: function(response)
            {
                if ($.isEmptyObject(response))
                {
                    alert('Dublicate Isn key!!!');
                    return;
                } else
                {
                    $('#modal_form_add')
                        .animate({opacity: 0, top: '45%'}, 200,
                            function(){
                                $(this).css('display', 'none');
                                $('#overlay').fadeOut(400);
                            }
                        );

                    var tab=$('#books tbody');
                    var buttonTake = nameAddVal;
                    if (nameAddVal == 'null')
                    {
                        buttonTake  = '<button class = "buttonhead" id="takeButton" book_id_of="'+ isnAddVal +'" user_name = "' + nameAddVal + '">' + decodeURIComponent(escape('Взять'))+ '</button>';
                    } else if (nameAddVal == currentUserName)
                    {
                        buttonTake  = '<button class = "buttonhead" id="returnButton" book_id_of="'+ isnAddVal +'" user_name = "' + nameAddVal + '">' + decodeURIComponent(escape('Вернуть'))+ '</button>';
                    }
                    tab.append('<tr id="book_'+
                        isnAddVal
                        +'"> <td>' +
                        isnAddVal
                        + ' </td><td> ' +
                        authorAddVal
                        + ' </td><td> ' +
                        nameAddVal
                        + ' </td><td> ' +
                        buttonTake
                        + ' </td><td> ' +
                        '<button id="delete"  class="buttonhead" book_id="' + isnAddVal + '">'  + decodeURIComponent(escape('Удалить')) + '</a> '
                        + ' </td> </tr>');
                }
            }
        });
    });

    var a = 1;
    var b = -1;
    var ca = 0;
    var cb = 0;

    var grid = document.getElementById('books');

    grid.onclick = function(e) {
        if (e.target.tagName != 'TH') return;
        sortGrid(e.target.cellIndex, e.target.getAttribute('data-type'));
    };

    document.getElementById('authorth').onclick = function(e)
    {
        if (ca % 2 != 0)
        {
            a = -1;
            b = 1;
        }else{
            a = 1;
            b = -1;
        }
        e.target.className = 'thsortcolor';
        document.getElementById("nameth").className = 'thnormalcolor';
    }

    document.getElementById('nameth').onclick = function(e)
    {
        if (cb % 2 != 0)
        {
            a = -1;
            b = 1;
        }else{
            a = 1;
            b = -1;
        }
        e.target.className = 'thsortcolor';
        document.getElementById('authorth').className = 'thnormalcolor';
    }



    function sortGrid(colNum, type) {
        ca++;
        cb++;
        var tbody = grid.getElementsByTagName('tbody')[0];

        var rowsArray = [].slice.call(tbody.rows);

        var compare;

        switch (type) {
            case 'number':
                compare = function(rowA, rowB) {
                    return rowA.cells[colNum].innerHTML - rowB.cells[colNum].innerHTML;
                };
                break;
            case 'string':
                compare = function(rowA, rowB) {
                    return rowA.cells[colNum].innerHTML > rowB.cells[colNum].innerHTML ? a : b;
                };
                break;
        }

        rowsArray.sort(compare);

        grid.removeChild(tbody);

        for (var i = 0; i < rowsArray.length; i++) {
            tbody.appendChild(rowsArray[i]);
        }

        grid.appendChild(tbody);

    }
});







