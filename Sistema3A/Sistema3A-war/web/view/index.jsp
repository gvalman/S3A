<%-- 
    Document   : index
    Created on : 04/06/2015, 16:45:37
    Author     : german
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema 3A de Saúde</title>

        <!--JQUERY(http://jquery.com/download/)-->
        <script src="js/jquery-1.11.1.js"></script>
        <script src="js/jquery-2.1.1.js"></script>

        <!--BOOTSTRAP(http://getbootstrap.com/getting-started/#download)-->
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <script type="text/javascript" src="js/npm.js"></script>
        <script type="text/javascript" src="js/carousel.js"></script>

        <!--https://code.google.com/p/jquery-csv/-->
        <script type="text/javascript" src="js/jquery.csv-0.71.js"></script>

        <script type="text/javascript" src="js/myjavascript.js"></script>

        <style type="text/css">
            <%@ include  file="/css/bootstrap.css"%>
            <%@ include  file="/css/bootstrap-theme.css"%>
            <%@ include  file="/css/mycss.css"%>
            <%@ include  file="/css/geral.css"%>
        </style>
    </head>
    <body>
        <!--Cabeçalho-->
        <jsp:include page="header.jsp" />

        <!--
        <div>
            <form action="../MainServlet" method="POST">
                <input type="hidden" name="tarefa" value="importarCSV">
                <button type="submit" class="btn btn-primary">Importar CSV</button>
            </form>
        </div>
        -->

        <!--Conteudo-->
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
                                                <img src="<%=request.getContextPath()%>/img/title01.jpg" class="img-responsive">
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

        <!--Rodapé-->
        <jsp:include page="footer.jsp" />
    </body>
</html>
