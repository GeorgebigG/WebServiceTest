package com.rustaronline.mobile.webservicetest;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by gio on 6/17/16.
 */
public class CallSoap {
    public String getFarenheit(String celsius) {
        String SOAP_ACTION = "http://www.w3schools.com/xml/CelsiusToFahrenheit";
        String OPERATION_NAME = "CelsiusToFahrenheit";
        String WSDL_TARGET_NAMESPACE = "http://www.w3schools.com/xml/";
        String XML_VERSION_TAG = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";

        String SOAP_ADRESS = "http://www.w3schools.com/xml/tempconvert.asmx";

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName("Celsius");
        propertyInfo.setValue(celsius);
        propertyInfo.setType(String.class);
        request.addProperty(propertyInfo);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        String response = null;

        try {
            HttpTransportSE transportSE = new HttpTransportSE(SOAP_ADRESS);
            transportSE.setXmlVersionTag(XML_VERSION_TAG);
            transportSE.debug = true;
            transportSE.call(SOAP_ACTION, envelope);

            return  transportSE.responseDump;
        } catch (Exception ex) {
            return "Error...";
        }
    }
}
