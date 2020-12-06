package com.maklersoft.springbe.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
public class AddressBlock {
    private String region;
    private String admArea;
    private String area;
    private String city;
    private String street;
    private String building;
    private String apartment;
    private Set<String> stations;
    private Set<String> busStops;
    private GeoPoint location;

    public String getAsString() {

        List<String> t = new ArrayList<>();

        if (region != null) t.add(region);
        if (admArea != null) t.add(admArea);
        if (area != null) t.add(area);
        if (city != null) t.add(city);
        if (street != null) t.add(street);
        if (building != null) t.add(building);
        if (apartment != null) t.add(apartment);
       /* if (stations != null) t.add(stations);
        if (busStops != null) t.add(busStops);*/
        return String.join(", ", t);
    }

    public boolean equals(AddressBlock o) {
        if (o == this) return true;
        if (o == null) return false;

        if (o.region == null && region != null) return false;
        if (o.region != null && !o.region.equals(region)) return false;
        if (o.admArea == null && admArea != null) return false;
        if (o.admArea != null && !o.admArea.equals(admArea)) return false;
        if (o.area == null && area != null) return false;
        if (o.area != null && !o.area.equals(area)) return false;
        if (o.city == null && city != null) return false;
        if (o.city != null && !o.city.equals(city)) return false;
        if (o.street == null && street != null) return false;
        if (o.street != null && !o.street.equals(street)) return false;
        if (o.building == null && building != null) return false;
        if (o.building != null && !o.building.equals(building)) return false;
        if (o.apartment == null && apartment != null) return false;
        if (o.apartment != null && !o.apartment.equals(apartment)) return false;
        if (o.stations == null && stations != null) return false;
        if (o.stations != null && !o.stations.equals(stations)) return false;
        if (o.busStops == null && busStops != null) return false;
        if (o.busStops != null && !o.busStops.equals(busStops)) return false;


        return true;
    }
}
