package com.maklersoft.springbe.models;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.ArrayList;

@Data
public class Request {
    /*private Long id;
    private Long accountId;

    private Long agentId;
    private User agent;
    private Long personId;
    private Person person;
    private Long companyId;
    private Organisation company;

    private String categoryCode;
    private ArrayList<String> buildingType;
    private ArrayList<String> buildingClass;
    private String typeCode;
    private String rentType;
    private String objectStage;
    private String distance;
    private String settlement;
    private Boolean guard;
    private String housingComplex;
    private Boolean mortgages;
    private ArrayList<String> houseType;
    private ArrayList<String> roomScheme;
    private String sourceCode;
    private String offerTypeCode;
    private Float squareTotalMin;
    private Float squareTotalMax;
    private Float squareLivingMin;
    private Float squareLivingMax;
    private Float squareKitchenMin;
    private Float squareKitchenMax;
    private Boolean balcony;
    private Boolean loggia;
    private Boolean terrace;
    private ArrayList<String> condition;
    private ArrayList<String> bathroom;
    private Float squareLandMin;
    private Float squareLandMax;
    private String squareLandType;
    private Boolean waterSupply;
    private Boolean gasification;
    private Boolean electrification;
    private Boolean sewerage;
    private Boolean centralHeating;
    private String objectName;
    private Float ceilingHeight;
    private Boolean lift;
    private Boolean parking;
    private auxclass.Rating locRating;
    private String thirdPartyRights;
    private Long addDate;
    private Long changeDate;
    private Long assignDate;
    private Long arrival_date;

    private Boolean newBuilding;
    private Boolean encumbrance;
    private Integer buildYearFrom;
    private Integer buildYearTo;
    private String rate;

    private Integer roomsCount;
    private ArrayList<String> floor;

    private String stageCode;

    private ContractBlock contractBlock;
    private Integer tag;
    private String costInfo;
    private String description;
    private Float ownerPrice;
    //Аренда
    private ConditionsBlock conditions;
    private String paymentType;
    private String period;
    private String paymentMethod;
    private Boolean deposit;
    private Boolean utilityBills;
    private Float commission;
    private Float mlsPrice;
    private Boolean prepayment;
    //Не аренда
    private Boolean cash;
    private Boolean mortgage;
    private Boolean certificate;
    private Boolean maternityCapital;

    @Field( type = FieldType.Nested)
    private GeoPoint[] searchArea;

    private ArrayList<UploadFile> documents;

    private String commisionType;
    private String mlsPriceType;

    private Boolean electrificPay;
    private Boolean waterPay;
    private Boolean gasPay;
    private Boolean heatingPay;

    public void preIndex() {
        if (this.id == null) {
            this.id = CommonUtils.getSystemTimestamp();
            this.addDate = CommonUtils.getUnixTimestamp();
        }
        if (this.agentId == null)
            this.agent = null;
        if (this.personId == null)
            this.person = null;
        if (this.companyId == null)
            this.company = null;

        this.changeDate = CommonUtils.getUnixTimestamp();
        this.stageCode = this.stageCode == null ? "raw" : this.stageCode;
        this.newBuilding = this.newBuilding == null ? false : this.newBuilding;
        this.encumbrance = this.encumbrance == null ? false : this.encumbrance;
        this.costInfo = this.costInfo == null ? "" : this.costInfo;
        this.description = this.description == null ? "" : this.description;

        if (this.typeCode.equals("rent")) {
            this.deposit = this.deposit == null ? false : this.deposit;
            this.utilityBills = this.utilityBills == null ? false : this.utilityBills;
            // Обнуление полей для "не аренды"
            this.cash = this.mortgage = this.certificate = this.maternityCapital = null;
        } else {
            this.cash = this.cash == null ? false : this.cash;
            this.mortgage = this.mortgage == null ? false : this.mortgage;
            this.certificate = this.certificate == null ? false : this.certificate;
            this.maternityCapital = this.maternityCapital == null ? false : this.maternityCapital;
            // Обнуление полей аренды
            this.deposit = this.utilityBills = null;
        }
        if (this.documents == null)
            this.documents = new ArrayList<>();
        //this.documents = UploadFile.moveFromTemp(this.documents, "docs/requests/" + getAccountId() + "/" + getId());
    }*/

    public static String needAdd(String val) {
        switch (val) {
            case "typeCode":
                return "offerTypeCode";
            case "rentType":
                return "rentType";
            case "offerTypeCode":
                return "typeCode";
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
            case "ownerPrice":
                return "ownerPrice";
            case "loggia":
                return "loggia";
            case "balcony":
                return "balcony";
            case "terrace":
                return "terrace";
            case "floor":
                return "floor";
            case "conditions":
                return "conditions";
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

            case "squareTotalMin":
                return "squareTotal";
            case "squareTotalMax":
                return "squareTotal";
            case "squareLivingMin":
                return "squareLiving";
            case "squareLivingMax":
                return "squareLiving";
            case "squareKitchenMin":
                return "squareKitchen";
            case "squareKitchenMax":
                return "squareKitchen";
            case "squareLandMin":
                return "squareLand";
            case "squareLandMax":
                return "squareLand";
            case "searchArea":
                return "location";
            default:
                return null;
        }

    }
}
