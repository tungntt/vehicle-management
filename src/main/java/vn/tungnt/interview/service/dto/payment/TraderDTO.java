package vn.tungnt.interview.service.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vn.tungnt.interview.service.dto.driver.DriverDTO;

@JsonIgnoreProperties(value = {"vehicles", "createdBy", "createdDate"})
public class TraderDTO extends DriverDTO {
    private static final long serialVersionUID = -9190853810450566123L;
}
