package vn.tungnt.interview.service.dto.driver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vn.tungnt.interview.service.dto.driver.DriverDTO;

@JsonIgnoreProperties(value = {"vehicles", "createdBy", "createdDate", "credentialId"})
public class UpdatedDriverRequestDTO extends DriverDTO {
    private static final long serialVersionUID = -8478871298453900088L;
}
