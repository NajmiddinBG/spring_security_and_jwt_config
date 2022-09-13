package uz.security.security_with_jwt.service.base;

import uz.security.security_with_jwt.dto.base.BaseDto;
import uz.security.security_with_jwt.dto.base.GenericDto;

public interface GenericCrudService<D extends GenericDto, CD extends BaseDto, UD extends GenericDto> {


    Long create(CD cd);

    Long update(UD ud);

    Boolean delete(Long id);


}
