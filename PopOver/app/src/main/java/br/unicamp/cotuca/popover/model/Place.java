package br.unicamp.cotuca.popover.model;

/**
 * Created by joao on 31/08/15.
 */
public class Place {
    private double lat,lng;
    private String name;
    private String address;
    private String description;

    public Place(double lat, double lng, String name, String address, String description, String imgURL) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.address = address;
        this.description = description;
        this.imgURL = imgURL;
    }

    private String imgURL;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
