package vn.tungnt.interview.service.dto;

import java.util.Date;

public class BaseDTO {

    private static final String DEFAULT_CREATED_BY_VALUE = "admin";

    protected Long id;

    protected String createdBy = DEFAULT_CREATED_BY_VALUE;

    protected Date createdDate = new Date();

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }
}
