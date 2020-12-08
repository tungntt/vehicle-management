package vn.tungnt.interview.service.dto;

import java.io.Serializable;
import java.util.Date;

public class BaseDTO implements Serializable {

    private static final String DEFAULT_CREATED_BY_VALUE = "admin";
    private static final long serialVersionUID = 5801978509689591237L;

    protected Long id;

    protected String createdBy = DEFAULT_CREATED_BY_VALUE;

    protected Date createdDate = new Date();

    protected long credentialId;

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

    public long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(final long credentialId) {
        this.credentialId = credentialId;
    }
}
