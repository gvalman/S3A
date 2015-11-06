<%-- 
    Document   : teste
    Created on : 03/08/2015, 10:58:25
    Author     : german
--%>

<div id="cabecalho" class="navbar navbar-fixed-top bg-primary">
    <div class="container-fluid">
        <div class="navbar-header">
            <h6 class="text-center" id="titulo">SISTEMA 3A DE SAÚDE
                <span class="label label-default">v1.0</span>
            </h6>
        </div>
    </div>
</div>


<div id="conteudo" class="section">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4 text-center container-fluid" id="AcessoBackground">
                <div class="row">
                    <div class="col-md-12 text-center container-fluid" id="Acesso1">
                        <h1 class="text-inverse" id="A1">A</h1>
                        <h2 class="text-inverse" id="acesso">ACESSO</h2>
                    </div>

                    <div class="col-md-6 text-center container-fluid" id="Acesso2">
                        <div class="section">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-md-6 text-center container-fluid">
                                        <img src="img/title01.jpg" class="img-responsive">
                                    </div>
                                    <div class="col-md-6 text-center container-fluid">
                                        <h1>MAPEAMENTO DAS UNIDADES MÉDICAS</h1>
                                        <p>No módulo ACESSO:</p> 
                                        <p>- Poderá verificar a localização de todas as unidades da cidade do Recife.</p>
                                        <p>- Poderá visualizar o trajeto até a unidade.</p>
                                        <p>- Poderá visualizar informações prévias sobre a unidade.</p>
                                    </div>
                                    <form action="MainServlet" method="POST">
                                        <input type="hidden" name="tarefa" value="AcessarAcesso">
                                        <button type="submit" class="btn btn-primary">ACESSO</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4 text-center container-fluid" id="AcolhimentoBackground">
                <div class="row">
                    <div class="col-md-12 text-center container-fluid">
                        <h1 class="text-inverse" id="A2">A</h1>
                        <h2 class="text-inverse" id="acolhimento">ACOLHIMENTO</h2>
                    </div>
                </div>
            </div>
            <div class="col-md-4 text-center container-fluid" id="AcaoBackground">
                <div class="row">
                    <div class="col-md-12 text-center container-fluid">
                        <h1 class="text-inverse" id="A3">A</h1>
                        <h2 class="text-inverse" id="acao">AÇÃO</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<footer id="rodape" class="navbar navbar-fixed-bottom bg-primary">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6">
                <h6>SISTEMA 3A DE SAÚDE</h6>
                <!--
                <p class="lead">Trabalho de Conclusão de Curso, Curso: Tecnologia de Análise e Desenvolvimento
                    de Sistemas.</p>
                -->
            </div>
            <div class="col-sm-6">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <!--
                        <img src="img/ifpe recife.png" class="center-block img-responsive" width="20%">
                        -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>