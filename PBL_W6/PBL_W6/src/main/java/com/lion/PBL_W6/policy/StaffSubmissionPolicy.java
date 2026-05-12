package com.lion.PBL_W6.policy;

public class StaffSubmissionPolicy implements SubmissionPolicy {

    @Override
    public boolean canSubmit() {
        return false;
    }
}