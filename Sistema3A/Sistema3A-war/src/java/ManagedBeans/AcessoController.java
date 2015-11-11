/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entidade.Ubs;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import javax.faces.event.FacesEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import sessionbeans.UbsFacade;

/**
 *
 * @author german
 */
@ManagedBean(name = "AcessoController")
@ViewScoped
public class AcessoController implements Serializable {

    //@ManagedProperty(value = "#{UbsController}")
    //private UbsController ubsControl;
    @EJB
    private UbsFacade ubsFacade;

    private MapModel advancedModel;
    private Marker marker;

    @PostConstruct
    public void init() {
        //setUbsControl(new UbsController());
        advancedModel = new DefaultMapModel();
        LatLng coord;

        /*
         for (Ubs unidade : this.getUbsControl().getUnidades()) {
         coord = new LatLng(unidade.getLatitude(), unidade.getLongitude());
         advancedModel.addOverlay(new Marker(coord, unidade.getUnidade(), unidade));
         }*/
        for (Ubs unidade : ubsFacade.findAll()) {
            System.out.println(unidade.getLatitude() + " " + unidade.getLongitude());
            coord = new LatLng(unidade.getLatitude(), unidade.getLongitude());
            advancedModel.addOverlay(new Marker(coord, unidade.getUnidade(), unidade));
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

    /*
     public UbsController getUbsControl() {
     return ubsControl;
     }
    
     public void setUbsControl(UbsController ubsControl) {
     this.ubsControl = ubsControl;
     }*/
}
