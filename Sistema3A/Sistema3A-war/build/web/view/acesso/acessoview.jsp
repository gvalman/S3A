<%-- 
    Document   : acessoview
    Created on : 14/06/2015, 19:31:27
    Author     : german
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema 3A de Saúde</title>

        <!--JQUERY(http://jquery.com/download/)-->
        <script src="resources/js/jquery-1.11.1.js"></script>
        <script src="resources/js/jquery-2.1.1.js"></script>
        <script src="resources/js/jquery-ui.custom.min.js"></script>

        <!--BOOTSTRAP(http://getbootstrap.com/getting-started/#download)-->
        <script type="text/javascript" src="resources/js/bootstrap.js"></script>
        <script type="text/javascript" src="resources/js/npm.js"></script>
        <script type="text/javascript" src="resources/js/carousel.js"></script>

        <!--https://code.google.com/p/jquery-csv/-->
        <script type="text/javascript" src="resources/js/jquery.csv-0.71.js"></script>
        <script type="text/javascript" src="resources/js/myjavascript.js"></script>

        <!-- Maps API Javascript -->
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>

        <style type="text/css">
            <%@ include  file="/resources/css/bootstrap.css"%>
            <%@ include  file="/resources/css/bootstrap-theme.css"%>
            <%@ include  file="/resources/css/geral.css"%>
            <%@ include  file="/resources/css/mapaestilo.css"%>
        </style>

        <c:choose>
            <c:when test="${empty unidades}">
                alert("Não há registro de Unidades.");
                <script type="text/javascript" src="resources/js/mapa.js"></script>
            </c:when>
            <c:otherwise>
                <script type="text/javascript">
                    var positions = [];//Array usada para registrar as posições das markers
                    var position = false;//Verificar se no "positions" existe um marker do endereço
                    var bounds;
                    var marker;
                    var geocoder;
                    var mapa;
                    var infoWindow;
                    var directionsDisplay;
                    var directionsService = new google.maps.DirectionService();

                    function CarregarMapa() {

                        infoWindow = new google.maps.InfoWindow();

                        //Colocar latitude e longitude nesta ordem
                        var latlng = new google.maps.LatLng(-8.043326721315049, -34.93619875000002);
                        var Opcao = {
                            zoom: 10,
                            center: latlng,
                            mapTypeId: google.maps.MapTypeId.ROADMAP
                        };

                        /*Cria um objeto Map indicando a DIV e colocando as opções de render*/
                        mapa = new google.maps.Map(document.getElementById("map_container"), Opcao);

                        // esta variável vai definir a área de mapa a abranger e o nível do zoom
                        // de acordo com as posições dos marcadores
                        bounds = new google.maps.LatLngBounds();

                        MostrarMarkers();

                        // Depois de criados todos os marcadores
                        // a API através da sua função fitBounds vai redefinir o nível do zoom
                        // e consequentemente a área do mapa abrangida.
                        mapa.fitBounds(bounds);

                        //Usado para modificar endereço para lat e long
                        geocoder = new google.maps.Geocoder();

                        //Criar um marker que será usado para o endereço digitado
                        marker = new google.maps.Marker({
                            map: mapa,
                            draggable: true,
                        });

                        function carregarNoMapa(endereco) {
                            geocoder.geocode({'address': endereco + ', Brasil', 'region': 'BR'}, function (results, status) {
                                if (status == google.maps.GeocoderStatus.OK) {
                                    if (results[0]) {
                                        var latitude = results[0].geometry.location.lat();
                                        var longitude = results[0].geometry.location.lng();

                                        $('#txtEndereco').val(results[0].formatted_address);
                                        $('#txtLatitude').val(latitude);
                                        $('#txtLongitude').val(longitude);

                                        var location = new google.maps.LatLng(latitude, longitude);
                                        marker.setPosition(location);
                                        //mapa.setCenter(location);
                                        //mapa.setZoom(16);

                                        //////Usado para atualizar o mapa com os markers e o endereço///
                                        if (position) {
                                            positions.pop();//Remove o último elemento do array                                             
                                        } else {
                                            position = true;
                                        }

                                        positions.push(location);

                                        bounds = new google.maps.LatLngBounds();

                                        for (i = 0; i < positions.length; i++) {
                                            bounds.extend(positions[i]);
                                        }

                                        mapa.fitBounds(bounds);
                                        //////////////////////////////////////////////////////////////
                                    }
                                }
                            })
                        }

                        /*
                         $("#btnEndereco").click(function () {
                         if ($(this).val() != "")
                         carregarNoMapa($("#txtEndereco").val());
                         })
                         
                         $("#txtEndereco").blur(function () {
                         if ($(this).val() != "")
                         carregarNoMapa($(this).val());
                         })
                         */

                        $("#btnEndereco").click(function () {
                            if ($("#txtEndereco").val() != "")
                                carregarNoMapa($("#txtEndereco").val());
                        })

                        $("#btnEnderecoRemove").click(function () {
                            alert("OK");
                            document.getElementById(btnEnderecoRemove).value = "";
                            //$("#txtEndereco").val() = "";
                        })


                        $("#txtEndereco").autocomplete({
                            source: function (request, response) {
                                geocoder.geocode({'address': request.term + ', Brasil', 'region': 'BR'}, function (results, status) {
                                    response($.map(results, function (item) {
                                        return {
                                            label: item.formatted_address,
                                            value: item.formatted_address,
                                            latitude: item.geometry.location.lat(),
                                            longitude: item.geometry.location.lng()
                                        }
                                    }));
                                })
                            },
                            select: function (event, ui) {
                                $("#txtLatitude").val(ui.item.latitude);
                                $("#txtLongitude").val(ui.item.longitude);
                                var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
                                //marker.setPosition(location);
                                //mapa.setCenter(location);
                                //mapa.setZoom(16);
                            }
                        });
                    }

                    function MostrarMarkers() {

                    <c:forEach items="${unidades}" var="unidade">
                        var posicao = new google.maps.LatLng('${unidade.latitude}', '${unidade.longitude}');
                        var titulo = '${unidade.unidade}';
                        var endereco = '${unidade.endereco}';
                        var bairro = '${unidade.bairro}';
                        var fone = '${unidade.fone}';

                        CriarMarker(posicao, titulo, endereco, bairro, fone);

                        //Registrar as posições dos markers
                        positions.push(posicao);

                        bounds.extend(posicao);
                    </c:forEach>

                    }

                    function CriarMarker(posicao, titulo, endereco, bairro, fone) {

                        var iconImage = new google.maps.MarkerImage("resources/img/LogoSaudeFamilia.png", null, null, null, new google.maps.Size(60, 60));

                        var marker = new google.maps.Marker({
                            map: mapa,
                            position: posicao,
                            title: titulo,
                            icon: iconImage
                        });

                        //Aplicados addListener para ativar o infoWindows
                        //Tipos de eventos para addListener "click" ou "mouseover" ou "mouseout"
                        google.maps.event.addListener(marker, 'click', function () {
                            var contentString = '<div>' +
                                    '<h6>' + titulo + '</h6>' +
                                    '<p><b>Endereço: </b>' + endereco + '</p>' +
                                    '<p><b>Bairro: </b>' + bairro + '</p>' +
                                    '<p><b>Contato: </b>' + fone + '</p>' +
                                    '</div>';

                            //Alguns sets do infoWindows
                            infoWindow.setContent(contentString);
                            //infoWindow.setPosition(posicao);

                            // A Info Window é aberta.
                            infoWindow.open(mapa, marker);
                        });
                    }
                </script>
            </c:otherwise>
        </c:choose>
    </head>
    <body onload="CarregarMapa()">
        <!--Cabeçalho-->
        <jsp:include page="../header.jsp" />
        <!--Conteudo-->
        <div id="conteudo" class="section">
            <div id="site" class="container-fluid">
                <div class="row">
                    <div class="col-md-12 container-fluid">
                        <div>
                            <label for="EnderecoPartida">Origem:</label>
                            <input type="text" id="txtEndereco" name="txtEnderecoPartida" />
                            <button id="btnEndereco" class="btn btn-primary">Enviar</button>
                            <button id="btnEnderecoRemove" class="btn btn-primary">Remover</button>
                            <input type="hidden" id="txtLatitude" name="txtLatitude" />
                            <input type="hidden" id="txtLongitude" name="txtLongitude" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8 container-fluid" id="map_container"></div>
                    <div class="col-md-4 container-fluid" id="trajeto"></div>
                </div>
            </div>
        </div>
        <!--Rodapé-->
        <jsp:include page="../footer.jsp" />
    </body>
</html>