package com.maklersoft.springbe.controllers;

import com.maklersoft.springbe.models.AddressBlock;
import com.maklersoft.springbe.models.enums.KladrContentType;
import com.maklersoft.springbe.models.responses.GeoObjectsResponse;
import com.maklersoft.springbe.models.utils.KladrResponse;
import com.maklersoft.springbe.security.JwtUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.springframework.http.ResponseEntity.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/${api.version}/geo")
@PropertySource(value = "classpath:application.properties")
public class GeoController {
    @Value("${kladr-api.key}")
    String kladrApiKey;
    @Value("${yamap.key}")
    String yamapKey;
    private final RestTemplate restTemplate;
    public GeoController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(value = "/fias")
    public ResponseEntity<?> get(@AuthenticationPrincipal JwtUser user,
                                 @RequestParam(value = "query") String query,
                                 @RequestParam(value = "contentType") String contentType,
                                 @RequestParam(value = "withParent") String withParent,
                                 @RequestParam(value = "parent", required = false) String parent) {

        String url = "https://kladr-api.ru/api.php?query={1}&contentType={2}&withParent={3}&limit={4}&token={5}";
        if (parent != null) {
            KladrContentType parentType = KladrContentType.valueOf(contentType.toUpperCase()).getParent();
            if(parentType != null)
                url += "&" + parentType.toString().toLowerCase() + "Id=" + parent;
        }

        ResponseEntity<KladrResponse> response = restTemplate.getForEntity(url, KladrResponse.class, query, contentType, withParent, 10, kladrApiKey);
        if(response.getStatusCode() == HttpStatus.OK) {
            response.getBody().getResult().remove(0);
            return ok(response.getBody().getResult());
        } else {
            return status(HttpStatus.BAD_REQUEST).body("Ошибка при получении данных о ФИАС");
        }
    }

    @PostMapping(value = "/latLonWithArea")
    public ResponseEntity<?> latLonWithArea(@RequestBody AddressBlock addressBlock) {
        String addressStr = addressBlock.getAsString();
        if (addressStr.length() > 0) {
            GeoPoint point = this.getCoordsByAddress(addressStr);
            if(point != null){
                addressBlock.setLocation(point);
                List<String> districts = this.getLocationDistrict(point);
                if (!districts.isEmpty()) {
                    if (districts.size() > 1) {
                        addressBlock.setArea(districts.get(0));
                        addressBlock.setAdmArea(districts.get(1));
                    } else {
                        addressBlock.setAdmArea(districts.get(0));
                        addressBlock.setArea("");
                    }
                }
            }
        }
        return ok(addressBlock);
    }

    public GeoPoint getCoordsByAddress(String addressStr){
        String url = "https://geocode-maps.yandex.ru/1.x/?geocode={1}&format=json&apikey={2}";

        ResponseEntity<GeoObjectsResponse> response = restTemplate.getForEntity(url, GeoObjectsResponse.class, addressStr, yamapKey);
        if(!response.getStatusCode().isError() && response.getBody().getCount() > 0){
            return response.getBody().getCoord();
        }
        else return null;
    }

    public List<String> getLocationDistrict(GeoPoint point) {
        String url = "https://geocode-maps.yandex.ru/1.x/?geocode={1},{2}&format=json&apikey={3}";
        List<String> dList = new ArrayList<>();

        ResponseEntity<GeoObjectsResponse> response = restTemplate.getForEntity(
                url, GeoObjectsResponse.class, point.getLon(), point.getLat(), yamapKey
        );
        if (!response.getStatusCode().isError() && response.getBody().getCount() > 0) {
            dList = response.getBody().getFutureMembersByKind("district");
        }
        return dList;
    }
}
