/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entidade.Ubs;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author german
 */
@ManagedBean(name = "AcessoController")
@ViewScoped
public class AcessoController implements Serializable {

    @ManagedProperty(value = "#{UbsController}")
    private UbsController ubsControl;

    /*
     @EJB
     private UbsFacade ubsFacade;
     */
    private MapModel advancedModel;
    private Marker marker;

    //Usado para o marcador que será adicionado
    private double latitude = 0, longitude = 0;

    @PostConstruct
    public void init() {

        //ubsFacade = new UbsFacade();
        advancedModel = new DefaultMapModel();
        LatLng coord;

        for (Ubs unidade : this.getUbsControl().getUnidades()) {
            coord = new LatLng(unidade.getLatitude(), unidade.getLongitude());
            advancedModel.addOverlay(new Marker(coord, unidade.getUnidade(), unidade));
        }

        /*      
         for (Ubs unidade : ubsFacade.findAll()) {
         System.out.println(unidade.getLatitude() + " " + unidade.getLongitude());
         coord = new LatLng(unidade.getLatitude(), unidade.getLongitude());
         advancedModel.addOverlay(new Marker(coord, unidade.getUnidade(), unidade));
         }*/
    }

    //Para registrar o marcador definido no Geocode
    public void onGeocode(GeocodeEvent event) {

        List<GeocodeResult> results = event.getResults();

        if (results != null && !results.isEmpty()) {
            
            checkMarkers();

            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                setLatitude(result.getLatLng().getLat());
                setLongitude(result.getLatLng().getLng());
                advancedModel.addOverlay(new Marker(result.getLatLng(), result.getAddress(), null, "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
            }
            //Mostra uma caixa de dialogo
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marcador adicionado", "Lat:" + getLatitude() + ", Lng:" + getLongitude()));
        }
    }

    public void checkMarkers() {

        Marker lastMarker = advancedModel.getMarkers().get(advancedModel.getMarkers().size() - 1);

        if (lastMarker.getLatlng().getLat() == getLatitude() && lastMarker.getLatlng().getLng() == getLongitude()) {
            advancedModel.getMarkers().remove(advancedModel.getMarkers().size() - 1);
            setLatitude(0);
            setLongitude(0);
        }
    }

    public MapModel getAdvancedModel() {
        return advancedModel;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }

    public Marker getMarker() {
        return marker;
    }

    public UbsController getUbsControl() {
        return ubsControl;
    }

    public void setUbsControl(UbsController ubsControl) {
        this.ubsControl = ubsControl;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
