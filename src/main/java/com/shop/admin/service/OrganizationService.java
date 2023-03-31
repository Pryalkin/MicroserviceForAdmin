package com.shop.admin.service;

import com.shop.admin.dto.OrganizationDTO;
import com.shop.admin.exception.model.NotFoundOrganizationException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrganizationService {

    Set<OrganizationDTO> findUnregisteredOrganizations();

    void registration(Map<Boolean, List<String>> organizations);

    String activation(String organization, String activation) throws NotFoundOrganizationException;

    Set<OrganizationDTO> getAll();
}
