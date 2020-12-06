package com.maklersoft.springbe.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.geo.Point;

import java.util.ArrayList;
import java.util.List;

@Data
class GeocoderMetaData{
    String kind;
}

@Data
class PointClass{
    String pos;
}

@Data
class GeoObject{
    @JsonProperty("Point")
    PointClass point;
    MetaDataProperty metaDataProperty;
    String name;
}

@Data
class FeatureMember{
    @JsonProperty("GeoObject")
    GeoObject geoObject;
}

@Data
class GeocoderResponseMetaData{
    String found;
    //List<GeoObjectClass> featureMember;
}

@Data
class MetaDataProperty{
    @JsonProperty("GeocoderResponseMetaData")
    GeocoderResponseMetaData geocoderResponseMetaData;
    @JsonProperty("GeocoderMetaData")
    GeocoderMetaData geocoderMetaData;
}

@Data
class GeoObjectCollection{
    MetaDataProperty metaDataProperty;
    List<FeatureMember> featureMember;
}

@Data
class Response{
    @JsonProperty("GeoObjectCollection")
    GeoObjectCollection geoObjectCollection;
}

@Data
public class GeoObjectsResponse {
    Response response;

    public Integer getCount(){
        return Integer.parseInt(this.response.geoObjectCollection.metaDataProperty.geocoderResponseMetaData.found);
    }

    public GeoPoint getCoord(){
        String[] t = this.response.geoObjectCollection.featureMember.get(0).geoObject.point.pos.split(" ");
        return GeoPoint.fromPoint(new Point( Double.valueOf(t[1]), Double.valueOf(t[0])));
    }

    public List<String> getFutureMembersByKind(String kind){
        List<String> names = new ArrayList<>();
        this.response.geoObjectCollection.featureMember.forEach(fm -> {
            if (fm.geoObject.metaDataProperty.geocoderMetaData.kind.equals(kind)) {
                names.add(fm.geoObject.name);
            }
        });
        return names;
    }
}
