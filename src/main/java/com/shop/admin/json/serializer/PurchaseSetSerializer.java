package com.shop.admin.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.shop.admin.model.Purchase;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PurchaseSetSerializer extends StdSerializer<Set<Purchase>> {

    public PurchaseSetSerializer() {
        this(null);
    }

    public PurchaseSetSerializer(Class<Set<Purchase>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<Purchase> purchases, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Set<Purchase> newPurs = new HashSet<>();
        for (Purchase pur : purchases) {
            Purchase purchase = new Purchase();
            purchase.getSerials().addAll(pur.getSerials());
            purchase.setDateOfPurchase(pur.getDateOfPurchase());
            newPurs.add(purchase);
        }
        gen.writeObject(newPurs);
    }
}