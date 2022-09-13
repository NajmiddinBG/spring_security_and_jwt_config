package uz.security.security_with_jwt.service.base;


import lombok.RequiredArgsConstructor;
import uz.security.security_with_jwt.mapper.base.BaseMapper;
import uz.security.security_with_jwt.repository.base.BaseRepository;

@RequiredArgsConstructor
public abstract class AbstractService<M extends BaseMapper, R extends BaseRepository> implements BaseService {

    protected final M mapper;
    protected final R repository;

}
