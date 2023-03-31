package com.shop.admin.controller;

import com.shop.admin.constant.HttpAnswer;
import com.shop.admin.dto.OrganizationDTO;
import com.shop.admin.exception.ExceptionHandling;
import com.shop.admin.exception.model.NotFoundOrganizationException;
import com.shop.admin.model.HttpResponse;
import com.shop.admin.service.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.shop.admin.constant.HttpAnswer.ORGANIZATION;
import static com.shop.admin.constant.HttpAnswer.REGISTRATION_COMPLETED_SUCCESSFULLY;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/admin/organization")
@AllArgsConstructor
@Slf4j
public class OrganizationController extends ExceptionHandling {

    private final OrganizationService organizationService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('user:getAllOrganization')")
    public ResponseEntity<Set<OrganizationDTO>> getAll(){
        return new ResponseEntity<>(organizationService.getAll(), OK);
    }

    @GetMapping("/unregistered")
    @PreAuthorize("hasAnyAuthority('user:update_organization')")
    public ResponseEntity<Set<OrganizationDTO>> unregistered(){
        return new ResponseEntity<>(organizationService.findUnregisteredOrganizations(), OK);
    }

    @PostMapping("/registration")
    @PreAuthorize("hasAnyAuthority('user:update_organization')")
    public ResponseEntity<HttpResponse> registration(@RequestBody Map<Boolean, List<String>> organizations){
        organizationService.registration(organizations);
        return HttpAnswer.response(OK, REGISTRATION_COMPLETED_SUCCESSFULLY);
    }

    @PostMapping("/activation/{organization}/{activation}")
    @PreAuthorize("hasAnyAuthority('user:update_organization')")
    public ResponseEntity<HttpResponse> activation(@PathVariable String organization,
                                                   @PathVariable String activation) throws NotFoundOrganizationException {
        String message = organizationService.activation(organization, activation);
        return HttpAnswer.response(OK, ORGANIZATION + message);
    }


}
