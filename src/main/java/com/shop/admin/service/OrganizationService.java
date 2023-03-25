package com.shop.admin.service;

import com.shop.admin.exception.model.NotFoundOrganizationException;
import com.shop.admin.model.user.Organization;

import java.util.List;
import java.util.Map;

public interface OrganizationService {

    List<Organization> findUnregisteredOrganizations();

    void registration(Map<Boolean, List<String>> organizations);

    String activation(String organization, String activation) throws NotFoundOrganizationException;

    List<Organization> getAll();
}
