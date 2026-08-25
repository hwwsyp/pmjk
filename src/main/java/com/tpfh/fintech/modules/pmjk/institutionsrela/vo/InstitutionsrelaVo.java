package com.tpfh.fintech.modules.pmjk.institutionsrela.vo;

import com.tpfh.fintech.modules.pmjk.institutionsrela.entity.InstitutionsrelaEntity;
import java.util.List;

public class InstitutionsrelaVo extends InstitutionsrelaEntity {
    private List<Long> contactIds;

    public List<Long> getContactIds() {
        return this.contactIds;
    }

    public void setContactIds(List<Long> contactIds) {
        this.contactIds = contactIds;
    }
}
