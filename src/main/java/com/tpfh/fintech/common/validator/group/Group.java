/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.validation.GroupSequence
 */
package com.tpfh.fintech.common.validator.group;

import com.tpfh.fintech.common.validator.group.AddGroup;
import com.tpfh.fintech.common.validator.group.UpdateGroup;
import javax.validation.GroupSequence;

@GroupSequence(value={AddGroup.class, UpdateGroup.class})
public interface Group {
}

