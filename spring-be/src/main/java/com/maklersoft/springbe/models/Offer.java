package com.maklersoft.springbe.models;

import com.maklersoft.springbe.models.enums.Tag;
import com.maklersoft.springbe.models.enums.offer.*;
import com.maklersoft.springbe.models.utils.ConditionsBlock;
import com.maklersoft.springbe.models.utils.Confirmation;
import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Document(indexName = "ms-offers", createIndex = true)
@Setting(settingPath = "normalizer.json")
public class Offer {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Keyword)
    private String importId;

    @Field(type = FieldType.Keyword)
    private Long accountId;

    @Field(type = FieldType.Keyword)
    private StageCode stageCode;

    @Field(type = FieldType.Nested)
    private AddressBlock addressBlock;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<Confirmation> phones;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<Confirmation> emails;

    @Field(type = FieldType.Keyword)
    private HouseType houseType;

    @Field(type = FieldType.Keyword)
    private RoomScheme roomScheme;

    @Field(type = FieldType.Keyword)
    private Condition condition;

    @Field(type = FieldType.Keyword)
    private Bathroom bathroom;

    @Field(type = FieldType.Float)
    private Float price;

    @Field(type = FieldType.Float)
    private Float ownerPrice;

    @Field(type = FieldType.Boolean)
    private Boolean deposit;

    @Field(type = FieldType.Float)
    private Float commission;

    @Field(type = FieldType.Keyword)
    private Commission commissionType;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String workInfo;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String description;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String costInfo;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String conditionInfo;

    @Field(type = FieldType.Keyword)
    private SourceMedia sourceMedia;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String sourceUrl;

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime addDate;

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime changeDate;

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime assignDate;

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime deleteDate;

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime arrivalDate;

    //private String period;

    @Field(type = FieldType.Keyword)
    private Commission mlsPriceType;

    @Field(type = FieldType.Float)
    private Float mlsPrice;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<User> agent;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<Person> person;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Organisation company;

    @GeoPointField
    private GeoPoint location;

    /*private ArrayList<UploadFile> photos;
    private ArrayList<UploadFile> documents;*/

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String documentsStr;

    @Field(type = FieldType.Keyword)
    private Source sourceCode;

    @Field(type = FieldType.Keyword)
    private OfferTypeCode offerTypeCode;

    @Field(type = FieldType.Keyword)
    private RentTypeCode rentType;

    @Field(type = FieldType.Keyword)
    private CategoryCode categoryCode;

    @Field(type = FieldType.Keyword)
    private TypeCode typeCode;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String settlement;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String housingComplex;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String distance;

    @Field(type = FieldType.Boolean)
    private Boolean newBuilding;

    @Field(type = FieldType.Keyword)
    private BuildingStage objectStage;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String buildYear;

    @Field(type = FieldType.Integer)
    private Integer roomsCount;

    @Field(type = FieldType.Integer)
    private Integer floor;

    @Field(type = FieldType.Integer)
    private Integer floorsCount;

    @Field(type = FieldType.Integer)
    private Integer levelsCount;

    @Field(type = FieldType.Float)
    private Float squareTotal;

    @Field(type = FieldType.Float)
    private Float squareLiving;

    @Field(type = FieldType.Float)
    private Float squareKitchen;

    @Field(type = FieldType.Float)
    private Float squareLand;

    @Field(type = FieldType.Float)
    private LandType squareLandType;

    @Field(type = FieldType.Boolean)
    private Boolean balcony;

    @Field(type = FieldType.Boolean)
    private Boolean loggia;

    @Field(type = FieldType.Boolean)
    private Boolean terrace;

    @Field(type = FieldType.Boolean)
    private Boolean guard;

    @Field(type = FieldType.Boolean)
    private Boolean waterSupply;

    @Field(type = FieldType.Boolean)
    private Boolean gasification;

    @Field(type = FieldType.Boolean)
    private Boolean electrification;

    @Field(type = FieldType.Boolean)
    private Boolean sewerage;

    @Field(type = FieldType.Boolean)
    private Boolean centralHeating;

    @Field(type = FieldType.Boolean)
    private Boolean lift;

    @Field(type = FieldType.Boolean)
    private Boolean parking;

    @Field(type = FieldType.Keyword)
    private LandPurpose landPurpose;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String objectName;

    @Field(type = FieldType.Keyword)
    private BuildingType buildingType;

    @Field(type = FieldType.Keyword)
    private BuildingClass buildingClass;

    @Field(type = FieldType.Float)
    private Float ceilingHeight;

    //private ContractBlock contractBlock;

    @Field(type = FieldType.Boolean)
    private Boolean encumbrance;

    @Field(type = FieldType.Boolean)
    private Boolean mortgages;

    @Field(type = FieldType.Boolean)
    private Boolean certificate;

    @Field(type = FieldType.Boolean)
    private Boolean maternityCapital;

    @Field(type = FieldType.Keyword)
    private Tag tag;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String mediatorCompany;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String thirdPartyRights;

    @Field(type = FieldType.Nested)
    private ConditionsBlock conditions;

    @Field(type = FieldType.Boolean)
    private Boolean prepayment;

    @Field(type = FieldType.Keyword)
    private PaymentType paymentType;

    @Field(type = FieldType.Boolean)
    private Boolean electrificPay;

    @Field(type = FieldType.Boolean)
    private Boolean waterPay;

    @Field(type = FieldType.Boolean)
    private Boolean gasPay;

    @Field(type = FieldType.Boolean)
    private Boolean heatingPay;

    @Field(type = FieldType.Boolean)
    private Boolean utilityBills;

    @Field(type = FieldType.Keyword)
    private String offerRef;


    //private auxclass.Rating locRating;

    public void preIndex() {
        if (this.id == null) {
            this.addDate = LocalDateTime.now();
            this.changeDate = LocalDateTime.now();
        }
        if (this.stageCode == null)
            this.stageCode = StageCode.RAW;
        if (this.mortgages == null)
            this.mortgages = false;
        if (this.newBuilding == null)
            this.newBuilding = false;

        if (this.offerTypeCode == OfferTypeCode.RENT) {
            /*if (this.getConditions() == null)
                this.setConditions(new ConditionsBlock());
            this.getConditions().setNullValues();*/
            //this.prepayment = CommonUtils.strNotNull(this.prepayment);
            if (this.rentType == null)
                this.rentType = RentTypeCode.LONG;
        }
        /*if (this.emailBlock == null) {
            this.emailBlock = new EmailBlock();
        }

        if (this.photos == null)
            this.photos = new ArrayList<>();
        if (this.documents == null)
            this.documents = new ArrayList<>();

        UploadFile uploadFile = new UploadFile("fdsfs", 0L, true);



        this.photos = UploadFile.moveFromTemp(this.photos, "photo/offers/" + getAccountId() + "/" + getId(),
                1280, 960, 512, 384);
        //this.documents = UploadFile.moveFromTemp(this.documents, "docs/offers/" + getAccountId() + "/" + getId());
*/
    }

    /*public static String needAdd(String val) {
        switch (val) {
            case "offerTypeCode":
                return "typeCode";
            case "rentType":
                return "rentType";
            case "typeCode":
                return "offerTypeCode";
            case "buildingClass":
                return "buildingClass";
            case "buildingType":
                return "buildingType";
            case "houseType":
                return "houseType";
            case "roomScheme":
                return "roomScheme";
            case "newBuilding":
                return "newBuilding";
            case "condition":
                return "condition";
            case "roomsCount":
                return "roomsCount";
            //case "ownerPrice": return "ownerPrice";
            case "loggia":
                return "loggia";
            case "balcony":
                return "balcony";
            case "terrace":
                return "terrace";
            /*case "floor": return "floor";
            case "conditions": return "conditions";
            //case "prepayment": return "prepayment";

            //case "mortgage": return "mortgage";

            /*case "deposit": return "deposit";
            case "utilityBills": return "utilityBills";
            case "electrificPay": return "electrificPay";
            case "waterPay": return "waterPay";
            case "gasPay": return "gasPay";
            case "heatingPay": return "heatingPay";
            case "certificate": return "certificate";
            case "maternityCapital": return "maternityCapital";
            case "encumbrance": return "encumbrance";*/

            /*case "squareTotalMin": return "squareTotal";
            case "squareTotalMax": return "squareTotal";
            case "squareLivingMin": return "squareLiving";
            case "squareLivingMax": return "squareLiving";
            case "squareKitchenMin": return "squareKitchen";
            case "squareKitchenMax": return "squareKitchen";
            case "squareLandMin": return "squareLand";
            case "squareLandMax": return "squareLand";
            case "searchArea": return "location";
            default:
                return null;
        }

    }*/
}
