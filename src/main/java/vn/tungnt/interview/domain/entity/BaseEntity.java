package vn.tungnt.interview.domain.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8119839851631798799L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

//    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_date", nullable = false)
//    protected Date createdDate;
//
//    @CreatedBy
//    @Column(name = "created_by", nullable = false)
//    protected String createdBy;
//
//    @LastModifiedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated_date", nullable = false)
//    protected Date updatedDate;
//
//    @LastModifiedBy
//    @Column(name = "updated_by", nullable = false)
//    protected String updateBy;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
