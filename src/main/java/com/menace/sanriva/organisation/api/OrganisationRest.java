package com.menace.sanriva.organisation.api;

import com.menace.sanriva.organisation.Organisation;
import com.menace.sanriva.organisation.dao.OrganisationDao;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganisationRest {

    private final OrganisationDao organisationDao;

    public OrganisationRest(OrganisationDao organisationDao) {
        this.organisationDao = organisationDao;
    }

    @GetMapping("/organisation/{sorCode}" )
    public HttpEntity<Object> getOrganisation(
        @PathVariable("sorCode") long sorCode
    ) {
        Organisation org = organisationDao.findBySorCode(sorCode);
        if (org == null) {
            return ResponseEntity.status(404).build();
        }

        OrganisationResponse res = OrganisationResponse.toResponse(org);

        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("/organisation/root/{sorCode}" )
    public HttpEntity<Object> getRootOrganisation(
            @PathVariable("sorCode") long sorCode
    ) {
        Organisation root = organisationDao.findRootBySorCode(sorCode);

        if (root == null) {
            return ResponseEntity.status(404).build();
        }

        OrganisationResponse res = OrganisationResponse.toResponse(root);

        return ResponseEntity.status(200).body(res);
    }

    public record OrganisationResponse(
        String name,
        String type,
        String region,
        String specialization,
        Long cvr,
        Long sorCode
    ) {

        private static OrganisationResponse toResponse(Organisation organisation) {
            return new OrganisationResponse(
                organisation.getName(),
                organisation.getType(),
                organisation.getRegion(),
                organisation.getSpecialization(),
                organisation.getCvr(),
                organisation.getSorCode()
            );
        }
    }

}
