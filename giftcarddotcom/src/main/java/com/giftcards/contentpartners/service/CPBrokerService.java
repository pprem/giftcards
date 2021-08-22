package com.giftcards.contentpartners.service;

import com.giftcards.contentpartners.model.ContentPartner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CPBrokerService {

    Map<String, ContentPartner> vendorMap = new HashMap<>();
    public ContentPartner registerVendor(ContentPartner contentPartner) {
        vendorMap.put(contentPartner.getId(), contentPartner);
        return contentPartner;
    }

    public ContentPartner getVendor(String vendorid) {
        return vendorMap.get(vendorid);
    }
}
