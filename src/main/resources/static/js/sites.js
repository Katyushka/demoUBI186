/**
 * Created by ekaterina on 12.10.2016.
 */
function addSite() {
    $('#siteDialog').dialog("option", "title", 'Добавить сайт');
    $('#siteDialog').dialog('open');
}

function editSite(id) {

    $.get("/sites/get/" + id, function(result) {

        $("#siteDialog").html(result);

        $('#siteDialog').dialog("option", "title", 'Редактировать?');

        $("#siteDialog").dialog('open');
    });
}

function deleteSite(id) {

    $( "#dialog-confirm" ).dialog({
        resizable: false,
        height:200,
        width: 300,
        modal: true,
        buttons: {
            "Удалить": function() {
                $.ajax({
                    url: "/sites/delete/" + id,
                    type: "POST",
                    success:function(response) {
                        $('#site-'+id).hide()
                    }
                });
                $( this ).dialog( "close" );
            },
            "Отменить": function() {
                $( this ).dialog( "close" );
            }
        }
    });
}



function resetDialog(form) {

    form.find("input").val("");
}




$(document).ready(function() {

    $('#siteDialog').dialog({

        autoOpen : false,
        position : 'center',
        modal : true,
        resizable : false,
        width : 440,
        buttons : {
            "Сохранить" : function() {
                if ($('#name').val()==""){
                    $('#emptySite').show();
                } else
                $('#siteForm').submit();
            },
            "Отменить" : function() {
                $(this).dialog('close');
            }
        },
        close : function() {

            resetDialog($('#siteForm'));

            $(this).dialog('close');
        }
    });
});

$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});