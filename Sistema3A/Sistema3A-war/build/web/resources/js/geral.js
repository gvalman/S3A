/*Onload com JQUERY*/
$(document).ready(function () {
    $("#Acesso2").hide();
    $("#Acolhimento2").hide();
    $("#Acao2").hide();

    console.log(document.querySelectorAll("#TabelaAAA td"));

    $("#AcessoBackground").hover(
            function () {
                $("#Acesso2").show();
            },
            function () {
                $("#Acesso2").hide();
            }
    );

    $("#AcolhimentoBackground").hover(
            function () {
                $("#Acolhimento2").show();
            },
            function () {
                $("#Acolhimento2").hide();
            }
    );

    $("#AcaoBackground").hover(
            function () {
                $("#Acao2").show();
            },
            function () {
                $("#Acao2").hide();
                //$("#TabelaAAA td:nth-child(3)").css({"width": "33%"});
            }
    );
});

