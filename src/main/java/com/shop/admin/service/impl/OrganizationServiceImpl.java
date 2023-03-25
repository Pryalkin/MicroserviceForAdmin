package com.shop.admin.service.impl;

import com.shop.admin.enumeration.Activity;
import com.shop.admin.enumeration.Role;
import com.shop.admin.exception.model.NotFoundOrganizationException;
import com.shop.admin.model.user.Organization;
import com.shop.admin.model.user.User;
import com.shop.admin.repository.OrganizationRepo;
import com.shop.admin.repository.UserRepo;
import com.shop.admin.service.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.shop.admin.constant.ExceptionConstant.NO_SUCH_ORGANIZATION_EXISTS;
import static java.lang.Boolean.*;

@Service
@AllArgsConstructor
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    private final UserRepo userRepo;
    private final OrganizationRepo organizationRepo;

    @Override
    public List<Organization> findUnregisteredOrganizations() {
        return organizationRepo.findByActivity(Activity.UNREGISTERED.name()).orElseGet(ArrayList::new);
    }

    @Transactional
    @Override
    public void registration(Map<Boolean, List<String>> organizations){
        organizations.keySet().forEach(key -> {
            if (key){
                organizations.get(TRUE).forEach(name -> {
                    Organization org = organizationRepo.findByName(name).get();
                    org.setActivity(Activity.ACTIVE.name());
                    org = organizationRepo.save(org);
                    if(org.getUser().getRole().equals(Role.ROLE_USER.name())){
                        User user = org.getUser();
                        user.setRole(Role.ROLE_EMPLOYEE.name());
                        user.setAuthorities(Role.ROLE_EMPLOYEE.getAuthorities());
                    }
                });
            } else {
                organizations.get(FALSE).forEach(name -> {
                    Organization org = organizationRepo.findByName(name).get();
                    org.setActivity(Activity.DELETED.name());
                    organizationRepo.save(org);
                });
            }
        });
    }

    @Override
    public String activation(String organization, String activation) throws NotFoundOrganizationException {
        Organization org = organizationRepo.findByName(organization).orElseThrow(() -> new NotFoundOrganizationException(NO_SUCH_ORGANIZATION_EXISTS));
        if (activation.toUpperCase().equals(Activity.ACTIVE.name())) {
            org.setActivity(Activity.ACTIVE.name());
            organizationRepo.save(org);
            return Activity.ACTIVE.name();
        }
        else if (activation.toUpperCase().equals(Activity.FROZEN.name())) {
            org.setActivity(Activity.FROZEN.name());
            organizationRepo.save(org);
            return Activity.FROZEN.name();
        }
        else if (activation.toUpperCase().equals(Activity.DELETED.name())) {
            org.setActivity(Activity.DELETED.name());
            organizationRepo.save(org);
            return Activity.DELETED.name();
        }
        return "NOT USED";
    }

    @Override
    public List<Organization> getAll() {
        return organizationRepo.findAll();
    }


}
