 var mapa;
 var directionDisplay;
 var directionsService = new google.maps.DirectionService();
 
 function CarregarMapa() {
    var latlng = new google.maps.LatLng(-8.043326721315049, -34.93619875000002);
    var Opcao = {
      zoom: 10,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    
    /*Cria um objeto Map indicando a DIV e colocando as opções de render*/
    mapa = new google.maps.Map(document.getElementById("map_container"),Opcao);
 
    //Cria um marcador para o mapa
    var marker = new google.maps.Marker({
      position: latlng, 
      map: mapa, 
      title:"Unidades de Saúde"
    }); 
   
    directionDisplay = new google.maps.DirectionsRenderer();
    /*Atualiza a renderização com o mapa modificado*/
    directionDisplay.setMap(mapa);
    /*Informa o trajeto em formato texto*/
    directionsDisplay.setPanel(document.getElementById("trajeto"));
    
    /*Colocar a geolocalização do usuário como exibição inicial do mapa*/
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(ShowPosition);
    }
  }
  
/*Função para localizar a localização do usuário*/
function ShowPosition(position) {

    pontoPadrao = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    mapa.setCenter(pontoPadrao);

    var geocoder = new google.maps.Geocoder();

    geocoder.geocode({
        "location": new google.maps.LatLng(position.coords.latitude, position.coords.longitude)
    },
    function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            $("#txtEnderecoPartida").val(results[0].formatted_address);
        }
    });
}

/*É chamado quando o FORM for submetido com dados fornecidos pelo usuário*/
$("form").submit(function (event) {
    event.preventDefault();
    var enderecoPartida = $("#txtEnderecoPartida").val();
    var enderecoChegada = $("#txtEnderecoChegada").val();

    var request = {
        origin: enderecoPartida,
        destination: enderecoChegada,
        travelMode: google.maps.TravelMode.DRIVING
    };

    /*Define a rota e atualiza o mapa*/
    directionsService.route(request, function (result, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(result);
        }
    });
});