package uz.security.security_with_jwt.service.base;

import uz.security.security_with_jwt.dto.base.GenericDto;

import java.io.Serializable;
import java.util.List;

public interface GenericService<
        D extends GenericDto,
        K extends Serializable> {

    D get(K id);

    List<D> getAll();


}
