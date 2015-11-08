var tempo = "slow";

$(document).ready(function () {
    
    $("#Acesso2").hide();

    $("#AcessoBackground").hover(
            function () {
                $("#AcaoBackground").hide();
                $("#AcolhimentoBackground").hide();
                $("#AcessoBackground").attr('class', 'col-md-12 text-center container-fluid');
                $("#Acesso2").show();
                $("#Acesso1").attr('class', 'col-md-6 text-center container-fluid');
            },
            function () {
                $("#AcaoBackground").show();
                $("#AcolhimentoBackground").show();
                $("#AcessoBackground").attr('class', 'col-md-4 text-center container-fluid');
                $("#Acesso2").hide();
                $("#Acesso1").attr('class', 'col-md-12 text-center container-fluid');
            }
    );

    $("#AcolhimentoBackground").hover(
            function () {
                $("#AcessoBackground").hide();
                $("#AcaoBackground").hide();
                $("#AcolhimentoBackground").attr('class', 'col-md-12 text-center container-fluid');
            },
            function () {
                $("#AcessoBackground").show();
                $("#AcaoBackground").show();
                $("#AcolhimentoBackground").attr('class', 'col-md-4 text-center container-fluid');
            }
    );

    $("#AcaoBackground").hover(
            function () {
                $("#AcessoBackground").hide();
                $("#AcolhimentoBackground").hide();
                $("#AcaoBackground").attr('class', 'col-md-12 text-center container-fluid');
            },
            function () {
                $("#AcessoBackground").show();
                $("#AcolhimentoBackground").show();
                $("#AcaoBackground").attr('class', 'col-md-4 text-center container-fluid');
            }
    );
}
);