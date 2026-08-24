package com.tpfh.fintech.modules.pmjk.releaseinfo.vo;

import com.tpfh.fintech.modules.pmjk.releaseinfo.entity.ReleaseinfoEntity;
import java.util.List;

public class ReleaseinfoVo extends ReleaseinfoEntity {
    private List<Long> contactIds;

    public List<Long> getContactIds() {
        return this.contactIds;
    }

    public void setContactIds(List<Long> contactIds) {
        this.contactIds = contactIds;
    }
}
